/**
 * Prueba de aplicar movimiento
 */
package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PruebaDefinirManiobras extends AnyFunSuite {
  test("Toy Test") {
    val obj = new ManiobrasTrenes()
    val t1 = List('a','b','c','d','e','f','g','h','i','j')
    val t2 = List('j','i','h','g','f','e','d','c','b','a')
    val movs = obj.definirManiobra(t1, t2)
    val ei = (t1, List(), List())
    val tf = obj.aplicarMovimientos(ei, movs).last
    val ef = (t2, List(), List())
    assert(tf == ef)
  }

  test("Small Test 100 movimientos") {
    val obj = new ManiobrasTrenes()
    val vagones = (0 until 100).toList.map(i => s"a$i")
    val vagonesObjetivo = vagones.reverse
    val movs = obj.definirManiobra(vagones, vagonesObjetivo)
    val ei = (vagones, List(), List())
    val tf = obj.aplicarMovimientos(ei, movs).last
    val ef = (vagonesObjetivo, List(), List())

    assert(tf == ef)

  }
  test("Medium Test"){
    val obj = new ManiobrasTrenes()
    val vagones = (0 until 500).toList.map(i => s"a$i")
    val vagonesObjetivo = vagones.reverse
    val movs = obj.definirManiobra(vagones, vagonesObjetivo)
    val ei = (vagones, List(), List())
    val tf = obj.aplicarMovimientos(ei, movs).last
    val ef = (vagonesObjetivo, List(), List())

    assert(tf == ef)

  }
  test("Big Test"){
    val obj = new ManiobrasTrenes()
    val vagones = (0 until 1000).toList.map(i => s"a$i")
    val vagonesObjetivo = vagones.reverse
    val movs = obj.definirManiobra(vagones, vagonesObjetivo)
    val ei = (vagones, List(), List())
    val tf = obj.aplicarMovimientos(ei, movs).last
    val ef = (vagonesObjetivo, List(), List())

    assert(tf == ef)

  }
}