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
    val vagones = List.range(0, 100).map(i => ('a' + (i % 26)).toChar)

    // Distribuir los vagones iniciales
    val t1 = vagones.take(33)
    val t2 = vagones.slice(33, 66)
    val t3 = vagones.drop(66)
    val e = (t1, t2, t3)

    val movs = {
      val lista = scala.collection.mutable.ListBuffer[Movimiento]()
      for (i <- 0 until 100) {
        val tamaño = (i % 5) + 1
        val mov = i % 4 match {
          case 0 => Uno(tamaño)
          case 1 => Dos(tamaño)
          case 2 => Uno(-tamaño)
          case _ => Dos(-tamaño)
        }
        lista += mov
      }
      lista.toList
    }

    val res = obj.aplicarMovimientos(e, movs)

    assert(movs.length == 100)
    assert(res.length == 101)

    println("Estados después de aplicar los movimientos:")
    res.foreach(println)
  }

  test("Small Test Caso 2") {
    val obj = new ManiobrasTrenes()
    val vagones = List.range(0, 100).map(i => ('a' + (i % 26)).toChar)

    // Distribuir los vagones iniciales
    val t1 = vagones.take(33)
    val t2 = vagones.slice(33, 66)
    val t3 = vagones.drop(66)
    val e = (t1, t2, t3)

    val movs = {
      val lista = scala.collection.mutable.ListBuffer[Movimiento]()
      for (i <- 0 until 100) {
        val tamaño = (i % 5) + 2 // Cambio en el tamaño
        val mov = i % 4 match {
          case 0 => Uno(tamaño)
          case 1 => Dos(tamaño)
          case 2 => Uno(-tamaño)
          case _ => Dos(-tamaño)
        }
        lista += mov
      }
      lista.toList
    }

    val res = obj.aplicarMovimientos(e, movs)

    assert(movs.length == 100)
    assert(res.length == 101)

    println("Estados después de aplicar los movimientos en Caso 2:")
    res.foreach(println)
  }
  test("Small Test Caso 3") {
    val obj = new ManiobrasTrenes()
    val vagones = List.range(0, 100).map(i => ('a' + (i % 26)).toChar)

    // Distribuir los vagones iniciales
    val t1 = vagones.take(33)
    val t2 = vagones.slice(33, 66)
    val t3 = vagones.drop(66)
    val e = (t1, t2, t3)

    val movs = {
      val lista = scala.collection.mutable.ListBuffer[Movimiento]()
      for (i <- 0 until 100) {
        val tamaño = (i % 5) + 1
        val mov = i % 4 match {
          case 0 => Uno(-tamaño) // Cambio en la dirección
          case 1 => Dos(-tamaño) // Cambio en la dirección
          case 2 => Uno(tamaño)
          case _ => Dos(tamaño)
        }
        lista += mov
      }
      lista.toList
    }

    val res = obj.aplicarMovimientos(e, movs)

    assert(movs.length == 100)
    assert(res.length == 101)

    println("Estados después de aplicar los movimientos en Caso 3:")
    res.foreach(println)
  }

  test("Medium Test") {
    val obj = new ManiobrasTrenes()
    val vagones = List.range(0, 500).map(i => ('a' + (i % 26)).toChar)

    // Distribuir los vagones iniciales
    val t1 = vagones.take(500 / 3)
    val t2 = vagones.slice(500 / 3, 2 * 500 / 3)
    val t3 = vagones.drop(2 * 500 / 3)
    val e = (t1, t2, t3)

    val movs = {
      val lista = scala.collection.mutable.ListBuffer[Movimiento]()
      for (i <- 0 until 500) {
        val tamaño = (i % 5) + 1
        val mov = i % 4 match {
          case 0 => Uno(tamaño)
          case 1 => Dos(tamaño)
          case 2 => Uno(-tamaño)
          case _ => Dos(-tamaño)
        }
        lista += mov
      }
      lista.toList
    }

    val res = obj.aplicarMovimientos(e, movs)

    assert(movs.length == 500)
    assert(res.length == 500 + 1)

    println("Estados después de aplicar los movimientos:")
    res.foreach(println)
  }
  test("Medium Test Caso 2") {
    val obj = new ManiobrasTrenes()
    val vagones = List.range(0, 500).map(i => ('a' + (i % 26)).toChar)

    // Distribuir los vagones iniciales
    val t1 = vagones.take(500 / 3)
    val t2 = vagones.slice(500 / 3, 2 * 500 / 3)
    val t3 = vagones.drop(2 * 500 / 3)
    val e = (t1, t2, t3)

    val movs = {
      val lista = scala.collection.mutable.ListBuffer[Movimiento]()
      for (i <- 0 until 500) {
        val tamaño = (i % 5) + 3 // Cambio en el tamaño
        val mov = i % 4 match {
          case 0 => Uno(tamaño)
          case 1 => Dos(tamaño)
          case 2 => Uno(-tamaño)
          case _ => Dos(-tamaño)
        }
        lista += mov
      }
      lista.toList
    }

    val res = obj.aplicarMovimientos(e, movs)

    assert(movs.length == 500)
    assert(res.length == 500 + 1)

    println("Estados después de aplicar los movimientos en Caso 2:")
    res.foreach(println)
  }
  test("Medium Test Caso 3") {
    val obj = new ManiobrasTrenes()
    val vagones = List.range(0, 500).map(i => ('a' + (i % 26)).toChar)

    // Distribuir los vagones iniciales
    val t1 = vagones.take(500 / 3)
    val t2 = vagones.slice(500 / 3, 2 * 500 / 3)
    val t3 = vagones.drop(2 * 500 / 3)
    val e = (t1, t2, t3)

    val movs = {
      val lista = scala.collection.mutable.ListBuffer[Movimiento]()
      for (i <- 0 until 500) {
        val tamaño = (i % 5) + 1
        val mov = i % 4 match {
          case 0 => Uno(-tamaño) // Cambio en la dirección
          case 1 => Dos(-tamaño) // Cambio en la dirección
          case 2 => Uno(tamaño)
          case _ => Dos(tamaño)
        }
        lista += mov
      }
      lista.toList
    }

    val res = obj.aplicarMovimientos(e, movs)

    assert(movs.length == 500)
    assert(res.length == 500 + 1)

    println("Estados después de aplicar los movimientos en Caso 3:")
    res.foreach(println)
  }


  test("Big Test") {
    val obj = new ManiobrasTrenes()
    val vagones = List.range(0, 1000).map(i => ('a' + (i % 26)).toChar)

    // Distribuir los vagones iniciales
    val t1 = vagones.take(1000 / 3)
    val t2 = vagones.slice(1000 / 3, 2 * 1000 / 3)
    val t3 = vagones.drop(2 * 1000 / 3)
    val e = (t1, t2, t3)


    val movs = {
      val lista = scala.collection.mutable.ListBuffer[Movimiento]()
      for (i <- 0 until 1000) {
        val tamaño = (i % 5) + 1
        val mov = i % 4 match {
          case 0 => Uno(tamaño)
          case 1 => Dos(tamaño)
          case 2 => Uno(-tamaño)
          case _ => Dos(-tamaño)
        }
        lista += mov
      }
      lista.toList
    }

    val res = obj.aplicarMovimientos(e, movs)

    assert(movs.length == 1000)
    assert(res.length == 1000 + 1)

    println("Estados después de aplicar lost movimientos:")
    res.foreach(println)
  }
  test("Big Test Caso 2") {
    val obj = new ManiobrasTrenes()
    val vagones = List.range(0, 1000).map(i => ('a' + (i % 26)).toChar)

    // Distribuir los vagones iniciales
    val t1 = vagones.take(1000 / 3)
    val t2 = vagones.slice(1000 / 3, 2 * 1000 / 3)
    val t3 = vagones.drop(2 * 1000 / 3)
    val e = (t1, t2, t3)

    val movs = {
      val lista = scala.collection.mutable.ListBuffer[Movimiento]()
      for (i <- 0 until 1000) {
        val tamaño = (i % 5) + 4 // Cambio en el tamaño
        val mov = i % 4 match {
          case 0 => Uno(tamaño)
          case 1 => Dos(tamaño)
          case 2 => Uno(-tamaño)
          case _ => Dos(-tamaño)
        }
        lista += mov
      }
      lista.toList
    }

    val res = obj.aplicarMovimientos(e, movs)

    assert(movs.length == 1000)
    assert(res.length == 1000 + 1)

    println("Estados después de aplicar los movimientos en Caso 2:")
    res.foreach(println)
  }
  test("Big Test Caso 3") {
    val obj = new ManiobrasTrenes()
    val vagones = List.range(0, 1000).map(i => ('a' + (i % 26)).toChar)

    // Distribuir los vagones iniciales
    val t1 = vagones.take(1000 / 3)
    val t2 = vagones.slice(1000 / 3, 2 * 1000 / 3)
    val t3 = vagones.drop(2 * 1000 / 3)
    val e = (t1, t2, t3)

    val movs = {
      val lista = scala.collection.mutable.ListBuffer[Movimiento]()
      for (i <- 0 until 1000) {
        val tamaño = (i % 5) + 1
        val mov = i % 4 match {
          case 0 => Uno(-tamaño) // Cambio en la dirección
          case 1 => Dos(-tamaño) // Cambio en la dirección
          case 2 => Uno(tamaño)
          case _ => Dos(tamaño)
        }
        lista += mov
      }
      lista.toList
    }

    val res = obj.aplicarMovimientos(e, movs)

    assert(movs.length == 1000)
    assert(res.length == 1000 + 1)

    println("Estados después de aplicar los movimientos en Caso 3:")
    res.foreach(println)
  }

}