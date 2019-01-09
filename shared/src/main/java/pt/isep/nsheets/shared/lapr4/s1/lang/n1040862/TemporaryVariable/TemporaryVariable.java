package pt.isep.nsheets.shared.lapr4.s1.lang.n1040862.TemporaryVariable;

import pt.isep.nsheets.shared.core.Value;

public class TemporaryVariable {

	private String name;
	private Value value;

	public TemporaryVariable(String name) {
		this.name = name;
		this.value = null;
	}

	public TemporaryVariable(String name, Value value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
