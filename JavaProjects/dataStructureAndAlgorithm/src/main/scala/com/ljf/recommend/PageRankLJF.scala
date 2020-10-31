package com.ljf.recommend

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
 * @author ：ljf
 * @date ：Created in 2020/3/31 9:36
 * @description：
 * @modified By：
 * @version: 1.0
 */
object PageRankLJF {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder
      .appName("SparkPageRank")
      .master("local[*]")
      .getOrCreate()

    //迭代次数
    val iters: Int = 10

    //模拟数据    //KV格式
    val lines = spark.sparkContext.parallelize(List(
      //A指向B
      ("A", "B"),
      ("A", "C"),
      ("B", "A"),
      ("B", "C"),
      ("C", "A"),
      ("C", "B"),
      ("C", "D"),
      ("D", "C")
    ))

    //后面经常使用的数据，添加到缓存中加快速度
    val links: RDD[(String, Iterable[String])] = lines.groupByKey().cache()

    val kLen: Long = links.keys.count()

    //    links.foreach(println)

    //出链替换成PR初始值
    var ranks: RDD[(String, Double)] = links.mapValues(v => 1.0 / kLen)

    //迭代计算,什么时间用case？，什么时间不用
    for (i <- 1 to iters) {
      val contribs: RDD[(String, Double)] = links.join(ranks).values.flatMap {  case (urls, rank) =>{
        val size: Int = urls.size
        urls.map(url => (url, rank / size))
      }
      }
      //做聚合操作，入链权重累加
      ranks = contribs.reduceByKey(_ + _).mapValues(0.15 / kLen + 0.85 * _)
    }

    ranks.collect().foreach(tup => println(s"${tup._1} hash rank: ${tup._2} "))
    println(ranks.values.sum())

    spark.stop()

  }
}
