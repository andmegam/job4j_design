package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analise {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> userMap = new HashMap<>();

        for (User itemPrev : previous) {
            userMap.put(itemPrev.getId(), itemPrev.getName());
        }

        for (User itemCurr : current) {
            if (!userMap.containsKey(itemCurr.getId())) {
                added++;
            } else if (!userMap.get(itemCurr.getId()).equals(itemCurr.getName())) {
                changed++;
            }
            userMap.remove(itemCurr.getId());
        }
        deleted = userMap.size();
        return new Info(added, changed, deleted);
    }
}
