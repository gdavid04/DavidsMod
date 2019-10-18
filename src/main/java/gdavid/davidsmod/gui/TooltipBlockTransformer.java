package gdavid.davidsmod.gui;

import gdavid.davidsmod.api.BlockTransformation;
import gdavid.davidsmod.item.ItemBlockTransformer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.CLIENT)
public final class TooltipBlockTransformer {
	
	@SubscribeEvent
	public static void onRenderGui(RenderGameOverlayEvent.Pre event) {
		if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution scaled = new ScaledResolution(mc);
		EntityPlayer player = mc.player;
		Item item = player.getHeldItem(EnumHand.MAIN_HAND).getItem();
		if (mc.gameSettings.thirdPersonView == 0 && item instanceof ItemBlockTransformer) {
			ItemBlockTransformer transformer = (ItemBlockTransformer) item;
			RayTraceResult ray = player.rayTrace(player.getEntityAttribute(EntityPlayer.REACH_DISTANCE).getAttributeValue(), 0);
			if (ray.typeOfHit == Type.BLOCK) {
				BlockPos pos = ray.getBlockPos();
				World world = player.world;
				BlockTransformation t = transformer.firstHandler(world, pos);
				if (t != null) {
					IBlockState s = t.GetPreview(world, pos);
					// FIXME not working with cobblestone wall
					if (s != null && s.getRenderType() == EnumBlockRenderType.MODEL) {
						GlStateManager.pushMatrix();
						GlStateManager.enableAlpha();
						mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
						GlStateManager.translate(8 + scaled.getScaledWidth() / 2 + 16, 8 + scaled.getScaledHeight() / 2 + 8, 92);
						GlStateManager.scale(16, -16, 16);
						GlStateManager.translate(0.5f, 0.5f, 0.5f);
						GlStateManager.rotate(30, 1, 0, 0);
						GlStateManager.rotate(225, 0, 1, 0);
						GlStateManager.translate(-pos.getX(), -pos.getY(), -pos.getZ());
						Tessellator tessellator = Tessellator.getInstance();
						BufferBuilder bufferbuilder = tessellator.getBuffer();
						bufferbuilder.begin(7, DefaultVertexFormats.BLOCK);
						BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
						// FIXME no lighting
						blockrendererdispatcher.getBlockModelRenderer().renderModel(world, blockrendererdispatcher.getModelForState(s), s, pos, bufferbuilder, false);
						tessellator.draw();
						GlStateManager.disableAlpha();
						GlStateManager.popMatrix();
					}
				}
			}
		}
	}
	
}
