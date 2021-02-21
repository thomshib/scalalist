package org.shibut
package lectures.part1basics

import scala.annotation.tailrec
import scala.collection.convert.StreamExtensions.AccumulatorFactoryInfo

object Recursion extends  App {


  def Factorial(n: Int):Int = {
    if( n == 1)  1
    else{
      println(s"Computing Factorial of ${n} - first needs factorial of ${n-1}")
      val result = n * Factorial(n-1)
      println(s"Computed Factorils of ${n}")

      result
    }
  }

  println(Factorial(10))



  def anotherFactorial(n: Int):BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt ):BigInt = {

      if( x<=1) accumulator
      else factHelper( x - 1 , accumulator * x) // TAIL RECURSION = use recursive call as the LAST Expression

    }

    factHelper(n,1)
  }

  /*
    stack is replaced entirely for each recursive call; as the intermediate result is kept in the accumulator

    anotherFactorial(10) = factHelper(10,1)
    = anotherFactorial(9, 10 * 1)
    = anotherFactorial(8, 9 * 10 * 1)
    = anotherFactorial(7, 8 * 9 * 10 * 1)
    = anotherFactorial(6, 7 * 8 * 9 * 10 * 1)
    = ......
    = anotherFactorial(2, 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 1)
    = anotherFactorial(1, 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 1)
    = 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 1

  */


  println(anotherFactorial(5000))

  def concatenateString(aString: String, n: Int): String ={
    @tailrec
    def concatHelper(aString: String, x: Int, accumulator:String) :String = {
      if(x <= 0) accumulator
      else concatHelper(aString, x - 1, aString + accumulator)
    }
    concatHelper(aString,n, "")

  }

  println(concatenateString("hello",3))


  def isPrime(n: Int):Boolean = {
    @tailrec
    def isPrimeUntil(t: Int, isStillPrime: Boolean): Boolean= {
      if(!isStillPrime) false
      if( t <= 1) true
      else isPrimeUntil(t - 1 , isStillPrime && (n % t != 0) )
    }
    isPrimeUntil(n / 2,true)
  }

  println(isPrime(9))

  def Fibonacci(n: Int):Int = {
    // multiple accumulators are needed, as there are two recusive calls Fib(n-1) & Fib(n-2) in the code path
    def fibHelper(i: Int, last: Int, nextTolast: Int): Int =
      if( i >= n) last
      else fibHelper(i + 1, last + nextTolast, last)

    if(n <= 2) 1
    else fibHelper(2, 1, 1)

  }

  println(Fibonacci(8))

}
