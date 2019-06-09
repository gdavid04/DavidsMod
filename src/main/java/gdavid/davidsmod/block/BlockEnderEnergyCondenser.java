package gdavid.davidsmod.block;

import java.util.Random;

import gdavid.davidsmod.tile.TileEnderEnergyCondenser;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEnderEnergyCondenser extends Block implements ITileEntityProvider {
	
	public BlockEnderEnergyCondenser() {
		super(Material.ROCK);
		setHardness(5.0f);
		setSoundType(SoundType.WOOD);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEnderEnergyCondenser();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te != null && te instanceof TileEnderEnergyCondenser && ((TileEnderEnergyCondenser) te).isCharging()) {
			worldIn.spawnParticle(EnumParticleTypes.PORTAL, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, 0, -0.2, 0);
		}
	}
	
}
