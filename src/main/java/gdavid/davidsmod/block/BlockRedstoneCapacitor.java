package gdavid.davidsmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedstoneCapacitor extends Block {
	
	public static final PropertyInteger POWER = PropertyInteger.create("power", 0, 15);
	
	public BlockRedstoneCapacitor() {
		super(Material.ROCK, MapColor.STONE);
		setHardness(3.5F);
		setDefaultState(blockState.getBaseState().withProperty(POWER, 0));
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!worldIn.isRemote) {
			worldIn.setBlockState(pos, state.withProperty(POWER, Math.max(0, Math.max(worldIn.isBlockIndirectlyGettingPowered(pos) - 1, state.getValue(POWER)))), 3);
			worldIn.scheduleBlockUpdate(pos, this, 6, 0);
		}
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			worldIn.setBlockState(pos, state.withProperty(POWER, Math.max(0, Math.max(worldIn.isBlockIndirectlyGettingPowered(pos), state.getValue(POWER)) - 1)), 3);
			if (state.getValue(POWER) > 0) {
				worldIn.scheduleBlockUpdate(pos, this, 6, 0);
			}
		}
	}
	
	@Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return blockState.getValue(POWER);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(POWER, meta);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(POWER);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {POWER});
	}
	
	@Override
	public boolean canProvidePower(IBlockState state) {
		return true;
	}
	
}
