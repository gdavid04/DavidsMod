package gdavid.davidsmod.block;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.item.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockEnderCrop extends BlockCrops {
	
	public BlockEnderCrop() {
		setUnlocalizedName(DavidsMod.modID + ":" + "ender_crop");
		setRegistryName("ender_crop");
	}
	
	@Override
	protected Item getSeed() {
		return ModItems.enderSeed;
	}
	
	@Override
	protected Item getCrop() {
		return Items.ENDER_PEARL;
	}
	
	@Override
	protected boolean canSustainBush(IBlockState state) {
		return state.getBlock() == Blocks.END_STONE;
	}
	
	@Override
	protected int getBonemealAgeIncrease(World worldIn) {
		return MathHelper.getInt(worldIn.rand, 0, 100) < 5 ? 1 : 0;
	}
	
	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		IBlockState soil = worldIn.getBlockState(pos.down());
		return (worldIn.getLight(pos) >= 8 || worldIn.canSeeSky(pos)) && soil.getBlock() == Blocks.END_STONE;
	}
	
}
