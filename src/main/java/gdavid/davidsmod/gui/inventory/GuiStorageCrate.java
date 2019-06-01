package gdavid.davidsmod.gui.inventory;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.inventory.ContainerStorageCrate;
import gdavid.davidsmod.tile.TileStorageCrate;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiStorageCrate extends GuiContainer {
	
	public static final int WIDTH = 176;
    public static final int HEIGHT = 184;
	
    static final ResourceLocation background = new ResourceLocation(DavidsMod.modID, "textures/gui/container/storage_crate.png");
    
    public GuiStorageCrate(TileStorageCrate tileEntity, ContainerStorageCrate container) {
        super(container);
        xSize = WIDTH;
        ySize = HEIGHT;
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	drawDefaultBackground();
    	super.drawScreen(mouseX, mouseY, partialTicks);
    	renderHoveredToolTip(mouseX, mouseY);
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    	fontRenderer.drawString(((ContainerStorageCrate) inventorySlots).te.getDisplayName().getUnformattedText(), 8, 6, 4210752);
    	fontRenderer.drawString(((ContainerStorageCrate) inventorySlots).p.getDisplayName().getUnformattedText(), 8, 91, 4210752);
    }
    
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1);
		mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
}
