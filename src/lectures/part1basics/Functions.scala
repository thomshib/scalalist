package org.shibut
package lectures.part1basics

object Functions extends  App{

  def aRepeatedFunction(aString: String, n: Int) :String ={

    if(n == 1) aString
    else aString + " " +  aRepeatedFunction(aString, n - 1)

  }

  println(aRepeatedFunction("Hello", 3))

  //WHEN YOU NEED LOOPS, USE RECURSION


  def Factorial(n: Int): Int ={
    if(n == 1) 1
    else n * Factorial(n-1)

  }

  println(Factorial(4))

  def Fibonacci(n: Int):Int ={
    if(n < 2) 1
    else Fibonacci(n-1) +Fibonacci(n-2)
  }

  println(Fibonacci(6))

  def isPrime(n: Int): Boolean ={
    def isPrimeUntilT(t: Int):Boolean= {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntilT(t-1)
    }

    isPrimeUntilT(n /2)
  }

  println(isPrime(4))
  println(isPrime(11))

}
