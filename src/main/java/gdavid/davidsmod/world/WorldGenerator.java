package gdavid.davidsmod.world;

import java.util.Random;

import gdavid.davidsmod.block.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenerator implements IWorldGenerator {
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.getDimensionType() == DimensionType.OVERWORLD) {
			generateOre(ModBlocks.fossils.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 40, 128, 1 + random.nextInt(10), 4);
			generateOre(ModBlocks.quartzOre.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 0, 82, 2 + random.nextInt(4), 20);
		}
	}
	
	private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) {
		int deltaY = maxY - minY;
		for (int i = 0; i < chances; i++) {
			BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));
			WorldGenMinable generator = new WorldGenMinable(ore, size);
			generator.generate(world, random, pos);
		}
	}
	
}
