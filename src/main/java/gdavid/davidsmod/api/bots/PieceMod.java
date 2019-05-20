package gdavid.davidsmod.api.bots;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class PieceMod extends IForgeRegistryEntry.Impl<PieceMod> {
	
	private String unlocalizedName;
	private ResourceLocation textureLocation;
	
	public PieceModResult execute(PieceContext context) {
		return PieceModResult.normal;
	}
	
	public ResourceLocation getTextureLocation() {
		if (textureLocation == null) {
			return new ResourceLocation(
				getRegistryName().getResourceDomain(),
				"textures/gdavid/davidsmod/piece/mod/" + getRegistryName().getResourcePath() + ".png"
			);
		} else {
			return textureLocation;
		}
	}
	
	public PieceMod setTextureLocation(ResourceLocation loc) {
		textureLocation = loc;
		return this;
	}
	
	public String getUnlocalizedName() {
		return unlocalizedName;
	}
	
	public PieceMod setUnlocalizedName(String name) {
		unlocalizedName = name;
		return this;
	}
	
	public ITextComponent getDescription() {
		return new TextComponentTranslation("gdavid.davidsmod:piece.mod." + getUnlocalizedName() + ".desc");
	}
	
}
