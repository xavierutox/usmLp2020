#ifndef acciones_h
#define acciones_h

#include <stdio.h>
#include <string.h>
#include "escenario.h"

typedef struct dun{
    void (*funcion)();
    int identificador;
    struct dun* next;
}dun;
typedef struct acciones {
    dun* curr;
    dun* head;
    dun* tail;
    int length;
    int posCurr;
} acciones ;




void saltar_encima(escenario* esc, int i); 
void caminar_en(escenario* esc);
void rushear(escenario* esc);
void ver_plata(escenario* esc);
void choice(int accion, acciones* listaAcc, escenario* esc);
void nombrar(escenario* esc, char* nombre);
void acciones_disponibles(escenario* esc);
void iniciar_listacc(acciones* listaAcc, escenario* esc);
void borrar_listacciones(acciones* listaAcc);

#endif