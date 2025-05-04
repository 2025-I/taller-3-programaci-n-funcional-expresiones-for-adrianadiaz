/**
 * Prueba de aplicar movimiento
 */
package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PruebaAplicarManiobras extends AnyFunSuite {
  test("Toy Test 10 vagones con 10 movimientos") {
    val obj = new ManiobrasTrenes()
    val inicial = (List[Any]('a','b','c','d','e','f','g','h','i','j'), List.empty[Any], List.empty[Any])
    val movimientos = List(Uno(3), Dos(2), Uno(1), Dos(-2), Uno(-1), Uno(2), Dos(1), Dos(-1), Uno(-2))

    val estados = movimientos.scanLeft(inicial)(obj.aplicarMovimiento)

    val esperados = List(
      (List('a','b','c','d','e','f','g'), List('h','i','j'), List()),
      (List('a','b','c','d','e'), List('h','i','j'), List('f','g')),
      (List('a','b','c','d'), List('e','h','i','j'), List('f','g')),
      (List('a','b','c','d','f','g'), List('e','h','i','j'), List()),
      (List('a','b','c','d','f','g','e'), List('h','i','j'), List()),
      (List('a','b','c','d','f'), List('g','e','h','i','j'), List()),
      (List('a','b','c','d'), List('g','e','h','i','j'), List('f')),
      (List('a','b','c','d','f'), List('g','e','h','i','j'), List()),
      (List('a','b','c','d','f','g','e'), List('h','i','j'), List())
    )

    for ((estado, esperado) <- estados.tail.zip(esperados)) {
      val actual = (estado._1.map(_.asInstanceOf[Char]), estado._2.map(_.asInstanceOf[Char]), estado._3.map(_.asInstanceOf[Char]))
      assert(actual == esperado)
    }
  }

  test("Test de 100") {
    val obj = new ManiobrasTrenes()
    val letras: List[Char] = ('a' to 'k').toList
    val inicial = (letras: List[Any], List.empty[Any], List.empty[Any])

    val rand = new scala.util.Random(42)
    val movimientos: List[Movimiento] = (0 until 101).map { i =>
      val n = rand.between(-3, 6)
      if (i % 2 == 0) Uno(n) else Dos(n)
    }.toList

    val estados = movimientos.scanLeft(inicial)(obj.aplicarMovimiento)

    for (estado <- estados.tail) {
      val combinados = (estado._1 ++ estado._2 ++ estado._3).map(_.asInstanceOf[Char])
      assert(combinados.sorted == letras.sorted)
    }
  }

  test("Test de 500") {
    val obj = new ManiobrasTrenes()
    val letras: List[Char] = ('a' to 'k').toList
    val inicial = (letras: List[Any], List.empty[Any], List.empty[Any])

    val rand = new scala.util.Random(42)
    val movimientos: List[Movimiento] = (0 until 501).map { i =>
      val n = rand.between(-4, 20)
      if (i % 2 == 0) Uno(n) else Dos(n)
    }.toList

    val estados = movimientos.scanLeft(inicial)(obj.aplicarMovimiento)

    for (estado <- estados.tail) {
      val combinados = (estado._1 ++ estado._2 ++ estado._3).map(_.asInstanceOf[Char])
      assert(combinados.sorted == letras.sorted)
    }
  }

  test("Test de 1000") {
    val obj = new ManiobrasTrenes()
    val letras: List[Char] = ('a' to 'k').toList
    val inicial = (letras: List[Any], List.empty[Any], List.empty[Any])

    val rand = new scala.util.Random(42)
    val movimientos: List[Movimiento] = (0 until 1001).map { i =>
      val n = rand.between(-30, 60)
      if (i % 2 == 0) Uno(n) else Dos(n)
    }.toList

    val estados = movimientos.scanLeft(inicial)(obj.aplicarMovimiento)

    for (estado <- estados.tail) {
      val combinados = (estado._1 ++ estado._2 ++ estado._3).map(_.asInstanceOf[Char])
      assert(combinados.sorted == letras.sorted)
    }
  }
}
