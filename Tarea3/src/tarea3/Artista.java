package tarea3;

import java.util.Random;

public class Artista implements Trabajo {

	/*

	Funcion: Trabajar

	Input: Adulto, Int

	Funcionalidad: Hace elegir adulto entre sus 2 pegas posibles, y eso una cantidad de Horas.
				   Si es que el cansancio es menor a 0, se setea cansancio a 0 y se para el ciclo.

	*/

	public void trabajar(Adulto adulto, int horas){
		int res;

		while(horas-- > 0){
			System.out.println(adulto.getNombre() + ":  ¿En que trabajare esta hora?");
			System.out.println("1-Pintar");
			System.out.println("2-Esculpir");
			
			res = Mims.lectura.nextInt();
			if(res == 1){
				System.out.println(adulto.getNombre() + ": Eres arte, te pintare como una de mis Mims francesas!");
				this.pintar(adulto);
			}
			else{
				System.out.println(adulto.getNombre() + ": Hora de hacerme un amigo!");
				this.esculpir(adulto);
			}

			if(adulto.getEnergia() < 0){
				adulto.setEnergia(0);
				System.out.println(adulto.getNombre() + ": Puff estoy muy cansado, no doy más. La vida de un artista es dura");
				break;
			}
			if(horas == 0){
				System.out.println(adulto.getNombre() + ": A veces me pregunto que será de mi dentro de unos años. Se me recordara por lo que hice o por lo que mis obras dicen de mi?");
			}
		}
	}

	/*

	Funcion: Investigar

	Input: Adulto

	Funcionalidad: Por pintar el adulto gana entre 4 a 8 monedas y 
				   pierde entre 3 a 7 de energia

	*/

	public void pintar(Adulto adulto){
		Random rand = new Random();
		int dinero, energia;

		dinero = rand.nextInt(5) + 4;
		energia = rand.nextInt(5) + 3;

		System.out.println(adulto.getNombre() + ": Oh si, quedate en esa posicion ooof muy bien. Ahora volteate oh si. Uhh eres arte");
		System.out.println(adulto.getNombre() + ": Gane " + dinero + " aunque me canse " + energia);
		adulto.setDinero(adulto.getDinero() + dinero);
		adulto.setEnergia(adulto.getEnergia() - energia);
	}

	/*

	Funcion: Experimentar

	Input: Adulto

	Funcionalidad: Por esculpir el adulto gana entre 0 a 25 monedas y 
				   pierde entre 6 a 30 de energia

	*/

	public void esculpir(Adulto adulto){
		Random rand = new Random();
		int dinero, energia;

		dinero = rand.nextInt(26) + 0;
		energia = rand.nextInt(25) + 6;

		System.out.println(adulto.getNombre() + ": *tip* *top* oh si! Creo que te llamare juanito"); 
		System.out.println(adulto.getNombre() + ": Gane " + dinero + " aunque me canse " + energia);
		adulto.setDinero(adulto.getDinero() + dinero);
		adulto.setEnergia(adulto.getEnergia() - energia);
	}





}