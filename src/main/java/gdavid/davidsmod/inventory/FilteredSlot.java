package gdavid.davidsmod.inventory;

import java.util.function.Predicate;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class FilteredSlot extends SlotItemHandler {
	
	Predicate<ItemStack> predicate;
	
	public FilteredSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, Predicate<ItemStack> predicate) {
		super(itemHandler, index, xPosition, yPosition);
		this.predicate = predicate;
	}
	
	@Override
	public boolean isItemValid(@Nonnull ItemStack stack) {
		return predicate.test(stack) && super.isItemValid(stack);
	}
	
}
