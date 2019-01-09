/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1151708.javascript.compiler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.Token;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.BinaryOperation;
import pt.isep.nsheets.shared.core.formula.BinaryOperator;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.core.formula.Function;
import pt.isep.nsheets.shared.core.formula.FunctionCall;
import pt.isep.nsheets.shared.core.formula.Literal;
import pt.isep.nsheets.shared.core.formula.UnaryOperation;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaParser;
import pt.isep.nsheets.shared.core.formula.compiler.IllegalFunctionCallException;
import pt.isep.nsheets.shared.core.formula.lang.CellReference;
import pt.isep.nsheets.shared.core.formula.lang.Language;
import pt.isep.nsheets.shared.core.formula.lang.RangeReference;
import pt.isep.nsheets.shared.core.formula.lang.ReferenceOperation;
import pt.isep.nsheets.shared.core.formula.lang.UnknownElementException;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.shared.lang.MultipleExpressions;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1151708.javascriptBaseVisitor;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1151708.javascriptParser;

/**
 *
 * @author MarioDias
 */
public class JavascriptEvalVisitor extends javascriptBaseVisitor<Expression> {

    private Cell cell = null;
    int numberOfErros;
    private final StringBuilder errorBuffer;
    private final Spreadsheet spreadsheet;
    final private Language language;

    public JavascriptEvalVisitor(Cell cell, Language lang) {
        this.cell = cell;
        this.spreadsheet = cell.getSpreadsheet();
        numberOfErros = 0;
        errorBuffer = new StringBuilder();
        this.language = lang;
    }

    public int getNumberOfErrors() {
        return numberOfErros;
    }

    public String getErrorsMessage() {
        return errorBuffer.toString();
    }

    @Override
    public Expression visitMacro(javascriptParser.MacroContext ctx) {
        ArrayList<Expression> array = new ArrayList<>();

        for (int i = 0; i < ctx.getChildCount(); i++) {
            Expression exp = visit(ctx.line(i));
            array.add(exp);
        }
        MultipleExpressions mexp = new MultipleExpressions(array);
        return mexp;
    }

    @Override
    public Expression visitIfexpression(javascriptParser.IfexpressionContext ctx) {
        if (ctx.IF() != null) {
            try {
                boolean hasElse = ctx.getChildCount() > 7;
                Expression first_comparison = visit(ctx.getChild(2));
                Value value = first_comparison.evaluate();
                //If the return value is a boolean
                if (value.isOfType(Value.Type.BOOLEAN)) {
                    //Check if true, then evalute the first expression
                    if (value.toBoolean()) {
                        return visit(ctx.getChild(5));
                    } else {
                        //Else evaluate the second expression
                        if (hasElse) {
                            return visit(ctx.getChild(7));
                        }
                    }
                }
            } catch (IllegalValueTypeException ex) {
                Logger.getLogger(JavascriptEvalVisitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return visit(ctx);
    }

    @Override
    public Expression visitLine(javascriptParser.LineContext ctx) {
        return visit(ctx);
    }

 

    @Override
    public Expression visitComparison(javascriptParser.ComparisonContext ctx) {
        if (ctx.getChildCount() == 3) {
            try {
                //BinaryOperator operator = Language.getInstance().getBinaryOperator(ctx.getChild(1).getText());
                BinaryOperator operator = this.language.getBinaryOperator(ctx.getChild(1).getText());

                return new BinaryOperation(
                        visit(ctx.getChild(0)),
                        operator,
                        visit(ctx.getChild(2))
                );
            } catch (UnknownElementException ex) {
                addVisitError(ex.getMessage());
            }
        }

        return visit(ctx.concatenation(0));
    }

    @Override
    public Expression visitConcatenation(javascriptParser.ConcatenationContext ctx) {
        try {
            if (ctx.getChildCount() == 2) { // Convert unary operation
                int operatorid = 0, operand = 1;  // Assume operator on the left

//                if (ctx.getChild(1).getChildCount() == 0) { // Conclude that operator is on the right
                if (ctx.PERCENT() != null) { // Conclude that operator is on the right
                    operatorid = 1;
                    operand = 0;
                }

                return new UnaryOperation(
                        // Language.getInstance().getUnaryOperator(ctx.getChild(operatorid).getText()),
                        this.language.getUnaryOperator(ctx.getChild(operatorid).getText()),
                        visit(ctx.getChild(operand))
                );

            } else if (ctx.getChildCount() == 3) {
                // Convert binary operation
                // BinaryOperator operator = Language.getInstance().getBinaryOperator(ctx.getChild(1).getText());
                BinaryOperator operator = this.language.getBinaryOperator(ctx.getChild(1).getText());
                return new BinaryOperation(
                        visit(ctx.getChild(0)),
                        operator,
                        visit(ctx.getChild(2))
                );
            }

        } catch (FormulaCompilationException ex) {
            addVisitError(ex.getMessage());
        }

        return visitChildren(ctx);
    }

    @Override
    public Expression visitAtom(javascriptParser.AtomContext ctx) {
        if (ctx.getChildCount() == 3) {
            return visit(ctx.getChild(1));
        }

        return  visit(ctx.getChild(0));
    }

    @Override
    public Expression visitFunction_call(javascriptParser.Function_callContext ctx) {
        // Convert function call
        Function function = null;
        try {
            // function = Language.getInstance().getFunction(ctx.getChild(0).getText());
            function = this.language.getFunction(ctx.getChild(0).getText());
        } catch (UnknownElementException ex) {
            addVisitError(ex.getMessage());
        }

        if (function != null) {
            try {
                List<Expression> args = new ArrayList<>();
                if (ctx.getChildCount() > 3) {
                    for (int nChild = 2; nChild < ctx.getChildCount() - 1; nChild += 2) {
                        args.add(visit(ctx.getChild(nChild)));
                    }
                }
                Expression[] argArray = args.toArray(new Expression[args.size()]);
                return new FunctionCall(function, argArray);
            } catch (IllegalFunctionCallException ex) {
                addVisitError(ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public Expression visitReference(javascriptParser.ReferenceContext ctx) {
        try {
            if (ctx.getChildCount() == 3) {
                //BinaryOperator operator = Language.getInstance().getBinaryOperator(ctx.getChild(1).getText());
                BinaryOperator operator = this.language.getBinaryOperator(ctx.getChild(1).getText());

                return new ReferenceOperation(
                        new CellReference(cell.getSpreadsheet(), ctx.getChild(0).getText()),
                        (RangeReference) operator,
                        new CellReference(cell.getSpreadsheet(), ctx.getChild(2).getText())
                );
            } else {
                return new CellReference(cell.getSpreadsheet(), ctx.getText());
            }
            // return visitChildren(ctx); 
        } catch (ParseException | UnknownElementException ex) {
            addVisitError(ex.getMessage());
        }
        return null;
    }

    @Override
    public Expression visitLiteral(javascriptParser.LiteralContext ctx) {
        Token t = (Token) ctx.getChild(0).getPayload();

        if (t.getType() == javascriptParser.NUMBER) {
            return new Literal(Value.parseValue(ctx.getText()));
        } else {
            if (t.getType() == javascriptParser.STRING) {
                String value = ctx.getText();
                return new Literal(Value.parseValue(value));
            }
        }

        return null;
    }

    private void addVisitError(String msg) {
        errorBuffer.append(msg).append("\n");
        numberOfErros++;
    }

}
