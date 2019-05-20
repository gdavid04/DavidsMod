package gdavid.davidsmod.proxy;

import gdavid.davidsmod.gui.OverlayDataGoggles;
import gdavid.davidsmod.specialplayers.CapeHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerItemRenderer(Item item, int meta, ResourceLocation id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(id, "inventory"));
	}
	
	@Override
	public void registerBlockColorHandler(Block block, IBlockColor color) {
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(color, block);
	}
	
	@Override
	public void mapBlockModel(Block block, String location) {
		ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
			
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(location, getPropertyString(state.getProperties()));
			}
			
		});
	}
	
	@Override
	public void registerHandlers() {
		MinecraftForge.EVENT_BUS.register(OverlayDataGoggles.class);
		MinecraftForge.EVENT_BUS.register(CapeHandler.class);
	}
	
}
