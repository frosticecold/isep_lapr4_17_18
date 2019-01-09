// Generated from .\Formula.g4 by ANTLR 4.7.1

//    package pt.isep.nsheets.shared.core.formula.compiler;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FormulaParser}.
 */
public interface FormulaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FormulaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(FormulaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(FormulaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(FormulaParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(FormulaParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#concatenation}.
	 * @param ctx the parse tree
	 */
	void enterConcatenation(FormulaParser.ConcatenationContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#concatenation}.
	 * @param ctx the parse tree
	 */
	void exitConcatenation(FormulaParser.ConcatenationContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(FormulaParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(FormulaParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(FormulaParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(FormulaParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#attribution}.
	 * @param ctx the parse tree
	 */
	void enterAttribution(FormulaParser.AttributionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#attribution}.
	 * @param ctx the parse tree
	 */
	void exitAttribution(FormulaParser.AttributionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#function_call}.
	 * @param ctx the parse tree
	 */
	void enterFunction_call(FormulaParser.Function_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#function_call}.
	 * @param ctx the parse tree
	 */
	void exitFunction_call(FormulaParser.Function_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#reference}.
	 * @param ctx the parse tree
	 */
	void enterReference(FormulaParser.ReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#reference}.
	 * @param ctx the parse tree
	 */
	void exitReference(FormulaParser.ReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulaParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(FormulaParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulaParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(FormulaParser.LiteralContext ctx);
}