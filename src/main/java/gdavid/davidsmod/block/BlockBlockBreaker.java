package gdavid.davidsmod.block;

import gdavid.davidsmod.util.RsUtil;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBlockBreaker extends Block {

	public static final PropertyDirection FACING = BlockDirectional.FACING;
	public static final PropertyBool TRIGGERED = PropertyBool.create("triggered");
	
	public int level;
	
	public BlockBlockBreaker(int level) {
		super(Material.ROCK, MapColor.STONE);
		setHardness(3.5F);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TRIGGERED, false));
		this.level = level;
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!worldIn.isRemote) {
			boolean powered = RsUtil.isPowered(worldIn, pos);
			if (!state.getValue(TRIGGERED) && powered) {
				breakBlock(worldIn, pos.offset(state.getValue(FACING)), level);
			}
			worldIn.setBlockState(pos, state.withProperty(TRIGGERED, powered), 4);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void breakBlock(World world, BlockPos at, int strength) {
		IBlockState state = world.getBlockState(at);
		Block block = state.getBlock();
		if (block.canCollideCheck(state, false) && block.getHarvestLevel(state) <= strength && block.getBlockHardness(state, world, at) != -1) {
			world.destroyBlock(at, true);
		}
	}
	
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)).withProperty(TRIGGERED, false);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)).withProperty(TRIGGERED, false), 2);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7)).withProperty(TRIGGERED, (meta & 8) != 0);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing)state.getValue(FACING)).getIndex() | (state.getValue(TRIGGERED) ? 8 : 0);
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING, TRIGGERED});
	}
	
	@Override
	public boolean canProvidePower(IBlockState state) {
		return true;
	}
	
}
