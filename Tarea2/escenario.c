#include "escenario.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/*
Nombre: crearNodo
Retorno: casilla*
Descripcion: Crea un nodo de tipo casilla y lo retorna para su posterior uso
*/
casilla* crearNodo(){
    casilla* nodo = (casilla*)malloc(sizeof(casilla));
    nodo->next=NULL;
    return nodo;
}

/*
Nombre: printestadopersonaje
Parametros: int i
Descripcion: funcion auxilar para imprimir el nombre de un estado dada su id
*/
void printestadopersonaje(int i){
    if (i==1){
        printf("Estado: normal\n");
    }
    else if (i==2){
        printf("Estado: brillante\n");
    }
    else if (i==3){
        printf("Estado: mareado\n");
    }
    else if (i==4){
        printf("Estado: wario\n");
    }
}

/*
Nombre: printestadoobjeto
Parametros: int i
Descripcion: funcion auxilar para imprimir el nombre de un estado dado su id
*/
void printestadoobjeto(int i){
    if (i==1){
        printf("Estado: health\n");
    }
    else if (i==2){
        printf("Estado: mariado\n");
    }
    else if (i==3){
        printf("Estado: wario\n");
    }
    else if (i==4){
        printf("Estado: brillante\n");
    }
}

/*
Nombre: printestadosuelo
Parametros: int i
Descripcion: funcion auxilar para imprimir el nombre de un estado dada su id
*/
void printestadosuelo(int i){
    if (i==0){
        printf("Piso: firme\n");
    }
    else if (i==1){
        printf("Piso: agujero infinito\n");
    }
}

/*
Nombre: init_list
Parametros: escenario* esc, int i
Descripcion: inicializa la lista de casillas, asignando todo como piso firme, menos la primera casilla la cual esta ocupada por el jugador
*/
void init_list(escenario* esc, int i){
    int pos=0;
    esc->length=0;
    while(pos<i){
        elemento* ele;
        ele=(elemento*)malloc(sizeof(elemento));
        ele->caracteristica1=(int*)malloc(sizeof(int));
        int* estado;
        estado=(int*)malloc(sizeof(int));
        *estado=0;
        memcpy(ele->caracteristica1, estado, sizeof(int));
        anadir_elemento(esc, ele, 1, 5);
        pos++;
        free(ele->caracteristica1);
        free(estado);
        free(ele);
    }


}

/*
Nombre: mostrar
Parametros: esceneario* esc, int i
Descripcion: imprime los contenidos de una casilla en la posicion "i" de la lista
*/
void mostrar(escenario* esc, int i){
    printf("\n");
    if (esc->head==NULL){
        printf("Lista vacia\n");
    }else{
        casilla* puntero = esc->head;
        int posicion=0;
        while(posicion<i && puntero->next){
            puntero=puntero->next;
            posicion++;
        }
        if (posicion!=i){
            printf("No existe la posicion\n");
        }else{
            
            if(puntero->tipo==1){
                printf("Nombre: %s\n", (char*)puntero->cosa.caracteristica1);
                printestadopersonaje(*(int*)puntero->cosa.caracteristica2);
                printf("Monedas: %d\n", *(int*)puntero->cosa.caracteristica3);
            }
            else if(puntero->tipo==2){
                printf("Nombre: %s\n", (char*)puntero->cosa.caracteristica1);
                printestadoobjeto(*(int*)puntero->cosa.caracteristica2);
            }
            else if(puntero->tipo==3){
                printf("Nombre: %s\n", (char*)puntero->cosa.caracteristica1);
            }
            else if(puntero->tipo==4){
                
                printf("Tipo: %d\n", *(int*)puntero->cosa.caracteristica2);
                if (*(int*)puntero->cosa.caracteristica2==0){
                    printf("Monedas: %d\n", *(int*)puntero->cosa.caracteristica1);
                }else if (*(int*)puntero->cosa.caracteristica2==1){
                    void* p;
                    elemento* aux;
                    aux=(elemento*)malloc(sizeof(elemento));
                    p=aux;
                    aux = (elemento*)puntero->cosa.caracteristica1;
                    printf("Nombre: %s\n", (char*)aux->caracteristica1);
                    printestadoobjeto(*(int*)aux->caracteristica2);
                    free(p);
                }
            }
            else if(puntero->tipo==5){
                printestadosuelo(*(int*)puntero->cosa.caracteristica1);
            }
            else{
                printf("vacio\n");
            }
        }
    }
}

/*
Nombre: mostrar_todo
Parametros: esceneario* esc
Descripcion: imprime los contenidos de toda la lista
*/
void mostrar_todo(escenario* esc){
    int pos = 0;
    while (pos < esc->length){
        mostrar(esc, pos);
        pos++;
    }
}

/*
Nombre: anadir_elemento
Parametros: escenario* esc, elemento* ele, int i, int t
Descripcion: agrega nuevos nodos a la lista de casillas
*/
void anadir_elemento(escenario* esc, elemento* ele, int i, int t){
    casilla* nodo = crearNodo();
    if (i==0 && t!=1){
        printf("Posicion reservada para personaje. Abortando\n");
        return;
    }
    i=i-1;
    if (esc->head==NULL){
        esc->head=nodo;
    }else{
        casilla* puntero = esc->head;
        int posicion = 0;
        while(posicion<i && puntero->next){
            puntero = puntero->next;
            posicion++;
        }
        nodo->next=puntero->next;
        puntero->next=nodo;
    }
    esc->length++;
    nodo->tipo=t;
    nodo->cosa.caracteristica1=ele->caracteristica1;
    nodo->cosa.caracteristica2=ele->caracteristica2;
    nodo->cosa.caracteristica3=ele->caracteristica3;

}
/*
Nombre: anadir_elelist
Parametros: escenario* esc, elemento* ele, int i, int t
Descripcion: permite editar un nodo ya creado y cambiar su componente elemento. Se utiliza en la etapa de jugar, para de este modo convertir las casillas a piso firme cuando sea necesario
*/
void anadir_elelist(escenario* esc, elemento* ele, int i, int t){
    if (esc->head==NULL){
        printf("Lista vacia\n");
    }else{
        casilla* puntero = esc->head;
        int posicion=0;
        while(posicion<i && puntero->next){
            puntero=puntero->next;
            posicion++;
        }
        if (posicion!=i){
            printf("\n");
        }else{
            puntero->cosa=*ele;
            puntero->tipo=t;
        }
    }
}


/*
Nombre: borar_elemento
Parametros: escenario* esc, int i
Descripcion: borra el nodo en la posicion i de la lista esc
*/
void borrar_elemento(escenario* esc, int i){
    if (i==0){
        casilla* eliminado = esc->head;
        esc->head=esc->head->next;        
        free(eliminado);
        esc->length--;
    }else if (i< esc->length){
        if(esc->head){
            casilla* puntero = esc->head;
            int posicion = 0;
            while(posicion< (i-1)){
                puntero = puntero->next;
                posicion++;
            }
            casilla* eliminado = puntero->next;
            puntero->next=eliminado->next;
            free(eliminado);
            esc->length--;
        }
    }
}

/*
Nombre: borrar_elelist
Parametros: escneario* esc, elemento* ele, int i
Descripcion: edita el elemento de un nodo, convirtiendolo en un piso firme 
*/
void borrar_elelist(escenario* esc, int i){
    int * suelo;
    suelo=(int*)malloc(sizeof(int));
    *suelo=0;
    if (esc->head==NULL){
        printf("Lista vacia\n");
    }else{
        casilla* puntero = esc->head;
        int posicion=0;
        while(posicion<i && puntero->next){
            puntero=puntero->next;
            posicion++;
        }
        if (posicion!=i){
            printf("\n");
        }else{
            memcpy(puntero->cosa.caracteristica1,suelo,sizeof(int));
            puntero->cosa.caracteristica2=NULL,
            puntero->cosa.caracteristica3=NULL;
            puntero->tipo=5;
        }
    }
    free(suelo);
        
    
}

/*
Nombre: borrar_todo
Parametros: escenario* esc
Descripcion: borra toda la lista, liberando su memoria
*/
void borrar_todo(escenario* esc){
    while(esc->length>=0){
        borrar_elemento(esc, 0);
    }
}
/*
Nombre: get_elemento    
Parametros: escneario* esc, int p
Retorno: elemento*
Descripcion: retorna puntero a un elemento ubicado en una posicion p
*/
elemento* get_elemento(escenario* esc, int p){
    if (esc->head==NULL){
        return NULL;
    }else{
        casilla* puntero = esc->head;
        int posicion=0;
        while(posicion<p && puntero->next){
            puntero=puntero->next;
            posicion++;
        }
        if (posicion!=p){
            return NULL;
        }else{
            return &puntero->cosa;
        }
    }
}

/*
Nombre: get_tipo
Parametros: escenario* esc, int p
Retorno: int
Descripcion: retorna el tipo de un nodo en la posicion "p" de una lista
*/
int get_tipo(escenario* esc, int p){
    if (esc->head==NULL){
        return 0;
    }else{
        casilla* puntero = esc->head;
        int posicion=0;
        while(posicion<p && puntero->next){
            puntero=puntero->next;
            posicion++;
        }
        if (posicion!=p){
            return 0;
        }else{
            return puntero->tipo;
        }
    }
}

/*
Nombre: largo
Parametros: escenario* esc
Retorno: int
Descripcion: retorna el largo de una lista
*/
int largo(escenario* esc){
    return esc->length;
}