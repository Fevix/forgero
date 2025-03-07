package com.sigmundgranaas.forgero.fabric.tag;

import static com.sigmundgranaas.forgero.core.Forgero.LOGGER;
import static com.sigmundgranaas.forgero.fabric.resources.ARRPGenerator.RESOURCE_PACK;

import net.devtech.arrp.json.tags.JTag;

import net.fabricmc.loader.api.SemanticVersion;

import net.minecraft.util.Identifier;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.VersionParsingException;
import net.fabricmc.loader.impl.util.version.SemanticVersionImpl;

public class BlockTagCompatRegistration {
	// Namespaces
	public static final String MINECRAFT_NAMESPACE = "minecraft";
	public static final String COMMON_NAMESPACE = "c";
	public static final String BIOMES_YOULL_GO_NAMESPACE = "byg";
	public static final String REGIONS_UNEXPLORED_NAMESPACE = "regions-unexplored";
	// Blocks
	public static final String CHERRY_PLANKS = "cherry_planks";
	public static final String BAMBOO_PLANKS = "bamboo_planks";

	public static void register() {
		registerCherryPlanksTag();
		registerBambooPlanksTag();
	}

	private static int checkIfMinecraftVersionIsEqualOrNewer(String version) {
		var minecraftModContainer = FabricLoader.getInstance().getModContainer(MINECRAFT_NAMESPACE);
		if (minecraftModContainer.isEmpty()) {
			LOGGER.error("Couldn't parse Minecraft version (Minecraft mod container is empty).");
			return -1;
		}

		var minecraftVersion = minecraftModContainer.get().getMetadata().getVersion();
		try {
			return minecraftVersion.compareTo(SemanticVersion.parse(version));
		} catch (VersionParsingException e) {
			LOGGER.error("Couldn't parse Minecraft version: {}. See stacktrace below:\n{}", minecraftVersion, e);
			return -1;
		}
	}

	private static void registerCherryPlanksTag() {
		var cherryPlanksTag = new JTag();

		if (checkIfMinecraftVersionIsEqualOrNewer("1.20") >= 0) {
			cherryPlanksTag.add(new Identifier(MINECRAFT_NAMESPACE, CHERRY_PLANKS));
		}

		if (FabricLoader.getInstance().isModLoaded(BIOMES_YOULL_GO_NAMESPACE)) {
			cherryPlanksTag.add(new Identifier(BIOMES_YOULL_GO_NAMESPACE, CHERRY_PLANKS));
		}

		if (FabricLoader.getInstance().isModLoaded(REGIONS_UNEXPLORED_NAMESPACE)) {
			cherryPlanksTag.add(new Identifier(REGIONS_UNEXPLORED_NAMESPACE, CHERRY_PLANKS));
		}

		RESOURCE_PACK.addTag(new Identifier(COMMON_NAMESPACE, CHERRY_PLANKS), cherryPlanksTag);
	}

	private static void registerBambooPlanksTag() {
		var bambooPlanksTag = new JTag();

		if (checkIfMinecraftVersionIsEqualOrNewer("1.20") >= 0) {
			bambooPlanksTag.add(new Identifier(MINECRAFT_NAMESPACE, BAMBOO_PLANKS));
		}

		RESOURCE_PACK.addTag(new Identifier(COMMON_NAMESPACE, CHERRY_PLANKS), bambooPlanksTag);
	}
}
