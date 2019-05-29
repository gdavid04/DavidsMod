package gdavid.davidsmod.item;

import java.util.Map;
import java.util.function.BiFunction;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlockTransformer extends Item {
	
	public final Map<IBlockState, BiFunction<World, BlockPos, Boolean>> handlers;
	
	public ItemBlockTransformer(Map<IBlockState, BiFunction<World, BlockPos, Boolean>> handlers) {
		this.handlers = handlers;
	}
	
	// TODO dispenser behavior
	// TODO hud showing what will be the result
	// TODO wheel hud for multiple options (storing selected in nbt for each blockstate)
	
	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		BiFunction<World, BlockPos, Boolean> handler = handlers.get(world.getBlockState(pos));
		if (handler != null) {
			if (handler.apply(world, pos) && !world.isRemote && !player.isCreative()) {
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
	
}
