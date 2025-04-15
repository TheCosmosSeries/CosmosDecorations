package com.tcn.cosmosdecorations.core.block;

import com.tcn.cosmoslibrary.common.block.CosmosBlockConnectedGlass;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class BlockConnGlassColour extends CosmosBlockConnectedGlass {

	private final int blockColour;
	
	public BlockConnGlassColour(Properties propertiesIn, int blockColourIn) {
		super(propertiesIn);
		
		this.blockColour = blockColourIn;
	}
	
	public int getBlockColour() {
		return this.blockColour;
	}

    @Override
    protected boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        return adjacentBlockState.is(this) ? true : super.skipRendering(state, adjacentBlockState, side);
    }
}
