package test

import org.scalatest._
/**
  * Created by saz on 10/8/2016.
  */
class MovementGeneratorSpec extends FlatSpec with Matchers {

  val mg = test.MovementGenerator

  "upperLeft" should "move north west" in {

    val p = Position(3, 1)

    assert(mg.upperLeft(p, 3, 3).equals(Position(2, 2)::Position(1, 3)::List()))
  }

  "bottomLeft" should "move south west" in {

    val p = Position(3, 2)

    assert(mg.lowerLeft(p, 3, 3).equals(Position(2, 1)::List()))
  }

  "leftToRight" should "move horizontally" in {

    val p = Position(2,3)

    val expected = Position(1,3) ::
                   Position(3,3) ::
                   Position(4,3) ::
                   Position(5,3) ::
                   List()

    assert(mg.leftToRight(p, 5, 5).equals(expected))
  }

  "topToBottom" should "move vertically" in {

    val p = Position(2,2)

    val expected = Position(2,1) ::
                   Position(2,3) ::
                   Position(2,4) ::
                   Position(2,5) ::
                   List()

    assert(mg.topToBottom(p, 5, 5).equals(expected))
  }

  "lowerRight" should "move south east" in {

    val p = Position(1, 3)

    assert(mg.lowerRight(p, 3, 3).equals(Position(2, 2)::Position(3, 1)::List()))
  }

  "upperRight" should "move north east" in {

    val p = Position(1, 2)

    assert(mg.upperRight(p, 4, 4).equals(Position(2, 3)::Position(3, 4)::List()))
  }

  "knightMoves" should "Jump like a knight" in {

    val p = Position(3, 3)

    val expected = Position(5,4) ::
                   Position(5,2) ::
                   Position(1,4) ::
                   Position(1,2) ::
                   Position(4,5) ::
                   Position(4,1) ::
                   Position(2,5) ::
                   Position(2,1) ::
                   List()
    assert(mg.knightMoves(p, 8, 8).equals(expected))

    assert(mg.knightMoves(Position(1, 3), 3, 3)
             .equals(Position(3, 2)::Position(2, 1)::List()))
  }

  "a king" should " move only one square" in {

    val p = Position(3, 3)

    val expected = Position(3,4) ::
                   Position(2,4) ::
                   Position(2,3) ::
                   Position(2,2) ::
                   Position(3,2) ::
                   Position(4,2) ::
                   Position(4,3) ::
                   Position(4,4) :: List()

    assert(mg.kingMoves(p, 5, 5).equals(expected))
  }
}
