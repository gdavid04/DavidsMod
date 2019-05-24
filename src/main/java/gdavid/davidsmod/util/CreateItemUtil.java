package gdavid.davidsmod.util;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.tab.TabDavidsmod;
import net.minecraft.item.Item;

public class CreateItemUtil {
	
	public static Item create(
		String name
	) {
		return adapt(name, new Item());
	}
	
	public static Item create(
		String name,
		int stackSize
	) {
		return adapt(name, new Item())
			.setMaxStackSize(stackSize);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Item> T adapt(String name, T item) {
		return (T) item
			.setRegistryName(name)
			.setUnlocalizedName(DavidsMod.modID + ":" + name)
			.setCreativeTab(TabDavidsmod.get());
	}
	
}
