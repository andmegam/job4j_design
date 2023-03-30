package ru.job4j.question;

import java.util.Objects;

public class User {

    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return true;
        }

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
