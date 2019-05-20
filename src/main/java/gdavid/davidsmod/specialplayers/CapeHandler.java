package gdavid.davidsmod.specialplayers;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import com.google.common.collect.ImmutableSet;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;

import gdavid.davidsmod.DavidsMod;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class CapeHandler {
	
	private static final String[] PLAYER_INFO = new String[]{"d", "field_175157_a", "playerInfo"};
	private static final String[] PLAYER_TEXTURES = new String[]{"a", "field_187107_a", "playerTextures"};
	
	private static final ImmutableSet<String> devs = ImmutableSet.of(
		"5ecdbd88-c604-437b-a81f-8b4769218384" // GDavid
	);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static final Set<EntityPlayer> done = Collections.newSetFromMap(new WeakHashMap());
	
	@SubscribeEvent
	public static void onRenderedPlayer(RenderPlayerEvent.Post event) {
		EntityPlayer player = event.getEntityPlayer();
		String uuid = EntityPlayer.getUUID(player.getGameProfile()).toString();
		if(player instanceof AbstractClientPlayer && devs.contains(uuid) && !done.contains(player)) {
			AbstractClientPlayer clplayer = (AbstractClientPlayer) player;
			if(clplayer.hasPlayerInfo()) {
				NetworkPlayerInfo info = ReflectionHelper.getPrivateValue(AbstractClientPlayer.class, clplayer, PLAYER_INFO);
				Map<MinecraftProfileTexture.Type, ResourceLocation> textures = ReflectionHelper.getPrivateValue(NetworkPlayerInfo.class, info, PLAYER_TEXTURES);
				ResourceLocation loc = new ResourceLocation(DavidsMod.modID, "textures/capes/developer.png");
				textures.put(MinecraftProfileTexture.Type.CAPE, loc);
				textures.put(MinecraftProfileTexture.Type.ELYTRA, loc);
				done.add(player);
			}
		}
	}
	
}
