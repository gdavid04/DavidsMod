package gdavid.davidsmod.item;

import java.util.ArrayList;

import gdavid.davidsmod.api.BlockTransformation;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlockTransformer extends Item {
	
	public final ArrayList<BlockTransformation> handlers;
	
	public ItemBlockTransformer(ArrayList<BlockTransformation> handlers) {
		this.handlers = handlers;
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, new BehaviorDefaultDispenseItem() {
			@Override
			protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
				IPosition ipos = BlockDispenser.getDispensePosition(source);
				BlockPos pos = new BlockPos(ipos.getX(), ipos.getY(), ipos.getZ());
				World world = source.getWorld();
				if (!world.isRemote) {
					BlockTransformation handler = firstHandler(world, pos);
					if (handler != null && handler.Apply(world, pos)) {
						if (isDamageable()) {
							stack.damageItem(1, null);
						} else {
							stack.shrink(1);
						}
					}
				}
				return stack;
			}
		});
	}
	
	// TODO advanced version with wheel hud for multiple options (storing selected in nbt for each blockstate)
	
	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		BlockTransformation handler = firstHandler(world, pos);
		if (handler != null) {
			if (!world.isRemote && handler.Apply(world, pos) && !player.isCreative()) {
				if (isDamageable()) {
					player.getHeldItem(hand).damageItem(1, player);
				} else {
					player.getHeldItem(hand).shrink(1);
				}
			}
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
	
	public BlockTransformation firstHandler(World world, BlockPos pos) {
		for (BlockTransformation handler : handlers) {
			if (handler.Match(world, pos)) {
				return handler;
			}
		}
		return null;
	}
	
}
