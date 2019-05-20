package gdavid.davidsmod.tab;

import gdavid.davidsmod.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabDavidsmod extends CreativeTabs {
	
	private static TabDavidsmod tab;
	
	public static TabDavidsmod get() {
		if (tab == null) {
			tab = new TabDavidsmod();
		}
		return tab;
	}
	
	private TabDavidsmod() {
		super("davidsmod");
		setBackgroundImageName("item_search.png");
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.redstoneMeter);
	}
	
	@Override
	public boolean hasSearchBar() {
		return true;
	}
	
}
