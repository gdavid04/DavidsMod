package gdavid.davidsmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBlockUpdateRouter extends Block {

	public BlockBlockUpdateRouter(Material materialIn) {
		super(materialIn);
	}

	public BlockBlockUpdateRouter(Material materialIn, MapColor color) {
		super(materialIn, color);
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!worldIn.isRemote) {
			BlockPos p = fromPos.add(-pos.getX(), -pos.getY(), -pos.getZ());
			EnumFacing fv = EnumFacing.getFacingFromVector(p.getX(), p.getY(), p.getZ());
			for (int i = 0; i < 6; i++) {
				EnumFacing f = EnumFacing.getFront(i);
				if (fv != f) {
					BlockPos op = pos.offset(f);
					if (!(worldIn.getBlockState(op).getBlock() instanceof BlockBlockUpdateRouter)) {
						worldIn.neighborChanged(pos.offset(f), this, pos);
					}
					worldIn.observedNeighborChanged(pos.offset(f), this, pos);
				}
			}
		}
	}

}
