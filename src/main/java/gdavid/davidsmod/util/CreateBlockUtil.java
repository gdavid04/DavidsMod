package gdavid.davidsmod.util;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.tab.TabDavidsmod;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class CreateBlockUtil {
	
	public static Block create(
		String name,
		Material material,
		float hardness
	) {
		return new Block(material)
			.setRegistryName(DavidsMod.modID, name)
			.setUnlocalizedName(DavidsMod.modID + ":" + name)
			.setCreativeTab(TabDavidsmod.get())
			.setHardness(hardness);
	}
	
	public static Block create(
		String name,
		Material material,
		float hardness,
		float resistance
	) {
		return new Block(material)
			.setRegistryName(DavidsMod.modID, name)
			.setUnlocalizedName(DavidsMod.modID + ":" + name)
			.setCreativeTab(TabDavidsmod.get())
			.setHardness(hardness)
			.setResistance(resistance);
	}
	
	public static Block create(
		String name,
		Material material,
		MapColor color,
		float hardness
	) {
		return new Block(material, color)
			.setRegistryName(DavidsMod.modID, name)
			.setUnlocalizedName(DavidsMod.modID + ":" + name)
			.setCreativeTab(TabDavidsmod.get())
			.setHardness(hardness);
	}
	
	public static Block create(
		String name,
		Material material,
		MapColor color,
		float hardness,
		float resistance
	) {
		return new Block(material, color)
			.setRegistryName(DavidsMod.modID, name)
			.setUnlocalizedName(DavidsMod.modID + ":" + name)
			.setCreativeTab(TabDavidsmod.get())
			.setHardness(hardness)
			.setResistance(resistance);
	}
	
}
