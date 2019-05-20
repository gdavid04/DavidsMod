package gdavid.davidsmod.api.bots;

import gdavid.davidsmod.api.DavidsModRegistries;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;

public class Program {
	
	public static final int processCount = 4;
	public static final int stepCount = 5;
	public static final int modCount = 2;
	
	public Piece[][] pieces = new Piece[processCount][stepCount];
	public PieceMod[][][] mods = new PieceMod[processCount][stepCount][modCount];
	
	public static Program fromNbt(NBTTagList nbt) {
		Program p = new Program();
		if (nbt == null) {
			return p;
		}
		for (int process = 0; process < processCount; process++) {
			NBTBase ptb = nbt.get(process);
			if (!(ptb instanceof NBTTagList)) {
				continue;
			}
			NBTTagList ptag = (NBTTagList) ptb;
			for (int step = 0; step < stepCount; step++) {
				NBTTagCompound stag = ptag.getCompoundTagAt(step);
				String id = stag.getString("piece");
				if (id != "") {
					p.pieces[process][step] = DavidsModRegistries.piece.getValue(new ResourceLocation(id));
				}
				NBTTagList mtag = stag.getTagList("mods", 8);
				for (int mod = 0; mod < modCount; mod++) {
					String mid = mtag.getStringTagAt(mod);
					if (mid != "") {
						p.mods[process][step][mod] = DavidsModRegistries.pieceMod.getValue(new ResourceLocation(mid));
					}
				}
			}
		}
		return p;
	}
	
	public NBTTagList toNbt() {
		NBTTagList nbt = new NBTTagList();
		for (int process = 0; process < processCount; process++) {
			NBTTagList ptag = new NBTTagList();
			for (int step = 0; step < stepCount; step++) {
				NBTTagCompound stag = new NBTTagCompound();
				Piece piece = pieces[process][step];
				if (piece != null) {
					stag.setString("piece", piece.getRegistryName().toString());
				}
				NBTTagList mtag = new NBTTagList();
				for (int mod = 0; mod < modCount; mod++) {
					PieceMod m = mods[process][step][mod];
					if (m != null) {
						mtag.set(mod, new NBTTagString(m.getRegistryName().toString()));
					}
				}
				stag.setTag("mods", mtag);
				ptag.set(step, stag);
			}
			nbt.set(process, ptag);
		}
		return nbt;
	}
	
}
