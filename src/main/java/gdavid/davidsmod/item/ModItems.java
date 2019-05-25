package gdavid.davidsmod.item;

import gdavid.davidsmod.util.CreateItemUtil;
import gdavid.davidsmod.util.RegUtil;

import net.minecraft.item.Item;

public class ModItems {
	
	public static final ItemRedstoneMeter redstoneMeter = RegUtil.regItem(CreateItemUtil.adapt("redstone_meter", new ItemRedstoneMeter()));
	public static final ItemRedstoneMeterWithDisplay redstoneMeterWithDisplay = RegUtil.regItem(CreateItemUtil.adapt("redstone_meter_with_display", new ItemRedstoneMeterWithDisplay()));
	public static final Item moduleBase = RegUtil.regItem(CreateItemUtil.create("module_base", 16));
	public static final Item moduleDisplayBasic = RegUtil.regItem(CreateItemUtil.create("basic_display_module", 16));
	public static final Item moduleDisplay = RegUtil.regItem(CreateItemUtil.create("display_module", 16));
	public static final Item moduleInput = RegUtil.regItem(CreateItemUtil.create("input_module", 16));
	public static final Item moduleInput2 = RegUtil.regItem(CreateItemUtil.create("tier2_input_module", 16));
	public static final ItemBotProgrammer botProgrammer = RegUtil.regItem(CreateItemUtil.adapt("bot_programmer", new ItemBotProgrammer()));
	public static final ItemEnderSeed enderSeed = RegUtil.regItem(CreateItemUtil.adapt("ender_seed", new ItemEnderSeed()));
	public static final ItemInfinityPickaxe infinityPickaxe = RegUtil.regItem(CreateItemUtil.adapt("infinity_pickaxe", new ItemInfinityPickaxe()));
	public static final Item bedrockDust = RegUtil.regItem(CreateItemUtil.create("bedrock_dust"));
	
	public static void reg() {
		// used to explicitly initialize static fields, thus registering the items
	}
	
}
