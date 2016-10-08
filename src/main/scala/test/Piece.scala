package test

import scala.collection.mutable.{MutableList => MList};
/**
  * Created by saz on 10/8/2016.
  */
sealed trait Piece {
}

final class King extends Piece {
}

final class Queen extends Piece {
}

final class Rook extends Piece {
}

final class Knight extends Piece {
}