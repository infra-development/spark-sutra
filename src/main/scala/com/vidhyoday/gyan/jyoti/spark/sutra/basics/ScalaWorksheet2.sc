import scala.util._

def getDept(empId: Int): String = {
  if (empId == 1) "IT" else null
}

def getDeptOption(empId: Int): Option[String] = {
  if (empId == 1) Some("IT") else None
}

val dept = getDept(2)
println(s"Department: $dept")

val deptOption = getDeptOption(2)
val deptOptionValue = deptOption.map(x => x)
println(s"Department Option: ${deptOptionValue}")

val list = List("100", "200", "abc", "300")
val successfulList: Seq[Int] = list.flatMap(str => Try(str.toInt).toOption)

val parsedList: Seq[Try[Int]] = list.map(str => Try(str.toInt))
val successfulInts: Seq[Int] = parsedList.collect {
  case Success(value) => value
}
println(s"Parsed List: $parsedList")
println(s"Successful Ints: $successfulInts")
println(s"Successful List: $successfulList")

val t = Try(5)
val tryResult = t.flatMap(x => Try(100 / x))
println(s"Try Result: $tryResult")