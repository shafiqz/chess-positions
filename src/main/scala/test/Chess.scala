package test

import scala.collection.parallel.ParSet

/**
  * Created by saz on 10/7/2016.
  */
object Chess {

  implicit val posOrdering = new PositionOrdering

  /**
    * Check to see if a piece can safely occupy a square on the board.
    * <p>
    *    The piece can occupy the square if the following condition holds:
    *     1. The square has not been occupied by another piece.
    *     2. The square is not under attacked by any already placed pieced.
    *     3. The piece will not threaten any existing pieces when placed.
    *  </p>
    * @param square Square a piece wants to occupy.
    * @param occupied List of positions already occupied by the piece.
    * @param attackable List of squares that is attacked by the pieces of
    *                   occupied
    * @param boardWidth Width of the board
    * @param boardHeight Height of the board.
    * @return True if it is safe to place the piece there. False otherwise.
    */
  def safeToPlace(square: Placement,         occupied: List[Placement],
                  attackable: List[Position], boardWidth: Int,
                  boardHeight: Int) : Boolean = {

    //If the square is already occupied, then it's not safe.
    if (occupied.exists( p => { p.position == square.position })) {
      return false
    }

    //If the square can be attacked by already placed pieces, then it is not safe.
    if (attackable.contains( square.position )) {
      return false
    }

    //If when placed in the square, the candidate can attack already placed
    //pieces, then it is not safe.
    val e = square.piece.movablePositions(square.position, boardWidth,
      boardHeight)
    if(occupied.exists(p => { e.contains(p.position) })) {
      return false
    }

    true
  }

  def generatePieces(piece: Piece, count: Int) : List[Piece] = {

    if (count <= 0) {
      List()
    }
    else {

      piece match {
        case King => {
          List.fill(count)(King)
        }
        case Queen => {
          List.fill(count)(Queen)
        }
        case Rook => {
          List.fill(count)(Rook)
        }
        case Bishop => {
          List.fill(count)(Bishop)
        }
        case Knight => {
          List.fill(count)(Knight)
        }
      }
    }
  }

  /**
    * Generate a list of piece.
    * The generated List will be in this order: Kings, Queens, Rooks, Bishops,
    * Knights.
    * @param kings No. of kings to generate. Start from 0.
    * @param queens No. of queens to generate. Start from 0.
    * @param rooks No. of rooks to generate. Start from 0.
    * @param bishops No. of bishops to generate. Start from 0.
    * @param knights No. of knights to generate. Start from 0.
    * @return A list of pieces.
    */
  def generatePieces(kings: Int, queens: Int, rooks: Int, bishops: Int, knights: Int):
  List[Piece] = {

    generatePieces(King, kings) ++
    generatePieces(Queen, queens) ++
    generatePieces(Rook, rooks) ++
    generatePieces(Bishop, bishops) ++
    generatePieces(Knight, knights)
  }

  /**
    * Generate the chess-board.
    * @param boardWidth Width of the board.
    * @param boardHeight Height of the board.
    * @return All the positions of the board.
    */
  def generateBoardPositions(boardWidth: Int, boardHeight: Int):
  List[Position] = {
    val rows = List.range(1, boardHeight + 1)
    val cols = List.range(1, boardWidth + 1)

    for {
      col <- cols
      row <- rows
    } yield { Position(col, row) }
  }

   def sortByPosition(left: Placement, right: Placement) : Boolean = {

    left.position < right.position
  }

  def solve2(piecesToPut:List[Piece], occupied: List[Placement],
             freeSquares: Set[Position], attackable : List[Position],
             boardWidth: Int, boardHeight: Int): Set[List[Placement]] = {


    if (piecesToPut.isEmpty) {
      return Set(occupied.sortWith(sortByPosition))
    }

    if (freeSquares.isEmpty) {
      return Set()
    }

    val aPiece = piecesToPut.head

    val safeSquares = freeSquares
                      .filter(aSquare => {
                         val candidate = Placement(aPiece, aSquare)
                         safeToPlace(candidate,  occupied,   attackable,
                         boardWidth, boardHeight)
                      })

    safeSquares.flatMap(aSquare => {

      val candidate = Placement(aPiece, aSquare)

      val movablePositions = candidate.piece.movablePositions(candidate.position, boardWidth, boardHeight)
      val newAttackable    = attackable ++ movablePositions
      val newFreeSquares   = freeSquares.filter( fSquare => { !newAttackable.contains(fSquare) && !(fSquare == aSquare) } )
      solve2(piecesToPut.tail, (occupied ++ List(candidate)).sortBy(_.position),
             newFreeSquares,   newAttackable,
             boardWidth,       boardHeight)
    })
  }

  def compute(kings: Int,   queens: Int,  rooks: Int,
              bishops: Int, knights: Int, boardWith: Int,
              boardHeight: Int) : ParSet[List[Placement]] = {

    val boardSquares = generateBoardPositions(boardWith, boardHeight).toSet
    val parSquares   = boardSquares.par
    val allPieces    = generatePieces(kings, queens, rooks, bishops, knights)

    parSquares.flatMap( square => {
      val aPiece      = allPieces.head
      val placement   = Placement(aPiece, square)
      val attackable  = aPiece.movablePositions(square, boardWith, boardHeight)
      val freeSquares = boardSquares.filter(sq => { !attackable.contains(sq) &&
                                                    !(sq == square) })

      solve2(allPieces.tail, List(placement), freeSquares,
             attackable,      boardWith,      boardHeight)
    })

  }
}