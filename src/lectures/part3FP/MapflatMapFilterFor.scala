package org.shibut
package lectures.part3FP

object MapflatMapFilterFor extends  App {

  val list = List(1,2,3)
  println(list)

  println(list.head)
  println(list.tail)


  // map
  println(list.map( _ + 1 ) )

  println(list.map(_ + " is a number"))


  // filter
  println(list.filter(_ % 2 == 0))


  // flatMap

  val toPair = (x: Int) => List(x, x+1)

  println(list.flatMap(toPair))

  // print all combinations of two lists
  // "a1", "a2",..... "d4"

  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')
  val colors = List("white", "black")


  // "iterations"
  val combinations = numbers.flatMap( n => chars.flatMap( c => colors.map(color =>  "" + c + n + "-" + color )))
  println(combinations)

  // foreach ~ map except that it returns Unit
  list.foreach(println)

  // for-comprehensions

  // compiler rewrites this to map,flatmap and filter calls
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  }yield "" + c + n + "-" + color

  println(forCombinations)

  // alternative syntax
  list.map { x =>
    x * 2
  }



}
