// Generated from D:/School/LAPR/project/shared/src/main/antlr4/pt/isep/nsheets/shared/lapr4/green.s2.lang.n1161294\MonetaryExpression.g4 by ANTLR 4.7

//    package pt.isep.nsheets.shared.core.formula.compiler;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MonetaryExpressionParser}.
 */
public interface MonetaryExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MonetaryExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MonetaryExpressionParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonetaryExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MonetaryExpressionParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonetaryExpressionParser#monetary_formula}.
	 * @param ctx the parse tree
	 */
	void enterMonetary_formula(MonetaryExpressionParser.Monetary_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonetaryExpressionParser#monetary_formula}.
	 * @param ctx the parse tree
	 */
	void exitMonetary_formula(MonetaryExpressionParser.Monetary_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link MonetaryExpressionParser#operations}.
	 * @param ctx the parse tree
	 */
	void enterOperations(MonetaryExpressionParser.OperationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MonetaryExpressionParser#operations}.
	 * @param ctx the parse tree
	 */
	void exitOperations(MonetaryExpressionParser.OperationsContext ctx);
}