import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReversePolishNotationCalculatorTest {
    private final ReversePolishNotationCalculator reversePolishNotationCalculator = new ReversePolishNotationCalculator();

    @Test
    public void shouldCalculateAddition() {
        int result = reversePolishNotationCalculator.calculatePolishNotation("4 2 +");
        Assertions.assertEquals(result, 6);
    }

    @Test
    public void shouldCalculateSubtraction() {
        int result = reversePolishNotationCalculator.calculatePolishNotation("4 2 -");
        Assertions.assertEquals(result, 2);
    }

    @Test
    public void shouldCalculateMultiplication() {
        int result = reversePolishNotationCalculator.calculatePolishNotation("4 2 *");
        Assertions.assertEquals(result, 8);
    }

    @Test
    public void shouldCalculateAdditionWithNegativeNumber() {
        int result = reversePolishNotationCalculator.calculatePolishNotation("4 -2 +");
        Assertions.assertEquals(result, 2);
    }

    @Test
    public void shouldCalculateAdditionWithSpaces() {
        int result = reversePolishNotationCalculator.calculatePolishNotation("4   2 +");
        Assertions.assertEquals(result, 6);
    }
}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}