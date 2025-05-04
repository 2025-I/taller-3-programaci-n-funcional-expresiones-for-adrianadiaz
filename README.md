# Resumen del Proyecto: Maniobras en Trenes

**Realizado por:**
Juan José Guzmán Camacho - 2380403

Natalia Martínez Castañeda - 2380414

Juan José Wallens Serna - 2380556

## Objetivo

El propósito del proyecto es desarrollar destrezas en programación funcional utilizando expresiones `for` mediante la simulación de maniobras de trenes en una estación.

## Contexto

Se trabaja con trenes sin motor específico, donde cada vagón puede moverse individualmente. Dado un tren inicial y uno deseado, se deben realizar maniobras en una estación para transformar el primero en el segundo, registrando cada movimiento.

## Estructura de la Estación

La estación cuenta con tres trayectos:

* **Principal**
* **Uno** (auxiliar)
* **Dos** (auxiliar)

Un **estado** se representa como una tupla con tres listas:

```scala
type Vagon = Any
type Tren = List[Vagon]
type Estado = (Tren, Tren, Tren)
```

## Movimientos

Se definen mediante clases `case`:

```scala
trait Movimiento
case class Uno(n: Int) extends Movimiento
case class Dos(n: Int) extends Movimiento
```

* `Uno(n)`: mueve `n` vagones del trayecto principal al trayecto uno si `n > 0`, o en sentido contrario si `n < 0`.
* `Dos(n)`: similar pero con el trayecto dos.

## Funciones Clave

### aplicarMovimiento

Aplica un movimiento a un estado:

```scala
def aplicarMovimiento(e: Estado, m: Movimiento): Estado = { ... }
```

### aplicarMovimientos

Aplica una lista de movimientos y retorna la secuencia de estados:

```scala
def aplicarMovimientos(e: Estado, movs: List[Movimiento]): List[Estado] = { ... }
```

### definirManiobra

Determina la lista de movimientos para transformar un tren inicial `t1` en uno deseado `t2`, partiendo con los trayectos auxiliares vacíos:

```scala
definirManiobra(t1: Tren, t2: Tren): List[Movimiento]
```

## Notas

* No se permiten vagones duplicados en un tren.
* Existen múltiples soluciones posibles para una misma transformación.

---
