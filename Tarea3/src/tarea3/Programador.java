package tarea3;
import java.util.Random;

public class Programador implements Trabajo {
	/*
	Metodo: Trabajar
	Parametros: Adulto, Int
	Descripcion: Hace elegir adulto entre sus 2 pegas posibles, y eso una cantidad de Horas.
				   Si es que el cansancio es menor a 0, se setea cansancio a 0 y se para el ciclo.
	*/
	public void trabajar(Adulto adulto, int horas){
        int res;

		while(horas-- > 0){
			System.out.println(adulto.getNombre() + ":  ¿En que trabajare esta hora?");
			System.out.println("1-Trabajar en wokdonnals");
			System.out.println("2-Programar");
			
			res = Mims.lectura.nextInt();
			if(res == 1){
				System.out.println(adulto.getNombre() + ": Voy a juntar dinero para sobrevivir y pagar el pcgammer");
				this.wokdonnals(adulto);
			}
			else{
				System.out.println(adulto.getNombre() + ": Voy a avanzar en mi projecto personal");
				this.programar(adulto);
			}

			if(adulto.getEnergia() < 0){
				adulto.setEnergia(0);
				System.out.println(adulto.getNombre() + ": SELECT estoyCansando FROM Adultos WHERE this='YO'; a')or 1=1 ;drop table Adultos \n espera que? ");
				break; 
			}
			if(horas == 0){
				System.out.println(adulto.getNombre() + ": this.nextDay()");
			}
        }

    }
    /*
	Metodo: wokdonnals
	Parametros: Adulto
	Descripcion: Por trabajar en el wokdonnals el adulto gana entre 0 a 70 monedas y 
				 pierde entre 15 a 35 de energia
	*/
    public void wokdonnals(Adulto adulto){
		Random rand = new Random();
		int dinero, energia;

		dinero = rand.nextInt(71) + 0;
		energia = rand.nextInt(21) + 15;

		System.out.println(adulto.getNombre() + ": Que rica me quedo esta hamburguesa!");
		System.out.println(adulto.getNombre() + ": Gane " + dinero + " aunque me canse " + energia);
		adulto.setDinero(adulto.getDinero() + dinero);
		adulto.setEnergia(adulto.getEnergia() - energia);
	}
	/*
	Metodo: Programar
	Parametros: Adulto
	Descripcion: Por Programar el adulto gana entre 10 a 25 monedas y 
				 pierde entre 3 a 14 de energia
	*/
	public void programar(Adulto adulto){
		Random rand = new Random();
		int dinero, energia;

		dinero = rand.nextInt(16) + 10;
		energia = rand.nextInt(12) + 3;

		System.out.println(adulto.getNombre() + ": Terminé el proyecto, hora de correrlo:\n SEGMENTATION FAULT\n Chale :c"); 
		System.out.println(adulto.getNombre() + ": Gane " + dinero + " aunque me canse " + energia);
		adulto.setDinero(adulto.getDinero() + dinero);
		adulto.setEnergia(adulto.getEnergia() - energia);

    }
}