package gdavid.davidsmod.gui.inventory;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.inventory.ContainerMiracleCondenser;
import gdavid.davidsmod.tile.TileMiracleCondenser;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiMiracleCondenser extends GuiContainer {
	
	public static final int WIDTH = 176;
    public static final int HEIGHT = 166;
	
    static final ResourceLocation background = new ResourceLocation(DavidsMod.modID, "textures/gui/container/miracle_condenser.png");
    
    public GuiMiracleCondenser(TileMiracleCondenser tileEntity, ContainerMiracleCondenser container) {
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
    	fontRenderer.drawString(((ContainerMiracleCondenser) inventorySlots).te.getDisplayName().getUnformattedText(), 8, 6, 4210752);
    	fontRenderer.drawString(((ContainerMiracleCondenser) inventorySlots).p.getDisplayName().getUnformattedText(), 8, 72, 4210752);
    }
    
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1);
		mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        if (((ContainerMiracleCondenser) inventorySlots).te.isCondensing()) {
        	drawTexturedModalRect(guiLeft + 79, guiTop + 37, 176, 0, 12, 12);
        	drawTexturedModalRect(guiLeft + 79, guiTop + 37, 188, 0, Math.round(getProgress() * 12), 12);
        }
	}
	
	float getProgress() {
		TileMiracleCondenser te = ((ContainerMiracleCondenser) inventorySlots).te;
		int p = te.getCondenseProgress();
		int m = te.getRequiredCondenseProgress();
		return p > m ? 1 : p / (float) m;
	}
	
}
