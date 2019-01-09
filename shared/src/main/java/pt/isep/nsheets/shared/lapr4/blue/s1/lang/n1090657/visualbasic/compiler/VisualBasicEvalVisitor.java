/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.visualbasic.compiler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import pt.isep.nsheets.shared.core.Cell;
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
import pt.isep.nsheets.shared.core.formula.compiler.IllegalFunctionCallException;
import pt.isep.nsheets.shared.core.formula.lang.CellReference;
import pt.isep.nsheets.shared.core.formula.lang.Language;
import pt.isep.nsheets.shared.core.formula.lang.RangeReference;
import pt.isep.nsheets.shared.core.formula.lang.ReferenceOperation;
import pt.isep.nsheets.shared.core.formula.lang.UnknownElementException;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.VisualBasicBaseVisitor;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.VisualBasicParser;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.shared.lang.ForExpr;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.shared.lang.IfExpr;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.shared.lang.MultipleExpressions;
import pt.isep.nsheets.shared.lapr4.green.s2.lang.n1160818.shared.lang.MacroCall;

/**
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class VisualBasicEvalVisitor extends VisualBasicBaseVisitor<Expression> {

    private Cell cell = null;
    private Spreadsheet spreadsheet = null;
    int numberOfErros;
    private final StringBuilder errorBuffer;

    final private Language language;

    public VisualBasicEvalVisitor(Cell cell, Language lang) {
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

    private void addVisitError(String msg) {
        errorBuffer.append(msg).append("\n");
        numberOfErros++;
    }

    /**
     * Parses the macro call
     *
     * @param ctx Marco Call Context
     * @return the expression
     * @author Rui Almeida <1160818> Sprint 2 Lang 07.2
     */
    @Override
    public Expression visitMacro_call(VisualBasicParser.Macro_callContext ctx) {
        if (ctx.MACRO_START() != null) {
            if(ctx.getChildCount() == 4){
                return new MacroCall(ctx.STRING(), cell);
            }
            ArrayList<String> parameters = new ArrayList<>();
            for (int i = 4; i < ctx.getChildCount(); i += 2 ) {
                parameters.add(ctx.getChild(i).getText());
            }
            return new MacroCall(ctx.STRING(), parameters, cell);
        }

        return null;
    }


    /**
     * Base macro
     *
     * @param ctx
     * @return
     */
    @Override
    public Expression visitMacro(VisualBasicParser.MacroContext ctx) {
        ArrayList<Expression> array = new ArrayList<>();

        for (int i = 0; i < ctx.getChildCount(); i++) {
            Expression exp = visit(ctx.line(i));
            array.add(exp);
        }
        MultipleExpressions mexp = new MultipleExpressions(array);
        return mexp;
    }

    /**
     * Each line parsed
     *
     * @param ctx
     * @return
     */
    @Override
    public Expression visitLine(VisualBasicParser.LineContext ctx) {
        return visit(ctx.comparison());
    }

    @Override
    public Expression visitComparison(VisualBasicParser.ComparisonContext ctx) {
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
    public Expression visitConcatenation(VisualBasicParser.ConcatenationContext ctx) {
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
        return visit(ctx.getChild(0));
    }

    @Override
    public Expression visitAtom(VisualBasicParser.AtomContext ctx) {
        //If this is a comparison
        if (ctx.getChildCount() == 3) {
            return visit(ctx.getChild(1));
        }

        return visit(ctx.getChild(0));
    }

    @Override
    public Expression visitAttribution(VisualBasicParser.AttributionContext ctx) {
        if (ctx.CELL_REF() != null) {
            BinaryOperator op;
            try {
                op = this.language.getBinaryOperator(ctx.getChild(1).getText());
                return new BinaryOperation(visit(ctx.getChild(0)), op, visit(ctx.getChild(2)));
            } catch (UnknownElementException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public Expression visitFunction_call(VisualBasicParser.Function_callContext ctx) {
        // Convert function call
        Function function = null;
        try {
            // function = Language.getInstance().getFunction(ctx.getChild(0).getText());
            String functionName[] = ctx.getChild(0).getText().split(".");
            function = this.language.getFunction(functionName[1]);
        } catch (UnknownElementException | ArrayIndexOutOfBoundsException ex) {
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
    public Expression visitIfexpr(VisualBasicParser.IfexprContext ctx) {

        Expression toEvaluate = null, ifTrue = null, ifFalse = null;
        if (ctx.IF() != null) {
            boolean hasElse = ctx.ELSE() != null;
            toEvaluate = visit(ctx.getChild(2));
            //If the return value is a boolean
            ifTrue = visit(ctx.getChild(5));
            //Else evaluate the second expression
            if (hasElse) {
                ifFalse = visit(ctx.getChild(7));
            }
        }
        return new IfExpr(toEvaluate, ifTrue, ifFalse);
    }

    @Override
    public Expression visitForexpr(VisualBasicParser.ForexprContext ctx) {
        if (ctx.FOR() != null) {
            if (ctx.ENDFOR() != null) {
                Expression step = null;
                if (ctx.STEP() != null) {
                    step = visit(ctx.getChild(5));
                }
                Expression start = visit(ctx.getChild(1));
                Expression end = visit(ctx.getChild(3));
                List<Expression> list = new ArrayList<>();
                for (int i = 0; i < ctx.concatenation().size(); i++) {
                    list.add(visit(ctx.concatenation(i)));
                }
                Expression multipleArguments = new MultipleExpressions(list);
                return new ForExpr(start, end, step, multipleArguments);

            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public Expression visitLiteral(VisualBasicParser.LiteralContext ctx) {
        Token t = (Token) ctx.getChild(0).getPayload();
        if (t.getType() == VisualBasicParser.NUMBER) {
            return new Literal(Value.parseValue(ctx.getText()));
        } else {
            if (t.getType() == VisualBasicParser.STRING) {
                String value = ctx.getText();
                return new Literal(Value.parseValue(value));
            }
        }

        return null;
    }

    @Override
    public Expression visitReference(VisualBasicParser.ReferenceContext ctx) {
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

}
