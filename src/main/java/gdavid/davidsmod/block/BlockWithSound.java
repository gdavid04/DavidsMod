package gdavid.davidsmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockWithSound extends Block {
	
	public BlockWithSound(Material mat, SoundType sound) {
		super(mat);
		setSoundType(sound);
	}
	
	public BlockWithSound(Material mat, MapColor col, SoundType sound) {
		super(mat, col);
		setSoundType(sound);
	}
	
}
