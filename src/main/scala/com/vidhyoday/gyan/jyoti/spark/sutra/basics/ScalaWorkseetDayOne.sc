// Problem 1 – Immutable Transformation
val numbers = List(1, 2, 3, 4, 5)

val evenNumber = numbers.collect(
  {
    case x if x % 2 == 0 => x
  }
).map(_ * 2)

val evenNumber2 = numbers.filter(_ % 2 ==0).map(_ * 2)

println(s"Even Numbers: $evenNumber")
println(s"Even Numbers: $evenNumber2")

// Problem 2 – Grouping with Collections
val words = List("spark", "scala", "sql", "stream", "storage", "banana", "apple", "avocado")

val groupedWords = words.groupBy(word => word.charAt(0))
println(s"Grouped Words: $groupedWords")

//Problem 3 – Spark-Style Data Preparation
case class Employee(id: Int, dept: String, salary: Int)

val employees = List(
  Employee(1, "IT", 70000),
  Employee(2, "HR", 40000),
  Employee(3, "IT", 90000),
  Employee(4, "FIN", 60000)
)

val employeesWithGreaterSalary: Seq[Employee] = employees.filter(em => em.salary > 50000)


val deptWiseCount = employees.groupBy(em => em.dept).map {
  case (dept, empList) => (dept, empList.size)
}

println(s"Employees with Salary > 50000 grouped by Department: $employeesWithGreaterSalary")
println(s"Department wise count: $deptWiseCount")

