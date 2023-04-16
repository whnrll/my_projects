//package com.example.common;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import cn.hutool.core.math.Calculator;
//import org.junit.Test;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//
///**
// * 描述：计算器测试
// *
// * @author xutao
// * @Date 2023-02-25 23:57:09
// */
//public class CalculatorTest {
//    @Test
//    @DisplayName("1 + 1 = 2")
//    public void addsTwoNumbers() {
//        Calculator calculator = new Calculator();
//        assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2");
//    }
//
//    @ParameterizedTest(name = "{0} + {1} = {2}")
//    @CsvSource({
//            "0,    1,   1",
//            "1,    2,   3",
//            "49,  51, 100",
//            "1,  100, 101"
//    })
//    public void add(int first, int second, int expectedResult) {
//        Calculator calculator = new Calculator();
//        assertEquals(expectedResult, calculator.add(first, second),
//                () -> first + " + " + second + " should equal " + expectedResult);
//    }
//}
