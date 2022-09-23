package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotEmpty()
                .containsIgnoringCase("sp")
                .contains("Sp", "re")
                .doesNotContain("Unknown")
                .isEqualTo("Sphere");
    }

    @Test
    void isNumberOfVerticesThisMinusOne() {
        Box box = new Box(0, -10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex)
                .isNegative()
                .isOdd()
                .isGreaterThan(-2)
                .isLessThan(0)
                .isEqualTo(-1);

    }

    @Test
    void isExistTrue() {
        Box box = new Box(0, 10);
        boolean res = box.isExist();
        assertThat(res)
                .isEqualTo(true)
                .isTrue();
    }

    @Test
    void checkDoubleArea() {
        Box box = new Box(0, 10);
        double res = box.getArea();
        assertThat(res)
                .isEqualTo(1256.637d, withPrecision(0.07d))
                .isCloseTo(1256.637d, withPrecision(0.01d))
                .isCloseTo(1256.637d, Percentage.withPercentage(1.0d))
                .isGreaterThan(1255.0d)
                .isLessThan(1258.0d);
    }
}