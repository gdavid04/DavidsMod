package gdavid.davidsmod.block;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.tab.TabDavidsmod;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockChargedRock extends Block {
	
	public BlockChargedRock() {
		super(Material.ROCK, MapColor.CYAN);
		setUnlocalizedName(DavidsMod.modID + ":" + "charged_rock");
		setRegistryName("charged_rock");
		setCreativeTab(TabDavidsmod.get());
		setHardness(5.0F);
		setResistance(10.0F);
	}
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		entityIn.attackEntityFrom(DamageSource.GENERIC, 1.0f);
		super.onEntityWalk(worldIn, pos, entityIn);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
		return 15728880;	// glow in the dark
	}
	
}