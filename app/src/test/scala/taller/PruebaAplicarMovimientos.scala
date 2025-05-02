/**
 * Prueba de aplicar movimiento
 */
package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PruebaAplicarMovimientos extends AnyFunSuite {
  test("Toy Test") {
    val obj = new ManiobrasTrenes()
    val e = (List('a','b','c','d','e'), List('f','g'), List('h','i','j'))

    val movs = List(
      Uno(2),     // mueve 'd','e' a t2
      Dos(1),     // mueve 'c' a t3
      Uno(-2),    // trae 'd','e' de t2 a t1
      Dos(-1),    // trae 'c' de t3 a t1
      Uno(3),     // mueve 'c','d','e' a t2
      Dos(2),     // mueve 'a','b' a t3
      Uno(-3),    // trae 'c','d','e' de t2 a t1
      Dos(-2),    // trae 'a','b' de t3 a t1
      Uno(1),     // mueve 'b' a t2
      Dos(1)      // mueve 'a' a t3
    )


    val res = obj.aplicarMovimientos(e, movs)
    val esperado = List(
      (List('a','b','c','d','e'), List('f','g'), List('h','i','j')),
      (List('a','b','c'), List('d','e','f','g'), List('h','i','j')),
      (List('a','b'), List('d','e','f','g'), List('c','h','i','j')),
      (List('a','b','d','e'), List('f','g'), List('c','h','i','j')),
      (List('a','b','d','e','c'), List('f','g'), List('h','i','j')),
      (List('a','b'), List('d','e','c','f','g'), List('h','i','j')),
      (List(), List('d','e','c','f','g'), List('a','b','h','i','j')),
      (List('d','e','c'), List('f','g'), List('a','b','h','i','j')),
      (List('d','e','c','a','b'), List('f','g'), List('h','i','j')),
      (List('d','e','c','a'), List('b','f','g'), List('h','i','j')),
      (List('d','e','c'), List('b','f','g'), List('a','h','i','j'))
    )
    assert(res == esperado)
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