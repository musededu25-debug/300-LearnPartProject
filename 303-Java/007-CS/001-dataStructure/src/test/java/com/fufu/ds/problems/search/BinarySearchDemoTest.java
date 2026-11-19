package com.fufu.ds.problems.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/11/19 14:07
 */


public class BinarySearchDemoTest {

    @Test
    @DisplayName("LeetCode704")
    public void leetCode704() {

    }
    public int search_v1(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while( i <= j){
            int mid = (i + j) >>>1;
            if(target < nums[mid]){
                j = mid - 1;
            }else if (nums[mid] < target){
                i = mid +1;
            }else {
                return mid;
            }
        }
        return -1;
    }
    public int search_v2(int[] nums, int target) {
        int i = 0, j = nums.length;
        while( i < j){
            int mid = (i + j) >>>1;
            if(target < nums[mid]){
                j = mid;
            }else if (nums[mid] < target){
                i = mid +1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    public int search_v3(int[] nums, int target) {
        int i = 0, j = nums.length;
        while( 1 < j - i){
            int mid = (i + j) >>>1;
            if(target < nums[mid]){
                j = mid;
            }else {
                i = mid;
            }
        }
        if(target == nums[i]){
            return i;
        }else {
            return -1;
        }
    }


    @Test
    @DisplayName("LeetCode35")
    public void leetCode35() {

    }

    public int searchInsert(int[] nums, int target) {

        int i = 0, j = nums.length - 1;
        while( i <= j){
            int mid = (i + j) >>>1;
            if(target <= nums[mid]){
                j = mid - 1;
            }else {
                i = mid +1;
            }
        }
        return i;
    }


}
