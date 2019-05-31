package gdavid.davidsmod.entity;

import gdavid.davidsmod.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ModEntityBehaviors {
	
	@SubscribeEvent
	public static void drop(LivingDropsEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		World world = entity.getEntityWorld();
		if (!world.isRemote) {
			Entity source = event.getSource().getImmediateSource();
			if (source instanceof EntityLivingBase) {
				EntityLivingBase livingSource = (EntityLivingBase) source;
				for (EnumHand hand : EnumHand.values()) {
					ItemStack stack = livingSource.getHeldItem(hand);
					if (stack.getItem() == Items.GLASS_BOTTLE && !(entity instanceof EntitySkeleton)) {
						stack.shrink(1);
						if (stack.getCount() == 0) {
							livingSource.setHeldItem(hand, new ItemStack(ModItems.bloodBottle));
							break;
						} else if (livingSource instanceof EntityPlayer) {
							((EntityPlayer) livingSource).addItemStackToInventory(new ItemStack(ModItems.bloodBottle));
						} else {
							world.spawnEntity(new EntityItem(world, source.posX, source.posY, source.posZ, new ItemStack(ModItems.bloodBottle)));
						}
					}
				}
			}
		}
	}
	
}
