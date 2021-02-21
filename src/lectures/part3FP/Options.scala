package org.shibut
package lectures.part3FP

import scala.util.Random

object Options extends  App{

  val myFirstOption: Option[Int]= Some(4)
  val noOption: Option[Int] = None
  println(myFirstOption)


  // unsafe API

  def unsafeMethod(): String = null

  val result = Option(unsafeMethod()) // Some or None
  println(result)

  // chained methods

  def backupMethod():String = " A valid value"

  val chainedResult = Option(unsafeMethod()).getOrElse(Option(backupMethod()))

  // DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackUpMethod(): Option[String] = Some("A valid result")
  val betterChainedResult = betterUnsafeMethod().getOrElse(betterBackUpMethod())

  //functions on Options
  println(myFirstOption.isEmpty)


  // map, flatMap and filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap( x => Option( x * 10) ))

  println("--------------------------------------------")

  //for comprehensions

  val config: Map[String,String] = Map(
    "host" -> "176.45.26.1",
    "port" -> "80"
  )

  class Connection{
    def connect  = "connected"
  }

  object Connection{
    val random = new Random(System.nanoTime())
    def apply(host:String, port:String):Option[Connection]= {
      if(random.nextBoolean()) Some(new Connection)
      else None

    }
  }

  // try to establish the connection, if so print the connection status

  val host = config.get("host")
  val port = config.get("port")

  /*
      if( h != null)
        if( p != null)
          return Connection.apply(h,p)

      return null
   */

  val connection = host.flatMap(h => port.flatMap( p => Connection.apply(h,p)))

  /*
    if( c != null)
      c.connect

    return null

   */
  val connectionStatus = connection.map( c => c.connect)

  // if( connectionStatus = null) println(None) else println(Some(connectionStatus.get))
  println(connectionStatus)

  /*
    if( status != null)
      println(status)
   */

  connectionStatus.foreach(println)


  // alernative short hand

  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)


  //for comprehensions
  val forconnectionStatus = for{
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host,port)
  } yield connection.connect

  forconnectionStatus.foreach(println)



}
