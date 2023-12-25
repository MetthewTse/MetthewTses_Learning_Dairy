package algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 */
public class Algorithm_1_twoSum {
    public static void main(String[] args) {
        int[] ints = betterSolution(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] tuoSumOfMySolution(int[] nums,int target){
        if (nums.length==0){
            return nums;
        }

        for (int i=0;i<nums.length;i++) {
            for (int j=nums.length-1;j>i;j--){
                if (nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return nums;
    }

    /**
     * 思路：1.数组值作为key，下标作为value,通过哈希查找key做匹配
     * @param nums
     * @param target
     * @return
     */
    public static int[] betterSolution(int[] nums,int target){
        Map<Integer,Integer> targetMap = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (targetMap.containsKey(target-nums[i])){
                return new int[]{targetMap.get(target-nums[i]),i};
            }
            targetMap.put(nums[i],i);
        }
        throw new RuntimeException("NOT FOUND");
    }
}
