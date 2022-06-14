package net.seakerman.inventoryfullalert;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.seakerman.inventoryfullalert.config.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.seakerman.inventoryfullalert.hud.InventoryAlertHud;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InventoryFullAlert implements ClientModInitializer {
	public static Config inventoryAlertConfigData;
	//public static InventoryAlertHud hudInfo;

	@Override
	public void onInitializeClient() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.

		System.out.println("InventoryFullAlert started.");
		ifa$loadConfig();
		//this.hudInfo = new InventoryAlertHud();

	}


	// config code based on bedrockify & actually unbreaking fabric config code
	// https://github.com/juancarloscp52/BedrockIfy/blob/1.17.x/src/main/java/me/juancarloscp52/bedrockify/Bedrockify.java
	// https://github.com/wutdahack/ActuallyUnbreakingFabric/blob/1.18.1/src/main/java/wutdahack/actuallyunbreaking/ActuallyUnbreaking.java
	public static void ifa$loadConfig() {
		File config = new File(FabricLoader.getInstance().getConfigDir().toFile(), "inventoryfullalert.json");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if (config.exists()) {
			try {
				FileReader fileReader = new FileReader(config);
				inventoryAlertConfigData = gson.fromJson(fileReader, Config.class);
				fileReader.close();
				saveConfig();
			} catch (IOException e) {
				System.out.println("Config could not be loaded, using defaults");
			}
		} else {
			inventoryAlertConfigData = new Config();
			saveConfig();
		}
	}
	public static void saveConfig() {
		File config = new File(FabricLoader.getInstance().getConfigDir().toFile(), "inventoryfullalert.json");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if (!config.getParentFile().exists()) {
			//noinspection ResultOfMethodCallIgnored
			config.getParentFile().mkdir();
		}
		try {
			FileWriter fileWriter = new FileWriter(config);
			fileWriter.write(gson.toJson(inventoryAlertConfigData));
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Config file could not be saved");
		}
	}

}