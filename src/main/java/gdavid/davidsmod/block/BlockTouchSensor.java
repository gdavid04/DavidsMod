package gdavid.davidsmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTouchSensor extends Block {
	
	public static final PropertyDirection FACING = BlockDirectional.FACING;
	public static final PropertyBool ACTIVATED = PropertyBool.create("activated");
	
	static final AxisAlignedBB CHK_AABB = new AxisAlignedBB(0, 0, 0, 1, 1, 1);
	
	public BlockTouchSensor() {
		super(Material.ROCK, MapColor.STONE);
		setHardness(3.5F);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVATED, false));
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!worldIn.isRemote) {
			if (!worldIn.isUpdateScheduled(pos, this)) {
				worldIn.scheduleBlockUpdate(pos, this, 5, 0);
			}
		}
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			recalculate(worldIn, pos, state);
		}
	}
	
	public void recalculate(World world, BlockPos pos, IBlockState state) {
		Vec3i dv = state.getValue(FACING).getDirectionVec();
		AxisAlignedBB aabb = CHK_AABB.offset(dv.getX(), dv.getY(), dv.getZ()).intersect(CHK_AABB.grow(0.1F)).offset(pos);
		boolean active = !world.getEntitiesWithinAABBExcludingEntity(null, aabb).isEmpty();
		world.setBlockState(pos, state.withProperty(ACTIVATED, active), 3);
		world.scheduleBlockUpdate(pos, this, 5, 0);
	}
	
	@Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return blockState.getValue(ACTIVATED) ? 15 : 0;
	}
	
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)).withProperty(ACTIVATED, false);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)).withProperty(ACTIVATED, false), 2);
		worldIn.scheduleBlockUpdate(pos, this, 5, 0);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7)).withProperty(ACTIVATED, (meta & 8) != 0);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing)state.getValue(FACING)).getIndex() | (state.getValue(ACTIVATED) ? 8 : 0);
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING, ACTIVATED});
	}
	
	@Override
	public boolean canProvidePower(IBlockState state) {
		return true;
	}
	
}
