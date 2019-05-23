package gdavid.davidsmod.block;

import java.util.Random;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.tab.TabDavidsmod;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockDOre extends BlockOre {
	
	public final Item drop;
	public final int minDrop, maxDrop;
	public final int minExpDrop, maxExpDrop;

	public BlockDOre(String name, Item drop, int minDrop, int maxDrop, int minExpDrop, int maxExpDrop, int level) {
		super();
		setUnlocalizedName(DavidsMod.modID + ":" + name);
		setRegistryName(name);
		setCreativeTab(TabDavidsmod.get());
		setHardness(1.5F);
		setResistance(10.0F);
		setHarvestLevel("pickaxe", level);
		this.drop = drop;
		this.minDrop = minDrop;
		this.maxDrop = maxDrop;
		this.minExpDrop = minExpDrop;
		this.maxExpDrop = maxExpDrop;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return drop;
	}

	@Override
	public int quantityDropped(Random random) {
		if (this.getItemDropped(getDefaultState(), random, 0) != Item.getItemFromBlock(this)) {
			return MathHelper.getInt(random, minDrop, maxDrop);
		} else {
			return 1;
		}
	}
	
	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
		if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)) {
			return MathHelper.getInt(rand, minExpDrop, maxExpDrop);
		} else {
			return 0;
		}
	}
	
}
