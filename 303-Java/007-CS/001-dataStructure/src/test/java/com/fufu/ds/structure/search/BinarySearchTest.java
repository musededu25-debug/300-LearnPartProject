package com.fufu.ds.structure.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * BinarySearch 的测试。
 *
 * <p>写法约定：一个方法测一个版本，方法名用「被测方法_场景」。
 * 每个方法只放几行断言，先保证基础行为，不追求一次覆盖所有边界。
 */
@DisplayName("二分查找")
class BinarySearchTest {

    private static final int[] ARR = {1, 3, 5, 7, 9};

    @Test
    @DisplayName("binarySearch2：左闭右闭，能找到 / 找不到 / 空数组")
    void binarySearch2_basic() {
        Assertions.assertEquals(2, BinarySearch.binarySearch2(ARR, 5));
        Assertions.assertEquals(0, BinarySearch.binarySearch2(ARR, 1));
        Assertions.assertEquals(4, BinarySearch.binarySearch2(ARR, 9));

        Assertions.assertEquals(-1, BinarySearch.binarySearch2(ARR, 0));
        Assertions.assertEquals(-1, BinarySearch.binarySearch2(ARR, 6));
        Assertions.assertEquals(-1, BinarySearch.binarySearch2(new int[]{}, 1));
    }

    @Test
    @DisplayName("binarySearch3：左闭右开，能找到 / 找不到 / 空数组")
    void binarySearch3_basic() {
        Assertions.assertEquals(2, BinarySearch.binarySearch3(ARR, 5));
        Assertions.assertEquals(0, BinarySearch.binarySearch3(ARR, 1));
        Assertions.assertEquals(4, BinarySearch.binarySearch3(ARR, 9));

        Assertions.assertEquals(-1, BinarySearch.binarySearch3(ARR, 6));
        Assertions.assertEquals(-1, BinarySearch.binarySearch3(new int[]{}, 1));
    }

    @Test
    @DisplayName("binarySearch4：平衡版，能找到 / 找不到 / 单元素")
    void binarySearch4_basic() {
        Assertions.assertEquals(2, BinarySearch.binarySearch4(ARR, 5));
        Assertions.assertEquals(0, BinarySearch.binarySearch4(ARR, 1));
        Assertions.assertEquals(4, BinarySearch.binarySearch4(ARR, 9));

        Assertions.assertEquals(-1, BinarySearch.binarySearch4(ARR, 6));
        Assertions.assertEquals(0, BinarySearch.binarySearch4(new int[]{42}, 42));
        Assertions.assertEquals(-1, BinarySearch.binarySearch4(new int[]{42}, 41));
    }

    @Test
    @DisplayName("binarySearch5：重复元素返回最左下标")
    void binarySearch5_leftmost() {
        int[] arr = {1, 2, 2, 2, 3};

        Assertions.assertEquals(1, BinarySearch.binarySearch5(arr, 2));
        Assertions.assertEquals(0, BinarySearch.binarySearch5(arr, 1));
        Assertions.assertEquals(4, BinarySearch.binarySearch5(arr, 3));
        Assertions.assertEquals(-1, BinarySearch.binarySearch5(arr, 4));
    }

    @Test
    @DisplayName("binarySearch6：重复元素返回最右下标")
    void binarySearch6_rightmost() {
        int[] arr = {1, 2, 2, 2, 3};

        Assertions.assertEquals(3, BinarySearch.binarySearch6(arr, 2));
        Assertions.assertEquals(0, BinarySearch.binarySearch6(arr, 1));
        Assertions.assertEquals(4, BinarySearch.binarySearch6(arr, 3));
        Assertions.assertEquals(-1, BinarySearch.binarySearch6(arr, 4));
    }

    @Test
    @DisplayName("binarySearch7：第一个 >= target 的下标（lower bound）")
    void binarySearch7_lowerBound() {
        Assertions.assertEquals(0, BinarySearch.binarySearch7(ARR, 0));
        Assertions.assertEquals(1, BinarySearch.binarySearch7(ARR, 2));
        Assertions.assertEquals(2, BinarySearch.binarySearch7(ARR, 5));
        Assertions.assertEquals(4, BinarySearch.binarySearch7(ARR, 8));
        Assertions.assertEquals(5, BinarySearch.binarySearch7(ARR, 10));

        // 有重复元素时，返回第一个 2
        Assertions.assertEquals(1, BinarySearch.binarySearch7(new int[]{1, 2, 2, 2, 3}, 2));
    }

    @Test
    @DisplayName("binarySearch8：最后一个 <= target 的下标")
    void binarySearch8_upperBound() {
        Assertions.assertEquals(-1, BinarySearch.binarySearch8(ARR, 0));
        Assertions.assertEquals(0, BinarySearch.binarySearch8(ARR, 1));
        Assertions.assertEquals(1, BinarySearch.binarySearch8(ARR, 3));
        Assertions.assertEquals(2, BinarySearch.binarySearch8(ARR, 5));
        Assertions.assertEquals(4, BinarySearch.binarySearch8(ARR, 9));
        Assertions.assertEquals(4, BinarySearch.binarySearch8(ARR, 10));

        // 有重复元素时，返回最后一个 2
        Assertions.assertEquals(3, BinarySearch.binarySearch8(new int[]{1, 2, 2, 2, 3}, 2));
    }

    @Test
    @DisplayName("Arrays.binarySearch：找不到时返回 -(插入点) - 1")
    void arraysBinarySearch_insertionPoint() {
        Assertions.assertEquals(-1, Arrays.binarySearch(ARR, 0));   // 插入点 0
        Assertions.assertEquals(-3, Arrays.binarySearch(ARR, 4));   // 插入点 2
        Assertions.assertEquals(-6, Arrays.binarySearch(ARR, 10));  // 插入点 5

        int result = Arrays.binarySearch(ARR, 4);
        int insertIndex = -result - 1;
        Assertions.assertEquals(2, insertIndex);
    }
}
