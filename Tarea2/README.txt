Javier Alexander Perez Riveros
201873517-5

cosas previas:
    El programa fue creado utilizando la ultima version de C disponible y se comprobo su compatibilidad con 
    c11, por tanto no se asegura que el programa pueda funcionar en versiones anteriores de gcc. Se recomienda,
    en el caso de estar en ubuntu, actualizar previamente las librerias "sudo apt-update" y "sudo apt-get upgrade"
correr con:
    make run
Como usar:
    1) Indicar largo del nivel
    2) En esta etapa solo podras ver el mapa o borrar elementos. La funcion de anadir se encuentra bloqueada hasta que
    el usuario decida borrar algun nodo. Borrar todo creará un nuevo proceso, puedes salir de esto al ingresar varios comandos con "0"
    3) Una vez creado en nivel, debes ingresar 0 para pasar a la siguiente etapa. En esta etapa podras renombrar al personaje y jugar!
    4) Cuando hayas terminado, ingresa "0"
Concideraciones:
    - Si te encuentras en la posicion 0 y saltas a la izquieda, el programa morira. Se un buen usuario y no abuses de este bug :c
    - Se realizaron varias pruebas con valgrind para comprobar memoryleaks, el programa logro pasarlas todas, pero puede que como usuario,
    pruebes otros caminos, por lo que me disculpo de antemano en caso de que se me pasara un malloc.
    - Despues de todas las consultas, llegue a un punto donde no podia seguir perdiendo tiempo en reescribir mi codigo, por lo que decidi tomar las siguientes cocideraciones:
        -La primera casilla la ocupara el jugador y este se asignara apenas se inicie el programa
        -El resto de casillas estaran ocupadas por "piso firme", por lo que el usuario debera borrarlas y crearlas nuevamente.
        -Borrar todo, borra todo incluyendo al jugador, por lo que al usarlo el programa deja de funcionar, dado esto se uso system() para crear un nuevo proceso e iniciar desde 0
    - Por alguna razon mi funcion de saltar no borra los bloques y no me dio tiempo de corregir esto antes de la entrega.
    - La muerte no reinicia el mapa, solo al jugador y su estado (el pdf no habla de que hacer con las monedas)
    - Si estas en la posicion 0 y saltas -1 bloques, el programa morira por realizar un movimiento ilegal.
Como funciona el programa:
    - Primero te pedira el largo del nivel y se llenara la lista con: 1 personaje + n-1 casillas
    - Luego tendras que borrar las casillas 1 a n (notar que si borras la casilla 0, morira mario de forma definitiva y no podras jugar) 
    - Despues de eso podras añadir casillas en las posiciones 1 a nivel
    - El menu del programa lleva un contador de casillas, para bloquear la funciond de añadir/borrar si el largo no lo permite
    - Despues de eso, deberas presionar 0 para pasar a la etapa de juego
    - En la etapa de juego tendras la opcion de cambiar el nombre del personaje
    - Saltar: Permite saltar n casillas y caer n casillas mas adelante, el personaje ignorara las casillas entre su posicion original y la de llegada, interactuando solo con la de llegada
    - Caminar: Avanza 1 casilla, si mueres el personaje regresa al al inicio y obtiene el estado normal
    - Rushear: Avanza 3 casillas, notar que si el personaje muere, volvera al inicio y si quedan casillas por recorrer se seguira moviendo aprovechando los utimos movimientos del modo brillante
    - Taclear: Solo avanza si hay un enemigo, de lo contrario se queda donde esta y obtiene el estado mareado
    - Ver plata: Unicamente te muestra tus monedas
    - Si llegas al final, el programa te avisara y deberas manualmente terminarlo presionando "0" (de lo contrario no se liberará la memoria)