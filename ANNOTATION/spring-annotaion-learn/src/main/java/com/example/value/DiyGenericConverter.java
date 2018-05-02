package com.example.value;

import java.util.HashSet;
import java.util.Set;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.Nullable;

/**
 * ConversionServiceFactoryBean
 *
 * Created by liuzz on 2018/04/17
 */
public class DiyGenericConverter implements GenericConverter {

    @Nullable
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> sets = new HashSet<>();
        ConvertiblePair convertiblePair = new ConvertiblePair(String.class, Goods.class);
        sets.add(convertiblePair);
        return sets;
    }

    @Nullable
    @Override
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        String str = (String) source;
        System.out.println(str);
        return new Goods(1024);
    }
}
