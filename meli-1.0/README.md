Test para Mercadolibre
============================

Repositorio para la solución del problema planteado de los planetas y el calculo del clima.

Herramientas utilizadas

* Java 7
* [Maven](https://maven.apache.org/download.cgi) (at least 3.3.9)
* [Gradle](https://gradle.org/gradle-download/) (optional)
* [Google Cloud SDK](https://cloud.google.com/sdk/) (aka gcloud)
* [Google GSon] (https://github.com/google/gson)
* [Java Servlet]

Se utilizó la siguiente estructura
* Entity (contiene las clases para el cálculo del clima)
* Repository (persistencia del modelo de datos generado, dentro de una instancia de Redis hosteada en RedisLab)
* Job (tarea para ejecutar el proceso de cáclculo cuando se requiera)
* Servlet (endpoints configurados de gcloud para consumirlos como un servicio)

En base a los datos proporcionados, lo primero que se realiza es transformar la posición de los planetas de coordenadas polares (r, &alpha;) a coordenadas cartesianas (x, y) para poder realizar cálculos de espacio R2, utilizando las fórmulas
* x = r * cos(&alpha;)
* y = r * sen(&alpha;)

Esta fórmula se aplica en el método de instancia setCoordenadaCartesiana() dentro de la clase `src/main/java/com/meli/entity/Planeta.java`.

Para la resolución se aplicaron las siguientes reglas: 

-Planetas alineados
Utilizando la teoría de "la recta que pasa por 2 o mas puntos" se puede deducir si los planteas se encuentran alineados, mediante el cálculo de sus pendientes, usando la fórmula
* dx = x2 - x1
* dy = y2 - y1
* mA = dy / dx (siempre que dx sea <> 0)
Si los planetas se encuentran alineados entre si, ya se deduce un estado del clima (óptimas condiciones de presión y temperatura). Utilizando la misma fórmula se verifica si la recta para por el sol (0,0) para obtener un estado de clima distinto (sequía).
Esta fórmula se encuntra en le método de instancia getCalculoDePendiente de la clase `src/main/java/com/meli/entity/ServicioMeteorologico.java`, la cual recibe por parámetro 2 objetos del tipo Point2D

-Puntos dentro del triángulo 
Como dice el enunciado, si los planetas no esta alineados, es decir que no pasan por la misma recta, entonces forman un triángulo. En base a eso se puede verificar si el sol se encuentra dentro de ese triángulo para obtener un estado de clima (lluvias), caso contrario el enunciado no hace referencia al estado de clima por lo que se deduce que es del tipo "indefinido".

Para verificar si un punto se encuentra dentro de un triángulo primero utilice la fórmulas de áreas (áreas de los 3 triángulos formados entre los 4 puntos tiene que ser igual al triángulo formado por los 3 puntos) pero en algunos casos no tuve buenos resultados y su proceso no era lo mas óptimo.
Entonces utilicé una fórmula del producto escalar, la cual obtuve de este sitio que posee varios algoritmos para realizar este tipo de problema.

* [Accurate point in triangle test] (http://totologic.blogspot.com.ar/2014/01/accurate-point-in-triangle-test.html)

Esta fórmula se aplica dentro de la clase `src/main/java/com/meli/entity/ServicioMeteorologico.java` en los métodos de instancia getProductoEscalar(Point2D p1, Point2D p2, Point2D p3) y puntoDentroDelTriangulo(Point2D p, Point2D v1, Point2D v2, Point2D v3)

========================================================================================================================================

El proyecto fué hosteado en google cloud 
