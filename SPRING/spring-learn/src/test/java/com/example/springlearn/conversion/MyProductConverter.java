//package com.example.springlearn.conversion;
//
//import com.example.springlearn.model.Product;
//import com.google.gson.Gson;
//import java.util.HashSet;
//import java.util.Set;
//import org.springframework.core.convert.TypeDescriptor;
//import org.springframework.core.convert.converter.GenericConverter;
//import org.springframework.lang.Nullable;
//
///**
// * Created by liuzz on 2018/04/26
// */
//public class MyProductConverter implements GenericConverter {
//
//    @Nullable
//    @Override
//    public Set<ConvertiblePair> getConvertibleTypes() {
//        System.out.println("=============================11111");
//        Set<ConvertiblePair> sets = new HashSet<>();
//        ConvertiblePair convertiblePair = new ConvertiblePair(String.class, Product.class);
//        sets.add(convertiblePair);
//        return sets;
//    }
//
//    @Nullable
//    @Override
//    public Object convert(@Nullable Object o, TypeDescriptor sourceType, TypeDescriptor targetType) {
//        System.out.println("=============================22222");
//        System.out.println(sourceType.getType());
//        System.out.println(targetType.getType());
//        System.out.println(o);
//        if (sourceType.getType().equals(String.class) && targetType.getType().equals(Product.class)) {
//            return new Gson().fromJson(String.valueOf(o), Product.class);
//        }
//        return null;
//    }
//}
