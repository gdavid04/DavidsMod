package gdavid.davidsmod.block;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.tab.TabDavidsmod;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockChargedIron extends Block {
	
	public BlockChargedIron() {
		super(Material.IRON, MapColor.CYAN);
		setUnlocalizedName(DavidsMod.modID + ":" + "charged_iron_block");
		setRegistryName("charged_iron_block");
		setCreativeTab(TabDavidsmod.get());
		setHardness(5.0F);
		setResistance(10.0F);
		setDefaultSlipperiness(1.0989011f);
	}
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (0.5 < entityIn.motionX) {
			entityIn.motionX = 0.5;
		}
		if (0.5 < entityIn.motionZ) {
			entityIn.motionZ = 0.5;
		}
		if (entityIn.motionX < -0.5) {
			entityIn.motionX = -0.5;
		}
		if (entityIn.motionZ < -0.5) {
			entityIn.motionZ = -0.5;
		}
		super.onEntityWalk(worldIn, pos, entityIn);
	}
	
}