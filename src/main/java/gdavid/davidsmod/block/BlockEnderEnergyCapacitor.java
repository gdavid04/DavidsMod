package gdavid.davidsmod.block;

import gdavid.davidsmod.tile.TileEnderEnergyCapacitor;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEnderEnergyCapacitor extends Block implements ITileEntityProvider {
	
	public BlockEnderEnergyCapacitor() {
		super(Material.ROCK);
		setHardness(5.0f);
		setSoundType(SoundType.WOOD);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEnderEnergyCapacitor();
	}
	
}
