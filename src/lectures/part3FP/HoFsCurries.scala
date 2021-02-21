package org.shibut
package lectures.part3FP

object HoFsCurries extends  App{

  // function that applies a function n times over x
  // nTimes(f,n,x)
  // nTimes(f,3,x) = f(f(f(x)))
  // nTimes(f,3,x) = nTimes(f, 2, f(x)) ....
  // nTimes(f, n, x) = nTimes(f, n-1, f(x))

  def nTimes(f: Int => Int,  n: Int, x: Int): Int = {
    if(n <= 0) x
    else nTimes(f, n-1, f(x))
  }

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  // increment10 = ntb(plusOne(plusOne...pluesOne(x))
  // val y = increment10(1)

  // nTimesBetter returns a lambada or a function
  // w

  def nTimesBetter( f: Int => Int, n : Int): (Int => Int) = {

    if (n <= 0) (x: Int) => x //identity lambda
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  }

    val plus10 = nTimesBetter(plusOne, 10)
    println(plus10(1))


  // curried function

  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y

  val add3 = superAdder(3) // returns a lambda y => y + 3
  println(add3(10))
  println(superAdder(3)(10))

  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double) : String = c.format(x)

  val standardFormat: Double => String = curriedFormatter("%4.2f")
  val preciseFormat: Double => String = curriedFormatter("%10.0f")
  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))



  /*
    1. Expand MyList
      - foreach method A => Int
      [1,2,3],foreach(x => println(x))

      - sort function (A,A) => Int = MyList
      [1,2,3].sort( (x,y) => y - x)   [3,2,1]

      -zipWith (list , (A,A) => B) => MyList[B]
      [1,2,3].zipWith([4,5,6], x * y)  [ 1*4, 2*5, 3*6] = [4,10,18]

      -fold(start)(function) => a value
      [1,2,3].fold(0)(x+y) = 6


     2. toCurry(f: (Int,Int) => Int)  => Int => Int = Int

      fromCurry(f: (Int => Int = Int) => (Int,Int) => Int


      3. compose(f,g) => f(g(x))
        andThen(f,g) => g(f(x)



   */

  def toCurry(f: (Int,Int) => Int) : (Int => Int => Int) = {
    x => y => f(x,y)

  }

  def fromCurry(f: (Int => Int => Int)) : (Int,Int) => Int = {
    (x,y) => f(x)(y)

  }

  def compose[A,B,T](f: A => B, g: T => A): T => B ={
    x => f(g(x))
  }

  def andThen[A,B,T](f: A => B, g: B => T): A => T ={
    x => g(f(x))
  }

  def superAdder2 : (Int => Int => Int) = toCurry(_ + _)
  def add4 = superAdder2(4)
  println(add4(17))

  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4,17))

  val add2 = (x: Int) => x + 2
  val mul3 = (x: Int) => x * 3

  val composed = compose(add2,mul3)
  val ordered = andThen(add2,mul3)
  println(composed(4))
  println(ordered(4))





}
