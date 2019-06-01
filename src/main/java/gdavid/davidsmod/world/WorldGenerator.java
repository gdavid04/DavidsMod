package gdavid.davidsmod.world;

import java.util.Random;
import java.util.function.BiPredicate;

import gdavid.davidsmod.block.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
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
		int x = chunkX << 4, z = chunkZ << 4;
		if (world.provider.getDimensionType() == DimensionType.OVERWORLD) {
			generateOre(ModBlocks.fossils.getDefaultState(), world, random, x, z, 40, 128, 1 + random.nextInt(10), 4);
			generateOre(ModBlocks.quartzOre.getDefaultState(), world, random, x, z, 0, 82, 2 + random.nextInt(4), 20);
			floodLayer(Blocks.LAVA.getDefaultState(), world, random, x + 8, z + 8, 1, 5, 0.6f, (w, p) -> w.getBlockState(p).getBlock() != Blocks.BEDROCK);
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
	
	private void floodLayer(IBlockState fill, World world, Random random, int x, int z, int minY, int maxY, float chance, BiPredicate<World, BlockPos> replace) {
		for (int y = minY; y <= maxY; y++) {
			for (int cx = x; cx < x + 16; cx++) {
				for (int cz = z; cz < z + 16; cz++) {
					BlockPos pos = new BlockPos(cx,  y, cz);
					if (random.nextFloat() <= chance && replace.test(world, pos)) {
						world.setBlockState(pos, fill, 2);
					}
				}
			}
		}
	}
	
}
