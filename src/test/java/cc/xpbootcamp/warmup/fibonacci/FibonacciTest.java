package cc.xpbootcamp.warmup.fibonacci;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("斐波那契数列相关测试")
class FibonacciTest {

    private Fibonacci fibonacci = null;

    @BeforeEach
    public void setUp() {
        fibonacci = new Fibonacci();
    }

    @DisplayName("当求斐波那契数列的第 1 位时, 返回 1")
    @Test
    void should_return_1_when_calculate_given_position_is_1() {
        int result = fibonacci.calculate(1);
        Assertions.assertEquals(1, result);
    }

    @DisplayName("当求斐波那契数列的第 2 位时, 返回 1")
    @Test
    void should_return_1_when_calculate_given_position_is_2() {
        int result = fibonacci.calculate(2);
        Assertions.assertEquals(1, result);
    }
}