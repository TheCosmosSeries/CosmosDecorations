package com.tcn.cosmosdecorations.core.block;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.tcn.cosmoslibrary.common.block.CosmosBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockWallConnGlassColourPlayer extends CosmosBlock {
	
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
	protected static final VoxelShape X_AXIS_AABB = Block.box(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 12.0D);
	protected static final VoxelShape Z_AXIS_AABB = Block.box(4.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);
	
	public static final BooleanProperty DOWN = BooleanProperty.create("down");
	public static final BooleanProperty UP = BooleanProperty.create("up");
	public static final BooleanProperty LEFT = BooleanProperty.create("left");
	public static final BooleanProperty RIGHT = BooleanProperty.create("right");

	private final boolean collidePlayers;
	private final int blockColour;

	public BlockWallConnGlassColourPlayer(Properties properties, boolean collidePlayersIn, int blockColourIn) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.X).setValue(DOWN, false).setValue(UP, false).setValue(LEFT, false).setValue(RIGHT, false));
		this.collidePlayers = collidePlayersIn;
		this.blockColour = blockColourIn;
	}


	@Override
	public VoxelShape getShape(BlockState stateIn, BlockGetter levelIn, BlockPos posIn, CollisionContext context) {
		switch ((Direction.Axis) stateIn.getValue(AXIS)) {
		case Z:
			return Z_AXIS_AABB;
		case X:
		default:
			return X_AXIS_AABB;
		}
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction directionIn, BlockState facingState, LevelAccessor levelIn, BlockPos currentPos, BlockPos facingPos) {
		return stateIn.setValue(UP, this.canSideConnect(levelIn, currentPos, Direction.UP, null))
				.setValue(DOWN, this.canSideConnect(levelIn, currentPos, Direction.DOWN, null))
				.setValue(LEFT, this.canSideConnect(levelIn, currentPos, null, "left"))
				.setValue(RIGHT, this.canSideConnect(levelIn, currentPos, null, "right"));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(AXIS, context.getHorizontalDirection().getClockWise().getAxis());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AXIS, DOWN, UP, LEFT, RIGHT);
	}

	private boolean canSideConnect(LevelAccessor world, BlockPos pos, @Nullable Direction facing, @Nullable String leftRight) {
		final BlockState blockState = world.getBlockState(pos);
		
		if (facing != null) {
			final BlockState otherState = world.getBlockState(pos.offset(facing.getNormal()));
			
			return blockState != null && otherState != null && this.canConnect(blockState, otherState);
		} else {
			if (blockState.getOptionalValue(AXIS).isPresent()) {
				Axis axis = blockState.getValue(AXIS);
				
				if (axis.equals(Axis.Z)) {
					if (leftRight.equals("left")) {
						final BlockState dirState = world.getBlockState(pos.offset(Direction.SOUTH.getNormal()));
						return blockState != null && dirState != null && this.canConnect(blockState, dirState);
						
					} else if (leftRight.equals("right")) {
						final BlockState dirState = world.getBlockState(pos.offset(Direction.NORTH.getNormal()));
						return blockState != null && dirState != null && this.canConnect(blockState, dirState);
					}
					
				} else if (axis.equals(Axis.X)) {
					if (leftRight.equals("left")) {
						final BlockState dirState = world.getBlockState(pos.offset(Direction.EAST.getNormal()));
						return blockState != null && dirState != null && this.canConnect(blockState, dirState);
						
					} else if (leftRight.equals("right")) {
						final BlockState dirState = world.getBlockState(pos.offset(Direction.WEST.getNormal()));
						return blockState != null && dirState != null && this.canConnect(blockState, dirState);
					}
				}
			}
		}
		return false;
	}
	
	private boolean canConnect(@Nonnull BlockState orig, @Nonnull BlockState conn) {
		if (orig.getBlock() == conn.getBlock()) {
			if (orig.getOptionalValue(AXIS).isPresent() && conn.getOptionalValue(AXIS).isPresent()) {
				return orig.getValue(AXIS).equals(conn.getValue(AXIS));
			}
		}
		return false;
	}

	public int getBlockColour() {
		return this.blockColour;
	}

    @Override
    protected boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        return adjacentBlockState.is(this) ? true : super.skipRendering(state, adjacentBlockState, side);
    }

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return (((context instanceof EntityCollisionContext col && col.getEntity() instanceof Player)) == this.collidePlayers) ? state.getShape(world, pos) : Shapes.empty();
	}
}