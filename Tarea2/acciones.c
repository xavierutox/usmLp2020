#include "acciones.h"
#include "escenario.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/*
Nombre: creardun
Retorno: dun*
Descripcion: iniciliza un nodo de tipo dun de la lista acciones
*/
dun* creardun(){
    dun* nodo;
    nodo = (dun*)malloc(sizeof(dun));
    nodo->funcion=NULL;
    nodo->identificador=0;
    nodo->next=NULL;    
    return nodo;
}

/*
Nombre: caminar_rush
Parametros: escenario* esc
Descripcion: Avanza una casilla, si hay un enemigo lo mata. El resto es igual que la funcion caminar_en
*/
void caminar_rush(escenario* esc){
    if (esc->posCurr>=esc->length-1){
        printf("Gracias alumnos de lp por ayudarme a recuperar la memoria, pero tu 100 esta en otra tarea.");
        return;
    }
    int nuevapos;
    int posactual= esc->posCurr;
    nuevapos=posactual+1;
    elemento* ele;
    ele=(elemento*)malloc(sizeof(elemento));
    memcpy(ele, get_elemento(esc,posactual),sizeof(elemento));

    if (get_tipo(esc,esc->posCurr+1)==5){
        if (*(int*)get_elemento(esc, esc->posCurr+1)->caracteristica1==1){
            printf("haz muerto\n");
            borrar_elelist(esc,posactual);
            int* aux;
            aux=(int*)malloc(sizeof(int));
            *aux=1;
            memcpy(ele->caracteristica2,aux,sizeof(int));
            anadir_elelist(esc, ele, 0, 1);
            esc->posCurr=0;
            esc->curr=esc->head;
            if (posactual!=0){
                anadir_elelist(esc, esc->guardado, posactual, esc->tipoguardado);
            }
            free(ele);
            free(aux);
            return;
        }
    }
    if (get_tipo(esc,esc->posCurr+1)==2){
        int estadosig;
        estadosig=*(int*)get_elemento(esc,esc->posCurr+1)->caracteristica2;
        int *newestado;
        newestado= (int*)malloc(sizeof(int));
        printf("%d",estadosig);
        if (estadosig==1){
            *newestado=1;
            memcpy(ele->caracteristica2,newestado,sizeof(int));
        }
        else if(estadosig==2){
            *newestado=3;
            memcpy(ele->caracteristica2,newestado,sizeof(int));
        }
        else if (estadosig==3){
            *newestado=4;
            memcpy(ele->caracteristica2,newestado,sizeof(int));
        }
        else if (estadosig==4){
            *newestado=2;
            memcpy(ele->caracteristica2,newestado,sizeof(int));
        }
        free(newestado);
    }
    if (posactual==0){
        borrar_elelist(esc,posactual);
    }else{
        if (esc->tipoguardado!=3 && esc->tipoguardado!=2){
            anadir_elelist(esc, esc->guardado, posactual, esc->tipoguardado);
        }else{
            borrar_elelist(esc, posactual);   
        }
    }
    memcpy(esc->guardado,get_elemento(esc,nuevapos),sizeof(elemento));
    esc->tipoguardado=get_tipo(esc,nuevapos);
    anadir_elelist(esc, ele, nuevapos, 1);
    esc->posCurr=nuevapos;
    
    if (esc->head==NULL){
        printf("Lista vacia\n");
    }else{
        casilla* puntero = esc->head;
        int posicion=0;
        while(posicion<nuevapos && puntero->next){
            puntero=puntero->next;
            posicion++;
        }
        if (posicion!=nuevapos){
            printf("\n");
        }else{
            esc->curr=puntero;
        }
    }
    free(ele);
}

/*
Nombre: caminar_saltando_positivo
Parametros: escenario* esc
Descripcion: Avanza una casilla, a diferencia de la funcion caminar_en, esta funcion es capaz de matar a los enemigos y de consumir bloques
*/
void caminar_saltando_positivo(escenario* esc, int i){
    int flagm=0;
    int flagb=0;
    int *newestado;
    if (esc->posCurr>=esc->length-i){
        printf("Gracias alumnos de lp por ayudarme a recuperar la memoria, pero tu 100 esta en otra tarea.");
        return;
    }
    int nuevapos;
    int* moneda3;
    int* normal;
    elemento* ele2;
    int posactual= esc->posCurr;
    nuevapos=posactual+i;
    elemento* ele;
    ele=(elemento*)malloc(sizeof(elemento));
    memcpy(ele, get_elemento(esc,posactual),sizeof(elemento));

    if (get_tipo(esc,esc->posCurr+i)==5){
        if (*(int*)get_elemento(esc, esc->posCurr+i)->caracteristica1==1){
            printf("haz muerto\n");
            borrar_elelist(esc,posactual);
            int* aux;
            aux=(int*)malloc(sizeof(int));
            *aux=1;
            memcpy(ele->caracteristica2,aux,sizeof(int));
            anadir_elelist(esc, ele, 0, 1);
            esc->posCurr=0;
            esc->curr=esc->head;
            if (posactual!=0){
                anadir_elelist(esc, esc->guardado, posactual, esc->tipoguardado);
            }
            free(aux);
            free(ele);
            return;
        }
    }
    if (get_tipo(esc,esc->posCurr+i)==2){
        int estadosig;
        estadosig=*(int*)get_elemento(esc,esc->posCurr+i)->caracteristica2;
        int *newestado;
        newestado= (int*)malloc(sizeof(int));
        printf("%d",estadosig);
        if (estadosig==1){
            *newestado=1;
            memcpy(ele->caracteristica2,newestado,sizeof(int));
        }
        else if(estadosig==2){
            *newestado=3;
            memcpy(ele->caracteristica2,newestado,sizeof(int));
        }
        else if (estadosig==3){
            *newestado=4;
            memcpy(ele->caracteristica2,newestado,sizeof(int));
        }
        else if (estadosig==4){
            *newestado=2;
            memcpy(ele->caracteristica2,newestado,sizeof(int));
        }
        free(newestado);
        
    }
    if (get_tipo(esc,esc->posCurr+i)==4){
        int tiposig;
        tiposig=*(int*)get_elemento(esc,esc->posCurr+i)->caracteristica2;
        if (tiposig==0){
            normal = (int*)malloc(sizeof(int));
            *normal=1;
            int moneda;
            moneda=*(int*)ele->caracteristica3;
            int moneda2;
            moneda3=(int*)malloc(sizeof(int));
            ele2=get_elemento(esc,esc->posCurr+i);
            moneda2=*(int*)ele2->caracteristica1;
            if(*(int*)ele->caracteristica2==4){
                moneda2=moneda2+moneda2;
                ele->caracteristica2=normal;
            }
            *moneda3=moneda2+moneda;
            ele->caracteristica3=moneda3;
            flagm=1;

        }else if(tiposig==1){
            flagb=1;
            elemento* ele2;
            int estadosig;
            ele2=get_elemento(esc,esc->posCurr+i)->caracteristica1;
            estadosig=*(int*)ele2->caracteristica2;
            
            newestado= (int*)malloc(sizeof(int));
            printf("%d",estadosig);
            if (estadosig==1){
            *newestado=1;
            memcpy(ele->caracteristica2,newestado,sizeof(int));
            }
            else if(estadosig==2){
                *newestado=3;
                memcpy(ele->caracteristica2,newestado,sizeof(int));
            }
            else if (estadosig==3){
                *newestado=4;
                memcpy(ele->caracteristica2,newestado,sizeof(int));
            }
            else if (estadosig==4){
                *newestado=2;
                memcpy(ele->caracteristica2,newestado,sizeof(int));
            }
        } 


        
        
        
    }
    if (posactual==0){
        borrar_elelist(esc,posactual);
    }else{
        if (esc->tipoguardado!=3 && esc->tipoguardado!=4 && esc->tipoguardado!=2){
            anadir_elelist(esc, esc->guardado, posactual, esc->tipoguardado);
        }else{
            borrar_elelist(esc,posactual);   
        }
    }
    memcpy(esc->guardado,get_elemento(esc,nuevapos),sizeof(elemento));
    esc->tipoguardado=get_tipo(esc,nuevapos);
    anadir_elelist(esc, ele, nuevapos, 1);
    esc->posCurr=nuevapos;
    

 
    
    if (esc->head==NULL){
        printf("Lista vacia\n");
    }else{
        casilla* puntero = esc->head;
        int posicion=0;
        while(posicion<nuevapos && puntero->next){
            puntero=puntero->next;
            posicion++;
        }
        if (posicion!=nuevapos){
            printf("\n");
        }else{
            esc->curr=puntero;
        }
    }
    free(ele);
    if(flagm==1){
        free(moneda3);
        free(normal);
    }
    if(flagb==1){
        free(newestado);
    }
}

/*
Nombre: caminar_saltando_negativo
Parametros: escneario *esc
Descripcion: igual que la funcion caminar_saltando_positivo, pero con la diferencia de que esta funcion permite retroceder una casilla
*/
void caminar_saltando_negativo(escenario* esc, int i){
    int flagm=0;
    int flagb=0;
    int* normal;
    int* moneda3;
    int *newestado;
    if (esc->length-i<0){
        printf("Largo ilegal");
    }
    int nuevapos;
    int posactual= esc->posCurr;
    nuevapos=posactual+i;
    elemento* ele;
    ele=(elemento*)malloc(sizeof(elemento));
    memcpy(ele, get_elemento(esc,posactual),sizeof(elemento));
    if (get_tipo(esc,nuevapos)==5){
        if (*(int*)get_elemento(esc, esc->posCurr+i)->caracteristica1==1){
            printf("haz muerto\n");
            borrar_elelist(esc,posactual);
            int* aux;
            aux=(int*)malloc(sizeof(int));
            *aux=1;
            memcpy(ele->caracteristica2,aux,sizeof(int));
            anadir_elelist(esc, ele, 0, 1);
            esc->posCurr=0;
            esc->curr=esc->head;
            if (posactual!=0){
                anadir_elelist(esc, esc->guardado, posactual, esc->tipoguardado);
            }
            free(aux);
            free(ele);
            return;
        }
    }
    
    if (get_tipo(esc,nuevapos)==2){
        int estadosig;
        estadosig=*(int*)get_elemento(esc,esc->posCurr+i)->caracteristica2;
        int *newestado;
        newestado= (int*)malloc(sizeof(int));
        printf("%d",estadosig);
        if (estadosig==1){
            *newestado=1;
            memcpy(ele->caracteristica2,newestado,sizeof(int));
        }
        else if(estadosig==2){
            *newestado=3;
            memcpy(ele->caracteristica2,newestado,sizeof(int));
        }
        else if (estadosig==3){
            *newestado=4;
            memcpy(ele->caracteristica2,newestado,sizeof(int));
        }
        else if (estadosig==4){
            *newestado=2;
            memcpy(ele->caracteristica2,newestado,sizeof(int));
        }
        free(newestado);
 
        
    }
    if (get_tipo(esc,nuevapos)==4){
        int tiposig;
        tiposig=*(int*)get_elemento(esc,esc->posCurr+i)->caracteristica2;
        if (tiposig==0){
            flagm=1;
            normal = (int*)malloc(sizeof(int));
            *normal=1;
            elemento* ele2;
            int moneda;
            moneda=*(int*)ele->caracteristica3;
            int moneda2;
            moneda3=(int*)malloc(sizeof(int));
            ele2=get_elemento(esc,esc->posCurr+i);
            moneda2=*(int*)ele2->caracteristica1;
            if(*(int*)ele->caracteristica2==4){
                moneda2=moneda2+moneda2;
                ele->caracteristica2=normal;
            }
            *moneda3=moneda2+moneda;
            ele->caracteristica3=moneda3;
        }else if(tiposig==1){
            flagb=1;
            elemento* ele2;
            int estadosig;
            ele2=get_elemento(esc,esc->posCurr+i)->caracteristica1;
            estadosig=*(int*)ele2->caracteristica2;
            
            newestado= (int*)malloc(sizeof(int));
            printf("%d",estadosig);
            if (estadosig==1){
                *newestado=1;
                ele->caracteristica2=newestado;
            }
            else if(estadosig==2){
                *newestado=3;
                ele->caracteristica2=newestado;
            }
            else if (estadosig==3){
                *newestado=4;
                ele->caracteristica2=newestado;
            }
            else if (estadosig==4){
                *newestado=2;
                ele->caracteristica2=newestado;
            }

        }   
        
    }
    
    if (posactual==0){
        borrar_elelist(esc,posactual);
    }else{
        if (esc->tipoguardado!=3 && esc->tipoguardado!=4 && esc->tipoguardado!=2){
            anadir_elelist(esc, esc->guardado, posactual, esc->tipoguardado);
        }else{
            borrar_elelist(esc,posactual);   
        }
    }
 
    memcpy(esc->guardado,get_elemento(esc,nuevapos),sizeof(elemento));
    esc->tipoguardado=get_tipo(esc,nuevapos);
    anadir_elelist(esc, ele, nuevapos, 1);
    esc->posCurr=nuevapos;
    

    
    if (esc->head==NULL){
        printf("Lista vacia\n");
    }else{
        casilla* puntero = esc->head;
        int posicion=0;
        while(posicion<nuevapos && puntero->next){
            puntero=puntero->next;
            posicion++;
        }
        if (posicion!=nuevapos){
            printf("\n");
        }else{
            esc->curr=puntero;
        }
    }
    free(ele);
        if(flagm==1){
        free(moneda3);
        free(normal);
    }
    if(flagb==1){
        free(newestado);
    }
}

/*
Nombre: caminar_en
Parametros: escenario* esc
Descripcion: avanza una casilla, se si encuentra con un agujero o un enemigo, el jugador muere
*/
void caminar_en(escenario* esc){
    if (esc->posCurr>=esc->length-1){
        printf("Gracias alumnos de lp por ayudarme a recuperar la memoria, pero tu 100 esta en otra tarea.");
        return;
    }
    int nuevapos;
    int posactual= esc->posCurr;
    nuevapos=posactual+1;
    elemento* ele;
    ele=(elemento*)malloc(sizeof(elemento));
    memcpy(ele, get_elemento(esc,posactual),sizeof(elemento));
    if (get_tipo(esc,esc->posCurr+1)==3){
        printf("haz muerto\n");
        borrar_elelist(esc, posactual);
        int* aux;
        aux=(int*)malloc(sizeof(int));
        *aux=1;
        memcpy(ele->caracteristica2,aux,sizeof(int));
        anadir_elelist(esc, ele, 0, 1);
        esc->posCurr=0;
        esc->curr=esc->head;
        if (posactual!=0){
            anadir_elelist(esc, esc->guardado, posactual, esc->tipoguardado);
        }
        free(aux);
        free(ele);
        return;
    }
    if (get_tipo(esc,esc->posCurr+1)==5){
        if (*(int*)get_elemento(esc, esc->posCurr+1)->caracteristica1==1){
            printf("haz muerto\n");
            borrar_elelist(esc, posactual);
            int* aux;
            aux=(int*)malloc(sizeof(int));
            *aux=1;
            memcpy(ele->caracteristica2,aux,sizeof(int));
            anadir_elelist(esc, ele, 0, 1);
            esc->posCurr=0;
            esc->curr=esc->head;
            if (posactual!=0){
                anadir_elelist(esc, esc->guardado, posactual, esc->tipoguardado);
            }
            free(aux);
            free(ele);
            return;
        }
    }
    if (get_tipo(esc,esc->posCurr+1)==2){
        int estadosig;
        estadosig=*(int*)get_elemento(esc,esc->posCurr+1)->caracteristica2;
        int *newestado;
        newestado= (int*)malloc(sizeof(int));
        printf("%d",estadosig);
        if (estadosig==1){
            *newestado=1;
            memcpy(ele->caracteristica2,newestado,sizeof(int));
        }
        else if(estadosig==2){
            *newestado=3;
            memcpy(ele->caracteristica2,newestado,sizeof(int));
        }
        else if (estadosig==3){
            *newestado=4;
            memcpy(ele->caracteristica2,newestado,sizeof(int));
        }
        else if (estadosig==4){
            *newestado=2;
            memcpy(ele->caracteristica2,newestado,sizeof(int));
        }
        
        free(newestado);
    }


    if (posactual==0){
        borrar_elelist(esc, posactual);
    }else{
        if ( esc->tipoguardado!=2){
            anadir_elelist(esc, esc->guardado, posactual, esc->tipoguardado);
        }else{
            borrar_elelist(esc, posactual);   
        }
    }

    memcpy(esc->guardado,get_elemento(esc,nuevapos),sizeof(elemento));
    esc->tipoguardado=get_tipo(esc,nuevapos);
    anadir_elelist(esc, ele, nuevapos, 1);
    esc->posCurr=nuevapos;


    
    if (esc->head==NULL){
        printf("Lista vacia\n");
    }else{
        casilla* puntero = esc->head;
        int posicion=0;
        while(posicion<nuevapos && puntero->next){
            puntero=puntero->next;
            posicion++;
        }
        if (posicion!=nuevapos){
            printf("\n");
        }else{
            esc->curr=puntero;
        }
    }
    free(ele);
}

/*
Nombre: rushear
Parametros: escenario* esc
Descripcion: funcion que llama 3 veces a la funcion caminar_rush. Su objetivo es avanzar 3 casillas, matando enemigos si hay uno
*/
void rushear(escenario* esc){
    int* aux;
    aux =(int*)malloc(sizeof(int));
    *aux=1;
    
    caminar_rush(esc);
    caminar_rush(esc);
    caminar_rush(esc);
    esc->curr->cosa.caracteristica2=aux;
    free(aux);
}

/*
Nombre: taclear_al_frente
Parametros: escenario* esc
Descripcion: avanza una casilla y mata a un enemigo, si hay un enemigo. De lo contrario se queda en la misma posicion y obtiene el estado mareado
*/
void taclear_al_frente(escenario* esc){
    
    int* aux;
    aux =(int*)malloc(sizeof(int));
    *aux=3;
    int nuevapos;
    int posactual= esc->posCurr;
    nuevapos=posactual+1;
    if(get_tipo(esc,nuevapos)==3){
        caminar_rush(esc);
    }else{
        memcpy(esc->curr->cosa.caracteristica2,aux,sizeof(int));
        
    }
    free(aux);
}

/*
Nombre: ver_plata
Parametros: escenario* ec
Descripcion: muestra el dinero del jugador
*/
void ver_plata(escenario* esc){
    printf("%d",*(int*)esc->curr->cosa.caracteristica3);
}

/*
Nombre: saltar_encima
Parametros: escenario* esc, int i
Descripcion: comrpueba si el numero ingresado es positivo y negativo, luego llama a la funcion que corresponda en cada caso.
*/
void saltar_encima(escenario* esc, int i){
    
    if (i>0){
        caminar_saltando_positivo(esc,i);
    }else if(i<0){
        caminar_saltando_negativo(esc,i);
    }
}   

/*
Nombre: choice
Parametros: int accion, acciones* listaAcc, escenario* esc
Descripcion: Recibe una accion, la busca en la lista y luego la llama mediante su puntero a funcion
*/
void choice(int accion, acciones* listaAcc, escenario* esc){
    dun* puntero = listaAcc->head;
    int posicion=0;
    int i;
    if (accion==1){ 
        while(posicion<listaAcc->length && puntero->next && puntero->identificador!=1){
            puntero=puntero->next;
            posicion++;
        }
        printf("Ingrese largo del salto: ");
        scanf("%d",&i);
        printf("HICE saltar_encima\n");
        (puntero->funcion)(esc,i);
    }
    else if (accion==2){
        while(posicion<listaAcc->length && puntero->next && puntero->identificador!=2){
            puntero=puntero->next;
            posicion++;
        }
        printf("HICE caminar_en\n");
        (puntero->funcion)(esc);
    }
    else if (accion==3){ 
        while(posicion<listaAcc->length && puntero->next && puntero->identificador!=3){
            puntero=puntero->next;
            posicion++;
        }
        printf("HICE taclear_al_frente\n");
        (puntero->funcion)(esc);
    }
    else if (accion==4){ 
        while(posicion<listaAcc->length && puntero->next && puntero->identificador!=4){
            puntero=puntero->next;
            posicion++;
        }
        printf("HICE rushear\n");
        (puntero->funcion)(esc);
    }
    else if (accion==5){ 
        while(posicion<listaAcc->length && puntero->next && puntero->identificador!=5){
            puntero=puntero->next;
            posicion++;
        }
        printf("HICE ver_plata\n");
        (puntero->funcion)(esc);
    }
    else{
        printf("OH OH!");
    }
}

/*
Nombre: nombrar
Parametros: escenario* esc, char* nombre
Descripcion: permite renombrar al jugador
*/
void nombrar(escenario* esc, char* nombre){
    esc->curr->cosa.caracteristica1=nombre;
}

/*
Nombre: acciones_disponibles
Parametros: acciones* listaAcc, escenario* esc
Descripcion: Muestra la lista de accione disponibles dependiendo del estado del jugador
*/
void acciones_disponibles(escenario* esc){
    int estado;
    estado= *(int*)esc->curr->cosa.caracteristica2;
    if (estado==1){ 
        printf("1. SALTAR\n");
        printf("2. CAMIMAR\n");
        printf("3. TACLEAR\n");
        printf("5. VER PLATA\n");
    }
    else if (estado==2){ 
        printf("1. SALTAR\n");
        printf("2. CAMIMAR\n");
        printf("3. TACLEAR\n");
        printf("4. RUSHEAR\n");
        printf("5. VER PLATA\n");
        
    }
    else if (estado==3){ 
        printf("2. CAMIMAR\n");
        printf("3. TACLEAR\n");
        printf("5. VER PLATA\n");
    }
    else if (estado==4){ 
        printf("1. SALTAR\n");
        printf("2. CAMIMAR\n");
        printf("3. TACLEAR\n");
        printf("5. VER PLATA\n");
        
    }
    else if (estado==5){
        printf("Gracias alumnos de lp por ayudarme a recuperar la memoria, pero tu 100 esta en otra tarea.");
    }
    else{
        printf("Estado desconocido: %d",estado);
    }
    
}

/*
Nombre: init_nodoaccion
Parametros: acciones* listaAcc, ini i
Descripcion: crea un nodo de tipo dun
*/
void init_nodoaccion(acciones* listaAcc, int i){
    
    dun * nodo = creardun();
    if (listaAcc->head==NULL){
        listaAcc->head=nodo;
    }else{
        dun* puntero = listaAcc->head;
        int posicion = 0;
        while(posicion<i && puntero->next){
            puntero = puntero->next;
            posicion++;
        }
        nodo->next=puntero->next;
        puntero->next=nodo;
    }
    listaAcc->length++;
}   

/*
Nombre: anadir_elementoacc
Parametros: acciones* listaAcc, int i, int d, void* fun
Descripcion: permite añadir nodos a la listaAcc
*/


void anadir_elementoacc(acciones* listaAcc, int i,int id, void* fun){
    if (listaAcc->head==NULL){
        printf("Lista vacia\n");
    }else{
        dun* puntero = listaAcc->head;
        int posicion=0;
        while(posicion<i && puntero->next){
            puntero=puntero->next;
            posicion++;
        }
        if (posicion!=i){
            printf("No existe la posicion\n");
        }else{
            puntero->identificador=id;
            puntero->funcion=fun;
        }
    }
}
/*
Nombre: iniciar_listaacc 
Parametros: acciones* listaAcc, escenario* esc
Descripcion: inicializa la lista, agregando las funciones a la lista
*/

void iniciar_listacc(acciones* listaAcc, escenario* esc){
    esc->curr=esc->head;
    while(listaAcc->length<6){
        init_nodoaccion(listaAcc, 0);
    }
    esc->guardado=(elemento*)malloc(sizeof(elemento));
    anadir_elementoacc(listaAcc,0,1, (void*) saltar_encima);
    anadir_elementoacc(listaAcc,1,2, (void*) caminar_en);
    anadir_elementoacc(listaAcc,2,3, (void*) taclear_al_frente);
    anadir_elementoacc(listaAcc,3,4, (void*) rushear);
    anadir_elementoacc(listaAcc,4,5, (void*) ver_plata);
}

/*
Nombre: borrar_accion
Parametros: acciones* listaAcc, int i
Descripcion: funcion auxilar para borrar_listaacciones, su mision es borrar un nodo y liberar su memoria
*/
void borrar_accion(acciones* listaAcc, int i){
    if (i==0){
        dun* eliminado = listaAcc->head;
        listaAcc->head=listaAcc->head->next;
        free(eliminado);
        listaAcc->length--;
    }else if (i< listaAcc->length){
        if(listaAcc->head){
            dun* puntero = listaAcc->head;
            int posicion = 0;
            while(posicion< (i-1)){
                puntero = puntero->next;
                posicion++;
            }
            dun* eliminado = puntero->next;
            puntero->next=eliminado->next;
            free(eliminado);
            listaAcc->length--;
        }
    }
}

/*
Nombre: borrar_listaacciones
Parametros: acciones* listaAcc
Descripcion: borra la lista de acciones
*/
void borrar_listacciones(acciones* listaAcc){
    while(listaAcc->length>0){
        borrar_accion(listaAcc, 0);
    }
}