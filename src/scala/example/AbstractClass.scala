package scala.example

/**
  * Created by jason on 16-9-13.
  */
class AbstractClass {

}
//因为抽象类有未实现的方法,所以无法通过 new 来创建类的实例。抽象类可以作为其它类的基类,由其它类实现基类中未实现的方法。
abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
}


//特性、特质:Traits。除了 abstract class , trait 也是 Scala 中经常使用的关键字。
// Traits也可以看作是抽象类,不过Trait的成员是通过混入组合(mixin)加入到其它的类和对象中,而抽象类通过继承将自己的成员加入到其它类中。
// 例如, Bordered trait 可以为所有的图形部件增加边框功能。
// Trait的另一个应用场景是:把不同类提供的公共成员的签名(signature)进行集中,就像 Java 中 interface 的作用一样。
// Trait 是 Scala 的一项重要特性,本文中这一段介绍太过简略,请自行参考其它资料做深入了解。对于 Java 程序员来说,Trait 最直观但不是十分准确类比是:可以带实现的接口。
trait IntSetTrait {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
}

class EmptySet extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmptySet(x, new EmptySet, new EmptySet)
}
class NonEmptySet(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if ( x > elem) right contains x
    else true
  def incl(x: Int): IntSet =
    if (x < elem) new NonEmptySet(elem, left incl x, right)
    else if (x > elem) new NonEmptySet(elem, left, right incl x)
    else this
}


//动态绑定。
// 包括 Scala 在内的面向对象语言,都使用动态分发(dynamic dispatch)来实现方法调用。
// 也就是说,一个方法调用所执行的实际代码,依赖于该方法所在对象的运行态的类型 (run-time type)。
// 例如,对于表达式 s contains 7 ,虽然 s 声明为 IntSet 类型,但在运行时, s 的实际类型决定了所执行的代码是 EmptySet 类上的,还是 NonEmptySet 类上的

//动态方法分发与高阶函数调用(higher-order function)有些类似,因为这两种情况下,实际执行的代码都是在运行时才能知道的。
// 这种相似性并不只是面儿上的,实际上,Scala 中所有的函数都是对象(参见 8.6 节)。
// (个人理解,最后一句话的意思是说,因为函数(当然,也包括方法)都是对象,所以,方法调用和函数传递,实际上都是拿运行时的对象进行代换,所以动态方法分发与高阶函数调用的底层机制相同)

object MainClass {
  //对象。前面对整数集合的实现中,要表示空集合,需要 new EmptySet 。
  // 这就意味着,每次用到空集合都要创建一个新的对象,而实际这是一种浪费。
  // 为了免去不必要的对象创建动作,我们可以定义一个名为 empty 的值,所有用到空集合的地方,引用该值即可。例如:
  val EmptySetVal = new EmptySet


  //这种方法有个缺点:在 Scala 中,值定义并不是合法的顶级定义,所以前面提到的代表空集合的 empty ,必须是另一个类或者方法的成员,这样访问起来不方便。
  // 另外,用一个类 EmptySet 来表示空集合,这种方式本身就有些过火(overkill)- 如果一个类只有一个有意义的实例,那么我们为什么还要定义一个类呢?
  // 但是,不用类用什么?在Scala中,可以使用对象定义(object definition)

  def main( args: Array[String] ) {
    println((new EmptySet).contains(7))
  }
}


// 对象定义(object definition)遵从类定义的语法:有可选的 extends 子句,以及可选的 body。
// Extends 子句从父类中继承成员,而 body 中则重写继承的成员或者定义新的成员。
// 不同的是,对象定义仅定义了一个对象,不能再通过 new 来创建该结构的其它实例
// 因此,对象定义不能有构造参数(constructor parameter),这一点与类定义有差异。
// Scala 中没有静态(static)概念,而是用对象定义代替。

//对象定义可以出现在 Scala 程序的任何地方,当然包括顶层(top-level)定义。
// 由于 Scala 中的顶层定义并没有固定的执行顺序(execution order),所以对象定义到底在什么时候创建并初始化对应的单例对象。
// 实际上,当该对象的成员第一次被访问到的时候,对象才被创建,这种策略称为惰性计算(lazy evaluation ,或者叫延迟计算 )。
object EmptySet extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmptySet(x, EmptySet, EmptySet)
}