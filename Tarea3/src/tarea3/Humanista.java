package tarea3;
import java.util.Random;

public class Humanista implements Trabajo {
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
			System.out.println("1-Enseñar");
			System.out.println("2-Escribir");
			
			res = Mims.lectura.nextInt();
			if(res == 1){
				System.out.println(adulto.getNombre() + ": Voy a hacer una ayudantia de java!");
				this.ensenar(adulto);
			}
			else{
				System.out.println(adulto.getNombre() + ": Vamos a escribir un libro de java!");
				this.escribir(adulto);
			}

			if(adulto.getEnergia() < 0){
				adulto.setEnergia(0);
				System.out.println(adulto.getNombre() + ": Puff estoy muy cansado, no doy más. A veces me gustaria sumergirme en una de mis historias y nunca regresar");
				break;
			}
			if(horas == 0){
				System.out.println(adulto.getNombre() + ": Que es la vida? No es nada mas que la busqueda interminable de encontrar un proposito? Y que es un proposito mas que un constante recuerdo de que estamos a solo un paso del olvido? Y que hay de este pobre humanista? Sin propositos ni metas, destinado a un nado existencial en el rio de la vida hacia... un destino desconocido?");
			}
        }

	}
	/*

	Funcion: Ensenar

	Input: Adulto

	Funcionalidad: Por enseñar el adulto gana entre 9 a 13 monedas y 
				   pierde entre 6 a 9 de energia

	*/
    public void ensenar(Adulto adulto){
		Random rand = new Random();
		int dinero, energia;

		dinero = rand.nextInt(5) + 9;
		energia = rand.nextInt(4) + 6;

		System.out.println(adulto.getNombre() + ": Otro dia, otra ayudantia que termino.");
		System.out.println(adulto.getNombre() + ": Gane " + dinero + " aunque me canse " + energia);
		adulto.setDinero(adulto.getDinero() + dinero);
		adulto.setEnergia(adulto.getEnergia() - energia);
	}

	/*

	Funcion: Escribir

	Input: Adulto

	Funcionalidad: Por escribir el adulto gana entre 4 a 14 monedas y 
				   pierde entre 4 a 13 de energia

	*/

	public void escribir(Adulto adulto){
		Random rand = new Random();
		int dinero, energia;

		dinero = rand.nextInt(11) + 4;
		energia = rand.nextInt(10) + 4;

		System.out.println(adulto.getNombre() + ": Este libro que quedó super duper. Hora de publicarlo y rogar que alguien lo lea."); 
		System.out.println(adulto.getNombre() + ": Gane " + dinero + " aunque me canse " + energia);
		adulto.setDinero(adulto.getDinero() + dinero);
		adulto.setEnergia(adulto.getEnergia() - energia);

    }
    

}