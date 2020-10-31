package com.ljf.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/3/2 10:18
 * @modified By：
 * @version: 1.0
 */
public class LetterCasePermutation {

  public List<String> letterCasePermutation(String S) {
    List<StringBuilder> ans = new ArrayList();
    ans.add(new StringBuilder());

    for (char c : S.toCharArray()) {
      int n = ans.size();
      if (Character.isLetter(c)) {
        for (int i = 0; i < n; ++i) {
          ans.add(new StringBuilder(ans.get(i)));
          ans.get(i).append(Character.toLowerCase(c));
          ans.get(n + i).append(Character.toUpperCase(c));
        }
      } else {
        for (int i = 0; i < n; ++i) {
          ans.get(i).append(c);
        }
      }
    }

    List<String> finalans = new ArrayList();
    for (StringBuilder sb : ans) {
      finalans.add(sb.toString());
    }
    return finalans;
  }


  public static void main(String[] args) {
    LetterCasePermutation permutation = new LetterCasePermutation();
    System.out.println(permutation.letterCasePermutation("abc"));
  }
}
