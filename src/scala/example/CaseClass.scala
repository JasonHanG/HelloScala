package scala.example

/**
  * Created by jason on 16-9-13.
  */
class CaseClass {

}
//条件类和条件对象和普通的类和对象的定义类似,只是在前面增加了 case 修饰符。例如,如下代码:
//模式匹配和方法,在被匹配的类体系之外,使用模式匹配技术定义了一个独立的方法。
// 当然,在类体系内,同样也可以定义使用模式匹配的函数来匹配自身。
// 例如,我们可以将函数 eval 定义成基类 Expr 的一个方法,依然用模式匹配来实现:
abstract class Expr {
  def eval: Int = this match {
    case Number(n) => n
    case Sum(e1, e2) => e1.eval + e2.eval
  }
}

//把 Number 和 Sum 定义为条件类。类或者定义之前的 case 修饰符有如下影响:
//1、条件类有隐含的构造函数,函数名和类名相同。
//2、条件对象和条件类都默认的重写了来自 AnyRef 类的 toString、equals 和 hashCode 三个方法。
//这三个方法的实现,无一例外都照顾了条件类本身的结构特征。
//比如,表达式的 toString 方法返回的结果,就是构造该表达式的代码文本
//3、条件类还默认为每个构造参数实现了访问函数(nullary access method,无参的访问函数,相当于 java 的 get 方法)。
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr

//模式匹配相当于 C 或者 Java 中 switch 语句在类体系上的实现
// 但没有使用 switch 关键字,而是在 Scala 的根类 Any 上定义了一个标准方法 match ,
// 这样所有类的对象都可以访问该方法。match 方法的参数是一系列的case,利用模式匹配,重新实现 eval 功能如下:

object Expr {
  // 本例中共有两个case,每个 case 都把一个模式和一个表达式关联起来。
  // 模式匹配的 selector是 match 前面的 e 。
  // 本例中的第一个模式 Number(n) ,匹配形如 Number(v) 的值,其中 v 可以是任意值,如果匹配成功,则 v 的值被赋予模式中的变量 n ,
  // 右边的表达式就可以使用变量 n 来访问被匹配的值。同理,模式 Sum(l, r) 匹配 Sum(v 1 ,v 2 ) 并把 v 1 和 v 2 分别绑定到变量 l 和 r上。
  // 模式匹配在解析的时候,会执行第一个成功匹配 selector 的模式的右部表达式,而右部表达式中引用的模式变量,被替换成对应的构造参数的值。
  // 如果没有任何模式能够匹配,则该语句终止并给出 MatchError 异常。
  def eval(e: Expr): Int = e match {
    case Number(n) => n
    case Sum(l, r) => eval(l) + eval(r)
  }
}