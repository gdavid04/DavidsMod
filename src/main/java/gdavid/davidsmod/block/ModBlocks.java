package gdavid.davidsmod.block;

import gdavid.davidsmod.util.CreateBlockUtil;
import gdavid.davidsmod.util.RegUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;

public class ModBlocks {
	
	// no ItemBlock, these need a special item
	public static final BlockEnderCrop enderCrop = RegUtil.regBlock(false, new BlockEnderCrop());
	
	// these have a normal ItemBlock
	public static final Block stoneTile = RegUtil.regBlock(CreateBlockUtil.create("stone_tile", Material.ROCK, MapColor.STONE, 1.5f, 10.0f));
	public static final BlockDOre fossils = (BlockDOre) RegUtil.regBlock(new BlockDOre("fossils", Items.BONE, 1, 4, 0, 3).setHardness(3.0F));
	public static final Block machineBlock = RegUtil.regBlock(CreateBlockUtil.create("machine_block", Material.ROCK, 3.5f));
	public static final Block7segDisplay display7seg = RegUtil.regBlock(new Block7segDisplay());
	public static final BlockBarDisplay displayBar = RegUtil.regBlock(new BlockBarDisplay());
	public static final BlockRedstoneRod redstoneRod = RegUtil.regBlock(new BlockRedstoneRod());
	
	public static final BlockChargedIron chargedIron = RegUtil.regBlock(new BlockChargedIron());
	public static final BlockChargedRock chargedRock = RegUtil.regBlock(new BlockChargedRock());
	
	public static void reg() {
		// used to explicitly initialize static fields, thus registering the blocks
	}
	
}
