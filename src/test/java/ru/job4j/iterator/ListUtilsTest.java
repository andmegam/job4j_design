package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    public void whenAddAfterLast() {
        ListUtils.addAfter(input, 1, 4);
        assertThat(input).hasSize(3).containsSequence(1, 3, 4);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIfEvenNumber() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.removeIf(input, item -> item % 2 == 0);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    public void whenNothingRemove() {
        ListUtils.removeIf(input, item -> item == 0);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    public void whenReplaceTo10() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.replaceIf(input, item -> item % 2 == 0, 10);
        assertThat(input).hasSize(4).containsSequence(1, 10, 3, 10);
    }

    @Test
    public void whenNothingReplace() {
        ListUtils.replaceIf(input, item -> item == 0, 10);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    public void whenReplaceAll() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        List<Integer> element = new ArrayList<>(Arrays.asList(2, 4));
        ListUtils.removeAll(input, element);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }
}