package com.example.architecture.converter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by liuzz on 2018/03/19
 *
 * @param <S> source
 * @param <T> target
 */
public interface Converter<S, T> {

    T sourceToTarget(final S s);

    default List<T> sourceToTargetList(final Collection<S> sCollection) {
        if (sCollection == null) {
            return null;
        }
        return sCollection.stream().map(this::sourceToTarget).collect(Collectors.toList());
    }

    S targetToSource(final T t);

    default List<S> targetToSourceList(final Collection<T> tCollection) {
        if (tCollection == null) {
            return null;
        }
        return tCollection.stream().map(this::targetToSource).collect(Collectors.toList());
    }

    default S convertSourceWithMap(final S s, Map<String, Object> map) {
        return s;
    }

    default T convertTargetWithMap(final T t, Map<String, Object> map) {
        return t;
    }
}