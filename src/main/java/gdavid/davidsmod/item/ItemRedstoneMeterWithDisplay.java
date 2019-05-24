package gdavid.davidsmod.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemRedstoneMeterWithDisplay extends Item {

	public ItemRedstoneMeterWithDisplay() {
		setMaxDamage(256);
		setMaxStackSize(1);
		this.addPropertyOverride(new ResourceLocation("display"), new IItemPropertyGetter() {

			@Override
			public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
				if (!stack.hasTagCompound()) return 0;
				return stack.getTagCompound().getInteger("value");
			}
			
		});
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			CooldownTracker tracker = player.getCooldownTracker();
			if (!tracker.hasCooldown(this)) {
				ItemStack stack = player.getHeldItem(hand);
				NBTTagCompound tag = stack.getTagCompound();
				if (!stack.hasTagCompound()) {
					tag = new NBTTagCompound();
				}
				int power = worldIn.getRedstonePower(pos, facing);
				tag.setInteger("value", power + 1);
				stack.setTagCompound(tag);
				stack.damageItem(1, player);
				tracker.setCooldown(this, 1 * 20);
			}
		}
		return EnumActionResult.SUCCESS;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!worldIn.isRemote) {
			if (worldIn.getBlockState(entityIn.getPosition().add(0, 1, 0)).getBlock() == Blocks.WATER ||
					worldIn.getBlockState(entityIn.getPosition().add(0, 1, 0)).getBlock() == Blocks.FLOWING_WATER) {
				NBTTagCompound tag = stack.getTagCompound();
				if (stack.hasTagCompound()) {
					tag.removeTag("value");
				}
				stack.setTagCompound(tag);
				if (entityIn instanceof EntityPlayer) {
					CooldownTracker tracker = ((EntityPlayer) entityIn).getCooldownTracker();
					tracker.setCooldown(this, 5 * 20);
				}
			}
		}
	}
	
}
