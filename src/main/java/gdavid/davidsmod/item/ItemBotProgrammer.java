package gdavid.davidsmod.item;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.gui.GuiHandler;
import gdavid.davidsmod.tab.TabDavidsmod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBotProgrammer extends Item {

	public ItemBotProgrammer() {
		setUnlocalizedName(DavidsMod.modID + ":" + "bot_programmer");
		setRegistryName("bot_programmer");
		setCreativeTab(TabDavidsmod.get());
		setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		playerIn.openGui(DavidsMod.instance, GuiHandler.BOT_PROGRAMMER, worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		return false; // TODO upload to bot, cancel
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return !ItemStack.areItemsEqual(oldStack, newStack);
	}
	
}
