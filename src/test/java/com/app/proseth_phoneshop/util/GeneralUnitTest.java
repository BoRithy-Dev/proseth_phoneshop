package com.app.proseth_phoneshop.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneralUnitTest {
    @Test
//    public void toIntegerListTest(){
//        //Given
//        List<String> name = List.of("Ruthy","Proseth","Lyly");
//        //When
//        List<Integer> integerList = GeneralUnit.toIntegerList(name);
//        //Then
//        assertEquals(3, integerList.size());
//        assertEquals(5,integerList.get(0));
//        assertEquals(7,integerList.get(1));
//        assertEquals(4,integerList.get(2));
//    }
    public void getEvenNumberTest(){
        //Given
        List<Integer>  list = List.of(4,2,3,10,5,6);
        //When
        List<Integer> evenNumber = GeneralUnit.getEvenNumber(list);
        //Then
        assertEquals(4, evenNumber.size());
        assertEquals(4,evenNumber.get(0));
        assertEquals(2,evenNumber.get(1));
        assertEquals(10,evenNumber.get(2));
        assertEquals(6,evenNumber.get(3));
    }
}
