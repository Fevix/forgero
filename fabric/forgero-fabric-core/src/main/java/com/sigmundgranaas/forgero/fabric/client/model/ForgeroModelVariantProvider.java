package com.sigmundgranaas.forgero.fabric.client.model;

import com.sigmundgranaas.forgero.core.ForgeroStateRegistry;
import com.sigmundgranaas.forgero.core.model.ModelRegistry;
import com.sigmundgranaas.forgero.minecraft.common.client.model.CompositeModelVariant;
import com.sigmundgranaas.forgero.minecraft.common.service.StateService;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.util.ModelIdentifier;

import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelVariantProvider;

public class ForgeroModelVariantProvider implements ModelVariantProvider {
	private final CompositeModelVariant compositeModel;

	public ForgeroModelVariantProvider(ModelRegistry modelRegistry) {
		this.compositeModel = new CompositeModelVariant(modelRegistry, StateService.INSTANCE);
	}

	@Override
	public @Nullable
	UnbakedModel loadModelVariant(ModelIdentifier modelId, ModelProviderContext context) {
		if (ForgeroStateRegistry.COMPOSITES.contains(String.format("%s:%s", modelId.getNamespace(), modelId.getPath()))) {
			return compositeModel;
		}
		return null;
	}

}
