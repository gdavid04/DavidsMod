package gdavid.davidsmod.api;

import java.util.ArrayList;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DavidsModCrafting {
	
	public static ArrayList<BlockTransformation> mossBall;
	
	public static void addBlockConversion(ArrayList<BlockTransformation> with, IBlockState from, IBlockState to) {
		addBlockConversion(with, from, to, true);
	}
	
	public static void addBlockConversion(ArrayList<BlockTransformation> with, IBlockState from, IBlockState to, boolean consume) {
		with.add(new BlockTransformation() {
			
			@Override
			public boolean Match(World world, BlockPos pos) {
				return world.getBlockState(pos) == from;
			}
			
			@Override
			public boolean Apply(World world, BlockPos pos) {
				world.setBlockState(pos, to, 3);
				return consume;
			}
			
			@Override
			public IBlockState GetPreview(World world, BlockPos pos) {
				return to;
			}
			
		});
	}
	
	public static void init() {
		mossBall = new ArrayList<BlockTransformation>();
	}
	
}
