package gdavid.davidsmod.util;

import java.util.ArrayList;
import java.util.HashMap;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.api.bots.Piece;
import gdavid.davidsmod.api.bots.PieceCategory;
import gdavid.davidsmod.api.bots.PieceMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraft.block.Block;

@EventBusSubscriber
public class RegUtil {
	
	static ArrayList<Block> blocks = new ArrayList<Block>();
	static HashMap<String, Class<? extends TileEntity>> tileEntities = new HashMap<String, Class<? extends TileEntity>>();
	static ArrayList<Item> items = new ArrayList<Item>();
	static HashMap<Block, ItemBlock> itemBlocks = new HashMap<Block, ItemBlock>();
	static ArrayList<PieceCategory> pieceCategories = new ArrayList<PieceCategory>();
	static ArrayList<Piece> pieces = new ArrayList<Piece>();
	static ArrayList<PieceMod> pieceMods = new ArrayList<PieceMod>();
	
	public static <T extends Item> T regItem(T item) {
		items.add(item);
		return item;
	}
	
	public static void regItems(Item... items) {
		for (Item i : items) {
			regItem(i);
		}
	}
	
	public static <T extends Block> T regBlock(T block) {
		return regBlock(true, block);
	}
	
	public static <T extends Block> T regBlock(boolean itemBlock, T block) {
		blocks.add(block);
		if (itemBlock) {
			ItemBlock item = new ItemBlock(block);
			item.setRegistryName(block.getRegistryName());
			itemBlocks.put(block, item);
			regItem(item);
		}
		return block;
	}
	
	public static void regBlocks(Block... blocks) {
		regBlocks(true, blocks);
	}
	
	public static void regBlocks(boolean itemBlock, Block... blocks) {
		for (Block b : blocks) {
			regBlock(itemBlock, b);
		}
	}
	
	public static void regTe(String name, Class<? extends TileEntity> te) {
		tileEntities.put(name, te);
	}
	
	public static ItemBlock getItemBlock(Block block) {
		return itemBlocks.get(block);
	}
	
	public static PieceCategory regPieceCategory(PieceCategory cat) {
		pieceCategories.add(cat);
		return cat;
	}
	
	public static void regPieceCategories(PieceCategory... cat) {
		for (PieceCategory c : cat) {
			regPieceCategory(c);
		}
	}
	
	public static <T extends Piece> T regPiece(T p) {
		pieces.add(p);
		return p;
	}
	
	public static void regPieces(Piece... p) {
		for (Piece c : p) {
			regPiece(c);
		}
	}
	
	public static PieceMod regPieceMod(PieceMod m) {
		pieceMods.add(m);
		return m;
	}
	
	public static void regPieceMods(PieceMod... m) {
		for (PieceMod c : m) {
			regPieceMod(c);
		}
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> reg = event.getRegistry();
		for (Item i : items) reg.register(i);
	}
	
	@SubscribeEvent
	public static void registerItemModels(ModelRegistryEvent event) {
		for (Item i : items) {
			DavidsMod.proxy.registerItemRenderer(i, 0, i.getRegistryName());
		}
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> reg = event.getRegistry();
		for (Block i : blocks) reg.register(i);
		for (String id : tileEntities.keySet()) {
			Class<? extends TileEntity> te = tileEntities.get(id);
			GameRegistry.registerTileEntity(te, DavidsMod.modID + ":" + id);
		}
	}
	
	@SubscribeEvent
	public static void registerCategories(RegistryEvent.Register<PieceCategory> event) {
		IForgeRegistry<PieceCategory> reg = event.getRegistry();
		for (PieceCategory i : pieceCategories) reg.register(i);
	}
	
	@SubscribeEvent
	public static void registerPieces(RegistryEvent.Register<Piece> event) {
		IForgeRegistry<Piece> reg = event.getRegistry();
		for (Piece i : pieces) reg.register(i);
	}
	
	@SubscribeEvent
	public static void registerMods(RegistryEvent.Register<PieceMod> event) {
		IForgeRegistry<PieceMod> reg = event.getRegistry();
		for (PieceMod i : pieceMods) reg.register(i);
	}
	
}
