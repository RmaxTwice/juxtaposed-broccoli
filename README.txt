Generador de fotomosaicos - Por Rafael Vasquez y Raquel Escalante.
==================================================================

---------------------------------------------------------------------------------------------------------
Software que genera un fotomosaico bas�ndose en una pel�cula, una imagen obtenida de �sta, y una serie de
muestras, tambi�n obtenidas de la misma, almacenadas en un directorio para realizar un mosaico.
---------------------------------------------------------------------------------------------------------

Al hacer click en Abrir Pel�cula, se obtiene la ruta de la pel�cula a procesar desde el directorio de documentos.
Luego, al hacer click en Elegir Imagen, se despliega un peque�o campo de texto que permite seleccionar, colocando
una marca de tiempo de la pel�cula, un cuadro determinado que funcionar� como la imagen base del mosaico. 

En el men� "Preferencias" se pueden escoger los par�metros de personalizaci�n antes de generar las muestras y
generar el mosaico. Se pueden establecer las columnas y filas que se desea agregar, as� como las columnas y filas de 
sampleo, y el n�mero de muestras a generar, dependiendo del mosaico. Se recomienda que el numero de filas y columnas 
se mantenga en m�ltiplos de 2 y cuadrados, para mantener la relaci�n de aspecto de la imagen original.

La creaci�n de la base de datos se realiza almacenando las imagenes en un directorio IMAGEDB, y gestion�ndolo a trav�s de
un arreglo en el c�digo fuente.

Para definir cu�les son las im�genes que mejor se acoplan a cada bloque de la imagen original, donde cada bloque 
corresponde al espacio que ocupar� una imagen muestra, se utiliza la f�rmula de la distancia euclideana entre los valores
RGB de cada bloque y los valores RGB de la imagen muestra. A su vez, para analizar cada imagen de muestra, se realiza un 
c�lculo del promedio dividiendo cada imagen de muestra en features, a los que se les calcula un promedio y con este promedio
se realiza finalmente el c�lculo de la distancia.

Luego por cada bloque se va acoplando la mejor imagen candidata, y se le asigna un flag en la base de datos que indica que ya ha sido
utilizada para evitar repeticiones. 
