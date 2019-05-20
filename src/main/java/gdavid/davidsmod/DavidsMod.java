package gdavid.davidsmod;

import gdavid.davidsmod.api.DavidsModRegistries;
import gdavid.davidsmod.api.bots.Piece;
import gdavid.davidsmod.api.bots.PieceCategory;
import gdavid.davidsmod.api.bots.PieceMod;
import gdavid.davidsmod.block.ModBlocks;
import gdavid.davidsmod.bots.ModPieceCategories;
import gdavid.davidsmod.bots.ModPieceMods;
import gdavid.davidsmod.bots.ModPieces;
import gdavid.davidsmod.gui.GuiHandler;
import gdavid.davidsmod.item.ModItems;
import gdavid.davidsmod.proxy.CommonProxy;
import gdavid.davidsmod.tab.TabDavidsmod;
import gdavid.davidsmod.world.WorldGenerator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.RegistryBuilder;

@Mod(modid = DavidsMod.modID, version = DavidsMod.version, useMetadata = true)
@EventBusSubscriber
public class DavidsMod {
	
	public static final String modID = "davidsmod";
	public static final String version = "1.0.0";
	
	@Instance(modID)
	public static DavidsMod instance;
	
	@SidedProxy(
		serverSide="gdavid.davidsmod.proxy.CommonProxy",
		clientSide="gdavid.davidsmod.proxy.ClientProxy"
	)
	public static CommonProxy proxy;
	
	static {
		TabDavidsmod.get();
		ModBlocks.reg();
		ModItems.reg();
		ModPieceCategories.reg();
		ModPieces.reg();
		ModPieceMods.reg();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		proxy.registerHandlers();
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
	}
	
	// TODO move to charged ender chestplate
	@SubscribeEvent
	public static void onEntityHurt(LivingHurtEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		ItemStack chestplate = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		String damageType = event.getSource().getDamageType();
		/*
		if (chestplate.getItem() == itemChargedEnderChestplate) {
			chestplate.damageItem((int) event.getAmount(), entity);
			event.setCanceled(true);
		}
		*/ // TODO add charged ender chestplate
	}
	
	@SubscribeEvent
	public static void registerRegistries(RegistryEvent.NewRegistry event) {
		DavidsModRegistries.pieceCategory = new RegistryBuilder<PieceCategory>()
			.setType(PieceCategory.class)
			.setName(new ResourceLocation(DavidsMod.modID, "bot.piece.category"))
			.create();
		DavidsModRegistries.piece = new RegistryBuilder<Piece>()
			.setType(Piece.class)
			.setName(new ResourceLocation(DavidsMod.modID, "bot.piece.piece"))
			.create();
		DavidsModRegistries.pieceMod = new RegistryBuilder<PieceMod>()
			.setType(PieceMod.class)
			.setName(new ResourceLocation(DavidsMod.modID, "bot.piece.mod"))
			.create();
	}
	
}
