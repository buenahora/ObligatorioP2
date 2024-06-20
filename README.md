# ObligatorioP2
Franco Buenahora, Santiago Coll, Lorenzo Martínez

##IMPORTANTE 
Para que el programa funciones es necesario poner el CSV en la carpeta src. También verificar que no se haya abierto el csv con excel. Por alguna razon hacer esto cambia la estructura del CSV y pasa las separaciones pasa de ser "nombre","fecha" a ser "nombre,""fecha".

##Carga de los datos
Para cargar los datos, recorremos el archivo CSV y, por cada fila, creamos un objeto "canción". Este objeto se inserta en una estructura hash. Esta estructura hash contiene otro hash que a su vez contiene una lista. El primer hash utiliza la fecha como clave el segundo utiliza el código de país como clave y guarda una lista de canciones, la cual, debido a la estructura del CSV, ya viene ordenada por top diario.

Tiempo promedio de las consutas 
La consulta 1 se demora 0.3579 Milisegundos

La consulta 2 se demora 26 Milisegundos 

La consulta 3 se demora 1,3 segundos  (tomando la fecha inicial como la primera y la final como la ultima)

La consulta 4 se demora 3.7332 Milisegundos

La consulta 5 se demora 600 Milisegundos (tomando la fecha inicial como la primera y la final como la ultima)   


![image](https://github.com/buenahora/ObligatorioP2/assets/134079918/7e519b82-bce1-4c0f-8261-36487c6be2e1)

