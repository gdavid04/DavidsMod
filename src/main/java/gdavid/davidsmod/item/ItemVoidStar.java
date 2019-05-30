package gdavid.davidsmod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ItemVoidStar {
	
	@SubscribeEvent
	public static void netherStarThrownInVoid(ItemTossEvent event) {
		EntityPlayer player = event.getPlayer();
		if (!player.world.isRemote &&
			player.posY < 0 &&
			player.dimension == 0
		) {
			ItemStack stack = event.getEntityItem().getItem();
			if (stack.getItem() == Items.NETHER_STAR) {
				player.addItemStackToInventory(new ItemStack(ModItems.voidStar, stack.getCount()));
				player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("levitation"), 40 * stack.getCount(), 9, false, false));
				player.velocityChanged = true;
				event.setCanceled(true);
			}
		}
	}
	
}
