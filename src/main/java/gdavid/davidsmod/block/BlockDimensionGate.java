package gdavid.davidsmod.block;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDimensionGate extends Block {
	
	static final AxisAlignedBB AABB_PORTAL = new AxisAlignedBB(0.0625D, 0.0625D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);
    static final AxisAlignedBB AABB_BOTTOM = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
    static final AxisAlignedBB AABB_N = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0625D);
    static final AxisAlignedBB AABB_S = new AxisAlignedBB(0.0D, 0.0D, 0.9375D, 1.0D, 1.0D, 1.0D);
    static final AxisAlignedBB AABB_E = new AxisAlignedBB(0.9375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    static final AxisAlignedBB AABB_W = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0625D, 1.0D, 1.0D);
	
	public final int dim;
	
	public BlockDimensionGate(int dim, Material mat, MapColor col) {
		super(mat, col);
		this.dim = dim;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BOTTOM);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_N);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_S);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_W);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_E);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!worldIn.isRemote &&
			!entityIn.isRiding() &&
			!entityIn.isBeingRidden() &&
			entityIn.isNonBoss() &&
			entityIn.getEntityBoundingBox().intersects(AABB_PORTAL.offset(pos))
		) {
			entityIn.changeDimension(dim);
		}
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		if (face == EnumFacing.UP) {
			return BlockFaceShape.BOWL;
		} else {
			return BlockFaceShape.SOLID;
		}
	}
	
}
