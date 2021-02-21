package org.shibut
package lectures.part4pm

import exercises._

object AllThePatterns extends  App{

  // 1- constants

  val x: Any =  "Scala"

  val constants = x match {
    case 1 => " anumber"
    case "Scala" => "The Scala"
    case true => " The Truth"
    case AllThePatterns => " A Singleton object"
  }

  // 2 - match anything
  // 2.1  - wild card
  val matchAnything = x match {
    case _ =>
  }


  // 2.2 - a variable

  val matchAVariable = x match {
    case something => s"I 'v found $something"
  }

  // 3 - a tuple
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case (1,1) =>
    case (something, 2) => s"I 'v found $something"
  }

  val nestedTuple = (1, (2,3))
  val matchNestedTuple = nestedTuple match {
    case (_,(2,v)) =>
  }


  // 4 - case classes contructor pattern
  val aList: MyListF[Int] = ConsF(1, ConsF(2,EmptyF))

  val matchAList = aList match {
    case EmptyF =>
    case ConsF(head,(subhead, subtail)) =>
  }

  // 5 - list patterns

  val aStandardList = List(1,2,3,42)

  val aStandardListMatching = aStandardList match {
    case List(1,_,_,_) => // extractor
    case List(1, _*) => // list of arbirary length
    case 1 :: List(_) => // infix pattern
    case List(1,2,3) :+ 42  => // infix pattern


  }

  // 6 - type specifiers
  val unknown:Any = 2

  val matchUnknown = unknown match {
    case list:List[Int] => // type specifier
   }

  // 7 - name binding

  val nameBindingMatch = aList match {
    case nonEmptyList @ ConsF(_,_) => // name binding; use the name later
    case ConsF(1, rest @ Consf(2,_)) => // name binding for nested pattern
  }

  // 8 - multi patterns
  val multipattern = aList match {
    case Empty | ConsF(0,_) => // compound pattern
  }

  // 9 - if guard
  val secondElementMatch = aList match {
    case ConsF(_, ConsF(specialElement,_)) if specialElement % 2 == 0 =>
  }


}
