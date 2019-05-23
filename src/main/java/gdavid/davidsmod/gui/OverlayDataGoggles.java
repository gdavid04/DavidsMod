package gdavid.davidsmod.gui;

import gdavid.davidsmod.DavidsMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.CLIENT)
public final class OverlayDataGoggles {
	
	@SubscribeEvent
	public static void onRenderGui(RenderGameOverlayEvent.Pre event) {
		if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;
		Minecraft mc = Minecraft.getMinecraft();
		FontRenderer fontRenderer = mc.fontRenderer;
		ScaledResolution scaled = new ScaledResolution(mc);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();
		EntityPlayer player = mc.player;
		InventoryPlayer inv = player.inventory;
		ItemStack helmet = inv.armorItemInSlot(3);
		if (mc.gameSettings.thirdPersonView == 0 && helmet.getItem() == Items.LEATHER_HELMET) { // TODO data helmet
			renderVignette(mc, scaled, 0.5f, 0.5f, 0.5f, 0.5f);
			drawCompass(mc, scaled);
			/*
			GuiUtils.drawHoveringText(helmet, helmet.getTooltip(player, ITooltipFlag.TooltipFlags.NORMAL), width / 2, height / 2, width, height, width / 3, fontRenderer);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableBlend();
			*/
		}
	}
	
	private static final ResourceLocation COMPASS_TEX_PATH = new ResourceLocation(DavidsMod.modID, "textures/compass/compass.png");
	
	private static void drawCompass(Minecraft mc, ScaledResolution scaledRes) {
		float sa = 120 / 90f;
		float sf = 1.5f;
		float w = sa * 64 * sf;
		float h = 16 * sf;
		float tp = 40;
		int width = scaledRes.getScaledWidth();
		int height = scaledRes.getScaledHeight();
		float c = width / 2;
		float l = c - w / 2;
		float r = c + w / 2;
		float t = height / tp;
		float b = t + h;
		float y = mc.player.rotationYawHead;
		float xo = y / 90 * 32 + 0.5f;
		float co = xo + 128 / 2;
		float lo = co - sa * 32;
		float ro = co + sa * 32;
		GlStateManager.disableDepth();
		GlStateManager.depthMask(false);
		mc.getTextureManager().bindTexture(COMPASS_TEX_PATH);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(l, b, 0).tex((double)(lo / 128f), 1.0D).endVertex();
		bufferbuilder.pos(r, b, 0).tex((double)(ro / 128f), 1.0D).endVertex();
		bufferbuilder.pos(r, t, 0).tex((double)(ro / 128f), 0.0D).endVertex();
		bufferbuilder.pos(l, t, 0).tex((double)(lo / 128f), 0.0D).endVertex();
		tessellator.draw();
		GlStateManager.depthMask(true);
		GlStateManager.enableDepth();
	}
	
	private static final ResourceLocation VIGNETTE_TEX_PATH = new ResourceLocation(DavidsMod.modID, "textures/misc/vignette.png");
	
	private static void renderVignette(Minecraft mc, ScaledResolution scaledRes, float r, float g, float b, float a) {
		GlStateManager.disableDepth();
		GlStateManager.enableBlend();
		GlStateManager.depthMask(false);
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.color(r, g, b, a);
        GlStateManager.disableAlpha();
		mc.getTextureManager().bindTexture(VIGNETTE_TEX_PATH);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(0.0D, (double)scaledRes.getScaledHeight(), -90.0D).tex(0.0D, 1.0D).endVertex();
		bufferbuilder.pos((double)scaledRes.getScaledWidth(), (double)scaledRes.getScaledHeight(), -90.0D).tex(1.0D, 1.0D).endVertex();
		bufferbuilder.pos((double)scaledRes.getScaledWidth(), 0.0D, -90.0D).tex(1.0D, 0.0D).endVertex();
		bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).endVertex();
		tessellator.draw();
		GlStateManager.depthMask(true);
		GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	}
	
}
