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
		return adapt(name, new Block(material))
			.setHardness(hardness);
	}
	
	public static Block create(
		String name,
		Material material,
		SoundType sound,
		float hardness
	) {
		return adapt(name, new BlockWithSound(material, sound))
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
		return adapt(name, new Block(material, color))
			.setHardness(hardness);
	}
	
	public static Block create(
		String name,
		Material material,
		MapColor color,
		SoundType sound,
		float hardness
	) {
		return adapt(name, new BlockWithSound(material, color, sound))
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
	
	@SuppressWarnings("unchecked")
	public static <T extends Block> T adapt(String name, T block) {
		return (T) block
			.setRegistryName(DavidsMod.modID, name)
			.setUnlocalizedName(DavidsMod.modID + ":" + name)
			.setCreativeTab(TabDavidsmod.get());
	}
	
}
