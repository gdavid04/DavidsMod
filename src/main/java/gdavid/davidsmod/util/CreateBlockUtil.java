package gdavid.davidsmod.util;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.block.BlockWithSound;
import gdavid.davidsmod.tab.TabDavidsmod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
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
		SoundType sound,
		float hardness
	) {
		return new BlockWithSound(material, sound)
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
		return create(name, material, hardness)
			.setResistance(resistance);
	}
	
	public static Block create(
		String name,
		Material material,
		SoundType sound,
		float hardness,
		float resistance
	) {
		return create(name, material, sound, hardness)
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
		SoundType sound,
		float hardness
	) {
		return new BlockWithSound(material, color, sound)
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
		return create(name, material, color, hardness)
			.setResistance(resistance);
	}
	
	public static Block create(
		String name,
		Material material,
		MapColor color,
		SoundType sound,
		float hardness,
		float resistance
	) {
		return create(name, material, color, sound, hardness)
			.setResistance(resistance);
	}
	
}
