package tarea3;
import java.util.Random;

public class Adulto extends Personaje{

	public int estudios;
	public Trabajo trabajo;
	public int casado;
	//constructor de la clase adulto
	Adulto(String nombre, String sexo, int dinero, int edad, int energia, int fuerza, int comida){
		this.setNombre(nombre);
		this.setSexo(sexo);
		this.setDinero(dinero);
		this.setEdad(edad);
		this.setEnergia(energia);
		this.setFuerza(fuerza);
		this.setComida(comida);
		this.setCasado(0);
	}
	//set casado
	public void setCasado(int estado){
		this.casado=estado;
	}
	//get casado
	public int getCasado(){
		return this.casado;
	}
	/*
	Metodo: comer
	Descripcion: Metodo intercambia 6 de comida por 1 de fuerza"
	*/
	public void comer(){
		if(this.getComida()<6){
			System.out.println(this.getNombre() + ": No me queda comida, deberia ir a comprar arroz y fideos");
		}
		else{
			this.setComida(this.getComida()-6);
			this.setFuerza(this.getFuerza()+1);
			System.out.println(this.getNombre() + ": Gasté 6 de comida a cambio de 1 de fuerza");
			System.out.println(this.getNombre() + ": Comida actual :"+this.getComida()+" Fuerza actual: "+this.getFuerza());
		}
	}
	/*
	Parametros: int dinero
	Metodo: dameAlimento
	Descripcion: Metodo intercambia a razon de 4 de dinero por 6 de comida"
	*/
	public void dameAlimento(int dinero){
		int veces=dinero/4;
		if(this.getDinero()<dinero){
			System.out.println(this.getNombre() + ": No me queda dinero, deberia ir trabajar un poco");
		}
		else{
			this.setComida(this.getComida()+6*veces);
			this.setDinero(this.getDinero()-dinero);
			System.out.println(this.getNombre() + ": Obtuve "+(6*veces)+" a cambio de "+dinero+" de dinero");
			System.out.println(this.getNombre() + ": Comida actual :"+this.getComida()+" Dinero actual: "+this.getDinero());
		}
	}
	/*
	Metodo: dormir
	Descripcion: Metodo que recupera de 20 a 50 de energia"
	*/
	public void dormir(){
		if (this.getFuerza()<2){
			System.out.println(this.getNombre() + ": No me siento bien :c");
			System.out.println(this.getNombre() + ": Murio");
			Mims.personajes.remove(this);
			Mims.adultos.remove(this);
			Mims.res2=7;
			return;
			
		}else{
			this.setFuerza(this.getFuerza()-2);
			System.out.println(this.getNombre() + ": Perdi 2 de fuerza");
			System.out.println(this.getNombre()+": Ahora tengo "+this.getFuerza()+" de fuerza");
			int acciones= this.getAcciones();
			if (acciones<4){
				this.setAcciones(acciones+1);
			}else{
				this.setAcciones(0);
				int vida= this.getEdad();
				vida++;
				if (vida<80){
					this.setEdad(vida);
					System.out.println(this.getNombre() + ": Ahora tengo "+vida+" años");
				}else{
					System.out.println(this.getNombre() + ": No me siento bien :c");
					System.out.println(this.getNombre() + ": Murio");
					for (int i = 0; i < Mims.familias.size();i++) {
						for (int j = 0; j < Mims.familias.get(i).hijos.size(); j++) {
							if(Mims.familias.get(i).hijos.get(j).equals(this)){
								Mims.familias.get(i).hijos.remove(j);
							}
						}
					}
					Mims.personajes.remove(this);
					Mims.adultos.remove(this);
					Mims.res2=7;
					return;
				}
			}
		}
		System.out.println(this.getNombre() + ": a mimir!");
		Random rand = new Random();
		int energia = rand.nextInt(31) + 20;
		this.setEnergia(getEnergia()+energia);
		if(this.getEnergia()>100){
			this.setEnergia(100);
		}
		System.out.println(this.getNombre() + ": Fue una buena siesta, recupere "+energia+" de energia");
		System.out.println(this.getNombre()+": Ahora tengo "+this.getEnergia()+" de energia");
	}
	/*
	Metodo: estudiar
	Descripcion: Consume 50 de energia a cambio de conseguir un trabajo"
	*/
	public void estudiar(){
		if (this.getFuerza()<2){
			System.out.println(this.getNombre() + ": No me siento bien :c");
			System.out.println(this.getNombre() + ": Murio");
			Mims.personajes.remove(this);
			Mims.adultos.remove(this);
			Mims.res2=7;
			return;
			
		}else{
			this.setFuerza(this.getFuerza()-2);
			System.out.println(this.getNombre() + ": Perdi 2 de fuerza");
			System.out.println(this.getNombre()+": Ahora tengo "+this.getFuerza()+" de fuerza");
			int acciones= this.getAcciones();
			if (acciones<4){
				this.setAcciones(acciones+1);
			}else{
				this.setAcciones(0);
				int vida= this.getEdad();
				vida++;
				if (vida<80){
					this.setEdad(vida);
					System.out.println(this.getNombre() + ": Ahora tengo "+vida+" años");
				}else{
					System.out.println(this.getNombre() + ": No me siento bien :c");
					System.out.println(this.getNombre() + ": Murio");
					for (int i = 0; i < Mims.familias.size();i++) {
						for (int j = 0; j < Mims.familias.get(i).hijos.size(); j++) {
							if(Mims.familias.get(i).hijos.get(j).equals(this)){
								Mims.familias.get(i).hijos.remove(j);
							}
						}
					}
					Mims.personajes.remove(this);
					Mims.adultos.remove(this);
					Mims.res2=7;
					return;
				}
			}
		}
		estudios=1;
		System.out.println("Felicidades, te graduaste. \n Ahora es momento de que sufras de verdad");
		System.out.println("Seleccione un trabajo");
		System.out.println("1-Artista");
		System.out.println("2-Cientifico");
		System.out.println("3-Humanista");
		System.out.println("4-Programador");
		int respuesta = Mims.lectura.nextInt();
		Mims.lectura.nextLine();
		if (respuesta==1){
			Artista art = new Artista();
			this.trabajo=art;
			System.out.println("Felicidades, ahora eres un artista, maquina, campeon, crack, un grande");
		}else if(respuesta==2){
			Cientifico cien = new Cientifico();
			this.trabajo=cien;
			System.out.println("Felicidades, io que c no soy 100tifiko");
		}else if(respuesta==3){
			Humanista hum = new Humanista();
			this.trabajo=hum;
			System.out.println("Felicidades, ahora eres pobre pero un pobre feliz");
		}else if(respuesta==4){
			Programador pro = new Programador();
			System.out.println("Felicidades, ahora formatea windows y arregla computadores");
			this.trabajo=pro;
		}
		
	}
}