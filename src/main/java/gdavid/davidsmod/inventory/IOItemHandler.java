package gdavid.davidsmod.inventory;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class IOItemHandler implements IItemHandler {
	
	IItemHandler handler;
	int in;
	
	public IOItemHandler(IItemHandler handler, int in) {
		this.handler = handler;
		this.in = in;
	}
	
	@Override
	public int getSlots() {
		return handler.getSlots();
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		return handler.getStackInSlot(slot);
	}
	
	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		if (slot < in) {
			return handler.insertItem(slot, stack, simulate);
		}
		return stack;
	}
	
	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (slot >= in) {
			return handler.extractItem(slot, amount, simulate);
		}
		return ItemStack.EMPTY;
	}
	
	@Override
	public int getSlotLimit(int slot) {
		return handler.getSlotLimit(slot);
	}
	
}
