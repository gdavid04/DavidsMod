package gdavid.davidsmod.api.bots;

import gdavid.davidsmod.api.DavidsModRegistries;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class Program {
	
	public static final int processCount = 4;
	public static final int stepCount = 5;
	public static final int modCount = 2;
	
	public Piece[][] pieces = new Piece[processCount][stepCount];
	public PieceMod[][][] mods = new PieceMod[processCount][stepCount][modCount];
	
	public static Program fromNbt(NBTTagCompound nbt) {
		Program p = new Program();
		if (nbt == null) {
			return p;
		}
		for (int process = 0; process < processCount; process++) {
			NBTTagCompound ptag = nbt.getCompoundTag("" + process);
			for (int step = 0; step < stepCount; step++) {
				NBTTagCompound stag = ptag.getCompoundTag("" + step);
				String id = stag.getString("piece");
				if (id != "") {
					p.pieces[process][step] = DavidsModRegistries.piece.getValue(new ResourceLocation(id));
				}
				NBTTagCompound mtag = stag.getCompoundTag("mods");
				for (int mod = 0; mod < modCount; mod++) {
					String mid = mtag.getString("" + mod);
					if (mid != "") {
						p.mods[process][step][mod] = DavidsModRegistries.pieceMod.getValue(new ResourceLocation(mid));
					}
				}
			}
		}
		return p;
	}
	
	public NBTTagCompound toNbt() {
		NBTTagCompound nbt = new NBTTagCompound();
		for (int process = 0; process < processCount; process++) {
			NBTTagCompound ptag = new NBTTagCompound();
			for (int step = 0; step < stepCount; step++) {
				NBTTagCompound stag = new NBTTagCompound();
				Piece piece = pieces[process][step];
				if (piece != null) {
					stag.setString("piece", piece.getRegistryName().toString());
				}
				NBTTagCompound mtag = new NBTTagCompound();
				for (int mod = 0; mod < modCount; mod++) {
					PieceMod m = mods[process][step][mod];
					if (m != null) {
						mtag.setString("" + mod, m.getRegistryName().toString());
					}
				}
				stag.setTag("mods", mtag);
				ptag.setTag("" + step, stag);
			}
			nbt.setTag("" + process, ptag);
		}
		return nbt;
	}
	
}
