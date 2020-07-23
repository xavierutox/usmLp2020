TARGET = main
CFLAGS = -Wall -ggdb3

all:
	gcc -c escenario.c $(CFLAGS)
	gcc -c acciones.c $(CFLAGS)
	gcc -c main.c $(CFLAGS)
	gcc escenario.o acciones.o main.o -o $(TARGET) $(CFLAGS)
	make clear
run: all
	./$(TARGET)
play:
	./main
clear:
	find . -name '*.o' -type f -delete
