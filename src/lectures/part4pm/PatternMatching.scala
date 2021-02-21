package org.shibut
package lectures.part4pm

import org.shibut.lectures.part4pm.PatternMatching.Expr

import scala.util.Random

object PatternMatching extends  App {

  val random = new Random()
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the ONE"
    case 2 => "double or Nothing"
    case 3 => "third time is lucky"
    case _ => "somethig else" // _ = WILDCARD
  }

  println(x)
  println(description)

  // 1. Decompose values

  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)
  val greeting = bob match {
    case Person(n,a) if a < 21 => s"My name is $n and I am $a years old but I can't drink in the US"
    case Person(n,a) => s"My name is $n and I am $a years old"
    case _ => "I don't know who Iam "
  }

  println(greeting)

  /*
    Exercise
     takes and Expr and return human readable form

     Sum(Number(2), Number(3))  =  2 + 3
     Prod(Sum(Number(2),Number(1)), Number(3)) = (2 + 1) * 3
     Sum(Prod(Number(2),Number(1)), Number(3)) = 2 * 1 + 3


   */

  trait Expr

  case class Number(n: Int) extends  Expr
  case class Sum(e1: Expr, e2: Expr) extends  Expr
  case class Prod(e1: Expr, e2: Expr) extends  Expr

  def show(e: Expr) :String ={
    e match {
      case Number(n) => s"$n"
      case Sum(e1,e2) => show(e1) + " + " + show(e2)
      case Prod(e1,e2) =>{
        def maybeShowParenthesis(expr: Expr)= {
          expr match {
            case Prod(_,_) => show(expr)
            case Number(_) => show(expr)
            case _ => "(" + show(expr) + ")"
          }
        }

        maybeShowParenthesis(e1) + " * " + maybeShowParenthesis(e2)
      }
    }
  }

  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(show( Prod(Sum(Number(2),Number(1)), Number(3))))
  println(show(Sum(Prod(Number(2),Number(1)), Number(3))))




}
