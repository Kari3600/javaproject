package com.kari3600.me.javaproject.utils;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionUtil {
    public static <T> T getRandomElement(Collection<T> collection) {
        return new ArrayList<T>(collection).get((int) (Math.random()*collection.size()));
    }
}
