package gdavid.davidsmod.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemInfinityPickaxe extends Item {
	
	public ItemInfinityPickaxe() {
		setMaxStackSize(1);
		setMaxDamage(1024);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			CooldownTracker tracker = player.getCooldownTracker();
			if (!tracker.hasCooldown(this)) {
				IBlockState state = worldIn.getBlockState(pos);
				if (canBreakBlock(worldIn, pos, state, player.getHeldItem(hand))) {
					boolean normalDrop = dropsNormally(worldIn, pos, state, player.getHeldItem(hand));
					worldIn.destroyBlock(pos, normalDrop);
					if (!normalDrop) {
						addSpecialDrop(worldIn, pos, state, player.getHeldItem(hand));
					}
					player.getHeldItem(hand).damageItem(1, player);
					tracker.setCooldown(this, 5);
				}
			}
		}
		return EnumActionResult.SUCCESS;
	}
	
	public boolean canBreakBlock(World worldIn, BlockPos pos, IBlockState state, ItemStack stack) {
		Block block = state.getBlock();
		return (
			state.getBlockHardness(worldIn, pos) != -1 ||
			block == Blocks.BEDROCK ||
			(block == Blocks.END_PORTAL_FRAME && !state.getValue(BlockEndPortalFrame.EYE))
		);
	}
	
	public boolean dropsNormally(World worldIn, BlockPos pos, IBlockState state, ItemStack stack) {
		Block block = state.getBlock();
		return
			block != Blocks.BEDROCK &&
			block != Blocks.END_PORTAL_FRAME;
	}
	
	public void addSpecialDrop(World worldIn, BlockPos pos, IBlockState state, ItemStack stack) {
		Block block = state.getBlock();
		if (block == Blocks.BEDROCK) {
			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.bedrockDust, 3)));
		} else if (block == Blocks.END_PORTAL_FRAME) {
			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemBlock.getItemFromBlock(Blocks.END_PORTAL_FRAME))));
		}
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return !ItemStack.areItemsEqualIgnoreDurability(oldStack, newStack);
	}
	
}
