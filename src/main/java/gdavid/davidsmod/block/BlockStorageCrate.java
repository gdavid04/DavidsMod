package gdavid.davidsmod.block;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.gui.GuiHandler;
import gdavid.davidsmod.tile.TileStorageCrate;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockStorageCrate extends Block implements ITileEntityProvider {
	
	public BlockStorageCrate() {
		super(Material.WOOD);
		setHardness(2.5f);
		setSoundType(SoundType.WOOD);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileStorageCrate();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (!(te instanceof TileStorageCrate)) return false;
			playerIn.openGui(DavidsMod.instance, GuiHandler.STORAGE_CRATE, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof TileStorageCrate) {
			TileStorageCrate t = (TileStorageCrate) te;
			IItemHandler ih = t.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			for (int i = 0; i < ih.getSlots(); i++) {
				InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), ih.getStackInSlot(i));
			}
		}
		super.breakBlock(worldIn, pos, state);
	}
	
}
