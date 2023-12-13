package com.example.biz.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xingche
 * @date 2022/1/22
 */
public class Solution {
    public static boolean isValidBST(TreeNode root) {
        Boolean flag = true;
        List<Integer> array = new ArrayList();
        checkNode(root, flag, array);
        return flag;
    }

    public static void checkNode(TreeNode root, Boolean flag, List<Integer> array){
        if(!flag || root == null){
            return;
        }
        if(root.left != null){
            checkNode(root.left, flag, array);
        }
        array.add(root.val);
        if(array.size() > 1 && (array.get(array.size() - 1) < array.get(array.size() - 2))){
            flag = false;
        }
        if(root.right != null){
            checkNode(root.right, flag, array);
        }
        System.out.println();
    }
}
