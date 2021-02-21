package org.shibut
package lectures.part1basics

object StringOps extends  App{

  val str: String = "Hello, i am learning Scala"

  println(str.charAt(2))
  println(str.substring(7,11))
  println(str.replace(" ","-"))

  val aNumberString: String = "42"

  //prepend :+ and append +:

  println('a' +: aNumberString :+ 'z')

}
