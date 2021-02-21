package org.shibut
package lectures.part3FP

object WhatsAFunction extends  App {


  // Functional Programming: use Functions as first class elements

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int) =  element * 2
  }

  println(doubler(2))

  // instead of doing all this Scala supports function types upto 22 params
  // function types = Function1[A,B]


  val stringToIntConverter = new Function[String,Int] {
    override def apply(v1: String): Int = v1.toInt
  }

  println(stringToIntConverter("3") + 4)


  val adder : ((Int,Int) => Int) = new Function2[Int,Int,Int] {
    override def apply(v1: Int, v2: Int): Int =  v1 + v2
  }

  /*
    Function2[A,B,R] = (A,B) => R

    ALL SCALA FUNCTIONS ARE OBJECTS

   */

  println(adder(4,6))



  /*
    1. a function that takes two strings and concatenates them

   */

  val stringAdder = new ((String,String) => String) {
    override def apply(a: String, b: String) = a + b
  }

  println(stringAdder("Hello", " Scala"))

  // implement a function that takes an int and returns another function that int and returns int
  // Function1[Int, Function1[Int, Int]]

  val superAdder:  Function1[Int, Function1[Int, Int]] = new  Function1[Int, Function1[Int, Int]]{
    override def apply(x: Int): Function1[Int, Int]  = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y

    }
  }

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4))

}

trait MyFunction[A,B]{
  def apply(element: A): B
}