package com.tcn.cosmosdecorations.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DecorPlayerCollideGlass extends DecorConnectedGlassColour {

	private final boolean collidePlayers;
	
	public DecorPlayerCollideGlass(Properties propertiesIn, boolean collidePlayersIn, int blockColourIn) {
		super(propertiesIn, blockColourIn);
		
		this.collidePlayers = collidePlayersIn;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return (((context instanceof EntityCollisionContext && ((EntityCollisionContext)context).getEntity() instanceof Player)) == this.collidePlayers) ? state.getShape(world, pos) : Shapes.empty();
	}
}