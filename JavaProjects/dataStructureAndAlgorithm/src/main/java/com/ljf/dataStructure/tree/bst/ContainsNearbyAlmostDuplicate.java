package com.ljf.dataStructure.tree.bst;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/7 9:53
 * @modified By：
 * @version: $
 */
public class ContainsNearbyAlmostDuplicate {

  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = Math.max(0, i - k); j < i; j++) {
        if (Math.abs(nums[i] - nums[j]) <= t) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int[] nums1 = {1, 2, 3, 1};
    int[] nums2 = {1, 0, 1, 1};
    int[] nums3 = {1, 5, 9, 1, 5, 9};

    ContainsNearbyAlmostDuplicate duplicate = new ContainsNearbyAlmostDuplicate();
    System.out.println(duplicate.containsNearbyAlmostDuplicate(nums1,3,0));
    System.out.println(duplicate.containsNearbyAlmostDuplicate(nums2,1,2));
    System.out.println(duplicate.containsNearbyAlmostDuplicate(nums3,2,3));
  }
}
