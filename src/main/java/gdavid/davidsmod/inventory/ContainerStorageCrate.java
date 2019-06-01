package gdavid.davidsmod.inventory;

import gdavid.davidsmod.tile.TileStorageCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerStorageCrate extends Container {
	
	public TileStorageCrate te;
	public IInventory p;
	
	public ContainerStorageCrate(IInventory playerInventory, TileStorageCrate te) {
		this.te = te;
		this.p = playerInventory;
		addOwnSlots();
        addPlayerSlots(playerInventory);
	}
	
	void addPlayerSlots(IInventory playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = 8 + col * 18;
                int y = 102 + row * 18;
                this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 10, x, y));
            }
        }
        for (int row = 0; row < 9; ++row) {
            int x = 8 + row * 18;
            int y = 160;
            this.addSlotToContainer(new Slot(playerInventory, row, x, y));
        }
    }
	
	void addOwnSlots() {
        IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        int x = 8, y = 18;
        int slotIndex = 0;
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            addSlotToContainer(new SlotItemHandler(itemHandler, slotIndex, x, y));
            slotIndex++;
            x += 18;
            if (i % 9 == 8) {
            	x = 8;
            	y += 18;
            }
        }
    }
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < TileStorageCrate.size) {
                if (!this.mergeItemStack(itemstack1, TileStorageCrate.size, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, TileStorageCrate.size, false)) {
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
