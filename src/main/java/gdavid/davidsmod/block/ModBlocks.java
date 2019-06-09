package gdavid.davidsmod.block;

import static gdavid.davidsmod.util.CreateBlockUtil.*;
import static gdavid.davidsmod.util.RegUtil.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ModBlocks {
	
	// no ItemBlock, these need a special item
	public static final BlockEnderCrop enderCrop = regBlock(false, new BlockEnderCrop());
	
	// these have a normal ItemBlock
	public static final Block stoneTile = regBlock(create("stone_tile", Material.ROCK, MapColor.STONE, 1.5f, 10.0f));
	public static final Block flint = regBlock(adapt("flint_block", new BlockDFalling(Material.ROCK, MapColor.STONE, -8356741).setSoundType(SoundType.GROUND).setHardness(1.0F)));
	public static final Block smoothOak = regBlock(create("smooth_oak_wood", Material.WOOD, SoundType.WOOD, 2.0F, 5.0F).setFlammability(20));
	public static final Block oakPanel = regBlock(create("oak_panel", Material.WOOD, SoundType.WOOD, 2.0F, 5.0F).setFlammability(20));
	public static final Block oakPanelStack = regBlock(create("oak_panel_stack", Material.WOOD, SoundType.WOOD, 2.5F, 8.0F).setFlammability(20));
	
	public static final Block machineBlock = regBlock(create("machine_block", Material.ROCK, 3.5f));
	
	public static final Block7segDisplay display7seg = regBlock(adapt("7seg_display", new Block7segDisplay()));
	public static final BlockBarDisplay displayBar = regBlock(adapt("bar_display", new BlockBarDisplay()));
	public static final BlockRedstoneRod redstoneRod = regBlock(adapt("redstone_rod", new BlockRedstoneRod()));
	public static final BlockBlockBreaker blockBreaker = regBlock(adapt("block_breaker", new BlockBlockBreaker(2)));
	public static final BlockMechanicalFangs mechanicalFangs = regBlock(adapt("mechanical_fangs", new BlockMechanicalFangs()));
	public static final BlockTouchSensor touchSensor = regBlock(adapt("touch_sensor", new BlockTouchSensor()));
	public static final BlockBlockUpdateRouter blockUpdateRoutingStone = (BlockBlockUpdateRouter) regBlock(adapt("block_update_routing_stone", new BlockBlockUpdateRouter(Material.ROCK, MapColor.STONE)).setHardness(3.0F));
	public static final BlockRedstoneCapacitor redstoneCapacitor = regBlock(adapt("redstone_capacitor", new BlockRedstoneCapacitor()));
	public static final BlockPressurePlateInStone pressurePlateInStone = regBlock(adapt("pressure_plate_in_stone", new BlockPressurePlateInStone()));
	
	public static final BlockBlockDetector waterDetector = (BlockBlockDetector) regBlock(adapt("water_detector", new BlockBlockDetector(Material.ROCK, MapColor.STONE, (World world, BlockPos pos) -> {
		Block block = world.getBlockState(pos).getBlock();
		return block == Blocks.WATER || block == Blocks.FLOWING_WATER;
	})).setHardness(3.5F));
	public static final BlockBlockDetector lavaDetector = (BlockBlockDetector) regBlock(adapt("lava_detector", new BlockBlockDetector(Material.ROCK, MapColor.STONE, (World world, BlockPos pos) -> {
		Block block = world.getBlockState(pos).getBlock();
		return block == Blocks.LAVA || block == Blocks.FLOWING_LAVA;
	})).setHardness(3.5F));
	public static final BlockBlockDetector blockDetector = (BlockBlockDetector) regBlock(adapt("block_detector", new BlockBlockDetector(Material.ROCK, MapColor.STONE, (World world, BlockPos pos) -> {
		IBlockState state = world.getBlockState(pos);
		return !state.getBlock().isAir(state, world, pos);
	})).setHardness(3.5F));
	
	public static final BlockDimensionGate endGate = (BlockDimensionGate) regBlock(adapt("end_gate", new BlockDimensionGate(1, Material.ROCK, MapColor.BLACK).setHardness(5.0F)));
	
	public static final BlockStorageCrate storageCrate = regBlock(adapt("storage_crate", new BlockStorageCrate()));
	
	public static final BlockMiracleCondenser miracleCondenser = regBlock(adapt("miracle_condenser", new BlockMiracleCondenser()));
	// TODO model, crafting
	
	public static final BlockEnderEnergyCondenser enderEnergyCondenser = regBlock(adapt("ender_energy_condenser", new BlockEnderEnergyCondenser()));
	
	public static final BlockChargedIron chargedIron = regBlock(adapt("charged_iron_block", new BlockChargedIron()));
	public static final BlockChargedRock chargedRock = regBlock(adapt("charged_rock", new BlockChargedRock()));
	
	public static final BlockDOre fossils = (BlockDOre) regBlock(BlockDOre.create("fossils", Items.BONE, 1, 4, 0, 3, 1).setHardness(3.0F));
	public static final BlockDOre quartzOre = (BlockDOre) regBlock(BlockDOre.create("quartz_ore", Items.QUARTZ, 2, 3, 0, 3, 2).setHardness(4.5F));
	
	public static void reg() {
		// used to explicitly initialize static fields, thus registering the blocks
	}
	
}
