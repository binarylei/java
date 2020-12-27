package com.binarylei.algorithm.struct.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 栈实现数学表达式的运算：本示例只包含四个运算符 {"+", "-", "*", "/"}
 *
 * @author binarylei
 * @version 2020-02-24
 */
public class MathExpressStack {

    private static final Pattern EXPRESSION_NUMBER = Pattern.compile("\\d+");
    private static final Pattern EXPRESSION_OPERATOR = Pattern.compile("\\W+");
    private static final String OPERATOR_PLUS = "+";
    private static final String OPERATOR_SUB = "-";
    private static final String OPERATOR_MULTI = "*";
    private static final String OPERATOR_DIVISION = "/";
    private static final Map<String, Integer> OPERATOR_PRIORITY;
    private static final Integer MAX_PRIORITY = Integer.MIN_VALUE;
    private static final Integer MIN_PRIORITY = Integer.MAX_VALUE;

    private Stack operateStack;
    private Stack numberStack;

    static {
        // 数值越小，优先级越高
        OPERATOR_PRIORITY = new HashMap<>(16);
        OPERATOR_PRIORITY.put(OPERATOR_DIVISION, 1);
        OPERATOR_PRIORITY.put(OPERATOR_MULTI, 1);
        OPERATOR_PRIORITY.put(OPERATOR_PLUS, 2);
        OPERATOR_PRIORITY.put(OPERATOR_SUB, 2);
    }

    /**
     * 1. 只要当前操作符的优先级小于前一个操作符的优先级就会进行计算
     * 2. 第一个操作符插入时不会进行计算
     * 3. 结束时如果还有操作符也需要进行计算
     *
     * @param expression
     * @return
     */
    public double evaluate(String expression) {
        operateStack = new LinkedStack();
        numberStack = new LinkedStack();

        String[] operators = split(expression);
        // 开始不做任务计算，上一个操作符是最小优先级，则下一个操作符不会计算
        int lastPriority = MIN_PRIORITY;
        for (String operator : operators) {
            if (isOperator(operator)) {
                Integer currentPriority = OPERATOR_PRIORITY.get(operator);
                if (currentPriority >= lastPriority) {
                    calculate(currentPriority);
                }
                lastPriority = currentPriority;
                operateStack.push(operator);
            } else {
                numberStack.push(Double.parseDouble(operator));
            }
        }
        // 结束时强制计算，当前操作符是最小优先级，则会进行计算
        if (operateStack.size() != 0) {
            calculate(MIN_PRIORITY);
        }
        return (double) numberStack.pop();
    }

    private void calculate(int currentPriority) {
        String operator = (String) operateStack.pop();
        if (operator == null) {
            return;
        }
        double value1 = (double) numberStack.pop();
        double value2 = (double) numberStack.pop();
        double result = 0d;

        switch (operator) {
            case OPERATOR_PLUS:
                result = value2 + value1;
                break;
            case OPERATOR_SUB:
                result = value2 - value1;
                break;
            case OPERATOR_MULTI:
                result = value2 * value1;
                break;
            case OPERATOR_DIVISION:
                result = value2 / value1;
                break;
        }
        numberStack.push(result);

        operator = (String) operateStack.peek();
        if (operator != null && currentPriority >= OPERATOR_PRIORITY.get(operator)) {
            calculate(currentPriority);
        }
    }

    /**
     * @param expression 操作符必须用空格隔开： "5 * 1 + 3 + 8 / 4 + 1"
     * @return 返回所以的字条数组
     */
    private String[] split(String expression) {
        if (expression == null || expression.length() == 0) return new String[0];
        return expression.split("\\s+");
    }

    private boolean isOperator(String operator) {
        return OPERATOR_PRIORITY.containsKey(operator);
    }


    @Test
    public void test() {
        MathExpressStack mathExpress = new MathExpressStack();
        Assert.assertEquals(3 + 5,
                mathExpress.evaluate("3 + 5"), 0);
        Assert.assertEquals(3 + 5 * 8,
                mathExpress.evaluate("3 + 5 * 8"), 0);
        Assert.assertEquals(3 + 5 * 8 - 1,
                mathExpress.evaluate("3 + 5 * 8 - 1"), 0);
        Assert.assertEquals(3 + 4 * 8 / 2 - 1,
                mathExpress.evaluate("3 + 4 * 8 / 2 - 1"), 0);

    }
}
