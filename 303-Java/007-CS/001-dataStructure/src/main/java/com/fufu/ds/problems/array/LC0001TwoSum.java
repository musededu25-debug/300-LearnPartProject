package com.fufu.ds.problems.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1. 两数之和（示例文件，可保留可删除）
 *
 * <p>题意：给定整数数组 nums 和目标值 target，返回和为 target 的两个元素的下标。
 * 题目保证恰好有一个答案，且同一元素不能重复使用。
 *
 * <p>思路一（暴力）：两层循环枚举所有下标对，时间 O(n²)、空间 O(1)。
 * <br>思路二（哈希表）：边遍历边把「值 → 下标」存进 Map，
 * 对当前 nums[i] 只要查 target - nums[i] 是否出现过即可，时间 O(n)、空间 O(n)。
 *
 * <p>笔记：07-Algorithms/NN-专题/LC0001-两数之和.md（同步笔记时补上实际路径）
 */
public class LC0001TwoSum {

    /**
     * 哈希表解法。
     *
     * @param nums   整数数组，长度至少为 2
     * @param target 目标和
     * @return 两个不同下标，顺序不限
     * @throws IllegalArgumentException 无解时抛出（LeetCode 保证有解，这里显式失败便于测试）
     */
    public int[] twoSum(int[] nums, int target) {
        // key：已经见过的元素值；value：它在数组里的下标
        Map<Integer, Integer> indexByValue = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];

            // 先查再存：保证返回的两个下标一定不同，
            // 同时天然处理了「重复值」的情况（例如 [3, 3], target = 6）
            Integer j = indexByValue.get(need);
            if (j != null) {
                return new int[]{j, i};
            }

            indexByValue.put(nums[i], i);
        }

        throw new IllegalArgumentException("数组中不存在两数之和等于 " + target);
    }

    /**
     * 暴力解法，只用于对照和理解题意。
     *
     * <p>时间复杂度 O(n²)，空间复杂度 O(1)。
     */
    public int[] twoSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        throw new IllegalArgumentException("数组中不存在两数之和等于 " + target);
    }
}
