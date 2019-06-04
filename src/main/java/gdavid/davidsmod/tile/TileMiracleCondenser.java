package gdavid.davidsmod.tile;

import javax.annotation.Nonnull;

import gdavid.davidsmod.inventory.IOItemHandler;
import gdavid.davidsmod.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileMiracleCondenser extends TileEntity implements ICapabilityProvider, ITickable {
	
	int condenseProgress;
	
	public int getCondenseProgress() {
		return isCondensing() ? condenseProgress : 0;
	}
	
	public void setCondenseProgress(int time) {
		condensing = true;
		condenseProgress = time;
		if (world != null && !world.isRemote) markDirty();
	}
	
	public int getCondenseProgressPerTick() {
		return 1;
	}
	
	public int getRequiredCondenseProgress() {
		return 30 * 20;
	}
	
	boolean condensing = false;
	
	public boolean isCondensing() {
		return condensing;
	}
	
	public void startCondensing() {
		if (canStartCondensing()) {
			inv.getStackInSlot(0).shrink(1);
			setCondenseProgress(0);
			world.notifyBlockUpdate(getPos(), world.getBlockState(getPos()), world.getBlockState(getPos()), 2);
		}
	}
	
	public boolean canStartCondensing() {
		ItemStack stack = inv.getStackInSlot(0);
		return !isCondensing() && !stack.isEmpty() && stack.getItem() == Items.GUNPOWDER;
	}
	
	public void finishCondensing() {
		if (inv.insertItem(1, new ItemStack(ModItems.miracleDust), false).isEmpty()) {
			condensing = false;
			markDirty();
		}
	}
	
	ItemStackHandler inv = new ItemStackHandler(2) {
		@Override
		protected void onContentsChanged(int slot) {
			TileMiracleCondenser.this.markDirty();
		}
		
		@Override
		public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
			if (
				(slot == 1 && stack.getItem() != ModItems.miracleDust) ||
				(slot == 0 && stack.getItem() != Items.GUNPOWDER)
			) {
				return stack;
			}
			return super.insertItem(slot, stack, simulate);
		}
	};
	
	/**
	 * internal use only
	 */
	public ItemStackHandler getInv() {
		return inv;
	}
	
	IItemHandler ioInv = new IOItemHandler(inv, 1);
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if (compound.hasKey("items")) {
			inv.deserializeNBT((NBTTagCompound) compound.getTag("items"));
		}
		if (compound.hasKey("progress")) {
			setCondenseProgress(compound.getInteger("progress"));
		} else {
			condensing = false;
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setTag("items", inv.serializeNBT());
		if (isCondensing()) {
			compound.setInteger("progress", getCondenseProgress());
		}
		return compound;
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = super.getUpdateTag();
		if (isCondensing()) {
			nbt.setInteger("progress", getCondenseProgress());
		}
		return nbt;
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound nbt) {
		if (nbt.hasKey("progress")) {
			setCondenseProgress(nbt.getInteger("progress"));
		} else {
			condensing = false;
		}
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(getPos(), 1, getUpdateTag());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		handleUpdateTag(pkt.getNbtCompound());
	}
	
	public boolean canInteractWith(EntityPlayer playerIn) {
		return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(ioInv);
		}
		return super.getCapability(capability, facing);
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation("tile.davidsmod:miracle_condenser.name");
	}
	
	@Override
	public void update() {
		if (isCondensing()) {
			setCondenseProgress(getCondenseProgress() + getCondenseProgressPerTick());
			if (getCondenseProgress() >= getRequiredCondenseProgress()) {
				if (!world.isRemote) {
					finishCondensing();
					if (canStartCondensing()) {
						startCondensing();
					} else {
						world.notifyBlockUpdate(getPos(), world.getBlockState(getPos()), world.getBlockState(getPos()), 2);
					}
				}
			}
		} else if (!world.isRemote) {
			startCondensing();
		}
	}
	
}
