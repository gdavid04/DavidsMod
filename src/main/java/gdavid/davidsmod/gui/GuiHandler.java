package gdavid.davidsmod.gui;

import gdavid.davidsmod.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	public static final int BOT_PROGRAMMER = 1;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case BOT_PROGRAMMER:
			EnumHand hand = EnumHand.MAIN_HAND;
			if (player.getHeldItemMainhand().getItem() != ModItems.botProgrammer) {
				hand = EnumHand.OFF_HAND;
			}
			if (player.getHeldItem(hand).getItem() != ModItems.botProgrammer) {
				return null;
			}
			return new GuiBotProgrammer(player, hand);
		}
		return null;
	}
	
}
