package test

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by saz on 10/10/2016.
  */
class ChessSpec extends FlatSpec with Matchers {

  "2 kings and 1 rook in a 3x3 board" should " have 4 answers" in {

    assert(Chess.compute(2, 0, 1, 0, 0, 3, 3).size == 4)
  }

  "2 Rooks and 4 Knights in a 4x4 board" should " have 8 answers" in {

    assert(Chess.compute(0, 0, 2, 0, 4, 4, 4).size == 8)
  }

  "4 queens in a 4x4 board" should " have 2 answers" in {

  val answers = Chess.compute(0, 4, 0, 0, 0, 4, 4)
  assert(answers.size == 2)
  }

  "5 queens in a 5x5 board" should " have 10 answers" in {

    val answers = Chess.compute(0, 5, 0, 0, 0, 5, 5)
//    System.out.println("5x5 answers are: " + answers.size)
    assert(answers.size == 10)
  }

  "6 queens in a 6x6 board" should " have 4 answers" in {

    val answers = Chess.compute(0, 6, 0, 0, 0, 6, 6)
//    System.out.println("6x6 answers are: " + answers.size)
    assert(answers.size == 4)
  }

   "7 queens in a 7x7 board" should " have 40 answers" in {

     val start = System.currentTimeMillis();
     val size = Chess.compute(0, 7, 0, 0, 0, 7, 7).size
     val end = System.currentTimeMillis();

     System.out.println("time taken: " + (end - start) / 1000 + " seconds");
     assert(size == 40)
   }

  /*
  "8 queens in a 8x8 board" should " have 92 answers" in {

    val start = System.currentTimeMillis()
    val size = Chess.compute(0, 8, 0, 0, 0, 8, 8).size
    val end = System.currentTimeMillis()

    System.out.println("time taken: " + (end - start) / 1000 + " seconds");
    assert(size == 92)
  }
  */
}
