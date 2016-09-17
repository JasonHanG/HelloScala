package scala.learning

/**
  * Created by jason on 16-9-12.
  */
class ClassDef {

}

/**
  * 复数类（Complex）接受两个参数：实部和虚部。
  * 这些参数必须在实例化时进行传递，就像这样：new Complex(1.5, 2.3)。
  * 类定义中包括两个叫做re和im的方法，分别接受上面提到的两个参数。
  * 值得注意的是这两个方法的返回类型并没有显式的声明出来。他们会被编译器自动识别。
  * 在本例中他们被识别为Double 但是编译器并不总是像本例中的那样进行自动识别。
  * 不幸的是关于什么时候识别，什么时候不识别的规则相当冗杂。
  * 在实践中这通常不会成为一个问题，因为当编译器处理不了的时候会发出相当的抱怨。
  * 作为一个推荐的原则，Scala的新手们通常可以试着省略类型定义而让编译器通过上下文自己判断。
  * 久而久之，新手们就可以感知到什么时候应该省略类型，什么时候不应该。
  * Scala中的所有类都继承一个父类，当没有显示声明父类时（就像上面定义的Complex一样），它们的父类隐形指定为scala.AnyRef。
  * 　在子类中覆盖父类的成员是可能的。但是你需要通过override修饰符显示指定成员的覆盖。
  * 这样的规则可以避免意外覆盖的情况发生。作为演示，我们在Complex的定义中覆盖了Object的toString方法。
  */
class Complex( real: Double, imaginary: Double ) {
  def re( ) = real

  def im( ) = imaginary

  override def toString( ): String = {
    "" + re + ( if ( im < 0 ) "" else "+" ) + im + "i"
  }
}

object Complex extends App {
  val complex = new Complex ( 1.1, 2.2 )
  //方法re和im还有一个小问题：你必须在名字后面加上一对括号来调用它们
  //把这些函数当作变量使用，而不是当作函数进行调用，可能会更加令人感到舒服。
  // 事实上我们可以通过定义无参函数在Scala做到这点。
  // 这类函数与其他的具有0个参数的函数的不同点在于他们定义时不需要在名字后面加括弧，所以在使用时也不用加（但是无疑的，他们是函数）
  // 因此，我们的 Complex类中的re()方法去掉括号
  println ( "real = " + complex.re + " ; imaginary = " + complex.im ( ) )
  print ( complex )
}