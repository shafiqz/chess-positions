package test

import scala.collection.mutable.{MutableList => MList};
/**
  * Created by saz on 10/8/2016.
  */
sealed trait Piece {

  val mg = MovementGenerator

  def
  movablePositions(position: Position, boardWith: Int, boardHeight: Int) :
  List[Position]
}

object King extends Piece {

  override
  def movablePositions(position: Position, boardWith: Int, boardHeight: Int):
  List[Position] = mg.kingMoves(position, boardWith, boardHeight);
}

object Queen extends Piece {

  override
  def movablePositions(position: Position, boardWith: Int, boardHeight: Int):
  List[Position] = mg.queenMoves(position, boardWith, boardHeight)
}

object Bishop extends Piece {
  override
  def movablePositions(position: Position, boardWith: Int, boardHeight: Int):
  List[Position] = mg.bishopMoves(position, boardWith, boardHeight)
}

object Rook extends Piece {
  override
  def movablePositions(position: Position, boardWith: Int, boardHeight: Int):
  List[Position] = mg.rookMoves(position, boardWith, boardHeight)
}

object Knight extends Piece {

  override
  def movablePositions(position: Position, boardWith: Int, boardHeight: Int):
  List[Position] = mg.knightMoves(position, boardWith, boardHeight)
}