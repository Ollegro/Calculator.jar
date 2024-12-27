import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testCalculateWithArabicNumbers() {
        assertEquals("15", Calculator.calculate("10 + 5"));
        assertEquals("5", Calculator.calculate("10 - 5"));
        assertEquals("50", Calculator.calculate("10 * 5"));
        assertEquals("2", Calculator.calculate("10 / 5"));
    }

    @Test
    void testCalculateWithRomanNumbers() {
        assertEquals("XV", Calculator.calculate("X + V"));
        assertEquals("V", Calculator.calculate("X - V"));
        assertEquals("L", Calculator.calculate("X * V"));
        assertEquals("II", Calculator.calculate("X / V"));
    }

    @Test
    void testMixedArabicAndRomanNumbers() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Calculator.calculate("10 + V"));
        assertEquals("Нельзя смешивать арабские и римские числа.", exception.getMessage());
    }

    @Test
    void testInvalidOperator() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Calculator.calculate("10 ^ 5"));
        assertEquals("Оператор не найден или отсутствует.", exception.getMessage());
    }

    @Test
    void testDivisionByZero() {

        Exception exception = assertThrows(ArithmeticException.class,
                () -> Calculator.calculate("10 / 0"));
        assertEquals("Деление на ноль невозможно.", exception.getMessage());
    }

    @Test
    void testOutOfRangeNumbers() {
        Exception exception1 = assertThrows(IllegalArgumentException.class,
                () -> Calculator.calculate("101 + 1"));
        assertEquals("Число должно быть в диапазоне от 1 до 100.", exception1.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class,
                () -> Calculator.calculate("0 + 5"));
        assertEquals("Число должно быть в диапазоне от 1 до 100.", exception2.getMessage());
    }

    @Test
    void testRomanToArabicConversion() {
        assertEquals(10, Calculator.romanToArabic("X"));
        assertEquals(4, Calculator.romanToArabic("IV"));
        assertEquals(50, Calculator.romanToArabic("L"));

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Calculator.romanToArabic("INVALID"));
        assertEquals("Некорректное римское число: INVALID", exception.getMessage());
    }

    @Test
    void testArabicToRomanConversion() {
        assertEquals("X", Calculator.arabicToRoman(10));
        assertEquals("IV", Calculator.arabicToRoman(4));
        assertEquals("L", Calculator.arabicToRoman(50));

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Calculator.arabicToRoman(0));
        assertEquals("Результат не может быть меньше 1 для римских чисел.", exception.getMessage());
    }

    @Test
    void testPerformOperation() {
        assertEquals(15, Calculator.performOperation(10, 5, "+"));
        assertEquals(5, Calculator.performOperation(10, 5, "-"));
        assertEquals(50, Calculator.performOperation(10, 5, "*"));
        assertEquals(2, Calculator.performOperation(10, 5, "/"));

        Exception exception1 = assertThrows(IllegalArgumentException.class,
                () -> Calculator.performOperation(101, 5, "+"));
        assertEquals("Число должно быть в диапазоне от 1 до 100.", exception1.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class,
                () -> Calculator.performOperation(10, 5, "^"));
        assertEquals("Неизвестный оператор: ^", exception2.getMessage());
    }
}
