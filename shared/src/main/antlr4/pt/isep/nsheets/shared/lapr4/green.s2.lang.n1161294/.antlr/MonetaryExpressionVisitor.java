// Generated from D:/School/LAPR/project/shared/src/main/antlr4/pt/isep/nsheets/shared/lapr4/green.s2.lang.n1161294\MonetaryExpression.g4 by ANTLR 4.7

//    package pt.isep.nsheets.shared.core.formula.compiler;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MonetaryExpressionParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MonetaryExpressionVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MonetaryExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MonetaryExpressionParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MonetaryExpressionParser#monetary_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMonetary_formula(MonetaryExpressionParser.Monetary_formulaContext ctx);
	/**
	 * Visit a parse tree produced by {@link MonetaryExpressionParser#operations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperations(MonetaryExpressionParser.OperationsContext ctx);
}