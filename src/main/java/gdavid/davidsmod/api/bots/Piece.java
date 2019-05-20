package gdavid.davidsmod.api.bots;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class Piece extends IForgeRegistryEntry.Impl<Piece> {
	
	private String unlocalizedName;
	private ResourceLocation textureLocation;
	
	public PieceResult execute(PieceContext context) {
		return PieceResult.next;
	}
	
	public ResourceLocation getTextureLocation() {
		if (textureLocation == null) {
			return new ResourceLocation(
				getRegistryName().getResourceDomain(),
				"textures/gdavid/davidsmod/piece/piece/" + getRegistryName().getResourcePath() + ".png"
			);
		} else {
			return textureLocation;
		}
	}
	
	public Piece setTextureLocation(ResourceLocation loc) {
		textureLocation = loc;
		return this;
	}
	
	public String getUnlocalizedName() {
		return unlocalizedName;
	}
	
	public Piece setUnlocalizedName(String name) {
		unlocalizedName = name;
		return this;
	}
	
	public ITextComponent getDescription() {
		return new TextComponentTranslation("gdavid.davidsmod:piece.piece." + getUnlocalizedName() + ".desc");
	}
	
}
