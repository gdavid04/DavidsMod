package gdavid.davidsmod.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketBotProgrammerSetPiece implements IMessage {
	
	public PacketBotProgrammerSetPiece() {}
	
	static final int PIECE = -1;
	
	public EnumHand hand;
	public int process, step, mod;
	public String id = "";
	
	public PacketBotProgrammerSetPiece(EnumHand hand, int process, int step, String id) {
		this(hand, process, step, PIECE, id);
	}
	
	public PacketBotProgrammerSetPiece(EnumHand hand, int process, int step, int mod, String id) {
		this.hand = hand;
		this.process = process;
		this.step = step;
		this.mod = mod;
		this.id = id;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		hand = buf.readBoolean() ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND;
		process = buf.readInt();
		step = buf.readInt();
		mod = buf.readInt();
		id = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(hand == EnumHand.OFF_HAND);
		buf.writeInt(process);
		buf.writeInt(step);
		buf.writeInt(mod);
		ByteBufUtils.writeUTF8String(buf, id);
	}
	
}
