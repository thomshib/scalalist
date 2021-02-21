package org.shibut
package lectures.part3FP

import javax.management.RuntimeMBeanException
import scala.util.{Failure, Random, Success, Try}

object HandlingFailures  extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Super Failure"))

  println(aSuccess)
  println(aFailure)

  def unssafeMethod() :String = throw new RuntimeException("No string found error")

  val potentialFailure = Try(unssafeMethod())

  println(potentialFailure)

  val anotherPotentialFailure = Try{
    // code that throws error
  }

  // utilities
  println(potentialFailure.isSuccess)

  //orElse
  def backupMethod(): String = "A valid result"

  val fallbackTry = Try(unssafeMethod()).getOrElse(Try(backupMethod()))
  println(fallbackTry)

  // If you design the API

  def betterUnsafeMethod:Try[String] = Failure(new RuntimeException("failed"))
  def betterbackupMethod():Try[String] = Success("A valid result")
  val betterFallback = betterUnsafeMethod orElse betterbackupMethod
  println(betterFallback)

  // map, flatMap, filter

  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  //for comprehensions

  println("---------------------------------------")

  // Exercise
  val host = "localhost"
  val port = "8080"
  def renderHtml(page: String) = println(page)

  class Connection{
    def get(url: String):String ={
      val random = new Random(System.nanoTime())
      if(random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection Interrupted")
    }

    def getSafe(url:String): Try[String] = Try(get(url))

  }

  object HttpService{
    val random = new Random(System.nanoTime())
    def getConnection(host: String, port:String): Connection ={
      if (random.nextBoolean()) new Connection
      else throw  new RuntimeException("Port is in use")
    }

    def getSafeConnection(host:String, port: String): Try[Connection] = Try(getConnection(host,port))
  }

  // if you get html page from the connection, print it to the console using renderHtml

  val possibleConnection = HttpService.getSafeConnection(host,port)
  val possibleHtml = possibleConnection.flatMap( connection => connection.getSafe("/home"))
  possibleHtml.foreach(renderHtml)

  // short hand version

  HttpService.getSafeConnection(host,port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHtml)


  // for comprehension

  for {
       connection <- HttpService.getSafeConnection(host, port)
        html <- connection.getSafe("/home")
  } renderHtml(html)

  /*

    imperative logic
    try{
        connection = HttpService.getConnection(host,port)
        try{
           page = connection.get("/home)
           renderHtml(page)
        }catch{
        }

    catch{
    }


   */

}
