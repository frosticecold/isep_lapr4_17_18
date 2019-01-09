// Generated from MacroExcel.g4 by ANTLR 4.7.1

//    package pt.isep.nsheets.shared.core.formula.compiler;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MacroExcelParser}.
 */
public interface MacroExcelListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MacroExcelParser#macro}.
	 * @param ctx the parse tree
	 */
	void enterMacro(MacroExcelParser.MacroContext ctx);
	/**
	 * Exit a parse tree produced by {@link MacroExcelParser#macro}.
	 * @param ctx the parse tree
	 */
	void exitMacro(MacroExcelParser.MacroContext ctx);
	/**
	 * Enter a parse tree produced by {@link MacroExcelParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(MacroExcelParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MacroExcelParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(MacroExcelParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MacroExcelParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MacroExcelParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MacroExcelParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MacroExcelParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MacroExcelParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(MacroExcelParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link MacroExcelParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(MacroExcelParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link MacroExcelParser#concatenation}.
	 * @param ctx the parse tree
	 */
	void enterConcatenation(MacroExcelParser.ConcatenationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MacroExcelParser#concatenation}.
	 * @param ctx the parse tree
	 */
	void exitConcatenation(MacroExcelParser.ConcatenationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MacroExcelParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(MacroExcelParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link MacroExcelParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(MacroExcelParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link MacroExcelParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MacroExcelParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MacroExcelParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MacroExcelParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MacroExcelParser#attribution}.
	 * @param ctx the parse tree
	 */
	void enterAttribution(MacroExcelParser.AttributionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MacroExcelParser#attribution}.
	 * @param ctx the parse tree
	 */
	void exitAttribution(MacroExcelParser.AttributionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MacroExcelParser#function_call}.
	 * @param ctx the parse tree
	 */
	void enterFunction_call(MacroExcelParser.Function_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link MacroExcelParser#function_call}.
	 * @param ctx the parse tree
	 */
	void exitFunction_call(MacroExcelParser.Function_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link MacroExcelParser#reference}.
	 * @param ctx the parse tree
	 */
	void enterReference(MacroExcelParser.ReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MacroExcelParser#reference}.
	 * @param ctx the parse tree
	 */
	void exitReference(MacroExcelParser.ReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link MacroExcelParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(MacroExcelParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MacroExcelParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(MacroExcelParser.LiteralContext ctx);
}