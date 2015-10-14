package com.infomagnetic.linalg

import breeze.linalg.{DenseVector, DenseMatrix}
import breeze.storage.Zero

import scala.reflect.ClassTag

/**
 * linear algebra package
 */
object Linalg {
  /** Create Toeplitz matrix
    * {{{
    * scala > Linalg.toeplitz(DenseVector[Double](1,2,3))
    * 1 2 3
    * 2 1 2
    * 3 2 1
    * }}}
   * @param r DenseVector[Double] to create Toeplitz matrix
   * @return Toeplitz matrix:DenseMatrix[Double]
   */
  def toeplitz[T:ClassTag:Zero](r: Vector[T]): DenseMatrix[T] = {
    val len = r.size
    val A = DenseMatrix.zeros[T](len, len)
    (0 until len) foreach(i => {
      (i until len) foreach {j => A(i, j) = r(j-i)}
      (i until len) foreach { j => A(j, i) = r(j-i)}
    })
    A
  }

  /** Creat non-symmetric Toeplitz matrix
    * {{{
    * scala> Linalg.toeplitz(DenseVector[Double](1,2,3,4), DenseVector[Double](4,5,6))
    * 1 5 6
    * 2 1 5
    * 3 2 1
    * 4 3 2
    * }}}
   * @param c the fist column of Toeplitz matrix
   * @param r the fist row of Toeplitz matrix
   * @return non-symmetric Toeplitz matrix:DenseMatrix[Double]
   */
  def toeplitz[T:ClassTag:Zero](c: Vector[T], r: Vector[T]): DenseMatrix[T] = {
    if(c.head != r.head) println("Warning: toeplitz:DiagonalConflict")
    val clen = c.length
    val rlen = r.length
    val x = r.slice(1, rlen).reverse ++ c
    val cidx = 0 until clen
    val ridx = rlen to 1 by -1
    val ij = DenseMatrix.zeros[Int](clen, rlen)
    ij.foreachPair{case ((i, j), v) => ij(i,j) = cidx(i) + ridx(j)}
    val t = ij.mapPairs{ case ((i, j), v) => x(v-1)}
    t
  }
}