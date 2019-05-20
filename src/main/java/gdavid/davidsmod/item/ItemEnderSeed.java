package gdavid.davidsmod.item;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.block.ModBlocks;
import gdavid.davidsmod.tab.TabDavidsmod;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemEnderSeed extends ItemSeeds {
	
	public ItemEnderSeed() {
		super(ModBlocks.enderCrop, Blocks.END_STONE);
		setUnlocalizedName(DavidsMod.modID + ":" + "ender_seed");
		setRegistryName("ender_seed");
		setCreativeTab(TabDavidsmod.get());
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemstack = player.getHeldItem(hand);
		IBlockState state = worldIn.getBlockState(pos);
		if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && state.getBlock() == Blocks.END_STONE && worldIn.isAirBlock(pos.up())) {
			if (!worldIn.isRemote) {
				worldIn.setBlockState(pos.up(), ModBlocks.enderCrop.getDefaultState());
				if (player instanceof EntityPlayerMP) {
					CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos.up(), itemstack);
				}
				itemstack.shrink(1);
			}
			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}
	
}
