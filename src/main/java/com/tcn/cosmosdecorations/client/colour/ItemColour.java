package com.tcn.cosmosdecorations.client.colour;

import com.tcn.cosmosdecorations.core.block.DecorConnectedGlassColour;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class ItemColour implements ItemColor {

	@Override
	public int getColor(ItemStack stackIn, int tintIndexIn) {
		Item item = stackIn.getItem();
		
		if (item instanceof BlockItem blockItem) {
			Block block = blockItem.getBlock();
			
			if (block instanceof DecorConnectedGlassColour blockGlass) {
				return blockGlass.getBlockColour();
			}
		}
		
		return 0;
	}
}