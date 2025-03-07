package com.sigmundgranaas.forgero.fabric.mixins;

import com.google.common.collect.ImmutableList;
import com.sigmundgranaas.forgero.core.ForgeroStateRegistry;
import com.sigmundgranaas.forgero.core.state.MaterialBased;
import com.sigmundgranaas.forgero.core.state.State;
import com.sigmundgranaas.forgero.core.type.Type;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;


@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceWoodPartsFuelMixin {
	@Inject(method = "createFuelTimeMap", at = @At("RETURN"))
	private static void interceptReturn(CallbackInfoReturnable<Map<Item, Integer>> info) {
		var map = info.getReturnValue();
		if (ForgeroStateRegistry.TREE != null) {
			ForgeroStateRegistry.TREE.find(Type.PART)
					.map(node -> node.getResources(State.class))
					.orElse(ImmutableList.<State>builder().build())
					.stream()
					.filter(state -> state instanceof MaterialBased based && based.baseMaterial().test(Type.WOOD))
					.map(state -> new Identifier(state.identifier()))
					.filter(Registry.ITEM::containsId)
					.map(Registry.ITEM::get)
					.forEach(item -> map.put(item, 300));
		}
	}
}
