package datastructures.hashing;

import java.util.Map;

/**
 * Created by rkasha on 3/5/19.
 */
public class HashUtils {
    public static void printMap(Map<String, Integer> map) {
        for(Map.Entry e : map.entrySet()) {
            System.out.println(e.getKey()+"->"+e.getValue());
        }
    }
}
