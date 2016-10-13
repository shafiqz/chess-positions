package test

/**
  * Created by saz on 10/8/2016.
  * Describes a position on the chess board.
  * Note that (1, 1) means 1st row, 1st column where the square is located in
  * the lower left, just like a normal x-y axis graph.
  */
case class Position(x:Int, y:Int) extends Ordered[Position] {

  override def compare(that: Position): Int = {

    if (this.x == that.x && this.y == that.y) {
       0
    }
    else if (this.x > that.x) {
      1
    }
    else if (that.x > this.x) {
      -1
    }
    else if (this.x < that.x) {
      -1
    } else {
       if (this.y < that.y) {
         -1
       }
       else {
         1
       }
    }
  }
}

class PositionOrdering extends Ordering[Position] {

  override def compare(x:Position, y:Position): Int = {
    x.compare(y)
  }
}

