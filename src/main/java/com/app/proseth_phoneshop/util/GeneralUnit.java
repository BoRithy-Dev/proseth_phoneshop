package com.app.proseth_phoneshop.util;

import java.util.List;
import java.util.Map;

public class GeneralUnit {
    //convet string array list to interger list
    //String[] name = {"Rithy","Proseth","Lyly"}
    //=> [5,7,8]
    public  static  List<Integer> toIntegerList(List<String> list){
        return list.stream()
                .map(String::length)
                .toList();
    }
    public static List<Integer> getEvenNumber(List<Integer> list){
        return list.stream()
                .filter(x->x%2==0)
                .toList();
    }
}
