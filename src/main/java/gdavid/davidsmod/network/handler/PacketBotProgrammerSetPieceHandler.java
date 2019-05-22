package gdavid.davidsmod.network.handler;

import gdavid.davidsmod.api.DavidsModRegistries;
import gdavid.davidsmod.api.bots.Program;
import gdavid.davidsmod.item.ModItems;
import gdavid.davidsmod.network.message.PacketBotProgrammerSetPiece;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketBotProgrammerSetPieceHandler implements IMessageHandler<PacketBotProgrammerSetPiece, IMessage> {
	
	static final int PIECE = -1;
	
	@Override
	public IMessage onMessage(PacketBotProgrammerSetPiece message, MessageContext ctx) {
		EntityPlayerMP player = ctx.getServerHandler().player;
		player.getServerWorld().addScheduledTask(() -> {
			ItemStack stack = player.getHeldItem(message.hand);
			if (
				stack.getItem() == ModItems.botProgrammer &&
				message.process >= 0 &&
				message.step >= 0 &&
				message.mod >= PIECE &&
				message.process < Program.processCount &&
				message.step < Program.stepCount &&
				message.mod < Program.modCount
			) {
				NBTTagCompound nbt = stack.getTagCompound();
				if (nbt == null) {
					nbt = new NBTTagCompound();
				}
				Program program = Program.fromNbt(nbt.getCompoundTag("program"));
				if (message.mod == PIECE) {
					program.pieces[message.process][message.step] = message.id.isEmpty() ? null : DavidsModRegistries.piece.getValue(new ResourceLocation(message.id));
				} else {
					program.mods[message.process][message.step][message.mod] = message.id.isEmpty() ? null : DavidsModRegistries.pieceMod.getValue(new ResourceLocation(message.id));
				}
				nbt.setTag("program", program.toNbt());
				System.err.println(nbt.toString());
				stack.setTagCompound(nbt);
				player.setHeldItem(message.hand, stack);
			}
		});
		return null;
	}
	
}
