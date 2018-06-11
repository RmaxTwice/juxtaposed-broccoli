Generador de fotomosaicos - Por Rafael Vasquez y Raquel Escalante.
==================================================================

---------------------------------------------------------------------------------------------------------
Software que genera un fotomosaico basándose en una película, una imagen obtenida de ésta, y una serie de
muestras, también obtenidas de la misma, almacenadas en un directorio para realizar un mosaico.
---------------------------------------------------------------------------------------------------------

Al hacer click en Abrir Película, se obtiene la ruta de la película a procesar desde el directorio de documentos.
Luego, al hacer click en Elegir Imagen, se despliega un pequeño campo de texto que permite seleccionar, colocando
una marca de tiempo de la película, un cuadro determinado que funcionará como la imagen base del mosaico. 

En el menú "Preferencias" se pueden escoger los parámetros de personalización antes de generar las muestras y
generar el mosaico. Se pueden establecer las columnas y filas que se desea agregar, así como las columnas y filas de 
sampleo, y el número de muestras a generar, dependiendo del mosaico. Se recomienda que el numero de filas y columnas 
se mantenga en múltiplos de 2 y cuadrados, para mantener la relación de aspecto de la imagen original.

La creación de la base de datos se realiza almacenando las imagenes en un directorio IMAGEDB, y gestionándolo a través de
un arreglo en el código fuente.

Para definir cuáles son las imágenes que mejor se acoplan a cada bloque de la imagen original, donde cada bloque 
corresponde al espacio que ocupará una imagen muestra, se utiliza la fórmula de la distancia euclideana entre los valores
RGB de cada bloque y los valores RGB de la imagen muestra. A su vez, para analizar cada imagen de muestra, se realiza un 
cálculo del promedio dividiendo cada imagen de muestra en features, a los que se les calcula un promedio y con este promedio
se realiza finalmente el cálculo de la distancia.

Luego por cada bloque se va acoplando la mejor imagen candidata, y se le asigna un flag en la base de datos que indica que ya ha sido
utilizada para evitar repeticiones. 
