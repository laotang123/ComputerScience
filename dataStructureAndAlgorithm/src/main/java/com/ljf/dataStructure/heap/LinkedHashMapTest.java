package com.ljf.dataStructure.heap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/14 10:40
 * @modified By：
 * @version: 1.0
 */
public class LinkedHashMapTest {

  public static void main(String[] args) {
    HashMap<Integer, Integer> map = new LinkedHashMap<>(10, 0.75f, true);
    map.put(3, 11);
    map.put(1, 12);
    map.put(5, 23);
    map.put(2, 22);

    map.put(3,26);
    map.get(5);

    for (Map.Entry e : map.entrySet()) {
      System.out.println(e.getKey() + "\t");
    }
  }
}
