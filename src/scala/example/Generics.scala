package scala.example

/**
  * Created by jason on 16-9-13.
  */
class Generics {

}

abstract class Stack[A] {
  def push( x: A ): Stack[A] = new NonEmptyStack[A]( x, this )
  def isEmpty: Boolean
  def top: A
  def pop: Stack[A]
}

class EmptyStack[A] extends Stack[A] {
  def isEmpty = true
  def top = error ( "EmptyStack.top" )
  def pop = error ( "EmptyStack.pop" )
}

class NonEmptyStack[A]( elem: A, rest: Stack[A] ) extends Stack[A] {
  def isEmpty = false
  def top = elem
  def pop = rest
}

object Generics  {
  def isPrefix[A](p: Stack[A], s: Stack[A]): Boolean = {
    p.isEmpty ||
      p.top == s.top && isPrefix[A](p.pop, s.pop)
  }


  val x = new EmptyStack[Int]
  val y = x.push ( 1 ).push ( 2 )
  println ( y.pop.top )

  val s1 = new EmptyStack[String].push("abc")
  val s2 = new EmptyStack [String].push("abx").push(s1.top)
  // 本地类型推断。如果程序中大量使用泛型,却每次都要带上类型参数 [Int] 或者 [String] ,写代码会变得很麻烦。
  // 很多时候,通过检查值参数的类型,也能获取正确的类型信息,这种情况下,类型参数就是一种冗余。
  // 以表达式 isPrefix[String](s1, s2) 为例,编译器可以知道传进来的参数都是 Stack[String] 类型的,所以可以推断方法的类型参数必须是 String 。
  // Scala 有较强的类型推断能力,因此在适当的情况下,调用多态函数和 constructor 时,也可以省去类型参数。
  // 代码中可以只写 isPrefix(s1, s2) ,而由类型推测机制去补全类型信息。
  println(isPrefix[String](s1, s2))
}

//形似 A <: Ordered[A] 的参数声明,明确类型参数 A 只能接受 Ordered[A] 的子类作为实参,换 句话说,就是要求同类型的值之间,必须是可以互相比较的。
trait Set[A <: Ordered[A]] {
  def incl(x: A): Set[A]
  def contains(x: A): Boolean
}


//把类型参数和继承结合在一起考虑,会产生一些颇费思量的问题。
// 例如, Stack[String] 可以看作是 Stack[AnyRef] 的子类型吗?
// 凭直觉,存放 String 的栈的确是存放 AnyRef 栈的特殊情况,所以父子类关系应该是成立的。
// 通常,如果 T 是 S 的子类,那么 Stack[T] 也应该是 Stack[S]的子类,这种特性称为 co-variant subtyping(协变子类型)
// 在Scala中,泛型默认是 non-variant subtyping,也就是说,像前面的 Stack 泛型类,用不同的类型参数创建的栈之间,是永远不存在父子类关系的。

//前面已经见到过类型参数的上边界约束(upper bounds),也就是形如 T <: U 的类型参数声明,T 的取值范围必须是 U 的子类。
// 与此相对的是类型参数的下边界约束(lower bounds),声明方式为 T >: S ,意味着 T 的取值范围必须是 U 的超类(上下限是可以结合起来同时约束的,就像 T >: S <: U )。

//Scala 的类库中有大量的类是泛型类。两个使用广泛的泛型类家族:tuples 和 functions。
object TupleTest{
  //Tuple 能带来很多便利性,所以 Scala 为此提供了专门的语法。
  // 要创建包含个 n 元素 x 1 , ..., x n的tuple,直接写 (x 1 , ..., x n ) 即可,
  // 这等价于 Tuple n ( x 1 , ..., x n ) 。“(...)” 语法同样可用于类型和模式,基于此语法, divmod 如下:
  def divmod(x: Int, y: Int) = (x / y, x % y)
  def main ( args: Array[String] ) {
    //跟往常一样,在类型能推断出来的时候,类型参数就省略了( new Tuple2 )。
    // 除 Tuple2 以外,Scala 还定义了存放不同数量元素的 tuple (当然,目前的实现对元素的个数做了合理的限制)。
    // 如何访问 tuple 中的元素呢?因为 tuple 是 case class,所以有两种访问手段。
    // 一种是直接用构造参数名来访问(记得前面讲过,case class 自动为构造参数创建 access method),例如
    val xy = divmod(3, 2)
    println("quotient: " + xy._1 + ", rest: " + xy._2)
    //第二种就是在 tuple 类上用模式匹配,例如:
    divmod(3, 3) match {
      case Tuple2(n, d) =>
        println("quotient: " + n + ", rest: " + d)
    }
  }
}

object FunctionTest{
  // 函数式编程语言,因此函数是 first-class value。Scala 也是面向对象语言,因此任何值都 是 对象 , 包 括 函数 在内 。
  // 例如 ,一个接收 String 参 数 并 返 回 Int 的 函 数 , 是 TraitFunction1[String, Int] 的实例。
  // 该 Trait 的定义如下(译注:Function 后面的数字 1 实际代表参数个数):
  /* package scala
     trait Function1 [ -A, +B] {
      def apply(x: A): B
  }*/
  // 函数接受几个参数,我们就称其为几元函数。除了一元函数 Function1 ,还有任意元函数的定义(当然,目前的实现对参数个数做了合理的限制)。
  // Scala 的函数类型定义语法 (T 1 , ..., T n ) =>S 实际上是 Function n [ T 1 , ..., T n , S ] 的简写。
  // Scala 中,无论 f 是函数对象还是一个方法,都使用相同的语法“f (x)”来调用。
  // 不过,当 f是函数对象时,f (x) 实际上是 f .apply( x ) 的简写,也就是说,函数的 apply 方法是在必要的时候自动插入的。
}