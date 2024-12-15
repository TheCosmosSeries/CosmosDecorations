package com.tcn.cosmosdecorations.core.management;

import java.util.ArrayList;
import java.util.function.Supplier;

import com.tcn.cosmosdecorations.CosmosDecorations;
import com.tcn.cosmosdecorations.client.colour.BlockColour;
import com.tcn.cosmosdecorations.client.colour.ItemColour;
import com.tcn.cosmosdecorations.core.block.DecorPlayerCollideGlass;
import com.tcn.cosmoslibrary.common.lib.ComponentColour;
import com.tcn.cosmoslibrary.common.lib.ComponentHelper;
import com.tcn.cosmoslibrary.common.runtime.CosmosRuntimeHelper;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings({ "deprecation", "unused" })
@EventBusSubscriber(modid = CosmosDecorations.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModRegistrationManager {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CosmosDecorations.MOD_ID);
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CosmosDecorations.MOD_ID);
	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CosmosDecorations.MOD_ID);
	public static final ArrayList<Supplier<? extends ItemLike>> TAB_ITEMS = new ArrayList<>();
	
	public static final Supplier<CreativeModeTab> COSMOS_DECORATIONS_GROUP = TABS.register("cosmos_decorations", 
		() -> CreativeModeTab.builder()
			.title(ComponentHelper.style(ComponentColour.GREEN, "Cosmos Decorations")).icon(() -> { return new ItemStack(ModRegistrationManager.BLOCK_PLAYER_GLASS_WHITE); })
			.displayItems((params, output) -> TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get()))).build()
	);
	
	public static final DeferredBlock<Block> BLOCK_PLAYER_GLASS_WHITE = BLOCKS.register("block_player_glass_white", () -> new DecorPlayerCollideGlass(glassProperties(true), false, ComponentColour.WHITE.decOpaque()));
	public static final DeferredItem<Item> ITEM_PLAYER_GLASS_WHITE = addToTab(ITEMS.register("block_player_glass_white", () -> new BlockItem(BLOCK_PLAYER_GLASS_WHITE.get(), new Item.Properties())));

	public static final DeferredBlock<Block> BLOCK_PLAYER_GLASS_ORANGE = BLOCKS.register("block_player_glass_orange", () -> new DecorPlayerCollideGlass(glassProperties(true), false, ComponentColour.ORANGE.decOpaque()));
	public static final DeferredItem<Item> ITEM_PLAYER_GLASS_ORANGE = addToTab(ITEMS.register("block_player_glass_orange", () -> new BlockItem(BLOCK_PLAYER_GLASS_ORANGE.get(), new Item.Properties())));

	public static final DeferredBlock<Block> BLOCK_PLAYER_GLASS_MAGENTA = BLOCKS.register("block_player_glass_magenta", () -> new DecorPlayerCollideGlass(glassProperties(true), false, ComponentColour.MAGENTA.decOpaque()));
	public static final DeferredItem<Item> ITEM_PLAYER_GLASS_MAGENTA = addToTab(ITEMS.register("block_player_glass_magenta", () -> new BlockItem(BLOCK_PLAYER_GLASS_MAGENTA.get(), new Item.Properties())));
	
	public static final DeferredBlock<Block> BLOCK_PLAYER_GLASS_LIGHT_BLUE = BLOCKS.register("block_player_glass_light_blue", () -> new DecorPlayerCollideGlass(glassProperties(true), false, ComponentColour.LIGHT_BLUE.decOpaque()));
	public static final DeferredItem<Item> ITEM_PLAYER_GLASS_LIGHT_BLUE = addToTab(ITEMS.register("block_player_glass_light_blue", () -> new BlockItem(BLOCK_PLAYER_GLASS_LIGHT_BLUE.get(), new Item.Properties())));

	public static final DeferredBlock<Block> BLOCK_PLAYER_GLASS_YELLOW = BLOCKS.register("block_player_glass_yellow", () -> new DecorPlayerCollideGlass(glassProperties(true), false, ComponentColour.YELLOW.decOpaque()));
	public static final DeferredItem<Item> ITEM_PLAYER_GLASS_YELLOW = addToTab(ITEMS.register("block_player_glass_yellow", () -> new BlockItem(BLOCK_PLAYER_GLASS_YELLOW.get(), new Item.Properties())));

	public static final DeferredBlock<Block> BLOCK_PLAYER_GLASS_LIME = BLOCKS.register("block_player_glass_lime", () -> new DecorPlayerCollideGlass(glassProperties(true), false, ComponentColour.LIME.decOpaque()));
	public static final DeferredItem<Item> ITEM_PLAYER_GLASS_LIME = addToTab(ITEMS.register("block_player_glass_lime", () -> new BlockItem(BLOCK_PLAYER_GLASS_LIME.get(), new Item.Properties())));

	public static final DeferredBlock<Block> BLOCK_PLAYER_GLASS_PINK = BLOCKS.register("block_player_glass_pink", () -> new DecorPlayerCollideGlass(glassProperties(true), false, ComponentColour.PINK.decOpaque()));
	public static final DeferredItem<Item> ITEM_PLAYER_GLASS_PINK = addToTab(ITEMS.register("block_player_glass_pink", () -> new BlockItem(BLOCK_PLAYER_GLASS_PINK.get(), new Item.Properties())));
	
	public static final DeferredBlock<Block> BLOCK_PLAYER_GLASS_GRAY = BLOCKS.register("block_player_glass_gray", () -> new DecorPlayerCollideGlass(glassProperties(true), false, ComponentColour.GRAY.decOpaque()));
	public static final DeferredItem<Item> ITEM_PLAYER_GLASS_GRAY = addToTab(ITEMS.register("block_player_glass_gray", () -> new BlockItem(BLOCK_PLAYER_GLASS_GRAY.get(), new Item.Properties())));
	
	public static final DeferredBlock<Block> BLOCK_PLAYER_GLASS_LIGHT_GRAY = BLOCKS.register("block_player_glass_light_gray", () -> new DecorPlayerCollideGlass(glassProperties(true), false, ComponentColour.LIGHT_GRAY.decOpaque()));
	public static final DeferredItem<Item> ITEM_PLAYER_GLASS_LIGHT_GRAY = addToTab(ITEMS.register("block_player_glass_light_gray", () -> new BlockItem(BLOCK_PLAYER_GLASS_LIGHT_GRAY.get(), new Item.Properties())));
	
	public static final DeferredBlock<Block> BLOCK_PLAYER_GLASS_CYAN = BLOCKS.register("block_player_glass_cyan", () -> new DecorPlayerCollideGlass(glassProperties(true), false, ComponentColour.CYAN.decOpaque()));
	public static final DeferredItem<Item> ITEM_PLAYER_GLASS_CYAN = addToTab(ITEMS.register("block_player_glass_cyan", () -> new BlockItem(BLOCK_PLAYER_GLASS_CYAN.get(), new Item.Properties())));
	
	public static final DeferredBlock<Block> BLOCK_PLAYER_GLASS_PURPLE = BLOCKS.register("block_player_glass_purple", () -> new DecorPlayerCollideGlass(glassProperties(true), false, ComponentColour.PURPLE.decOpaque()));
	public static final DeferredItem<Item> ITEM_PLAYER_GLASS_PURPLE = addToTab(ITEMS.register("block_player_glass_purple", () -> new BlockItem(BLOCK_PLAYER_GLASS_PURPLE.get(), new Item.Properties())));
	
	public static final DeferredBlock<Block> BLOCK_PLAYER_GLASS_BLUE = BLOCKS.register("block_player_glass_blue", () -> new DecorPlayerCollideGlass(glassProperties(true), false, ComponentColour.BLUE.decOpaque()));
	public static final DeferredItem<Item> ITEM_PLAYER_GLASS_BLUE = addToTab(ITEMS.register("block_player_glass_blue", () -> new BlockItem(BLOCK_PLAYER_GLASS_BLUE.get(), new Item.Properties())));
	
	public static final DeferredBlock<Block> BLOCK_PLAYER_GLASS_BROWN = BLOCKS.register("block_player_glass_brown", () -> new DecorPlayerCollideGlass(glassProperties(true), false, ComponentColour.BROWN.decOpaque()));
	public static final DeferredItem<Item> ITEM_PLAYER_GLASS_BROWN = addToTab(ITEMS.register("block_player_glass_brown", () -> new BlockItem(BLOCK_PLAYER_GLASS_BROWN.get(), new Item.Properties())));
	
	public static final DeferredBlock<Block> BLOCK_PLAYER_GLASS_GREEN = BLOCKS.register("block_player_glass_green", () -> new DecorPlayerCollideGlass(glassProperties(true), false, ComponentColour.GREEN.decOpaque()));
	public static final DeferredItem<Item> ITEM_PLAYER_GLASS_GREEN = addToTab(ITEMS.register("block_player_glass_green", () -> new BlockItem(BLOCK_PLAYER_GLASS_GREEN.get(), new Item.Properties())));
	
	public static final DeferredBlock<Block> BLOCK_PLAYER_GLASS_RED = BLOCKS.register("block_player_glass_red", () -> new DecorPlayerCollideGlass(glassProperties(true), false, ComponentColour.RED.decOpaque()));
	public static final DeferredItem<Item> ITEM_PLAYER_GLASS_RED = addToTab(ITEMS.register("block_player_glass_red", () -> new BlockItem(BLOCK_PLAYER_GLASS_RED.get(), new Item.Properties())));
	
	public static final DeferredBlock<Block> BLOCK_PLAYER_GLASS_BLACK = BLOCKS.register("block_player_glass_black", () -> new DecorPlayerCollideGlass(glassProperties(true), false, ComponentColour.BLACK.decOpaque()));
	public static final DeferredItem<Item> ITEM_PLAYER_GLASS_BLACK = addToTab(ITEMS.register("block_player_glass_black", () -> new BlockItem(BLOCK_PLAYER_GLASS_BLACK.get(), new Item.Properties())));
	
	public static final DeferredBlock<Block> BLOCK_STONE_WALL = BLOCKS.register("block_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofLegacyCopy(Blocks.STONE).forceSolidOn().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final DeferredItem<Item> ITEM_STONE_WALL = addToTab(ITEMS.register("block_stone_wall", ()-> new BlockItem(BLOCK_STONE_WALL.get(), new Item.Properties())));

	public static final DeferredBlock<Block> BLOCK_DIRT_STAIRS = BLOCKS.register("block_dirt_stairs", () -> new StairBlock(Blocks.DIRT.defaultBlockState(), Block.Properties.of().mapColor(MapColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
	public static final DeferredItem<Item> ITEM_DIRT_STAIRS = addToTab(ITEMS.register("block_dirt_stairs", ()-> new BlockItem(BLOCK_DIRT_STAIRS.get(), new Item.Properties())));
	public static final DeferredBlock<Block> BLOCK_DIRT_SLAB = BLOCKS.register("block_dirt_slab", () -> new SlabBlock(Block.Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.GRAVEL)));
	public static final DeferredItem<Item> ITEM_DIRT_SLAB = addToTab(ITEMS.register("block_dirt_slab", ()-> new BlockItem(BLOCK_DIRT_SLAB.get(), new Item.Properties())));
	public static final DeferredBlock<Block> BLOCK_DIRT_WALL = BLOCKS.register("block_dirt_wall", () -> new WallBlock(BlockBehaviour.Properties.ofLegacyCopy(Blocks.DIRT).forceSolidOn().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.GRAVEL)));
	public static final DeferredItem<Item> ITEM_DIRT_WALL = addToTab(ITEMS.register("block_dirt_wall", ()-> new BlockItem(BLOCK_DIRT_WALL.get(), new Item.Properties())));

	public static final DeferredBlock<Block> BLOCK_ENDSTONE_STAIRS = BLOCKS.register("block_endstone_stairs", () -> new StairBlock(Blocks.END_STONE.defaultBlockState(), Block.Properties.of().mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(3.0F, 9.0F)));
	public static final DeferredItem<Item> ITEM_ENDSTONE_STAIRS = addToTab(ITEMS.register("block_endstone_stairs", ()-> new BlockItem(BLOCK_ENDSTONE_STAIRS.get(), new Item.Properties())));
	public static final DeferredBlock<Block> BLOCK_ENDSTONE_SLAB = BLOCKS.register("block_endstone_slab", () -> new SlabBlock(Block.Properties.of().mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(3.0F, 9.0F)));
	public static final DeferredItem<Item> ITEM_ENDSTONE_SLAB = addToTab(ITEMS.register("block_endstone_slab", ()-> new BlockItem(BLOCK_ENDSTONE_SLAB.get(), new Item.Properties())));
	public static final DeferredBlock<Block> BLOCK_ENDSTONE_WALL = BLOCKS.register("block_endstone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofLegacyCopy(Blocks.END_STONE).mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(3.0F, 9.0F)));
	public static final DeferredItem<Item> ITEM_ENDSTONE_WALL = addToTab(ITEMS.register("block_endstone_wall", ()-> new BlockItem(BLOCK_ENDSTONE_WALL.get(), new Item.Properties())));

	public static final DeferredBlock<Block> BLOCK_NETHERRACK_STAIRS = BLOCKS.register("block_netherrack_stairs", () -> new StairBlock(Blocks.NETHERRACK.defaultBlockState(), Block.Properties.of().mapColor(MapColor.NETHER).requiresCorrectToolForDrops().strength(0.4F).sound(SoundType.NETHERRACK)));
	public static final DeferredItem<Item> ITEM_NETHERRACK_STAIRS = addToTab(ITEMS.register("block_netherrack_stairs", ()-> new BlockItem(BLOCK_NETHERRACK_STAIRS.get(), new Item.Properties())));
	public static final DeferredBlock<Block> BLOCK_NETHERRACK_SLAB = BLOCKS.register("block_netherrack_slab", () -> new SlabBlock(Block.Properties.of().mapColor(MapColor.NETHER).requiresCorrectToolForDrops().strength(0.4F).sound(SoundType.NETHERRACK)));
	public static final DeferredItem<Item> ITEM_NETHERRACK_SLAB = addToTab(ITEMS.register("block_netherrack_slab", ()-> new BlockItem(BLOCK_NETHERRACK_SLAB.get(), new Item.Properties())));
	public static final DeferredBlock<Block> BLOCK_NETHERRACK_WALL = BLOCKS.register("block_netherrack_wall", () -> new WallBlock(BlockBehaviour.Properties.ofLegacyCopy(Blocks.NETHERRACK).mapColor(MapColor.NETHER).requiresCorrectToolForDrops().strength(0.4F).sound(SoundType.NETHERRACK)));
	public static final DeferredItem<Item> ITEM_NETHERRACK_WALL = addToTab(ITEMS.register("block_netherrack_wall", ()-> new BlockItem(BLOCK_NETHERRACK_WALL.get(), new Item.Properties())));

	public static void register(IEventBus bus) {
		ITEMS.register(bus);
		BLOCKS.register(bus);
		TABS.register(bus);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onRegisterColourHandlersEventBlock(RegisterColorHandlersEvent.Block event) {
		CosmosRuntimeHelper.registerBlockColours(event, new BlockColour(), 
			BLOCK_PLAYER_GLASS_WHITE.get(),
			BLOCK_PLAYER_GLASS_ORANGE.get(),
			BLOCK_PLAYER_GLASS_MAGENTA.get(),
			BLOCK_PLAYER_GLASS_LIGHT_BLUE.get(),
			BLOCK_PLAYER_GLASS_YELLOW.get(),
			BLOCK_PLAYER_GLASS_LIME.get(),
			BLOCK_PLAYER_GLASS_PINK.get(),
			BLOCK_PLAYER_GLASS_GRAY.get(),
			BLOCK_PLAYER_GLASS_LIGHT_GRAY.get(),
			BLOCK_PLAYER_GLASS_CYAN.get(),
			BLOCK_PLAYER_GLASS_PURPLE.get(),
			BLOCK_PLAYER_GLASS_BLUE.get(),
			BLOCK_PLAYER_GLASS_BROWN.get(),
			BLOCK_PLAYER_GLASS_GREEN.get(),
			BLOCK_PLAYER_GLASS_RED.get(),
			BLOCK_PLAYER_GLASS_BLACK.get()
		);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onRegisterColourHandlersEventItem(RegisterColorHandlersEvent.Item event) {
		CosmosRuntimeHelper.registerItemColours(event, new ItemColour(), 
				BLOCK_PLAYER_GLASS_WHITE.get(),
				BLOCK_PLAYER_GLASS_ORANGE.get(),
				BLOCK_PLAYER_GLASS_MAGENTA.get(),
				BLOCK_PLAYER_GLASS_LIGHT_BLUE.get(),
				BLOCK_PLAYER_GLASS_YELLOW.get(),
				BLOCK_PLAYER_GLASS_LIME.get(),
				BLOCK_PLAYER_GLASS_PINK.get(),
				BLOCK_PLAYER_GLASS_GRAY.get(),
				BLOCK_PLAYER_GLASS_LIGHT_GRAY.get(),
				BLOCK_PLAYER_GLASS_CYAN.get(),
				BLOCK_PLAYER_GLASS_PURPLE.get(),
				BLOCK_PLAYER_GLASS_BLUE.get(),
				BLOCK_PLAYER_GLASS_BROWN.get(),
				BLOCK_PLAYER_GLASS_GREEN.get(),
				BLOCK_PLAYER_GLASS_RED.get(),
				BLOCK_PLAYER_GLASS_BLACK.get()
		);
	}
	
	@OnlyIn(Dist.CLIENT)
	public static void registerClient(ModLoadingContext context) { }
	
	@OnlyIn(Dist.CLIENT)
	public static void onFMLClientSetup(FMLClientSetupEvent event) {
		CosmosRuntimeHelper.setRenderLayers(RenderType.translucent(), 
				BLOCK_PLAYER_GLASS_WHITE.get(),
				BLOCK_PLAYER_GLASS_ORANGE.get(),
				BLOCK_PLAYER_GLASS_MAGENTA.get(),
				BLOCK_PLAYER_GLASS_LIGHT_BLUE.get(),
				BLOCK_PLAYER_GLASS_YELLOW.get(),
				BLOCK_PLAYER_GLASS_LIME.get(),
				BLOCK_PLAYER_GLASS_PINK.get(),
				BLOCK_PLAYER_GLASS_GRAY.get(),
				BLOCK_PLAYER_GLASS_LIGHT_GRAY.get(),
				BLOCK_PLAYER_GLASS_CYAN.get(),
				BLOCK_PLAYER_GLASS_PURPLE.get(),
				BLOCK_PLAYER_GLASS_BLUE.get(),
				BLOCK_PLAYER_GLASS_BROWN.get(),
				BLOCK_PLAYER_GLASS_GREEN.get(),
				BLOCK_PLAYER_GLASS_RED.get(),
				BLOCK_PLAYER_GLASS_BLACK.get()
		);
	}
	
	private static BlockBehaviour.Properties glassProperties(boolean lightEmit) {
		BlockBehaviour.Properties prop = Block.Properties.of()
				.isValidSpawn(ModRegistrationManager::neverAllowSpawn)
				.isRedstoneConductor(ModRegistrationManager::isntSolid)
				.isSuffocating(ModRegistrationManager::isntSolid)
				.isViewBlocking(ModRegistrationManager::isntSolid)
				.noOcclusion().sound(SoundType.GLASS).strength(0.3F);
		
		if (lightEmit) {
			return prop.lightLevel((blockState) -> { return 15; });
		}
		
		return prop;
	}

	private static Boolean neverAllowSpawn(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> entity) {
		return Boolean.valueOf(false);
	}

	private static boolean isntSolid(BlockState state, BlockGetter reader, BlockPos pos) {
		return false;
	}

    private static <T extends Item> DeferredItem<T> addToTab(DeferredItem<T> itemLike) {
        TAB_ITEMS.add(itemLike);
        return itemLike;
    }

    private static <A extends Block> DeferredBlock<A> addToTabA(DeferredBlock<A> itemLike) {
        TAB_ITEMS.add(itemLike);
        return itemLike;
    }
}