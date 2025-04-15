package com.tcn.cosmosdecorations.client.colour;

import com.tcn.cosmosdecorations.core.block.BlockConnGlassColour;
import com.tcn.cosmosdecorations.core.block.BlockWallConnGlassColourPlayer;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BlockColour implements BlockColor {

	@Override
	public int getColor(BlockState stateIn, BlockAndTintGetter displayReaderIn, BlockPos posIn, int colourIn) {
		Block block = stateIn.getBlock();
		
		if (block instanceof BlockConnGlassColour blockGlass) {
			return blockGlass.getBlockColour();
		}

		if (block instanceof BlockWallConnGlassColourPlayer blockGlass) {
			return blockGlass.getBlockColour();
		}
		
		return 0;
	}	
}