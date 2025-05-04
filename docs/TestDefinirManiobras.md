# Documentación de Pruebas: `PruebaDefinirManiobras`

## Descripción General

La clase `PruebaDefinirManiobras` es una suite de pruebas desarrollada utilizando ScalaTest, específicamente la clase base `AnyFunSuite`. Esta suite tiene como objetivo verificar la correcta implementación de la lógica de maniobras de trenes, asegurando que la función `definirManiobra` genere una secuencia válida de movimientos que transformen un tren inicial (`t1`) en un tren objetivo (`t2`).

## Dependencias

* **ScalaTest**: Framework de pruebas utilizado para definir y ejecutar las pruebas unitarias.
* **JUnitRunner**: Permite la integración con herramientas que soportan JUnit.

## Estructura de la Suite de Pruebas

La suite contiene cuatro pruebas principales:

1. **Toy Test**: Prueba básica con una lista de caracteres.
2. **Small Test 100 movimientos**: Prueba con 100 vagones.
3. **Medium Test**: Prueba con 500 vagones.
4. **Big Test**: Prueba con 1000 vagones.

Cada prueba sigue la misma estructura:

1. **Inicialización**:

    * Se crea una instancia de `ManiobrasTrenes`.
    * Se definen `t1` y `t2`, representando el tren inicial y el tren objetivo, respectivamente.

2. **Generación de Maniobra**:

    * Se invoca `definirManiobra(t1, t2)` para obtener la secuencia de movimientos necesarios para transformar `t1` en `t2`.

3. **Aplicación de Movimientos**:

    * Se define el estado inicial `ei` como una tupla `(t1, List(), List())`, representando los tres trayectos: principal, uno y dos.
    * Se aplica la secuencia de movimientos utilizando `aplicarMovimientos(ei, movs)`.
    * Se obtiene el estado final `tf` tras aplicar todos los movimientos.

4. **Verificación**:

    * Se define el estado esperado `ef` como `(t2, List(), List())`.
    * Se utiliza `assert(tf == ef)` para verificar que el estado final coincide con el esperado.

## Explicación detallada de la prueba "Toy Test"

```scala
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
```

### Descripción General

Esta prueba verifica que la clase `ManiobrasTrenes` puede transformar un tren `t1` en el tren deseado `t2` usando una secuencia de movimientos definida por el método `definirManiobra`, y que estos movimientos, cuando se aplican paso a paso al estado inicial, producen exactamente el estado final esperado.

### Paso a Paso

1. **Inicialización del objeto:**

```scala
val obj = new ManiobrasTrenes()
```

Se crea una instancia de la clase que contiene la lógica de las maniobras.

2. **Definición de trenes de entrada y salida:**

```scala
val t1 = List('a','b','c','d','e','f','g','h','i','j')
val t2 = List('j','i','h','g','f','e','d','c','b','a')
```

`t1` es el tren inicial. `t2` es el tren objetivo. En este caso, se busca invertir el orden completo de los vagones.

3. **Cálculo de la maniobra:**

```scala
val movs = obj.definirManiobra(t1, t2)
```

Este método genera una lista de movimientos (`movs`) que permitirán transformar `t1` en `t2`. Es el "plan" de acciones necesarias para reordenar los vagones.

4. **Definición del estado inicial:**

```scala
val ei = (t1, List(), List())
```

El estado inicial consiste en los vagones en el trayecto principal (`t1`) y los trayectos auxiliares vacíos.

5. **Aplicación de movimientos:**

```scala
val tf = obj.aplicarMovimientos(ei, movs).last
```

Se aplican todos los movimientos generados por `definirManiobra`, comenzando desde el estado inicial. Se toma el último estado (`.last`), que corresponde al resultado final tras todas las maniobras.

6. **Definición del estado esperado:**

```scala
val ef = (t2, List(), List())
```

El estado esperado al final de la operación es que el trayecto principal contenga el tren `t2`, y los trayectos auxiliares estén vacíos.

7. **Aserción final:**

```scala
assert(tf == ef)
```

Se verifica que el estado final obtenido (`tf`) coincide exactamente con el estado esperado (`ef`). Si es así, la prueba pasa.

### Conclusión

Esta prueba valida que la lógica de definición y aplicación de maniobras funciona correctamente para una inversión total del tren. Sirve como un caso básico pero importante para confirmar el comportamiento esperado del sistema.

## Consideraciones

* Eficiencia: Las pruebas con 500 y 1000 vagones pueden ser costosas en términos de tiempo de ejecución. Se recomienda ejecutarlas en un entorno adecuado.
* Validación: Es fundamental que las funciones `definirManiobra` y `aplicarMovimientos` estén correctamente implementadas para que las pruebas pasen exitosamente.
