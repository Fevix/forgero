package com.sigmundgranaas.forgero.core.property.v2.attribute.attributes;

import com.sigmundgranaas.forgero.core.property.PropertyContainer;
import com.sigmundgranaas.forgero.core.property.v2.Attribute;
import com.sigmundgranaas.forgero.core.property.v2.cache.ContainerTargetPair;
import com.sigmundgranaas.forgero.core.util.match.MatchContext;
import com.sigmundgranaas.forgero.core.util.match.Matchable;

public class AttackDamage {
	public static final String KEY = "ATTACK_DAMAGE";

	public static Attribute of(PropertyContainer container) {
		return Attribute.of(container, KEY);
	}

	public static Float apply(PropertyContainer container) {
		return of(container).asFloat();
	}

	public static Float apply(PropertyContainer container, Matchable target, MatchContext context) {
		return of(container, target, context).asFloat();
	}

	public static Attribute of(PropertyContainer container, Matchable target, MatchContext context) {
		return Attribute.of(new ContainerTargetPair(container, target, context), KEY);
	}

}
