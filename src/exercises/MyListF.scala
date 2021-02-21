package org.shibut
package exercises

/*
   extend the list with FP
   1. Generic Trait MyPredicate[-T] with a test method Test[T] => Boolean
   2. Generic Trait MyTransformer[-A,B] with a method transform(A) => B
   3. MyList:
        - map(transformer) => MyList
        - filter(predicate) => MyList
        - flatMap(A transformer from A to B) => MyList[B]


    class EvenPredicate extends MyPredicate[Int]
    class StringToIntTransformer extends MyTransformer[String, Int]

    [1,2,3].map(n * 2) => [2,4,6]
    [1,2,3,4].filter( n % 2 ) => [2,4]
    [1,2,3[.flatMap(n => [n, n+1] ) => [1,2,2,3,3,4]


 */


/*
This is not needed in FP


// can be replaced with A => Boolean function type
trait MyPredicateF[-T]{
  def test(elem: T) : Boolean
}


A => B
trait MyTransformerF[-A,B]{
  def transform(elem: A): B
}

*/


abstract class MyListF[+A] {
  /*

    head - first element of the list
    tail - remainder of the list
    isEmpty
    add(Int) - return a new list with the element added
    toString - string representation of the list


   */




  def head : A
  def tail: MyListF[A]
  def isEmpty: Boolean

  /*
    this def is not posbile
    def add(element: A): MyList[A]
    throws the error
      "Covariant type A occurs in contravariant position in type A of value element"

     A = Cat
     B = Dog which in turn is an animal

     HARD question: what happens if we add a dog to a list of cats
     Answer: we convert the list to a list of animal instead of cat
     technical B = Animal

     this means that add method has to be as below
      def add[B >: A] (element: B) : MyList[B]

      where B is a supertype of A
   */

  def add[B >: A] (element: B) : MyListF[B]


  def printElements: String

  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: A => B ) : MyListF[B]

  def filter(predicate: A => Boolean): MyListF[A]

  def flatMap[B](transformer: A => MyListF[B]): MyListF[B]

  //concatenation
  def ++[B >: A](list: MyListF[B]):MyListF[B]


  //HOFs

  // [1,2,3].foreach(x => Unit
  def foreach(f: A => Unit): Unit

  def sort(comparer: (A,A) => Int): MyListF[A]

  def zipWith[B, C] (list: MyListF[B], zip: (A,B) => C): MyListF[C]


  def fold[B](start: B)(operator: (B,A) => B): B


}


// define an empty list

// any type can be replaced by Nothing
// val listOfInts: MyList[Int] = Empty
// val lisOfStrings = MyList[String] = Empty

object EmptyF extends MyListF[Nothing]{
  def head : Nothing =  throw new NoSuchElementException
  def tail: MyListF[Nothing] =  throw new NoSuchElementException
  def isEmpty: Boolean =  true
  def add[B >: Nothing](element: B) : MyListF[B] =  new ConsF(element,EmptyF)
  def printElements: String = ""

  def map[B](transformer: Nothing => B):MyListF[B] = EmptyF
  def flatMap[B](transformer: Nothing => MyListF[B]):MyListF[B] = EmptyF
  def filter(predicate: Nothing => Boolean):MyListF[Nothing] = EmptyF

  //concatenate with Nothing should return the list
  def ++[B >: Nothing](list: MyListF[B]):MyListF[B] = list

  //HOFs

  // unit value is ()
  def foreach(f: Nothing => Unit): Unit = ()

  def sort(comparer: (Nothing, Nothing) => Int): MyListF[Nothing] = EmptyF

  override def zipWith[B, C](list: MyListF[B], zip: (Nothing, B) => C): MyListF[C] = {
    // lists should have same length; here you are trying to zip a non empty list with empty
    if(!list.isEmpty) throw new RuntimeException("lists should be of same length")
    else EmptyF
  }


  // since this is an empty list, operator cannot be applied to an empty list, hence return start value
  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start






}

class ConsF[+A](h: A, t: MyListF[A]) extends MyListF[A]{

  def head : A =  h
  def tail: MyListF[A] =  t
  def isEmpty: Boolean =  false
  def add[B >: A] (element: B) : MyListF[B] = new ConsF(element, this)
  def printElements: String = {
    if(t.isEmpty) "" + h
    else h + " " + t.printElements
  }


  /*
    [1,2,3].filter(n % 2 == 0)
      = [2,3].filter( n % 2 == 0)
      = new Cons(2, [3].filter( n % 2 == 0))
      = new Cons(2, Empty.filter( n % 2 == 0))
      = new Cons(2,Empty)
   */

  def filter(predicate: A => Boolean): MyListF[A] ={
    if(predicate(h)) new ConsF(h,t.filter(predicate))
    else t.filter(predicate)
  }


  /*
    [1,2,3.map( n * 2)
      = new Cons(2, [2,3].map(n * 2))
      = new Cons(2, new Cons(4, [3].map(n * 2))
      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2)))
      = new Cons(2, new Cons(4, new Cons(6,  Empty)))

   */
  def map[B](transformer: A => B): MyListF[B] ={
    // transform head first
    // transform tail with recursive call
    new ConsF[B](transformer(h), t.map(transformer))
  }


  /*
    [1,2] ++ [3,4,5]
              = new Cons(1, [2] ++ [3,4,5])
              = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
              = new Cons(1, new Cons(2, [3,4,5]))
              = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5,Empty))))

   */
  def ++[B >: A](list: MyListF[B]):MyListF[B] = new ConsF(h, t ++ list)

  /*
    [1,2].flatMap( n => [n, n+1])
        = [1,2] ++ [2].flatMap( n => [n, n+1])
        = [1,2] ++ [2,3] ++ Empty.flatMap( n => [n, n+1])
        = [1,2] ++ [2,3] ++ Empty
        = [1,2,2,3]


   */
  def flatMap[B](transformer: A => MyListF[B]): MyListF[B] =
    transformer(h) ++ t.flatMap(transformer)


  //HOFs
  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(comparer: (A, A) => Int): MyListF[A] = {
    def insert(x: A, sortedList: MyListF[A]): MyListF[A]={
      if(sortedList.isEmpty) new ConsF[A](x, EmptyF)
      else if (comparer(x,sortedList.head) <= 0 ) new ConsF[A](x, sortedList)
      else new ConsF[A](sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(comparer)
    insert(h,sortedTail)

  }

   def zipWith[B, C](list: MyListF[B], zip: (A, B) => C): MyListF[C] = {
    if(list.isEmpty) throw new RuntimeException("lists should be of same length")
    else new ConsF[C](zip(h,list.head), t.zipWith(list.tail, zip))

  }


  /*
    [1,2,3].fold(0)(+)
    = [2,3].fold(0+1)(+)
    = [3].fold(1+2)(+)
    = [].fold(3+3)(+)
    = 6


   */
   def fold[B](start: B)(operator: (B, A) => B): B = {
//     val newStart = operator(start, h)
//     t.fold(newStart)(operator)

     // above equivalent
     t.fold(operator(start, h))(operator)

   }




}

object ListTestsF extends  App{
  val listOfIntegers:MyListF[Int] = new ConsF(1,new ConsF(2, new ConsF(3,EmptyF)))

  val anotherListOfIntegers:MyListF[Int] = new ConsF(4, new ConsF(5,EmptyF))

  println(listOfIntegers.head)
  println(listOfIntegers.tail.head)
  println(listOfIntegers.add(4).head)
  println(listOfIntegers.toString)

  val listOfStrings: MyListF[String] = new ConsF("Hello", new ConsF("Scala", EmptyF))


  println(listOfStrings.toString)


  // map , transform and filter

//  println( listOfIntegers.map(new Function1[Int,Int] {
//    override def apply(elem: Int): Int = elem * 2
//  }).toString)

  println( listOfIntegers.map(elem => elem * 2))

//  println( listOfIntegers.filter(new Function1[Int,Boolean] {
//    override def apply(elem: Int): Boolean = elem % 2 == 0
//  }).toString)

  println( listOfIntegers.filter( elem => elem % 2 == 0))


  println( listOfIntegers ++ anotherListOfIntegers)

//  println( listOfIntegers.flatMap(new Function1[Int, MyListF[Int]] {
//    override def apply(elem: Int): MyListF[Int] = new ConsF(elem , new ConsF[Int](elem + 1, EmptyF))
//  }).toString)

  println( listOfIntegers.flatMap(elem => new ConsF(elem, new ConsF(elem + 1, EmptyF)) ) )

  listOfIntegers.foreach(x => println(x))

  println(listOfIntegers.sort((x,y) => y - x))
  println(anotherListOfIntegers.zipWith[String,String](listOfStrings, _ + "-" + _ ))

  println(listOfIntegers.fold(0)( _ + _))


  // supports   for comprehensions

  val forCombinations = for{
    n <- anotherListOfIntegers
    s <- listOfStrings

  } yield n + "-" + s

  println(forCombinations)


}
