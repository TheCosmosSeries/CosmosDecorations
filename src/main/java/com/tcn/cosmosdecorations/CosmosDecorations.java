package com.tcn.cosmosdecorations;

import com.tcn.cosmosdecorations.core.management.DecorRegistrationManager;
import com.tcn.cosmoslibrary.runtime.common.CosmosConsoleManager;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(CosmosDecorations.MOD_ID)
public class CosmosDecorations {

	//This must NEVER EVER CHANGE!
	public static final String MOD_ID = "cosmosdecorations";
		
	public static CosmosConsoleManager CONSOLE = new CosmosConsoleManager(CosmosDecorations.MOD_ID, true, true);
	
	public CosmosDecorations(IEventBus bus) {
		DecorRegistrationManager.register(bus);
		
		bus.addListener(this::onFMLCommonSetup);
		bus.addListener(this::onFMLClientSetup);
	}
	
	public void onFMLCommonSetup(final FMLCommonSetupEvent event) {

		CONSOLE.startup("CosmosDecorations Common Setup complete.");
	}
	
	public void onFMLClientSetup(final FMLClientSetupEvent event) {
		final ModLoadingContext context = ModLoadingContext.get();

		DecorRegistrationManager.registerClient(context);
		DecorRegistrationManager.onFMLClientSetup(event);
		
		CONSOLE.startup("CosmosDecorations Client Setup complete.");
	}
}