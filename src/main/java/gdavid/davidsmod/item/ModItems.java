package gdavid.davidsmod.item;

import gdavid.davidsmod.api.DavidsModCrafting;
import static gdavid.davidsmod.util.CreateItemUtil.*;
import static gdavid.davidsmod.util.RegUtil.*;

import net.minecraft.item.Item;

public class ModItems {
	
	public static final ItemRedstoneMeter redstoneMeter = regItem(adapt("redstone_meter", new ItemRedstoneMeter()));
	public static final ItemRedstoneMeterWithDisplay redstoneMeterWithDisplay = regItem(adapt("redstone_meter_with_display", new ItemRedstoneMeterWithDisplay()));
	public static final Item moduleBase = regItem(create("module_base", 16));
	public static final Item moduleDisplayBasic = regItem(create("basic_display_module", 16));
	public static final Item moduleDisplay = regItem(create("display_module", 16));
	public static final Item moduleInput = regItem(create("input_module", 16));
	public static final Item moduleInput2 = regItem(create("tier2_input_module", 16));
	public static final ItemBotProgrammer botProgrammer = regItem(adapt("bot_programmer", new ItemBotProgrammer()));
	public static final ItemEnderSeed enderSeed = regItem(adapt("ender_seed", new ItemEnderSeed()));
	public static final ItemInfinityPickaxe infinityPickaxe = regItem(adapt("infinity_pickaxe", new ItemInfinityPickaxe()));
	public static final Item bedrockDust = regItem(create("bedrock_dust"));
	public static final ItemBlockTransformer mossBall = regItem(adapt("moss_ball", new ItemBlockTransformer(DavidsModCrafting.mossBall)));
	public static final Item voidStar = regItem(create("void_star"));
	public static final Item sand = regItem(create("sand"));
	public static final Item bloodBottle = regItem(create("bottle_of_blood", 1));
	public static final Item miracleDust = regItem(create("miracle_dust")); // TODO glow
	
	public static void reg() {
		// used to explicitly initialize static fields, thus registering the items
	}
	
}
