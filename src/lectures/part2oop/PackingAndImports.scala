package org.shibut
package lectures.part2oop

import java.util.Date
import java.sql.{Date => SqlDate}

object PackingAndImports extends  App{

  // package object
  // for universal constants and universal methods

  sayHello
  println(SPEED_OF_LIGHT)


  // import aliasing

  val date = new java.util.Date()
  val sqlDate = new SqlDate(2020,07,04)


}
