package gdavid.davidsmod;

import gdavid.davidsmod.api.DavidsModCrafting;
import gdavid.davidsmod.item.ModItems;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockWall;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModCrafting {
	
	public static void reg() {
		DavidsModCrafting.addBlockConvertion(DavidsModCrafting.mossBall,
			Blocks.COBBLESTONE.getDefaultState(),
			Blocks.MOSSY_COBBLESTONE.getDefaultState());
		DavidsModCrafting.addBlockConvertion(DavidsModCrafting.mossBall,
			Blocks.STONEBRICK.getDefaultState(),
			Blocks.STONEBRICK.getDefaultState()
			.withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY));
		DavidsModCrafting.addBlockConvertion(DavidsModCrafting.mossBall,
			Blocks.COBBLESTONE_WALL.getDefaultState(),
			Blocks.COBBLESTONE_WALL.getDefaultState()
			.withProperty(BlockWall.VARIANT, BlockWall.EnumType.MOSSY));
		// TODO infested stone spawns silverfish
		GameRegistry.addSmelting(ModItems.sand, new ItemStack(Blocks.GLASS_PANE), 0.2f);
	}
	
}
