package com.sigmundgranaas.forgero.core.condition;

import java.util.List;

import com.sigmundgranaas.forgero.core.property.Property;
import com.sigmundgranaas.forgero.core.property.PropertyContainer;
import com.sigmundgranaas.forgero.core.state.Identifiable;
import com.sigmundgranaas.forgero.core.util.match.MatchContext;
import com.sigmundgranaas.forgero.core.util.match.Matchable;
import org.jetbrains.annotations.NotNull;

public class NamedCondition implements PropertyContainer, Identifiable {
	private final String name;
	private final String nameSpace;
	private final List<Property> propertyList;

	public NamedCondition(String name, String nameSpace, List<Property> propertyList) {
		this.name = name;
		this.nameSpace = nameSpace;
		this.propertyList = propertyList;
	}

	public String name() {
		return this.name;
	}

	@Override
	public String nameSpace() {
		return nameSpace;
	}

	@Override
	public @NotNull
	List<Property> getRootProperties() {
		return propertyList;
	}

	@Override
	public @NotNull List<Property> getRootProperties(Matchable target, MatchContext context) {
		return getRootProperties();
	}
}
