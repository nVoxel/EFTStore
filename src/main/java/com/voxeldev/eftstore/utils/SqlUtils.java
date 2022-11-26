package com.voxeldev.eftstore.utils;

import java.util.List;
import java.util.Optional;

public class SqlUtils {
    public static <T> Optional<T> getSingleResult(List<T> list) {
        if (list.isEmpty()) {
            return Optional.empty();
        }
        
        return Optional.of(list.get(0));
    }
}
