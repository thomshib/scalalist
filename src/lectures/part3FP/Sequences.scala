package org.shibut
package lectures.part3FP

import scala.util.Random

object Sequences  extends  App{

  // Seq

  val aSeq = Seq(1,2,3,4, 7,6)
  println(aSeq.reverse)
  println(aSeq(2))
  println(aSeq ++ Seq(5,6,7))
  println(aSeq.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)

  (1 to 10).foreach(x => println("Hello"))

  //lists
  val aList = List(1,2,3)
  val prepend = 42 ::  aList
  println(prepend)
  //supports +: and :+ for prepend and append
  val anotherList = 42 +: aList :+ 43
  println(anotherList)

  val apple3 = List.fill(3)("apple")
  println(apple3)

  println(aList.mkString("-|-"))


  // arrays
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  threeElements.foreach(println)

  // mutations
  numbers(2) = 0  // syntatic sugar for numbers.update(2,0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers  //implict conversion
  println(numbersSeq)

  // vectors

  val vectors: Vector[Int] = Vector(1,2,3)
  println(vectors)

  // vectors vs lists

  val maxRuns = 1000
  val maxCapacity = 100000

  def getWriteTime(collection: Seq[Int]): Double ={
    val r = new Random
    val times = for(
      it <- 1 to maxRuns
    )yield{
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity),r.nextInt())
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  // keeps reference to the tail
  // updating the element in the middle takes a long tim
  val numbersList = (1 to maxCapacity).toList

  // depth of the tree is small
  // needs to replace an entire 32 element chunk
  val numbersVector = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector))






}
