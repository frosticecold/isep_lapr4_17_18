// Generated from D:/School/LAPR/project/shared/src/main/antlr4/pt/isep/nsheets/shared/lapr4/green.s2.lang.n1161294\MonetaryExpression.g4 by ANTLR 4.7

//    package pt.isep.nsheets.shared.core.formula.compiler;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link MonetaryExpressionVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class MonetaryExpressionBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements MonetaryExpressionVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitExpression(MonetaryExpressionParser.ExpressionContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitMonetary_formula(MonetaryExpressionParser.Monetary_formulaContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitOperations(MonetaryExpressionParser.OperationsContext ctx) { return visitChildren(ctx); }
}