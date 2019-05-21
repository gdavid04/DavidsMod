package gdavid.davidsmod.network;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.network.handler.PacketBotProgrammerSetPieceHandler;
import gdavid.davidsmod.network.message.PacketBotProgrammerSetPiece;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
	
	public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(DavidsMod.modID);
	
	static final int botProgrammerSetPiece = 0;
	
	public static void RegisterPackets() {
		instance.registerMessage(PacketBotProgrammerSetPieceHandler.class, PacketBotProgrammerSetPiece.class, botProgrammerSetPiece, Side.SERVER);
	}
	
}
