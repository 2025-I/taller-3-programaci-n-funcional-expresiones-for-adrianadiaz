package taller

import scala.annotation.tailrec

class ManiobrasTrenes {
  type Vagon = Any
  type Tren = List[Vagon]
  type Estado = (Tren, Tren, Tren)
  type Maniobra = List[Movimiento]

  def aplicarMovimiento(e: Estado, m: Movimiento): Estado = {
    val (t1, t2, t3) = e
    m match {
      case Uno(n) if n > 0 => (
        t1.dropRight(n)
        , t1.takeRight(n) ++ t2
        , t3)
      case Dos(n) if n > 0 =>
        (t1.dropRight(n)
          , t2
          , t1.takeRight(n) ++ t3)
      case Uno(n) if n < 0 =>
        val a = n * -1
        (t1 ++ t2.take(a)
          , t2.drop(a)
          , t3)
      case Dos(n) if n < 0 =>
        val a = n * -1
        (t1 ++ t3.take(a)
          , t2
          , t3.drop(a))
      case _ => e
    }
  }

  def aplicarMovimientos(e: Estado, movs: Maniobra): List[Estado] = {
    def aplicarMovimientosAux(e: Estado, movs: Maniobra): List[Estado] = {
      movs.scanLeft(e)((estado, mov) => aplicarMovimiento(estado, mov)).init
    }

    val estados = for ((mov, estadoPrevio) <- movs.zip(aplicarMovimientosAux(e, movs)))
      yield aplicarMovimiento(estadoPrevio, mov)
    e :: estados
  }

  def definirManiobra(t1: Tren, t2: Tren): Maniobra = {
    // No se puede, porqu eso es mas mecanico que iterativo

    def definirManiobraAux(vagon: Vagon, estado: Estado): (Maniobra, Estado) = {
      val (t1_actual, _, _) = estado
      val indexEnT1 = t1_actual.indexOf(vagon)

      if (indexEnT1 != -1) {
        val largoT1 = t1_actual.length

        val movs = List(
          Uno(largoT1 - indexEnT1),
          Dos(indexEnT1),
          Uno(-(largoT1 - indexEnT1)),
          Dos(-indexEnT1)
        )

        // Aplicamos los movimientos y actualizamos el estado
        val nuevoEstado = aplicarMovimientos(estado, movs).last
        val estadoActualizado: Estado = (nuevoEstado._1.tail, nuevoEstado._2, nuevoEstado._3)

        (movs, estadoActualizado)
      } else {
        (List(), estado)
      }
    }


    val estadoInicial: Estado = (t1, List[Vagon](), List[Vagon]())

    val (maniobras, _) = (for (vagon <- t2) yield vagon).foldLeft((List[Movimiento](), estadoInicial)) {
      case ((accMovs, estadoActual), vagon) =>
        val (movs, nuevoEstado) = definirManiobraAux(vagon, estadoActual)
        (accMovs ::: movs, nuevoEstado)
    }
    maniobras
  }
}