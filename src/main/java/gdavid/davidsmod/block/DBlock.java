package gdavid.davidsmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class DBlock extends Block {
	
	public DBlock(Material mat) {
		super(mat);
	}
	
	public DBlock(Material mat, MapColor col) {
		super(mat, col);
	}
	
	int flammability = 0;
	
	public DBlock setFlammability(int flammability) {
		this.flammability = flammability;
		return this;
	}
	
	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return flammability;
	}
	
	@Override
	public DBlock setSoundType(SoundType type) {
		super.setSoundType(type);
		return this;
	}
	
}
