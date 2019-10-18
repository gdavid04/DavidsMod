package gdavid.davidsmod;

import gdavid.davidsmod.api.BlockTransformation;
import gdavid.davidsmod.api.DavidsModCrafting;
import gdavid.davidsmod.item.ModItems;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModCrafting {
	
	public static void reg() {
		DavidsModCrafting.addBlockConversion(DavidsModCrafting.mossBall,
			Blocks.COBBLESTONE.getDefaultState(),
			Blocks.MOSSY_COBBLESTONE.getDefaultState());
		DavidsModCrafting.addBlockConversion(DavidsModCrafting.mossBall,
			Blocks.STONEBRICK.getDefaultState(),
			Blocks.STONEBRICK.getDefaultState()
			.withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY));
		DavidsModCrafting.addBlockConversion(DavidsModCrafting.mossBall,
			Blocks.COBBLESTONE_WALL.getDefaultState(),
			Blocks.COBBLESTONE_WALL.getDefaultState()
			.withProperty(BlockWall.VARIANT, BlockWall.EnumType.MOSSY));
		DavidsModCrafting.mossBall.add(new BlockTransformation() {
			
			@Override
			public boolean Match(World world, BlockPos pos) {
				return world.getBlockState(pos) == Blocks.MONSTER_EGG.getDefaultState()
				.withProperty(BlockSilverfish.VARIANT, BlockSilverfish.EnumType.STONEBRICK);
			}
			
			@Override
			public boolean Apply(World world, BlockPos pos) {
				world.destroyBlock(pos, true);
				return false;
			}
			
			@Override
			public IBlockState GetPreview(World world, BlockPos pos) {
				return Blocks.STONEBRICK.getDefaultState()
				.withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY);
			}
			
		});
		DavidsModCrafting.mossBall.add(new BlockTransformation() {
			
			@Override
			public boolean Match(World world, BlockPos pos) {
				return world.getBlockState(pos) == Blocks.MONSTER_EGG.getDefaultState()
				.withProperty(BlockSilverfish.VARIANT, BlockSilverfish.EnumType.COBBLESTONE);
			}
			
			@Override
			public boolean Apply(World world, BlockPos pos) {
				world.destroyBlock(pos, true);
				return false;
			}
			
			@Override
			public IBlockState GetPreview(World world, BlockPos pos) {
				return Blocks.MOSSY_COBBLESTONE.getDefaultState();
			}
			
		});
		GameRegistry.addSmelting(ModItems.sand, new ItemStack(Blocks.GLASS_PANE), 0.2f);
	}
	
}
