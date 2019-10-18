package gdavid.davidsmod.api;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface BlockTransformation {
	
	public boolean Match(World world, BlockPos pos);
	
	public boolean Apply(World world, BlockPos pos);
	
	public IBlockState GetPreview(World world, BlockPos pos);
	
}
