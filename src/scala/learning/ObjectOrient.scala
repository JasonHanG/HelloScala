package scala.learning

/**
  * Created by jason on 16-9-8.
  */
class ObjectOrient {

}

class Person{
  //名字可变使用var,初值给placeHolder_
  var name : String = _ //var声明的会生成getter＆setter
  val age = 10          //val只会生成getter
  private[this] val gender = "male"   //private属性只能在类内使用
}

//带主构造器的类，参数列表中如果不写var or val ,外部无法访问, 默认相当private[this]
class NewPerson(var name : String ,var age : Int) {
  //主构造器运行会执行类中的所有语句
  println("This is main constructor")
  println("name :" + name + ", age :" + age)
  val gender : String = "female"

  //附属构造器　都以this命名,参数名称不要再带有var/val,对应信息在主构造器和参数声明中进行说明
  def this(name : String ,age:Int ,gender : String) {
    //调用主构造器,必须放在第一条可执行语句上
    this(name,age)
    println("This is sub constructor")
  }
}

//覆盖父类属性,属性为var型的不可覆盖
class Student(name : String , age : Int ,  val grade :Int,override val gender :String = "male") extends NewPerson(name,age){
  println("This is a sub class of Person , grade :" + grade)

  //覆盖父类方法时需要声明override
  override def toString = "ToString:" + name + ", gender:" + gender
}


object ObjectOrientLearning {
  def main(args: Array[String]) {
/*
    val p = new Person()
    println(p.name)//此时输出null　
    p.name = "jason"
    println(p.name)


    val person = new NewPerson("jason",22,"male")
    println(person.gender)*/

    val student = new Student("ming",33,1)
    println(student)
  }
}