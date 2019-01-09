package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1131485.macroExcel.compiler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.Token;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.BinaryOperation;
import pt.isep.nsheets.shared.core.formula.BinaryOperator;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.core.formula.Function;
import pt.isep.nsheets.shared.core.formula.FunctionCall;
import pt.isep.nsheets.shared.core.formula.Literal;
import pt.isep.nsheets.shared.core.formula.MacroCallExcel;
import pt.isep.nsheets.shared.core.formula.UnaryOperation;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaEvalVisitor;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaParser;
import pt.isep.nsheets.shared.core.formula.compiler.IllegalFunctionCallException;
import pt.isep.nsheets.shared.core.formula.lang.CellReference;
import pt.isep.nsheets.shared.core.formula.lang.Language;
import pt.isep.nsheets.shared.core.formula.lang.RangeReference;
import pt.isep.nsheets.shared.core.formula.lang.ReferenceOperation;
import pt.isep.nsheets.shared.core.formula.lang.UnknownElementException;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.shared.lang.MultipleExpressions;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1131485.MacroExcelBaseVisitor;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1131485.MacroExcelParser;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1161386.formula2.NaryOperation;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1161386.formula2.NaryOperator;

/**
 *
 * @author Pedro Emanuel Coelho 1131485@isep.ipp.pt
 */
public class MacroExcelEvalVisitor extends MacroExcelBaseVisitor<Expression> {

    private Cell cell = null;
    int numberOfErros;
    private final StringBuilder errorBuffer;

    final private Language language;

    public MacroExcelEvalVisitor(Cell cell, Language lang) {
        this.cell = cell;
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
      /**
     * Parses the macro call
     *
     * @param ctx Marco Call Excel Context
     * @return the expression
     * @author Ricardo Sousa<1160900> 
     */
    @Override
    public Expression visitMacro_c(MacroExcelParser.Macro_cContext ctx) {
        if (ctx.MACRO() != null) {
            return new MacroCallExcel(ctx.STRING(), cell);
        }

        return null;
    }
    /**
     * Each line parsed
     *
     * @param ctx
     * @return
     */
    @Override
    public Expression visitLine(MacroExcelParser.LineContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Expression visitExpression(MacroExcelParser.ExpressionContext ctx) {
        return visit(ctx.comparison());
    }

    @Override
    public Expression visitComparison(MacroExcelParser.ComparisonContext ctx) {
        if (ctx.getChildCount() == 3) {
            try {

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
    public Expression visitConcatenation(MacroExcelParser.ConcatenationContext ctx) {
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
    public Expression visitAttribution(MacroExcelParser.AttributionContext ctx) {
        if (ctx.ATTRIB() != null) {
            try {
                BinaryOperator operator = this.language.getBinaryOperator(ctx.getChild(1).getText());
                return new BinaryOperation(visit(ctx.getChild(0)), operator, visit(ctx.getChild(2)));
            } catch (FormulaCompilationException ex) {
                Logger.getLogger(MacroExcelEvalVisitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public Expression visitBlock(MacroExcelParser.BlockContext ctx) {
        if (ctx.LBRACKET() != null) {
            try {
                Expression expressions[] = new Expression[ctx.getChildCount() / 2];

                for (int i = 1; i < ctx.getChildCount(); i += 2) {
                    expressions[i / 2] = visit(ctx.getChild(i));
                }

                NaryOperator operator = this.language.getNaryOperator(ctx.getChild(0).getText());
                return new NaryOperation(operator, expressions);
            } catch (UnknownElementException ex) {
                Logger.getLogger(MacroExcelEvalVisitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public Expression visitAtom(MacroExcelParser.AtomContext ctx) {
        if (ctx.getChildCount() == 3) {
            return visit(ctx.getChild(1));
        }

        return visitChildren(ctx);
    }

    @Override
    public Expression visitFunction_call(MacroExcelParser.Function_callContext ctx) {
        // Convert function call
        Function function = null;
        try {
            function = this.language.getFunction(ctx.getChild(0).getText().substring(1));
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
    public Expression visitReference(MacroExcelParser.ReferenceContext ctx) {
        try {
            if (ctx.getChildCount() == 3) {
                BinaryOperator operator = this.language.getBinaryOperator(ctx.getChild(1).getText());

                return new ReferenceOperation(
                        new CellReference(cell.getSpreadsheet(), ctx.getChild(0).getText()),
                        (RangeReference) operator,
                        new CellReference(cell.getSpreadsheet(), ctx.getChild(2).getText())
                );
            } else {
                return new CellReference(cell.getSpreadsheet(), ctx.getText());
            }
        } catch (ParseException | UnknownElementException ex) {
            addVisitError(ex.getMessage());
        }
        return null;
    }

    @Override
    public Expression visitLiteral(MacroExcelParser.LiteralContext ctx) {
        Token t = (Token) ctx.getChild(0).getPayload();

        if (t.getType() == MacroExcelParser.NUMBER) {
            return new Literal(Value.parseValue(ctx.getText()));
        } else {
            if (t.getType() == MacroExcelParser.STRING) {
                String value = ctx.getText().substring(1, ctx.getText().length() - 1);
                return new Literal(Value.parseValue(value, Value.Type.BOOLEAN, Value.Type.DATE));
            }
        }

        return null;
    }

    private void addVisitError(String msg) {
        errorBuffer.append(msg).append("\n");
        numberOfErros++;
    }

}
