package com.sigmundgranaas.forgeroforge.test.util;

import com.sigmundgranaas.forgero.core.ForgeroStateRegistry;
import com.sigmundgranaas.forgero.core.state.State;

public class StateHelper {
	public static State state(String id) {
		return ForgeroStateRegistry.stateFinder().find(id).orElseThrow();
	}

}
