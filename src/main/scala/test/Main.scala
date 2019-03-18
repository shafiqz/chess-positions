package test

/**
  * Created by saz on 10/13/2016.
  */
object Main {

  def main(args: Array[String]): Unit = {

    println("Solving 2 kings, 2 queens, 2 bishops and 1 knight on 7x7 board")
    val start = System.currentTimeMillis()
    val answers = Chess.compute(2, 2, 0, 2, 1, 7, 7)
    val end = System.currentTimeMillis()

    println("Time taken: " + (end - start) / 1000 + " seconds")
    println("No of solutions: " + answers.size)
    println("Showing first three solutions:")

    answers.take(3)
           .foreach( oneSolution => { println("* -- " + oneSolution + "\n\n") })
  }
}
