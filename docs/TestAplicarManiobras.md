# Test de maniobras de vagones
Este test tiene como finalidad validar la seguridad del sistema de maniobras de
trenes implementado en la clase ManiobrasTrenes. A lo largo de n (101 en este ejemplo) movimientos, 
se asegura que ningún vagón se pierda ni duplique, comprobando que todos los vagones
estén siempre presentes tras cada maniobra.

## Conceptos Clave
- **Vagón**: Representado por letras del alfabeto ('a' a 'k'), cada letra corresponde a un vagón único.
- **Movimiento**: Se define como una operación que mueve vagones entre diferentes vías (T1, T2, T3).
- **Estado**: Representa la situación actual de los vagones en las vías.
- **Vías**: Tres vias (T1, T2, T3) donde los vagones pueden ser movidos.

### Movimiento

Uno(n): Mueve n elementos entre T1 y T2, Si n es negativo, el movimiento se invierte
(de T2 hacia T1) Dos(n): Mueve n elementos entre T1 y T3

## Codigo

```scala
test("Test de 100") {
val obj = new ManiobrasTrenes()

val letras: List[Char] = ('a' to 'k').toList
var estado: (List[Char], List[Char], List[Char]) = (letras, List(), List())

val rand = new scala.util.Random(42)

val movimientos: List[Movimiento] = (0 until 101).map { i =>
val n = rand.between(-3, 6)
if (i % 2 == 0) Uno(n) else Dos(n)
}.toList

for ((mov, i) <- movimientos.zipWithIndex) {
val nuevoEstado = obj.aplicarMovimiento(estado, mov).asInstanceOf[(List[Char], List[Char], List[Char])]
val combinados = nuevoEstado._1 ++ nuevoEstado._2 ++ nuevoEstado._3

    assert(
      combinados.sorted == letras.sorted
    )
  }
}
```

### Setup Inicial
```scala
val obj = new ManiobrasTrenes()
```
Se crea una instancia de la clase que contiene los metodos de maniobras.


### Definición de Vagones
```scala
val letras: List[Char] = ('a' to 'k').toList
```

Se genera una lista con las letras del alfabeto desde 'a' hasta 'k' inclusive y
esta es la lista original de vagones que nunca debe perderse.


### Estado Inicial
```scala
var estado: (List[Char], List[Char], List[Char]) = (letras, List(), List())
```

Define el estado inicial del sistema: todos los vagones están en la vía principal
(T1) y las otras vías están vacías.

### Generación de Movimientos Aleatorios
```scala
val rand = new scala.util.Random(42)
```

Se crea un generador de números aleatorios con una semilla fija (42) para asegurar
resultados reproducibles.

```scala
val movimientos: List[Movimiento] = (0 until 101).map { i =>
val n = rand.between(-3, 6)
if (i % 2 == 0) Uno(n) else Dos(n)
}.toList
```

Se generan 101 movimientos.

Cada numero n aleatorio está entre -3 y 5, lo que permite mover de vuelta (n negativo)
o hacia delante (n positivo).

La paridad del índice i determina si se aplica Uno(n) o Dos(n):

- **i % 2 == 0**: Movimiento tipo Uno (entre T1 y T2)

- **i % 2 == 1**: Movimiento tipo Dos (entre T1 y T3)

### Aplicacion de Movimientos y Verificacion
```scala
for ((mov, i) <- movimientos.zipWithIndex) {} 
```
Itera sobre cada movimiento generado junto a su índice.
```scala
val nuevoEstado = obj.aplicarMovimiento(estado, mov).asInstanceOf[(List[Char], List[Char], List[Char])]
```
Aplica el movimiento actual sobre el estado acumulado despues se hace type cast
a tupla de `List[Char]` para mantener tipado explícito.

```scala
val combinados = nuevoEstado._1 ++ nuevoEstado._2 ++ nuevoEstado._3
```

Combina todas las listas (vias T1, T2 y T3) para verificar que todos los vagones
originales están presentes.

```Scala
assert(combinados.sorted == letras.sorted)
```
Verifica que al ordenar todos los vagones actuales, se obtenga la misma lista ordenada
que los vagones iniciales.

Esto para asegurar que:
- No hay vagones perdidos
- No hay duplicados

Todos los vagones originales están en alguna de las tres vías

## Resultado Esperado

Si todos los movimientos son válidos y la implementación de aplicarMovimiento es correcta, 
el test debe pasar sin errores.

De lo contrario, el assert fallara si hay pérdida o duplicación de vagones.
