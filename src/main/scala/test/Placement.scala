package test

/**
  * Created by saz on 10/9/2016.
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
