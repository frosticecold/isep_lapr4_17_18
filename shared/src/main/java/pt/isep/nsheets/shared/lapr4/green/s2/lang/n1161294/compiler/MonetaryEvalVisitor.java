package pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.compiler;

import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.BinaryOperation;
import pt.isep.nsheets.shared.core.formula.BinaryOperator;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.core.formula.Literal;
import pt.isep.nsheets.shared.core.formula.lang.Language;
import pt.isep.nsheets.shared.core.formula.lang.UnknownElementException;
import pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.MonetaryExpressionBaseVisitor;
import pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.MonetaryExpressionParser;
import pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.Money.Currency;
import pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.Money.CurrencyConverterService;
import pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.Money.Money;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MonetaryEvalVisitor extends MonetaryExpressionBaseVisitor<Expression> {

    /**
     * Cell to change
     */
    private Cell cell = null;

    /**
     * Number of errors
     */
    int numberOfErrors;

    /**
     * Error buffer
     */
    private final StringBuilder errorBuffer;

    /**
     * Language of compiler
     */
    final private Language language;

    /**
     * Final currency transformation
     */
    Currency resCurrency = null;

    /**
     * Constructor
     *
     * @param cell cell where to process content
     * @param lang language which will be interpret the cell
     */
    public MonetaryEvalVisitor(Cell cell, Language lang) {
        this.cell = cell;
        numberOfErrors = 0;
        errorBuffer = new StringBuilder();
        this.language = lang;
    }

    /**
     * Devolves number of errors
     *
     * @return number of errors
     */
    public int getNumberOfErrors() {
        return numberOfErrors;
    }

    /**
     * Devolves error message
     *
     * @return error message
     */
    public String getErrorsMessage() {
        return errorBuffer.toString();
    }

    /**
     * Add error message
     *
     * @param msg message
     */
    private void addVisitError(String msg) {
        errorBuffer.append(msg).append("\n");
        numberOfErrors++;
    }

    /**
     * Visit expression
     *
     * @param ctx expression context
     * @return final expression
     */
    @Override
    public Expression visitExpression(MonetaryExpressionParser.ExpressionContext ctx) {

        Logger logger = Logger.getLogger("logger");
        logger.log(Level.SEVERE, "*** Expression |  " + ctx.getText());

        return visit(ctx.monetary_formula());
    }

    /**
     * Visit monetary formula
     *
     * @param ctx monetary formula context
     * @return expression
     */
    @Override
    public Expression visitMonetary_formula(MonetaryExpressionParser.Monetary_formulaContext ctx) {
        Logger logger = Logger.getLogger("logger");
        logger.log(Level.SEVERE, "*** Monetary_formula |  " + ctx.getText());


        if(ctx.FINAL_CURRENCY() != null) {
            try{
                Currency currency = Currency.toCurrency(ctx.FINAL_CURRENCY().getText());

                if(resCurrency == null){
                    resCurrency =  currency;

                    Expression expression = visitOperations(ctx.operations());

                    if(expression.evaluate().isOfType(Value.Type.MONETARY)){
                        Expression exp = new Literal(CurrencyConverterService.convertToCurrency(expression.evaluate().toMoney(), resCurrency));

                        return exp;
                    }
                }else {
                    Currency toConvert = resCurrency;
                    resCurrency = currency;
                    Expression expression = visitOperations(ctx.operations());

                    if(expression.evaluate().isOfType(Value.Type.MONETARY)){
                        Expression exp = new Literal(CurrencyConverterService.convertToCurrency(expression.evaluate().toMoney(), resCurrency));
                        resCurrency = toConvert;

                        return exp;
                    }
                }


            }catch (Exception e){
                logger.log(Level.SEVERE, "visitMonetary error: " + e.getMessage());
            }
        }

        return null;
    }

    /**
     * Visit operations
     *
     * @param ctx operations context
     * @return expression
     */
    @Override
    public Expression visitOperations(MonetaryExpressionParser.OperationsContext ctx) {
        Logger logger = Logger.getLogger("logger");
        logger.log(Level.SEVERE, "*** Operations |  " + ctx.getText());
        BinaryOperator operator;

        if(ctx.currency() != null){
            logger.log(Level.SEVERE, "*** Entered |  2");
            return visit(ctx.currency());
        }else if(ctx.monetary_formula() != null){
            logger.log(Level.SEVERE, "*** Entered |  3");
            return visitMonetary_formula(ctx.monetary_formula());
        }else if(ctx.LPAR() != null && ctx.RPAR() != null){
            logger.log(Level.SEVERE, "*** Entered |  4");
            return visit(ctx.getChild(1));
        }else if(ctx.right_multiplication() != null){
            logger.log(Level.SEVERE, "*** Entered |  5");
            return visitRight_multiplication(ctx.right_multiplication());
        }else if(ctx.MULTI() != null || ctx.DIV() != null){
            logger.log(Level.SEVERE, "*** Entered |  6");
            try{
                Expression e1 = visit(ctx.getChild(0));
                Expression e2 = new Literal(new Value(Double.valueOf(ctx.NUMBER().getText())));
                logger.log(Level.SEVERE, "*** Entered |  6");
                operator = this.language.getBinaryOperator(ctx.getChild(1).getText());
                Expression expression = new BinaryOperation(e1, operator, e2);


                return new Literal(CurrencyConverterService.convertToCurrency(expression.evaluate().toMoney(), resCurrency));
            }catch (UnknownElementException e){
                addVisitError(e.getMessage());
            }catch (Exception e){
                logger.log(Level.SEVERE, "Error: " + e.getMessage());
            }

        }else if(ctx.PLUS() != null || ctx.MINUS() != null){
            logger.log(Level.SEVERE, "*** Entered |  7");
            try{
                operator = this.language.getBinaryOperator(ctx.getChild(1).getText());
                logger.log(Level.SEVERE, "*** Entered |  7.0");
                Money right = visit(ctx.getChild(2)).evaluate().toMoney();
                logger.log(Level.SEVERE, "*** Entered |  7.1");
                Money left = visit(ctx.getChild(0)).evaluate().toMoney();
                logger.log(Level.SEVERE, left.toString() + " *** Entered |  7.2 " + right.toString() + "-> " + operator.toString());

                Expression expression1 = new Literal(CurrencyConverterService.convertToCurrency(left, resCurrency));
                Expression expression2 = new Literal(CurrencyConverterService.convertToCurrency(right, resCurrency));

                logger.log(Level.SEVERE, "*** +++ Expression_1: " + expression1.evaluate().toMoney().toString());
                logger.log(Level.SEVERE, "*** +++ Expression_2: " + expression2.evaluate().toMoney().toString());
                Expression expression = new BinaryOperation(expression1, operator, expression2);


                return new Literal(CurrencyConverterService.convertToCurrency(expression.evaluate().toMoney(), resCurrency));
            }catch (UnknownElementException e){
                addVisitError(e.getMessage());
            }catch (Exception e){
                logger.log(Level.SEVERE, "Error: " + e.getMessage());
            }
        }
        return null;
    }

    /**
     * Visit right multiplication
     *
     * @param ctx right multiplication context
     * @return expression
     */
    @Override
    public Expression visitRight_multiplication(MonetaryExpressionParser.Right_multiplicationContext ctx) {
        Logger logger = Logger.getLogger("logger");
        logger.log(Level.SEVERE, "*** Right_multiplication |  " + ctx.getText());

        BinaryOperator operator;
        try{
            Expression e1 = new Literal(new Value(Double.valueOf(ctx.NUMBER().getText())));
            Expression e2 = visit(ctx.getChild(2));

            logger.log(Level.SEVERE, "*** Entered |  8");
            operator = this.language.getBinaryOperator(ctx.getChild(1).getText());
            Expression expression = new BinaryOperation(e1, operator, e2);

            return new Literal(CurrencyConverterService.convertToCurrency(expression.evaluate().toMoney(), resCurrency));
        }catch (UnknownElementException e){
            addVisitError(e.getMessage());
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Visit currency
     *
     * @param ctx currency context
     * @return expression
     */
    @Override
    public Expression visitCurrency(MonetaryExpressionParser.CurrencyContext ctx) {
        Logger logger = Logger.getLogger("logger");
        logger.log(Level.SEVERE, "*** Currency |  " + ctx.getText());
        return new Literal(
                new Value(new Money(Double.valueOf(ctx.NUMBER().getText()),
                        Currency.toCurrency(ctx.CURRENCY().getText()))));
    }

}
