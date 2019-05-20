package gdavid.davidsmod.util;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.tab.TabDavidsmod;
import net.minecraft.item.Item;

public class CreateItemUtil {
	
	public static Item create(
		String name
	) {
		return new Item()
			.setRegistryName(name)
			.setUnlocalizedName(DavidsMod.modID + ":" + name)
			.setCreativeTab(TabDavidsmod.get());
	}
	
	public static Item create(
		String name,
		int stackSize
	) {
		return new Item()
			.setRegistryName(name)
			.setUnlocalizedName(DavidsMod.modID + ":" + name)
			.setCreativeTab(TabDavidsmod.get())
			.setMaxStackSize(stackSize);
	}
	
}
