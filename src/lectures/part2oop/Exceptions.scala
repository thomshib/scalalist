package org.shibut
package lectures.part2oop

object Exceptions extends  App{

  val x: String = null
  //println(x.length)

  // throwing a catching exceptions

  //throw new NullPointerException

  //val aWeirdValue = throw new NullPointerException

  // throwable classes extend the Throwable class
  // Exceptions and Error are maot Throwable

  def getInt(withException: Boolean): Int = {
    if(withException) throw new RuntimeException("No int")
    else 42
  }


 val potentialFail = try{
    getInt(true)
  }catch {

    case e: RuntimeException => 43

  }finally {
    // code will always get executed
    // optional
    // does not influence of the return value of the expression
   // use finally for side effects

    println("finally")

  }

  println(potentialFail)


  // how to define you own exception

  class MyException extends  Exception

  val exception = new MyException

  // throw exception


  // out of memory error

  // val array = Array.ofDim(Int.MaxValue)


  // stack overflow error

  def infinite: Int = 1 + infinite
 // val noLimit = infinite



  class OverFlowException extends  RuntimeException
  class UnderFlowException extends  RuntimeException


  object  PocketCalculator{
    def add(x: Int, y: Int) = {
      val result = x  + y

      if( x > 0 && y > 0  && result < 0) throw new OverFlowException
      else if ( x < 0 && y < 0 && result > 0) throw  new UnderFlowException
      else result
    }

    def substract(x: Int, y: Int) = {
      val result = x  -  y

      if( x > 0 && y < 0  && result < 0) throw new OverFlowException   //adding two +ve
      else if ( x < 0 && y > 0 && result > 0) throw  new UnderFlowException
      else result
    }


  }

  val result = PocketCalculator.add(Int.MaxValue , 10)
  println(result)

}


