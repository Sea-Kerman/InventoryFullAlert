package net.seakerman.inventoryfullalert;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.seakerman.inventoryfullalert.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public class InventoryFullAlert implements ClientModInitializer {
	public static ModConfig config;
	public static final Logger LOGGER = LoggerFactory.getLogger("InventoryFullAlert");  // I'm using VS Code, so maybe this is the reason
																						// why regular System.out.println() don't work,
	@Override																			// so better use Logger
	public void onInitializeClient() {
		AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

		LOGGER.info("Initialized successfully.");
	}
}