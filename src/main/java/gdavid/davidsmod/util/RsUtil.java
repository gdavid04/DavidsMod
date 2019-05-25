package gdavid.davidsmod.util;

import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RsUtil {
	
	public static boolean isPowered(World world, BlockPos pos) {
		return getPower(world, pos) != 0;
	}
	
	public static int getPower(World world, BlockPos pos) {
		int r = 0;
		for (int i = 0; i < 6; i++) {
			EnumFacing f = EnumFacing.getFront(i);
			IBlockState state = world.getBlockState(pos.offset(f));
			if (state.getBlock() == Blocks.REDSTONE_WIRE) {
				r = Math.max(r, state.getValue(BlockRedstoneWire.POWER));
			}
		}
		return Math.max(r, world.isBlockIndirectlyGettingPowered(pos));
	}
	
}
