package gdavid.davidsmod.gui;

import gdavid.davidsmod.gui.inventory.GuiStorageCrate;
import gdavid.davidsmod.inventory.ContainerStorageCrate;
import gdavid.davidsmod.item.ModItems;
import gdavid.davidsmod.tile.TileStorageCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	public static final int BOT_PROGRAMMER = 1;
	public static final int STORAGE_CRATE = 2;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case STORAGE_CRATE:
			BlockPos pos = new BlockPos(x, y, z);
	        TileEntity te = world.getTileEntity(pos);
	        if (te instanceof TileStorageCrate) {
	            return new ContainerStorageCrate(player.inventory, (TileStorageCrate) te);
	        }
		}
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
		case STORAGE_CRATE:
			BlockPos pos = new BlockPos(x, y, z);
	        TileEntity te = world.getTileEntity(pos);
	        if (te instanceof TileStorageCrate) {
	        	TileStorageCrate containerTileEntity = (TileStorageCrate) te;
	            return new GuiStorageCrate(containerTileEntity, new ContainerStorageCrate(player.inventory, containerTileEntity));
	        }
		}
		return null;
	}
	
}
