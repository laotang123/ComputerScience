package com.ljf.algorithm.greed;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/2/29 8:51
 * @modified By：
 * @version: 1.0
 * https://leetcode-cn.com/problems/assign-cookies/
 */
public class FindContentChildren {

  public int findContentChildren(int[] g, int[] s) {
    int res = 0;

    //判空
    if (s.length == 0 || s == null) {
      return res;
    }

    Arrays.sort(s);
    Arrays.sort(g);
    int si = s.length - 1;
    int gi = g.length - 1;

    //饼干分完或者小孩分完，循环结束
    while (si >= 0 && gi >= 0) {
      if (s[si] >= g[gi]) {//当前饼干合适，同时++
        si--;
        gi--;
        res++;
      } else {//饼干太小
        gi--;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    int[] g = {10, 9, 8, 7};
    int[] s = {5, 6, 7, 8};

    FindContentChildren find = new FindContentChildren();
    System.out.println(find.findContentChildren(g, s));
  }
}
