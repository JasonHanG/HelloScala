package scala.example

/**
  * Created by jason on 16-9-13.
  */
  // Scala中的每个类都继承自某一个父类(或者说超类,superclass)
  // 若类定义时没有显式指定父类,则默认继承自根类型 scala.AnyRef (对基于 Java 的 Scala 实现来说,该类是 java.lang.Object 的别名)。
class Rational( n: Int, d: Int ) {
  private def gcd( x: Int, y: Int ): Int = {
    if ( x == 0 ) y
    else if ( x < 0 ) gcd ( -x, y )
    else if ( y < 0 ) -gcd ( x, -y )
    else gcd ( y % x, x )
  }

  // 私有成员。有理数类中定义了私有方法 gcd ,用于计算两个整数的最大公约数
  // 同时还定义了一个私有字段 g ,该字段存放构造参数(constructor arguments)的最大公约数。
  // 私有成员在类之外是无法访问的。最大公约数的作用,是消除构造参数中的公因子,使无理数的分子和分母保持规范化形式。
  private val g = gcd ( n, d )


  override def toString = "" + numer + "/" + denom

  val numer: Int = n / g
  val denom: Int = d / g

  def +( that: Rational ) =
    new Rational ( numer * that.denom + that.numer * denom,
      denom * that.denom )

  def -( that: Rational ) =
    new Rational ( numer * that.denom - that.numer * denom,
      denom * that.denom )

  def *( that: Rational ) =
    new Rational ( numer * that.numer, denom * that.denom )

  def /( that: Rational ) =
    new Rational ( numer * that.denom, denom * that.numer )

  def square = new Rational ( numer * numer, denom * denom )
}



object Rational {
  def main( args: Array[String] ) {
    var i = 1
    var x = new Rational ( 0, 1 )
    while (i <= 10) {
      x += new Rational ( 1, i )
      i += 1
    }
    println ( "" + x.numer + "/" + x.denom )


    // 如果类 A 继承自类 B,那么,凡是使用 B 的实例的地方,都能用 A 的实例代替,我们把这种情况称为 类型 A 与类型 B 相吻合(A conforms to B)
    var a: AnyRef = new Rational ( 1, 2 )

    // 无参方法。无参方法是 Java 中没有的特性,Scala 中的方法如果没有参数,声明和调用时可 以省略参数列表
    // 也就是说,访问类的无参方法的语法,跟访问类的值字段(value fields,比如 numer )是一样的,两者之间的差异在与它们的定义
    // 当创建对象时,值定义的右部表达式(right-hand side)算出一个值,且该值在后续使用中不再发生变化;相反,无参方法的右部,是在每次方法调用的时候进行运算的。
    // 类的字段(field)和无参方法在访问形式上的统一,增强了类实现的灵活性。
    // 比如,我们在实现一个类的时候,一开始定义了一个 field,后来,因为某种原因,需要把 field 改成一个计算值(computed value)
    // 这时候只需要用同名的无参方法替换该 field 即可,使用该类的代码无须做出调整。
    val r = new Rational ( 3, 4 )
    println ( r.square ) // 打印:9/16
  }
}