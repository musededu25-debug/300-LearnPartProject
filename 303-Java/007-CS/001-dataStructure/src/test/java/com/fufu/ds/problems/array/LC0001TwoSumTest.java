package com.fufu.ds.problems.array;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * LC0001TwoSum 的单元测试。
 *
 * <p>约定：测试类和被测类同包、同名加 Test 后缀，方便 IDE 和 Maven 定位。
 * <p>这个文件同时也是「一题一个类 + 一个测试类」的写法示例：
 * 同一题的多种解法放在同一个被测类里，用不同方法区分，不必为每种解法建文件。
 */
@DisplayName("LC 1. 两数之和")
class LC0001TwoSumTest {

    private final LC0001TwoSum solution = new LC0001TwoSum();

    @Test
    @DisplayName("示例：nums = [2,7,11,15], target = 9 → [0,1]")
    void example() {
        assertThat(solution.twoSum(new int[]{2, 7, 11, 15}, 9))
                .containsExactly(0, 1);
    }

    @Test
    @DisplayName("重复元素：必须返回两个不同的下标")
    void duplicateValues() {
        assertThat(solution.twoSum(new int[]{3, 3}, 6))
                .containsExactly(0, 1);
    }

    @Test
    @DisplayName("含负数：nums = [-3,4,3,90], target = 0 → [0,2]")
    void negativeNumbers() {
        assertThat(solution.twoSum(new int[]{-3, 4, 3, 90}, 0))
                .containsExactly(0, 2);
    }

    @Test
    @DisplayName("暴力解与哈希解在同一组数据上结果一致")
    void bruteForceAndHashAgree() {
        int[] nums = {2, 5, 5, 11};

        assertThat(solution.twoSumBruteForce(nums, 10))
                .containsExactlyInAnyOrder(solution.twoSum(nums, 10).clone());
    }

    @Test
    @DisplayName("无解时抛出 IllegalArgumentException")
    void noSolution() {
        assertThatThrownBy(() -> solution.twoSum(new int[]{1, 2}, 100))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
