package test

/**
  * Created by saz on 10/9/2016.
  * Represents a {@code Piece} on when it's put on the chess board.
  * @see test.Piece
  * @see test.Position
  */
case class Placement(piece : Piece, position: Position) {

  override def toString() = {
    val pieceStr = piece match {
      case King => "King"
      case Queen => "Queen"
      case Rook => "Rook"
      case Bishop => "Bishop"
      case Knight => "Knight"
    }

    val posStr = "(" + position.x + ", " + position.y + ")"

    pieceStr + posStr
  }
}
