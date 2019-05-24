package gdavid.davidsmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMechanicalFangs extends Block {
	
	public static final PropertyBool TRIGGERED = PropertyBool.create("triggered");
	
	static final AxisAlignedBB CHK_AABB = new AxisAlignedBB(0, 0, 0, 1, 1, 1);
	
	public BlockMechanicalFangs() {
		super(Material.ROCK, MapColor.STONE);
		setHardness(3.5F);
		setDefaultState(blockState.getBaseState().withProperty(TRIGGERED, false));
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!worldIn.isRemote) {
			boolean powered = worldIn.isBlockPowered(pos);
			if (!state.getValue(TRIGGERED) && powered && worldIn.getEntitiesWithinAABB(EntityEvokerFangs.class, CHK_AABB.offset(pos).offset(0, 1, 0)).isEmpty()) {
				worldIn.spawnEntity(new EntityEvokerFangs(worldIn, pos.getX() + 0.5F, pos.getY() + 1.0F, pos.getZ() + 0.5F, 0, 10, null));
			}
			worldIn.setBlockState(pos, state.withProperty(TRIGGERED, powered), 4);
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TRIGGERED, meta != 0);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return (state.getValue(TRIGGERED) ? 1 : 0);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {TRIGGERED});
	}
	
	@Override
	public boolean canProvidePower(IBlockState state) {
		return true;
	}
	
}
