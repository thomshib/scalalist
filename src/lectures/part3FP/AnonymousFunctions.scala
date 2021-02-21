package org.shibut
package lectures.part3FP

object AnonymousFunctions extends App{

  val doubler = new Function1[Int,Int]{
    override def apply(x: Int):Int = x * 2
  }

  // equivalent
  // anonymous Functions or Lambda
  val doublerAlternate = (x: Int) => x * 2

  // shorter form if you specify the functions type Int => Int
  val doublerAlternate2: Int => Int = x => x* 2

  // multiple parameters

  val adder : (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no parameters
  val justDoSomething: () => Int = () => 3


  // careful

  println( justDoSomething)  // function itself
  println(justDoSomething()) // call


  // curly braces with lambda

  val stringToInt = { (s: String) =>
    s.toInt

  }

  // more syntactic sugar

  val niceIncrementer : Int => Int = _ + 1  // equivalent to (x) => x + 1
  val niceAdder : (Int,Int) => Int = _ + _  // equivalent to (x,y) => x + y


  val superAdd = (x: Int) => (y: Int) => x + y

  println(superAdd(3)(4))

}
