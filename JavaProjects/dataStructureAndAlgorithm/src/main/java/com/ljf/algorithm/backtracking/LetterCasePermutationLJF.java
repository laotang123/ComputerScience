package com.ljf.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/3/3 10:33
 * @modified By：
 * 思路
 *https://leetcode-cn.com/problems/letter-case-permutation/solution/zi-mu-da-xiao-xie-quan-pai-lie-by-leetcode/
 * 从左往右依次遍历字符，过程中保持 ans 为已遍历过字符的字母大小全排列。
 *
 * 例如，当 S = "abc" 时，考虑字母 "a", "b", "c"，初始令 ans = [""]，依次更新 ans = ["a", "A"]， ans = ["ab", "Ab", "aB", "AB"]， ans = ["abc", "Abc", "aBc", "ABc", "abC", "AbC", "aBC", "ABC"]。
 *
 * 算法
 *
 * 如果下一个字符 c 是字母，将当前已遍历过的字符串全排列复制两份，在第一份的每个字符串末尾添加 lowercase(c)，在第二份的每个字符串末尾添加 uppercase(c)。
 *
 * 如果下一个字符 c 是数字，将 c 直接添加到每个字符串的末尾。
 * @version: 1.0
 */
public class LetterCasePermutationLJF {

  /*2**N种排列组合，遍历字符串分为两种情况
  1.如果是字母需要将现有的builder复制一份，一份追加小写，一份追加大写
  2.如果是数字不用复制builder，直接追加到现有的builder中
   */
  public List<String> letterCasePermutation(String S) {
    List<String> resList = new ArrayList<>();
    List<StringBuilder> ans = new ArrayList<>();

    //判空
    if (S == null || S.length() == 0) {
      return null;
    }

    int num = 0;
    //结果集中先追加一个builder
    ans.add(new StringBuilder());
    for (char c : S.toCharArray()) {
      int n = ans.size();
      if (Character.isLetter(c)) {
        //1.如果是字母需要将现有的builder复制一份，一份追加小写，一份追加大写
        for (int i = 0; i < n; i++) {
          ans.add(new StringBuilder(ans.get(i)));
          ans.get(i).append(Character.toLowerCase(c));//这是原有的一份
          ans.get(i + n).append(Character.toUpperCase(c)); //这是复制的一份

          num++;
        }
      } else {
        //如果是数字，遍历现有的所有builder，逐个追加
        for (int i = 0; i < n; i++) {
          ans.get(i).append(c);
        }
      }
    }

    //ans保存的就是最终结果
    for (StringBuilder builder : ans) {
      resList.add(builder.toString());
    }
    System.out.println("字符串长度：" + S.length() + "\t共计算：" + num);
    return resList;
  }

  public static void main(String[] args) {
    System.out.println(Character.isLetter('1'));
    LetterCasePermutationLJF ljf = new LetterCasePermutationLJF();
    System.out.println(ljf.letterCasePermutation("abcdef"));
  }
}
