/**
 * Prueba de aplicar movimiento
 */
package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import scala.util.Random

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
    val obj = new ManiobrasTrenes()
    val e = (List('a','b','c','d'), List('e'), List('f','i'))

    val movs: List[Movimiento] = List.fill(100) {
      Random.nextInt(2) match {
        case 0 => Uno(Random.between(-4, 5))  // Uno va de -4 a 4
        case 1 => Dos(Random.between(-3, 4))  // Dos va de -3 a 3
      }
    }

    val res = obj.aplicarMovimientos(e, movs)

    assert(movs.length == 100)
    assert(res.length == 101)

    println("Estados después de aplicar los movimientos:")
    res.foreach(println)
  }
  test("Medium Test") {
    val obj = new ManiobrasTrenes()
    val e = (List('a','b','c','d'), List('e','f','g'), List('h','i'))

    // Generar 1000 movimientos aleatorios dentro de los rangos del patrón original
    val movs: List[Movimiento] = List.fill(500) {
      Random.nextInt(2) match {
        case 0 => Uno(Random.between(-4, 5))  // Uno va de -4 a 4
        case 1 => Dos(Random.between(-3, 4))  // Dos va de -3 a 3
      }
    }

    val res = obj.aplicarMovimientos(e, movs)

    assert(movs.length == 500)
    assert(res.length == 501)

    println("Estados después de aplicar los movimientos:")
    res.foreach(println)
  }
  test("Big Test") {
    val obj = new ManiobrasTrenes()
    val e = (List('a','b','c','d','e'), List('f','g'), List('h','i','j'))

    // Generar 1000 movimientos aleatorios dentro de los rangos del patrón original
    val movs: List[Movimiento] = List.fill(1000) {
      Random.nextInt(2) match {
        case 0 => Uno(Random.between(-4, 5))  // Uno va de -4 a 4
        case 1 => Dos(Random.between(-3, 4))  // Dos va de -3 a 3
      }
    }

    val res = obj.aplicarMovimientos(e, movs)

    assert(movs.length == 1000)
    assert(res.length == 1001)

    println("Estados después de aplicar los movimientos:")
    res.foreach(println)
  }
}