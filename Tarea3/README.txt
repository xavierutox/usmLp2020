Javier Perez Riveros 201873517-5

Para compilar: makerun
Para ejecutar: - java -jar Mims.jar

Termine y envié la tarea antes de que se resolviera la duda que explicaba que la fuerza se descontaba a todos los personajes en vez de unicamente al que realizó la accion.
Por problemas de tiempo (control mate+ tarea fis+ tarea lp en mismo dia) no pude corregir este error :c


Al iniciar el programa se crearan 4 personajes (xav,mili,mario,peach) los cuales desde un inicio no tendran ni estudios ni trabajo.
En su estado inicial estos personajes solo podran estudiar, dormir o casarse.
Estudiar les dejara elegir entre los 4 trabajos y les permitirá trabajar y ganar dinero
El dinero se puede utilizar para comprar comida
La comida se puede utilizar para comer (y recuperar fuerza) o transferirla a los hijos de la familia

Solo es posible casarse si ambos adultos no estan casados previamente


Notas del programador: No supe como compilar java mostrando warnings, asi que me guie por los warnings que me daba vscode al escribir el codigo y el makefile que nos dieron
					   Se realizaron cambios en los archivos orginales:
					   		-> Habian multiples scanner lo cual hacia imposible cerrarlos todos, asi que se cambio a un unico scanner publico
							-> La carpeta contenedora de las clases se llamaba "Tarea3", pero las clases hacian referencia al paquete "tarea3", por lo que se cambio todo a "tarea3" includo el path señalado en el makefile
					   Se realizaron los siguientes cambios en los metodos indicados en el pdf:
					   		-> En algunos metodos se nos pedian parametros y retornos que no logre comprender su utilidad o que hacian imposible implementarlos, en dichos casos se cambio a "void" y se dejaron vacios los parametros (por ejemplo el metodo de añadir adultos)

					

	