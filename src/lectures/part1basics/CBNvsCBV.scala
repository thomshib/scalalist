package org.shibut
package lectures.part1basics

object CBNvsCBV extends  App{

  def calledByValue(x: Long)={

    println(s"called by value ${x}")
    println(s"called by value ${x}")


  }

  def calledByName(x: => Long)= {
    println(s"called by name  ${x}")
    println(s"called by name ${x}")
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  /*


  //the expression is not evaluated before in the call; the epxression is passed as is
  // and is invoked everytime

  def calledByName(x: => Long)= {
    println(s"called by name  ${System.nanoTime()}")
    println(s"called by name ${System.nanoTime()}")
  }

  calledByName(System.nanoTime())



  //the expression is evlated once in the call

  def calledByValue(x: Long)={

    println(s"called by value ${306064856603955L}")
    println(s"called by value ${306064856603955L}")


  }

  calledByValue(306064856603955L)


   */


}
