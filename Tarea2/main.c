#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "escenario.h"
#include "acciones.h"



int main(void){
    void* trashcan[10000];
    int trashpos=0;
    int state=6;
    int size;   
    int largolist; 
    escenario* esc;
    esc = (escenario*)malloc(sizeof(escenario));
    trashcan[trashpos]=esc;
    trashpos++;
    printf("*********************************\n");
    printf("*                               *\n");
    printf("*  Modo de creacion de niveles  *\n");
    printf("*                               *\n");
    printf("*********************************\n");
    printf("\n");
    printf("Tamano del nivel: ");
    scanf("%d", &size);
    if(size>1){
        elemento* ele;
        ele=(elemento*)malloc(sizeof(elemento));
        ele->caracteristica1=malloc(100*sizeof(char));
        ele->caracteristica2=malloc(100*sizeof(char));
        ele->caracteristica3=malloc(100*sizeof(char));
        trashcan[trashpos]=ele;
        trashpos++;
        trashcan[trashpos]=ele->caracteristica1;
        trashpos++;
        trashcan[trashpos]=ele->caracteristica2;
        trashpos++;
        trashcan[trashpos]=ele->caracteristica3;
        trashpos++;
        char** nombre;
        nombre=(char**)malloc(100*sizeof(char));
        nombre[0]="mario";
        int* monedas;
        monedas=(int*)malloc(sizeof(int));
        *monedas=0;
        int* estado;
        estado=(int*)malloc(sizeof(int));
        *estado=1;
        memcpy(ele->caracteristica1, nombre[0], 100*sizeof(char));
        memcpy(ele->caracteristica2, estado, 100*sizeof(char));
        memcpy(ele->caracteristica3, monedas, 100*sizeof(char));
        anadir_elemento(esc, ele, 0, 1);
        free(nombre);
        free(monedas);
        free(estado);
        init_list(esc,size);

    }
    while(state!=0){
        largolist = largo(esc);
        printf("\n");
        printf("0. SIGUIENTE ETAPA\n");
        printf("1. MOSTRAR\n");
        printf("2. MOSTRAR TODO\n");
        if(largolist<size){
            printf("3. ANADIR ELEMENTO\n");
        }
        if (largolist>0){
            printf("4. BORRAR ELEMENTO\n");
            printf("5. BORRAR TODO\n");
        }
        printf("\n");
        printf("Funcion: ");
        scanf("%d", &state);
        if (state==1){
            int p;
            printf("Posicion: ");
            scanf("%d", &p);
            mostrar(esc, p);
        }
        if (state==2){
            mostrar_todo(esc);
        }
        if (state==3){
            int p;
            int t;
            printf("tipo (2:Objeto, 3:Enemigo, 4:Bloque, 5:Suelo): ");
            scanf("%d", &t);
            if (t != 1){
                printf("Posicion: ");
                scanf("%d", &p);
            }
            while (t!=1 && p==0){
                printf("Posicion inicial reservada para el personaje, ingrese otra: ");
                scanf("%d", &p);
            }
            elemento* ele;
            ele=(elemento*)malloc(sizeof(elemento));
            ele->caracteristica1=malloc(100*sizeof(char)); 
            ele->caracteristica2=malloc(100*sizeof(char));
            ele->caracteristica3=malloc(100*sizeof(char));
            trashcan[trashpos]=ele;
            trashpos++;
            trashcan[trashpos]=ele->caracteristica1;
            trashpos++;
            trashcan[trashpos]=ele->caracteristica2;
            trashpos++;
            trashcan[trashpos]=ele->caracteristica3;
            trashpos++;
            if(t==2){
                char nombre[100];
                printf("Nombre: ");
                scanf("%s", nombre);
                int estado;
                printf("Estado (1:health, 2:mareado, 3:wario, 4: brillante): ");
                scanf("%d", &estado);
                memcpy(ele->caracteristica1, nombre, 100*sizeof(char));
                memcpy(ele->caracteristica2, &estado, 100*sizeof(char));
                anadir_elemento(esc, ele, p, t);
            }
            else if(t==3){
                char nombre[100];
                printf("Nombre: ");
                scanf("%s", nombre);
                memcpy(ele->caracteristica1, nombre, 100*sizeof(char));
                anadir_elemento(esc, ele, p, t);
            }
            else if(t==4){
                int contenido;
                printf("0: monedas, 1: objeto: ");
                scanf("%d", &contenido);
                if (contenido==0){
                    int monedas;
                    printf("Monedas: ");
                    scanf("%d", &monedas);
                    memcpy(ele->caracteristica1, &monedas, 100*sizeof(char));
                }else{
                    elemento* obj=(elemento*)malloc(sizeof(elemento));
                    obj->caracteristica1=(char*)malloc(100*sizeof(char));
                    obj->caracteristica2=(int*)malloc(sizeof(int));
                    ele->caracteristica1=(elemento*)malloc(sizeof(elemento));
                    trashcan[trashpos]=obj;
                    trashpos++;
                    trashcan[trashpos]=obj->caracteristica1;
                    trashpos++;
                    trashcan[trashpos]=obj->caracteristica2;
                    trashpos++;
                    trashcan[trashpos]=ele->caracteristica1;
                    trashpos++;
                    char nombre[100];
                    printf("Nombre: ");
                    scanf("%s", nombre);
                    int estado;
                    printf("Estado (1:health, 2:mareado, 3:wario, 4: brillante): ");
                    scanf("%d", &estado);
                    memcpy(obj->caracteristica1,nombre,100*sizeof(char));
                    memcpy(obj->caracteristica2,&estado,sizeof(int));
                    memcpy(ele->caracteristica1,obj,sizeof(elemento));

                }
                memcpy(ele->caracteristica2,&contenido,sizeof(int));
                anadir_elemento(esc, ele, p, t);
            }
            else if(t==5){
                int estado;
                printf("Piso: (0:piso firme, 1:agujero infinito): ");
                scanf("%d", &estado);
                memcpy(ele->caracteristica1, &estado, 100*sizeof(char));
                anadir_elemento(esc, ele, p, t);
            }
            
        }
        if (state==4){
            int p;
            printf("Posicion: ");
            scanf("%d", &p);
            borrar_elemento(esc,  p);
        }
        if (state==5){
            borrar_todo(esc);
            printf("Nooooo mataste a mario D:\n");
            printf("Volvamos a comenzar u.u\n");
            printf("\n");
            printf("\n");
            printf("\n");
            printf("\n");
            system("make play");
            printf("Hola de nuevo?\n");
            printf("Veo que regresaste, por lo que no tiene sentido seguir en este proceso\n");
            printf("Ingresa 0 para salir de este estado y en el siguiente estado vuelve a ingresar 0 para cerrar el programa\n");

        }
    }
    if (state==0){
        printf("\n");
        printf("*********************************\n");
        printf("*                               *\n");
        printf("*           Let's go!           *\n");
        printf("*                               *\n");
        printf("*********************************\n");
        printf("\n");
    }
    state=9;
    acciones* listaAcc;
    listaAcc=(acciones*)malloc(sizeof(acciones));
    trashcan[trashpos]=listaAcc;;
    trashpos++;
    iniciar_listacc(listaAcc,esc);
    while(state!=0){
        printf("\n");
        printf("0. EXIT\n");
        printf("1. NOMBRAR\n");
        printf("2. MOSTRAR\n");
        printf("3. MOSTRAR TODO\n");
        printf("4. ACCION\n");
        printf("\n");
        printf("Funcion: ");
        scanf("%d", &state);
        if (state==1){
            char nombre[100];
            printf("Nombre: ");
            scanf("%s", nombre);
            nombrar(esc, nombre);
        }
        if (state==2){
            int p;
            printf("Posicion: ");
            scanf("%d", &p);
            mostrar(esc, p);
        }
        if (state==3){
            mostrar_todo(esc);
        }
        if (state==4){
            int accion;
            acciones_disponibles(esc);
            printf("Accion: ");
            scanf("%d", &accion);
            choice(accion, listaAcc, esc);
        }
    }
    free(esc->guardado);
    borrar_listacciones(listaAcc);
    borrar_todo(esc);
    int x = 0;
    while(x<trashpos){
        free(trashcan[x]);
        x++;
    }
    return 0;
}