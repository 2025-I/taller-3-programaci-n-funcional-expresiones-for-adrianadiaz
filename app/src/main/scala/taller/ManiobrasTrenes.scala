package taller

import scala.annotation.tailrec

class ManiobrasTrenes {
  type Vagon = Any
  type Tren = List[Vagon]
  type Estado = (Tren,Tren,Tren)
  type Maniobra = List[Movimiento]

  def aplicarMovimiento(e:Estado,m:Movimiento):Estado = {
    val (t1,t2,t3) = e
    m match {
      case Uno(n) if n > 0 => (
        t1.dropRight(n)
        ,t1.takeRight(n) ++ t2
        ,t3)
      case Dos(n) if n > 0 =>
        (t1.dropRight(n)
          ,t2
          ,t1.takeRight(n) ++ t3)
      case Uno(n) if n < 0 =>
        val a = n * -1
        (t1 ++ t2.take(a)
          ,t2.drop(a)
          , t3)
      case Dos(n) if n < 0 =>
        val a = n * -1
        (t1 ++ t3.take(a)
          ,t2
          ,t3.drop(a))
      case _ => e
    }
  }
  def aplicarMovimientos(e:Estado, movs:Maniobra) : List[Estado] = {
    movs.foldLeft(List(e)) { (estados, mov) =>
      estados :+ aplicarMovimiento(estados.last, mov)
    }
  }
  def definirManiobra(t1: Tren, t2: Tren): Maniobra = {
    var movimientos: Maniobra = List()
    var estado: Estado = (t1, List(), List())

    for (vagonObjetivo <- t2) {
      val (t1_actual, _, _) = estado
      val indexEnT1 = t1_actual.indexOf(vagonObjetivo)

      if (indexEnT1 != -1) {
        val largoT1 = t1_actual.length

        // Llevar el vagon a t2
        val movs = List(
          Uno(largoT1 - indexEnT1),   // Mover n vagones a t2
          Dos(indexEnT1),            // Mover solo el deseado a t3
          Uno(-(largoT1 - indexEnT1)), // Devolver los demás a t1
          Dos(-indexEnT1)            // Traer de vuelta el deseado desde t3 a inicio de t1
        )

        movimientos = movimientos ::: movs
        estado = aplicarMovimientos(estado, movs).last
        // El vagon ya está en su lugar, lo quitamos de la cabeza para seguir con el resto
        estado = (estado._1.tail, estado._2, estado._3)
      }
    }

    movimientos
  }








}
