package com.tcn.cosmosdecorations.core.block;

import com.tcn.cosmoslibrary.common.block.CosmosBlockConnectedGlass;

public class DecorConnectedGlassColour extends CosmosBlockConnectedGlass {

	private final int blockColour;
	
	public DecorConnectedGlassColour(Properties propertiesIn, int blockColourIn) {
		super(propertiesIn);
		
		this.blockColour = blockColourIn;
	}
	
	public int getBlockColour() {
		return this.blockColour;
	}
}
