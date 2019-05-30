package gdavid.davidsmod.block;

import java.util.List;

import gdavid.davidsmod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ModBlockBehaviors {
	
	@SubscribeEvent
	public static void broken(BlockEvent.HarvestDropsEvent event) {
		World world = event.getWorld();
		if (!world.isRemote) {
			IBlockState state = event.getState();
			Block block = state.getBlock();
			List<ItemStack> drops = event.getDrops();
			if (block == Blocks.SAND) {
				drops.clear();
				drops.add(new ItemStack(ModItems.sand, 4));
			}
		}
	}
	
}
