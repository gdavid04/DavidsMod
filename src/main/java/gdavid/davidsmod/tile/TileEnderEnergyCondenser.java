package gdavid.davidsmod.tile;

import gdavid.davidsmod.api.IEnderEnergyHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.DimensionType;

public class TileEnderEnergyCondenser extends TileEntity implements IEnderEnergyHandler, ITickable {
	
	public int energy = 0;
	public static final int limit = 1000;
	int change = 1;
	int prev = 0;
	int ptick = 0;
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if (compound.hasKey("energy")) {
			energy = compound.getInteger("energy");
		}
		prev = energy;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("energy", energy);
		return compound;
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = super.getUpdateTag();
		nbt.setInteger("energy", energy);
		nbt.setInteger("change", change);
		return nbt;
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound nbt) {
		if (nbt.hasKey("energy")) {
			energy = nbt.getInteger("energy");
		}
		if (nbt.hasKey("change")) {
			change = nbt.getInteger("change");
		}
	}
	
	public boolean canInteractWith(EntityPlayer playerIn) {
		return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(getPos(), 1, writeToNBT(new NBTTagCompound()));
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		handleUpdateTag(pkt.getNbtCompound());
	}
	
	@Override
	public void update() {
		if (world.isRemote) {
			if (world.provider.getDimensionType() == DimensionType.THE_END) {
				energy += change;
				if (energy > limit) {
					energy = limit;
					change = 0;
				} else if (energy < 0) {
					energy = 0;
					change = 0;
				}
			}
		} else {
			if (energy < limit && world.provider.getDimensionType() == DimensionType.THE_END) {
				energy++;
				markDirty();
			}
			ptick++;
			if (ptick == 40) {
				ptick = 0;
				int chg = energy - prev;
				if (chg != change) {
					world.notifyBlockUpdate(getPos(), world.getBlockState(getPos()), world.getBlockState(getPos()), 2);
				}
				prev = energy;
			}
		}
	}
	
	@Override
	public int getEnderEnergy() {
		return energy;
	}
	
	@Override
	public void setEnderEnergy(int e) {
		energy = e;
	}
	
	@Override
	public int getEnderEnergyLimit() {
		return limit;
	}
	
	@Override
	public boolean canInsertEnderEnergy() {
		return false;
	}
	
	@Override
	public boolean canExtractEnderEnergy() {
		return true;
	}
	
	public boolean isCharging() {
		return world.provider.getDimensionType() == DimensionType.THE_END && energy < limit;
	}
	
}
