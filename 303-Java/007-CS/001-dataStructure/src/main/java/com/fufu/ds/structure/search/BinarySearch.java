package com.fufu.ds.structure.search;

public class BinarySearch {

//
//    /**
//     * 二分查找基础版本
//     * 找到则返回target的下标
//     * 若是找不到则返回-1
//     * @param arr  目标数组
//     * @param target  目标数值
//     */
//    public static int  binarySearch1(int[] arr , int target){
//        int i = 0;
//        int j = arr.length - 1;
//        while(i <= j){
//            int mid = (i + j)/2;
//            if(arr[mid]<target){
//                i = mid + 1;
//            }
//            else if(arr[mid]>target){
//                j = mid - 1;
//            }else{
//                return mid;
//            }
//        }
//        return -1;
//    }

    /**
     * 问题1：为什么while里面是i<=j而不是i<j
     * 会少判断一次
     */

    /**
     * 2.设计缺陷整数越界该用 >>>
     *
     *  * 问题3:代码为什么都要用小于号
     *      * 数组递增小于也更符合阅读逻辑
     */
    /**
     * 二分查找基础版本
     * 找到则返回target的下标
     * 若是找不到则返回-1
     * 类型：左闭右闭
     * @param arr  目标数组
     * @param target  目标数值
     */
    public static int  binarySearch2(int[] arr , int target){
        int i = 0;
        int j = arr.length - 1;
        while(i <= j){
            int mid = (i + j)>>>1;
            if(arr[mid]<target){
                i = mid + 1;
            }
            else if(target<arr[mid]){
                j = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找进阶版本
     * 找到则返回target的下标
     * 若是找不到则返回-1
     * 类型：左闭右开
     * j指向的永远不是目标数字
     * @param arr  目标数组
     * @param target  目标数值
     */
    public static int binarySearch3(int[] arr , int target){
        int i = 0 ,j = arr.length;
        while(i < j){
            int mid = (i + j)>>>1;
            if(arr[mid]<target){
                i = mid + 1;
            }
            else if(target<arr[mid]){
                j = mid;
            }else{
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找平衡版本
     * 找到则返回target的下标
     * 若是找不到则返回-1
     * 类型：左闭右开
     * j指向的永远不是目标数字
     * @param arr  目标数组
     * @param target  目标数值
     */
    public static int binarySearch4(int[] arr , int target){
        int i = 0 ,j = arr.length;
        while(1 < j - i){
            int mid = (i + j) >>> 1;
            if(target<arr[mid]){
                j = mid;
            }else {
                i = mid;
            }
        }
        if(arr[i] == target){
            return i;
        }else {
            return -1;
        }
    }

    /**
     * 二分查找有重复数值找最左侧版本
     * 找到则返回target的下标
     * 若是找不到则返回-1
     * 类型：左闭右闭
     * @param arr  目标数组
     * @param target  目标数值
     */
    public static int binarySearch5(int[] arr , int target){
        int i = 0;
        int j = arr.length - 1;
        int candidate = -1;
        while(i <= j){
            int mid = (i + j)/2;
            if(arr[mid]<target){
                i = mid + 1;
            }
            else if(arr[mid]>target){
                j = mid - 1;
            }else{
                candidate = mid;
                j = mid - 1;
            }
        }
        return candidate;
    }

    /**
     * 二分查找有重复数值找最右侧版本
     * 找到则返回target的下标
     * 若是找不到则返回-1
     * 类型：左闭右闭
     * @param arr  目标数组
     * @param target  目标数值
     */
    public static int binarySearch6(int[] arr , int target){
        int i = 0;
        int j = arr.length - 1;
        int candidate = -1;
        while(i <= j){
            int mid = (i + j)/2;
            if(arr[mid]<target){
                i = mid + 1;
            }
            else if(arr[mid]>target){
                j = mid - 1;
            }else{
                candidate = mid;
                i = mid + 1;
            }
        }
        return candidate;
    }

    /**
     * 二分查找有大于等于目标最靠左的位置
     * 找到则返回target的下标
     * 若是找不到则返回i
     * 类型：左闭右闭
     * @param arr  目标数组
     * @param target  目标数值
     */
    public static int binarySearch7(int[] arr , int target){
        int i = 0;
        int j = arr.length - 1;
        while( i <= j){
            int mid = (i + j) >>> 1;
            if(target <= arr[mid]){
                j = mid - 1;
            }else if(arr[mid]<target) {
                i = mid + 1;
            }
        }
        return i;
    }

    /**
     * 二分查找有最后一个 ≤ target的位置
     * 找到则返回target的下标
     * 若是找不到则返回-1
     * 类型：左闭右闭
     * @param arr  目标数组
     * @param target  目标数值
     */
    public static int binarySearch8(int[] arr , int target){
        int i = 0;
        int j = arr.length - 1;
        while( i <= j){
            int mid = (i + j) >>> 1;
            if(target < arr[mid]){
                j = mid - 1;
            }else if(arr[mid] <= target) {
                i = mid + 1;
            }
        }
        return i -1;
    }


    /**
     * 常用于秋排名，大于，大于等于，小于，小于等于
     */
}
