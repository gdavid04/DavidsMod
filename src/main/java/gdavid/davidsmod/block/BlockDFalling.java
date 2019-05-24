package gdavid.davidsmod.block;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDFalling extends BlockFalling {
	
	public final MapColor col;
	public final int color;
	
	public BlockDFalling(Material mat, MapColor col, int color) {
		super(mat);
		this.col = col;
		this.color = color;
	}
	
	@Override
	public BlockDFalling setSoundType(SoundType type) {
		super.setSoundType(type);
		return this;
	}
	
	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return col;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getDustColor(IBlockState state) {
		return color;
	}
	
}
