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
    val e1 = (List('a','b','c','d','e','f','g','h','i','j'), List(), List())

    val e2 = obj.aplicarMovimiento(e1, Uno(3))
    assert(e2 == (List('a','b','c','d','e','f','g'), List('h','i','j'), List()))

    val e3 = obj.aplicarMovimiento(e2, Dos(2))
    assert(e3 == (List('a','b','c','d','e'), List('h','i','j'), List('f','g')))

    val e4 = obj.aplicarMovimiento(e3, Uno(1))
    assert(e4 == (List('a','b','c','d'), List('e','h','i','j'), List('f','g')))

    val e5 = obj.aplicarMovimiento(e4, Dos(-2))
    assert(e5 == (List('a','b','c','d','f','g'), List('e','h','i','j'), List()))

    val e6 = obj.aplicarMovimiento(e5, Uno(-1))
    assert(e6 == (List('a','b','c','d','f','g','e'), List('h','i','j'), List()))

    val e7 = obj.aplicarMovimiento(e6, Uno(2))
    assert(e7 == (List('a','b','c','d','f'), List('g','e','h','i','j'), List()))

    val e8 = obj.aplicarMovimiento(e7, Dos(1))
    assert(e8 == (List('a','b','c','d'), List('g','e','h','i','j'), List('f')))

    val e9 = obj.aplicarMovimiento(e8, Dos(-1))
    assert(e9 == (List('a','b','c','d','f'), List('g','e','h','i','j'), List()))

    val e10 = obj.aplicarMovimiento(e9, Uno(-2))
    assert(e10 == (List('a','b','c','d','f','g','e'), List('h','i','j'), List()))
  }
  test("Small Test") {
    // Listas de tamaño 100 vagones y movimientos
  }
  test("Medium Test"){
    // Listas de tamaño 500 vagones y movimientos
  }
  test("Big Test"){
    // Listas de tamaño 1000 vagones y movimientos

  }
}