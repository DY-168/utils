package com.spacedo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        for (Integer b : a) {
            a.remove(b);
        }
        a.forEach(b -> {
            a.remove(b);
        });
        Iterator<Integer> iterator = a.iterator();
        while (iterator.hasNext()) {
            Integer e = iterator.next();
            iterator.remove();
        }
    }
}
