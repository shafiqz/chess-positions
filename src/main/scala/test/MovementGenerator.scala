package test

/**
  * Created by saz on 10/8/2016.
  */
object MovementGenerator {


  private
  def withinChessBoard(boardWidth: Int, boardHeight: Int): (Position) => Boolean = {
    p => {
      p.x <= boardWidth &&
      p.x > 0 &&
      p.y > 0 &&
      p.y <= boardHeight
    }
  }

  def upperLeft(pos: Position, width: Int, height: Int): List[Position] = {

    val xs = List.range(pos.x - 1, 0, -1)
    var y = pos.y

    xs.map( x => { y = y + 1; Position(x, y) })
      .filter(withinChessBoard(width, height))
  }

  def lowerLeft(pos: Position, width: Int, height: Int): List[Position] = {

    val xs = List.range(pos.x - 1, 0, -1)
    var y  = pos.y

    xs.map( x => { y = y - 1; Position(x, y) })
      .filter(withinChessBoard(width, height))
  }

  def lowerRight(pos: Position, width: Int, height: Int): List[Position] = {

    val xs = List.range(pos.x + 1, height + 1)
    var y  = pos.y

    xs.map(x => { y = y - 1; Position(x, y) })
      .filter(withinChessBoard(width, height))
  }

  def upperRight(pos: Position, width: Int, height: Int): List[Position] = {

    val xs = List.range(pos.x + 1, height + 1)
    var y  = pos.y

    xs.map( x => { y = y + 1; Position(x, y) })
      .filter(withinChessBoard(width, height))
  }

  def topToBottom(position: Position, boardWidth: Int, boardHeight: Int) : List[Position] = {

    for (y <- List.range(1, boardHeight + 1)
         if y != position.y)
    yield Position(position.x, y)
  }

  def leftToRight(position: Position, boardWidth: Int, boardHeight: Int) : List[Position] = {

    for (x <- List.range(1, boardWidth + 1)
         if x != position.x)
    yield Position(x, position.y)
  }

  def knightMoves(position: Position, boardWidth: Int, boardHeight: Int) : List[Position] = {

    (Position(position.x + 2, position.y + 1) ::
     Position(position.x + 2, position.y - 1) ::
     Position(position.x - 2, position.y + 1) ::
     Position(position.x - 2, position.y - 1) ::
     Position(position.x + 1, position.y + 2) ::
     Position(position.x + 1, position.y - 2) ::
     Position(position.x - 1, position.y + 2) ::
     Position(position.x - 1, position.y - 2) ::
     List()).filter(withinChessBoard(boardWidth, boardHeight))
  }

  def kingMoves(position: Position, boardWidth: Int, boardHeight: Int) : List[Position] = {

    (Position(position.x, position.y + 1) ::
     Position(position.x - 1, position.y + 1) ::
     Position(position.x - 1, position.y) ::
     Position(position.x - 1, position. y - 1) ::
     Position(position.x, position. y - 1) ::
     Position(position.x + 1, position. y - 1) ::
     Position(position.x + 1, position. y) ::
     Position(position.x + 1, position. y + 1) ::
     List()).filter(withinChessBoard(boardWidth, boardHeight))
  }

  def queenMoves(position: Position, boardWidth: Int, boardHeight: Int) : List[Position] = {

    (topToBottom(position, boardWidth, boardHeight) ++
     upperLeft(position, boardWidth, boardHeight) ++
     leftToRight(position, boardWidth, boardHeight) ++
     lowerLeft(position, boardWidth, boardHeight) ++
     leftToRight(position, boardWidth, boardHeight) ++
     lowerRight(position, boardWidth, boardHeight) ++
     upperRight(position, boardWidth, boardHeight)
    ).filter(withinChessBoard(boardWidth, boardHeight))
  }

  def rookMoves(position: Position, boardWidth: Int, boardHeight: Int) : List[Position] = {

    (topToBottom(position, boardWidth, boardHeight) ++
     leftToRight(position, boardWidth, boardHeight)
    ).filter(withinChessBoard(boardWidth, boardHeight))
  }

  def bishopMoves(position: Position, boardWidth: Int, boardHeight: Int) : List[Position] = {

    (upperLeft(position, boardWidth, boardHeight) ++
     lowerLeft(position, boardWidth, boardHeight) ++
     lowerRight(position, boardWidth, boardHeight) ++
     upperRight(position, boardWidth, boardHeight)
     ).filter(withinChessBoard(boardWidth, boardHeight))
  }
}
