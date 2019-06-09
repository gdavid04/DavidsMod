package gdavid.davidsmod.gui;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.api.IEnderEnergyHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.CLIENT)
public final class TooltipEnderEnergy {
	
	@SubscribeEvent
	public static void onRenderGui(RenderGameOverlayEvent.Pre event) {
		if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution scaled = new ScaledResolution(mc);
		EntityPlayer player = mc.player;
		RayTraceResult ray = player.rayTrace(player.getEntityAttribute(EntityPlayer.REACH_DISTANCE).getAttributeValue(), 0);
		if (mc.gameSettings.thirdPersonView == 0 && ray.typeOfHit == Type.BLOCK) {
			BlockPos pos = ray.getBlockPos();
			World world = player.world;
			TileEntity tile = world.getTileEntity(pos);
			if (tile != null && tile instanceof IEnderEnergyHandler) {
				IEnderEnergyHandler h = (IEnderEnergyHandler) tile;
				drawBar(mc, scaled, h.getEnderEnergy(), h.getEnderEnergyLimit());
			}
		}
	}
	
	static final ResourceLocation BAR_TEX_PATH = new ResourceLocation(DavidsMod.modID, "textures/bars/background.png");
	static final ResourceLocation BAR_OVERLAY_TEX_PATH = new ResourceLocation(DavidsMod.modID, "textures/bars/overlay.png");
	static final ResourceLocation BAR_FILL_TEX_PATH = new ResourceLocation(DavidsMod.modID, "textures/bars/ender_energy_fill.png");
	
	static void drawBar(Minecraft mc, ScaledResolution scaledRes, int e, int l) {
		int width = scaledRes.getScaledWidth();
		int height = scaledRes.getScaledHeight();
		GlStateManager.disableDepth();
		GlStateManager.depthMask(false);
		GlStateManager.enableAlpha();
		int w = 64;
		int h = 16;
		double top = height * 9 / 16;
		double left = (width - w) / 2;
		mc.getTextureManager().bindTexture(BAR_TEX_PATH);
		ScaledDrawFull(left, top, w, h);
		mc.getTextureManager().bindTexture(BAR_FILL_TEX_PATH);
		ScaledDraw(left + 2, top + 2, (w - 4) * ((float) e / l), h - 4, ((float) e / l), 1);
		mc.getTextureManager().bindTexture(BAR_OVERLAY_TEX_PATH);
		ScaledDrawFull(left, top, w, h);
		GlStateManager.disableAlpha();
		GlStateManager.depthMask(true);
		GlStateManager.enableDepth();
	}
	
	static void ScaledDrawFull(double x, double y, double w, double h) {
		ScaledDraw(x, y, w, h, 1, 1);
	}
	
	static void ScaledDraw(double x, double y, double w, double h, double tw, double th) {
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x + 0, y + h, 0).tex(0, th).endVertex();
        bufferbuilder.pos(x + w, y + h, 0).tex(tw, th).endVertex();
        bufferbuilder.pos(x + w, y + 0, 0).tex(tw, 0).endVertex();
        bufferbuilder.pos(x + 0, y + 0, 0).tex(0, 0).endVertex();
        tessellator.draw();
	}
	
}
