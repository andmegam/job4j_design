package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkNotEmpty() {
        NameLoad nameLoad = new NameLoad();
        nameLoad.parse("param1=12345");
        Map<String, String> map = nameLoad.getMap();
        assertThat(map.get("param1")).isEqualTo("12345");
    }

    @Test
    void checkParse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkParseNotContainSymbol() {
        NameLoad nameLoad = new NameLoad();
        String name = "string";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("this name: " + name + " does not contain the symbol \"=\"")
                .hasMessageContaining(name);
    }

    @Test
    void checkParseNotContainKey() {
        NameLoad nameLoad = new NameLoad();
        String name = "=value1";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("this name: " + name + " does not contain a key")
                .hasMessageContaining(name);
    }

    @Test
    void checkParseNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        String name = "key1=";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("this name: " + name + " does not contain a value")
                .hasMessageContaining(name);
    }
}