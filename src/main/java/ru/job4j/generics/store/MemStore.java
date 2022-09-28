package ru.job4j.generics.store;

import java.util.HashMap;
import java.util.Map;

public class MemStore<T extends Base> implements Store<T> {
    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        if (findById(model.getId()) == null) {
            storage.put(model.getId(), model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        boolean res = false;
        if (findById(model.getId()) != null) {
            storage.replace(model.getId(), model);
            res = true;
        }
        return res;
    }

    @Override
    public boolean delete(String id) {
        boolean res = false;
        T obj = findById(id);
        if (obj != null) {
            res = storage.remove(id, obj);
        }
        return res;
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}