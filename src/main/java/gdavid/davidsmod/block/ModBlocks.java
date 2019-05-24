package gdavid.davidsmod.block;

import gdavid.davidsmod.util.CreateBlockUtil;
import gdavid.davidsmod.util.RegUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;

public class ModBlocks {
	
	// no ItemBlock, these need a special item
	public static final BlockEnderCrop enderCrop = RegUtil.regBlock(false, new BlockEnderCrop());
	
	// these have a normal ItemBlock
	public static final Block stoneTile = RegUtil.regBlock(CreateBlockUtil.create("stone_tile", Material.ROCK, MapColor.STONE, 1.5f, 10.0f));
	public static final Block flint = RegUtil.regBlock(CreateBlockUtil.adapt("flint_block", new BlockDFalling(Material.ROCK, MapColor.STONE, -8356741).setSoundType(SoundType.GROUND).setHardness(1.0F)));
	public static final Block smoothOak = RegUtil.regBlock(CreateBlockUtil.create("smooth_oak_wood", Material.WOOD, SoundType.WOOD, 2.0F, 5.0F).setFlammability(20));
	public static final Block oakPanel = RegUtil.regBlock(CreateBlockUtil.create("oak_panel", Material.WOOD, SoundType.WOOD, 2.0F, 5.0F).setFlammability(20));
	public static final Block oakPanelStack = RegUtil.regBlock(CreateBlockUtil.create("oak_panel_stack", Material.WOOD, SoundType.WOOD, 2.5F, 8.0F).setFlammability(20));
	
	public static final Block machineBlock = RegUtil.regBlock(CreateBlockUtil.create("machine_block", Material.ROCK, 3.5f));
	
	public static final Block7segDisplay display7seg = RegUtil.regBlock(CreateBlockUtil.adapt("7seg_display", new Block7segDisplay()));
	public static final BlockBarDisplay displayBar = RegUtil.regBlock(CreateBlockUtil.adapt("bar_display", new BlockBarDisplay()));
	public static final BlockRedstoneRod redstoneRod = RegUtil.regBlock(CreateBlockUtil.adapt("redstone_rod", new BlockRedstoneRod()));
	public static final BlockBlockBreaker blockBreaker = RegUtil.regBlock(CreateBlockUtil.adapt("block_breaker", new BlockBlockBreaker(2)));
	
	public static final BlockChargedIron chargedIron = RegUtil.regBlock(CreateBlockUtil.adapt("charged_iron_block", new BlockChargedIron()));
	public static final BlockChargedRock chargedRock = RegUtil.regBlock(CreateBlockUtil.adapt("charged_rock", new BlockChargedRock()));
	
	public static final BlockDOre fossils = (BlockDOre) RegUtil.regBlock(BlockDOre.create("fossils", Items.BONE, 1, 4, 0, 3, 1).setHardness(3.0F));
	public static final BlockDOre quartzOre = (BlockDOre) RegUtil.regBlock(BlockDOre.create("quartz_ore", Items.QUARTZ, 2, 3, 0, 3, 2).setHardness(4.5F));
	
	public static void reg() {
		// used to explicitly initialize static fields, thus registering the blocks
	}
	
}
