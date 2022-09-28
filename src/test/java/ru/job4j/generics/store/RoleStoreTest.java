package ru.job4j.generics.store;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    public void whenAddAndFindThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("admin");
    }

    @Test
    public void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    public void whenAddDuplicateAndFindUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.add(new Role("1", "guest"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("admin");
    }

    @Test
    public void whenReplaceThenUsernameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.replace("1", new Role("1", "guest"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("guest");
    }

    @Test
    public void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.replace("10", new Role("10", "guest"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("admin");
    }

    @Test
    public void whenDeleteUserThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    public void whenNoDeleteUserThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("admin");
    }
}