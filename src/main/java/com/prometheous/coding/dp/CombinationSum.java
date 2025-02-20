package com.prometheous.coding.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CombinationSum {

    public static void main(String[] args) {
//        int[] c = {2, 3, 6, 7};
        int[] c = {1, 2};
        combinationSum(c, 3)
            .stream()
            .forEach(l -> {
                l.forEach(i -> System.out.print(i + " "));
                System.out.println();
            });
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>>[] dp = new ArrayList[target + 1];
        for(int i = 0; i < candidates.length; i++) {    // Initialize all candidates combinations
            if(candidates[i] < dp.length) {
                List<Integer> combinations = new ArrayList<>();
                combinations.add(candidates[i]);
                dp[candidates[i]] = new ArrayList<>();
                dp[candidates[i]].add(combinations);
            }
        }

        List<List<Integer>> zeroth = new ArrayList<>();
        zeroth.add(new ArrayList<>());
        dp[0] = zeroth;     // Initialize zeroth element

        // Find the ith number combinations
        for(int i = 1; i <= target; i++) {
            for(int k = 0; k < candidates.length; k++) {
                if(i - candidates[k] > 0) {
                    List<List<Integer>> combinations = dp[i - candidates[k]];
                    if(combinations != null) {
                        dp[i] = dp[i] != null ? dp[i] : new ArrayList<List<Integer>>();

                        // Add all the new combinations
                        for(List<Integer> combination : combinations) {
                            List<Integer> c = new ArrayList<>(combination);
                            c.add(candidates[k]);
                            if(!present(c, dp[i])) {
                                dp[i].add(c);
                            }
                        }
                    }
                }
            }
        }
        return dp[target] == null ? new ArrayList<>() : dp[target];
    }

    private static boolean present(List<Integer> combination, List<List<Integer>> lists) {
        Collections.sort(combination);
        for(List<Integer> list : lists) {
            boolean match = true;
            if(list.size() != combination.size()) match = false;
            else {
                Collections.sort(list);
                for(int i = 0; i < list.size(); i++) {
                    if(list.get(i) != combination.get(i)) {
                        match = false;
                        break;
                    }
                }
            }
            if(match) return true;
        }
        return false;
    }

}
