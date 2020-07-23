#ifndef escenario_h
#define escenario_h


#include <stdio.h>
#include <string.h>



typedef struct elemento {  
    void* caracteristica1;
    void* caracteristica2;
    void* caracteristica3;
} elemento;
typedef struct casilla { 
    elemento cosa;
    int tipo;
    struct casilla* next;
    struct casilla* anterior;
} casilla;
typedef struct escenario { 
    casilla* curr;
    casilla* head;
    casilla* tail;
    int length;
    int posCurr;
    elemento*  guardado;
    int tipoguardado;
} escenario;




void mostrar(escenario* esc, int i);
void mostrar_todo(escenario* esc);
void anadir_elemento(escenario* esc, elemento* ele, int i, int t);
void borrar_elemento(escenario* esc,  int i);
void borrar_todo(escenario* esc);
elemento* get_elemento(escenario* esc, int p);
int largo(escenario* esc);
void init_list(escenario* esc, int i);
void borrar_list(escenario* esc);
int get_tipo(escenario* esc, int p);
void borrar_elelist(escenario* esc, int i);
void anadir_elelist(escenario* esc, elemento* ele, int i, int t);
#endif