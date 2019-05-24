package gdavid.davidsmod.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemRedstoneMeter extends Item {
	
	public ItemRedstoneMeter() {
		setMaxDamage(64);
		setMaxStackSize(1);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			CooldownTracker tracker = player.getCooldownTracker();
			if (!tracker.hasCooldown(this)) {
				int power = worldIn.getRedstonePower(pos, facing);
				if (power > 0) {
					player.sendMessage(new TextComponentTranslation(getUnlocalizedName() + ".report", power));
				} else {
					player.sendMessage(new TextComponentTranslation(getUnlocalizedName() + ".nopower"));
				}
				player.getHeldItem(hand).damageItem(1, player);
				tracker.setCooldown(this, 2 * 20);
			}
		}
		return EnumActionResult.SUCCESS;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!worldIn.isRemote) {
			if (worldIn.getBlockState(entityIn.getPosition().add(0, 1, 0)).getBlock() == Blocks.WATER ||
					worldIn.getBlockState(entityIn.getPosition().add(0, 1, 0)).getBlock() == Blocks.FLOWING_WATER) {
				if (entityIn instanceof EntityPlayer) {
					CooldownTracker tracker = ((EntityPlayer) entityIn).getCooldownTracker();
					tracker.setCooldown(this, 5 * 20);
				}
			}
		}
	}
	
}
