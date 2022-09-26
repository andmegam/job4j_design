package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five", null);
        assertThat(list)
                .isNotNull()
                .hasSize(6)
                .startsWith("first")
                .contains("second", "five")
                .doesNotContain("zero", "one")
                .containsSequence("three", "four", "five")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));


        assertThat(list)
                .first()
                .isEqualTo("first");

        assertThat(list)
                .element(3)
                .isNotNull()
                .isEqualTo("four");

        assertThat(list)
                .last()
                .isNull();
    }

    @Test
    void checkSubList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five", null);
        Predicate<String> filter1 = (e) -> (e != null && e.length() > 5);

        assertThat(list).filteredOn(filter1).first().isEqualTo("second");

        Predicate<String> filter2 = (e) -> (e != null && e.length() <= 4);
        assertThat(list).filteredOn(filter2)
                .hasSize(2)
                .contains("four", "five");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "first", "second", "second", "three", "four", "five");
        assertThat(set)
                .isNotNull()
                .hasSize(5);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five", "five", null);
        assertThat(map)
                .isNotNull()
                .hasSize(6)
                .containsKeys("first", "second", "three", "four", "five")
                .containsValues(1, 3, 6)
                .doesNotContainKey("zero")
                .doesNotContainValue(10)
                .containsEntry("five", 4);
    }
}