//https://www.geeksforgeeks.org/find-number-of-employees-under-every-manager/
package datastructures.hashing.countreportees;

import datastructures.hashing.HashUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rkasha on 3/5/19.
 */
public class TestClass {
    static Map<String, Integer> result = new HashMap<>();

    public static void main(String[] args) throws IOException {

        final Map<String, String> relationMap = new HashMap<String, String>() {
            {
                put("A", "C");
                put("B", "C");
                put("C", "F");
                put("D", "E");
                put("E", "F");
                put("F", "F");
            }
        };

        final Map<String, List<String>> empMgrMap = new HashMap<>();
        for (Map.Entry e : relationMap.entrySet()) {
            String mgr = (String) e.getValue();
            if (!empMgrMap.containsKey(mgr)) {
                empMgrMap.put(mgr, new ArrayList<String>());
            }
            empMgrMap.get(mgr).add((String) e.getKey());
        }
        for (String mgr : empMgrMap.keySet()) {
            countreportees(empMgrMap, mgr);
        }
        HashUtil.printMap(result);

    }

    public static int countreportees(Map<String, List<String>> empMgrMap, String mgr) {

        int count = 0;
        if (result.containsKey(mgr)) {
            return result.get(mgr);
        }

        if (empMgrMap.get(mgr) != null) {
            for (String emp : empMgrMap.get(mgr)) {
                if (!emp.equals(mgr))
                    count = count + 1 + countreportees(empMgrMap, emp);
            }
        }
        result.put(mgr, count);
        return count;

    }

}
