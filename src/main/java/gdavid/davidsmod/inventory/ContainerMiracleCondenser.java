package gdavid.davidsmod.inventory;

import gdavid.davidsmod.tile.TileMiracleCondenser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerMiracleCondenser extends Container {
	
	public TileMiracleCondenser te;
	public IInventory p;
	
	public ContainerMiracleCondenser(IInventory playerInventory, TileMiracleCondenser te) {
		this.te = te;
		this.p = playerInventory;
		addOwnSlots();
		addPlayerSlots(playerInventory);
	}
	
	void addPlayerSlots(IInventory playerInventory) {
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 9; ++col) {
				int x = 8 + col * 18;
				int y = 84 + row * 18;
				this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 10, x, y));
			}
		}
		for (int row = 0; row < 9; ++row) {
			int x = 8 + row * 18;
			int y = 142;
			this.addSlotToContainer(new Slot(playerInventory, row, x, y));
		}
	}
	
	void addOwnSlots() {
		IItemHandler itemHandler = this.te.getInv();
		addSlotToContainer(new SlotItemHandler(itemHandler, 0, 53, 35));
		addSlotToContainer(new OutputSlot(itemHandler, 1, 116, 35));
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < TileMiracleCondenser.size) {
				if (!this.mergeItemStack(itemstack1, TileMiracleCondenser.size, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, TileMiracleCondenser.size, false)) {
				return ItemStack.EMPTY;
			}
			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		return itemstack;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.canInteractWith(playerIn);
	}
	
}
