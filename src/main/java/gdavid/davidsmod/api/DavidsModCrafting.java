package gdavid.davidsmod.api;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DavidsModCrafting {
	
	public static Map<IBlockState, BiFunction<World, BlockPos, Boolean>> mossBall;
	
	public static void addBlockConvertion(Map<IBlockState, BiFunction<World, BlockPos, Boolean>> with, IBlockState from, IBlockState to) {
		addBlockConvertion(true, with, from, to);
	}
	
	public static void addBlockConvertion(boolean consume, Map<IBlockState, BiFunction<World, BlockPos, Boolean>> with, IBlockState from, IBlockState to) {
		with.put(from, (World world, BlockPos pos) -> {
			if (!world.isRemote) {
				world.setBlockState(pos, to, 3);
			}
			return consume;
		});
	}
	
	public static void init() {
		mossBall = new HashMap<IBlockState, BiFunction<World, BlockPos, Boolean>>();
	}
	
}
