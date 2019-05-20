package gdavid.davidsmod.util;

import java.util.function.Function;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.api.bots.Piece;
import gdavid.davidsmod.api.bots.PieceCategory;
import gdavid.davidsmod.api.bots.PieceContext;
import gdavid.davidsmod.api.bots.PieceMod;
import gdavid.davidsmod.api.bots.PieceModResult;
import gdavid.davidsmod.api.bots.PieceResult;
import net.minecraft.util.ResourceLocation;

public class CreatePieceUtil {
	
	public static PieceCategory category(String name) {
		return new PieceCategory()
			.setRegistryName(DavidsMod.modID, name)
			.setUnlocalizedName(name)
			.setTextureLocation(new ResourceLocation(DavidsMod.modID, "textures/piece/category/" + name + ".png"));
	}
	
	public static Piece piece(String name, PieceCategory category, Function<PieceContext, PieceResult> callback) {
		Piece piece = new Piece() {
			@Override
			public PieceResult execute(PieceContext context) {
				return callback.apply(context);
			}
		}
			.setRegistryName(DavidsMod.modID, name)
			.setUnlocalizedName(name)
			.setTextureLocation(new ResourceLocation(DavidsMod.modID, "textures/piece/piece/" + name + ".png"));
		category.pieces.add(piece);
		return piece;
	}
	
	public static PieceMod mod(String name, PieceCategory category) {
		PieceMod mod = new PieceMod()
			.setRegistryName(DavidsMod.modID, name)
			.setUnlocalizedName(name)
			.setTextureLocation(new ResourceLocation(DavidsMod.modID, "textures/piece/mod/" + name + ".png"));
		category.mods.add(mod);
		return mod;
	}
	
	public static PieceMod mod(String name, PieceCategory category, Function<PieceContext, PieceModResult> callback) {
		PieceMod mod = new PieceMod() {
			@Override
			public PieceModResult execute(PieceContext context) {
				return callback.apply(context);
			}
		}
			.setRegistryName(DavidsMod.modID, name)
			.setUnlocalizedName(name)
			.setTextureLocation(new ResourceLocation(DavidsMod.modID, "textures/piece/mod/" + name + ".png"));
		category.mods.add(mod);
		return mod;
	}
	
}
