# Documentación de la Prueba "Toy Test" en Maniobras de Trenes

## Objetivo

El propósito de la prueba "Toy Test" es verificar el correcto funcionamiento del método `aplicarMovimientos` de la clase `ManiobrasTrenes`, asegurando que una serie de movimientos sobre un conjunto de vagones se ejecutan de forma secuencial y producen los estados esperados del sistema.

## Modelo de Maniobras

### Tipos de Datos

* **Vagón**: cualquier dato (carácter, entero, etc.).
* **Tren**: una lista de vagones sin duplicados.
  `type Tren = List[Vagon]`
* **Estado**: Representado como una tupla de tres listas: `(t1, t2, t3)`, donde:

    * `t1` representa el trayecto **principal**,
    * `t2` representa el trayecto auxiliar **uno**,
    * `t3` representa el trayecto auxiliar **dos**.
      `type Estado = (Tren, Tren, Tren)`
* **Movimiento**: Dos tipos principales de movimientos definidos por un entero `n`:

    * `Uno(n)`: Movimiento entre `t1` y `t2`.

        * Si `n > 0`, se mueven los `n` vagones más a la derecha de `t1` a `t2`.
        * Si `n < 0`, se mueven los `n` vagones más a la izquierda de `t2` a `t1`.
    * `Dos(n)`: Movimiento entre `t1` y `t3`.

        * Si `n > 0`, se mueven los `n` vagones más a la derecha de `t1` a `t3`.
        * Si `n < 0`, se mueven los `n` vagones más a la izquierda de `t3` a `t1`.
    * `n == 0` no produce ningún cambio.

### Función `aplicarMovimiento`

Esta función aplica un solo movimiento sobre un estado. La implementación sigue estas reglas:

```scala
def aplicarMovimiento(e: Estado, m: Movimiento): Estado = m match {
  case Uno(n) if n > 0 =>
    val (main, uno, dos) = e
    val (quedar, mover) = main.splitAt(main.length - n)
    (quedar, uno ++ mover, dos)

  case Uno(n) if n < 0 =>
    val (main, uno, dos) = e
    val (mover, quedar) = uno.splitAt(-n)
    (mover ++ main, quedar, dos)

  case Dos(n) if n > 0 =>
    val (main, uno, dos) = e
    val (quedar, mover) = main.splitAt(main.length - n)
    (quedar, uno, dos ++ mover)

  case Dos(n) if n < 0 =>
    val (main, uno, dos) = e
    val (mover, quedar) = dos.splitAt(-n)
    (mover ++ main, uno, quedar)

  case _ => e
}
```

En términos prácticos:

* Cuando se mueve desde `t1` hacia otro trayecto (positivo), se extraen los vagones desde el final de `t1` (parte derecha).
* Cuando se recuperan vagones hacia `t1` desde otro trayecto (negativo), se extraen desde el inicio (parte izquierda) del trayecto auxiliar.
* Los vagones se añaden al final del trayecto destino.

Esto asegura un comportamiento tipo "pila" para `t1` cuando se mueve a otros trayectos, y comportamiento de "cola" al retornar.

### Ejemplo de Aplicación de Movimientos

```scala
val e1 = (List('a','b','c','d'), Nil, Nil)
val e2 = aplicarMovimiento(e1, Uno(2))       // mueve 'c','d' a t2
val e3 = aplicarMovimiento(e2, Dos(3))       // mueve 'a','b' a t3
val e4 = aplicarMovimiento(e3, Dos(-1))      // trae 'a' de t3 a t1
val e5 = aplicarMovimiento(e4, Uno(-2))      // trae 'c','d' de t2 a t1
```

Resultados:

```scala
e1 = (List(a,b,c,d), List(), List())
e2 = (List(a,b), List(c,d), List())
e3 = (List(), List(c,d), List(a,b))
e4 = (List(a), List(c,d), List(b))
e5 = (List(a,c,d), List(), List(b))
```

Esta secuencia muestra cómo se modifican los estados al aplicar movimientos simples, permitiendo el análisis paso a paso del comportamiento esperado.

## Análisis de la Prueba "Toy Test"

En esta prueba, se parte de un estado inicial:

```scala
val e = (List('a','b','c','d','e'), List('f','g'), List('h','i','j'))
```

Donde:

* `t1 = List('a','b','c','d','e')`
* `t2 = List('f','g')`
* `t3 = List('h','i','j')`

Se aplica una secuencia de movimientos cuidadosamente diseñada:

```scala
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
```

La función `aplicarMovimientos` ejecuta cada uno de estos movimientos secuencialmente y produce una lista de estados:

```scala
val res = obj.aplicarMovimientos(e, movs)
```

El resultado esperado (`esperado`) contiene el estado después de cada movimiento, comenzando por el estado inicial:

```scala
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
```

Finalmente, la prueba verifica que los estados generados por `aplicarMovimientos` coincidan con los estados esperados:

```scala
assert(res == esperado)
```

Esta prueba es esencial para validar que la lógica de movimiento está correctamente implementada y que se mantiene la consistencia del sistema a lo largo de múltiples transformaciones complejas.
