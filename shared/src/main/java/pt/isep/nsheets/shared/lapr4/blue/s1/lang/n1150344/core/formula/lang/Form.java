package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1150344.core.formula.lang;

import com.google.gwt.user.client.Window;
import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.core.formula.Function;
import pt.isep.nsheets.shared.core.formula.FunctionParameter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A function that launches a window for creating/editing a form.
 *
 * @author Rui Ribeiro [1150344]
 */
public class Form implements Function {

	/** The only (but repeatable) parameter: a boolean expression */
	public static final FunctionParameter[] parameters = new FunctionParameter[] {
			new FunctionParameter(Value.Type.FORM, "Form", false,
					"FORM")
	};

	/**
	 * Creates a new instance of the FORM function.
	 */
	public Form() {}

	public String getIdentifier() {
		return "FORM";
	}

	public Value applyTo(Expression[] argument) throws IllegalValueTypeException {
		Window.open("#form","_self",null);
		Logger logger = Logger.getLogger("logger");
		logger.log(Level.SEVERE, "value 1");
		return new Value();
	}

	public FunctionParameter[] getParameters() {
		return parameters;
	}

	public boolean isVarArg() {
		return false;
	}
}