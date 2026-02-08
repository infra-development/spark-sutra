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
val (successes: Seq[Try[Int]], failures: Seq[Try[Int]]) = parsedList.partition(_.isSuccess)
println(s"Successes: $successes")
println(s"Failures: $failures")

println(s"Parsed List: $parsedList")
println(s"Successful Ints: $successfulInts")
println(s"Successful List: $successfulList")

val t = Try(5)
val tryResult = t.flatMap(x => Try(100 / x))
println(s"Try Result: $tryResult")

case class RawRecord(id: String, salary: String)
val records = List(
  RawRecord("e1", "100000"),
  RawRecord("e2", "abc"),
  RawRecord("e3", "200000")
)

def parseSalary(record: RawRecord): Either[String, Int] = {
  Try(record.salary.toInt) match {
    case Success(value) => Right(value)
    case Failure(exception) => Left(s"Failed to parse salary for record ${record.id}: ${exception.getMessage}")
  }
}

val parsedSalaries: Seq[Either[String, Int]] = records.map(parseSalary)
val (errors, successes) = parsedSalaries.partition(_.isLeft)

val validSalaries: Seq[Int] = successes.collect {
  case Right(salary) => salary
}

val errorMessages: Seq[String] = errors.collect {
  case Left(error) => error
}