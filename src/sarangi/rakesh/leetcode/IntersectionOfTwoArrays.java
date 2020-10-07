package sarangi.rakesh.leetcode;

import java.util.*;

public class IntersectionOfTwoArrays {

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        List<Integer> intersection = new LinkedList<>();

        for (int num: nums1) {
            if (map1.containsKey(num)) {
                map1.put(num, map1.get(num) + 1);
            } else {
                map1.put(num, 1);
            }
        }
        for (int num: nums2) {
            if (map1.containsKey(num)) {
                intersection.add(num);
                map1.put(num, map1.get(num) - 1);
                if (map1.get(num) == 0) {
                    map1.remove(num);
                }
            }
        }
        return intersection.stream().mapToInt(a -> a).toArray();
    }
}
