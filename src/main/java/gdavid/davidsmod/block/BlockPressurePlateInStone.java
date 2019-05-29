package gdavid.davidsmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPressurePlateInStone extends Block {
	
	public static final PropertyBool ACTIVATED = PropertyBool.create("activated");
	
	static final AxisAlignedBB CHK_AABB = new AxisAlignedBB(0, 1, 0, 1, 1.1, 1);
	
	public BlockPressurePlateInStone() {
		super(Material.ROCK, MapColor.STONE);
		setHardness(3.5F);
		setDefaultState(blockState.getBaseState().withProperty(ACTIVATED, false));
	}
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(ACTIVATED, true), 3);
		worldIn.scheduleBlockUpdate(pos, this, 5, 0);
		super.onEntityWalk(worldIn, pos, entityIn);
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			recalculate(worldIn, pos, state);
		}
	}
	
	public void recalculate(World world, BlockPos pos, IBlockState state) {
		AxisAlignedBB aabb = CHK_AABB.offset(pos);
		boolean active = !world.getEntitiesWithinAABBExcludingEntity(null, aabb).isEmpty();
		world.setBlockState(pos, state.withProperty(ACTIVATED, active), 3);
		if (active) {
			world.scheduleBlockUpdate(pos, this, 5, 0);
		}
	}
	
	@Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return blockState.getValue(ACTIVATED) ? 15 : 0;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(ACTIVATED, meta == 1);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(ACTIVATED) ? 1 : 0;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {ACTIVATED});
	}
	
	@Override
	public boolean canProvidePower(IBlockState state) {
		return true;
	}
	
}
