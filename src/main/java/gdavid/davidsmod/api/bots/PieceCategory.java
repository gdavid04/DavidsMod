package gdavid.davidsmod.api.bots;

import java.util.ArrayList;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class PieceCategory extends IForgeRegistryEntry.Impl<PieceCategory> {
	
	public ArrayList<Piece> pieces = new ArrayList<Piece>();
	public ArrayList<PieceMod> mods = new ArrayList<PieceMod>();
	
	public PieceCategory() {
	}
	
	private String unlocalizedName;
	private ResourceLocation textureLocation;
	
	public ResourceLocation getTextureLocation() {
		if (textureLocation == null) {
			return new ResourceLocation(
				getRegistryName().getResourceDomain(),
				"textures/gdavid/davidsmod/piece/category/" + getRegistryName().getResourcePath() + ".png"
			);
		} else {
			return textureLocation;
		}
	}
	
	public PieceCategory setTextureLocation(ResourceLocation loc) {
		textureLocation = loc;
		return this;
	}
	
	public String getUnlocalizedName() {
		return unlocalizedName;
	}
	
	public PieceCategory setUnlocalizedName(String name) {
		unlocalizedName = name;
		return this;
	}
	
	public ITextComponent getDescription() {
		return new TextComponentTranslation("gdavid.davidsmod:piece.category." + getUnlocalizedName() + ".desc");
	}
	
}
