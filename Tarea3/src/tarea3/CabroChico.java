package tarea3;
import java.util.Random;

public class CabroChico extends Personaje{
	//constructor de la clase cabrochico
    CabroChico(String nombre, String sexo, int dinero, int edad, int energia, int fuerza, int comida){
		this.setNombre(nombre);
		this.setSexo(sexo);
		this.setDinero(dinero);
		this.setEdad(edad);
		this.setEnergia(energia);
		this.setFuerza(fuerza);
		this.setComida(comida);
	}
	/*
	Metodo: comer
	Descripcion: Metodo intercambia 3 de comida por 1 de fuerza"
	*/
    public void comer(){
		if(this.getComida()<3){
			System.out.println(this.getNombre() + ": No me queda comida, deberia ir a comprar mas papitas al negocio de la esquina o pedirle comida a mis padres");
		}
		else{
			this.setComida(this.getComida()-3);
			this.setFuerza(this.getFuerza()+1);
			System.out.println(this.getNombre() + ": Gasté 3 de comida a cambio de 1 de fuerza");
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
			System.out.println(this.getNombre() + ": Dinero, que es eso? Deberia ir a jugar al parque a ver si encuentro algo");
		}
		else{
			this.setComida(this.getComida()+6*veces);
			this.setDinero(this.getDinero()-dinero);
			System.out.println(this.getNombre() + ": Obtuve "+(this.getComida()+6*veces)+" a cambio de "+dinero+" de dinero");
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
			Mims.chicos.remove(this);
			Mims.res2=6;
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
				if (vida<18){
					this.setEdad(vida);
					System.out.println(this.getNombre() + ": Ahora tengo "+vida+" años");
				}else{
					System.out.println(this.getNombre() + ": Ahora puedo luchar!");
					System.out.println(this.getNombre() + ": Se ha convertido en un adulto");
					Adulto nuevo = new Adulto(this.getNombre(),this.getSexo(), this.getDinero(), this.getEdad(), this.getEnergia(), this.getFuerza(), this.getComida());
					for (int i = 0; i < Mims.familias.size();i++) {
						for (int j = 0; j < Mims.familias.get(i).hijos.size(); j++) {
							if(Mims.familias.get(i).hijos.get(j).equals(this)){
								Mims.familias.get(i).hijos.remove(j);
								Mims.familias.get(i).hijos.add(nuevo);
							}
						}
					}
					Mims.adultos.add(nuevo);
					Mims.personajes.add(nuevo);
					Mims.personajes.remove(this);
					Mims.chicos.remove(this);
					Mims.res2=6;
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
	Metodo: jugar
	Descripcion: Metodo que consume de 2 a 10 de energia y posee una pequeña probabilidad de encontrar dinero o comida"
	*/
	public void jugar(){
		if (this.getFuerza()<2){
			System.out.println(this.getNombre() + ": No me siento bien :c");
			System.out.println(this.getNombre() + ": Murio");
			Mims.personajes.remove(this);
			Mims.chicos.remove(this);
			Mims.res2=6;
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
				if (vida<18){
					this.setEdad(vida);
					System.out.println(this.getNombre() + ": Ahora tengo "+vida+" años");
				}else{
					System.out.println(this.getNombre() + ": Ahora puedo luchar!");
					System.out.println(this.getNombre() + ": Se ha convertido en un adulto");
					Adulto nuevo = new Adulto(this.getNombre(),this.getSexo(), this.getDinero(), this.getEdad(), this.getEnergia(), this.getFuerza(), this.getComida());
					for (int i = 0; i < Mims.familias.size();i++) {
						for (int j = 0; j < Mims.familias.get(i).hijos.size(); j++) {
							if(Mims.familias.get(i).hijos.get(j).equals(this)){
								Mims.familias.get(i).hijos.remove(j);
								Mims.familias.get(i).hijos.add(nuevo);
							}
						}
					}
					Mims.adultos.add(nuevo);
					Mims.personajes.add(nuevo);
					Mims.personajes.remove(this);
					Mims.chicos.remove(this);
					Mims.res2=6;
				}
			}
		}
		Random rand = new Random();
		int energia = rand.nextInt(9) + 2;
		this.setEnergia(getEnergia()-energia);

		//probabilidades con randint 0-99 0,1,2,3,4,5,6,7,8,9 10/100 = 100% y 0,1,2,3,4 = 5/100 = 5%
		if (rand.nextInt(100) < 10){
			int dinero = rand.nextInt(2) + 2;
			this.setDinero(this.getDinero()+dinero);
			System.out.println(this.getNombre() + ": Encontre "+dinero+" de dinero!");
			System.out.println(this.getNombre() + ": Dinero actual: "+this.getDinero());
		}
		if (rand.nextInt(100) < 5){
			int alimento = rand.nextInt(2) + 1;
			this.setComida(this.getComida()+alimento);
			System.out.println(this.getNombre() + ": Encontre "+alimento+" de comida!");
			System.out.println(this.getNombre() + ": Comida actual: "+this.getComida());
		}
		System.out.println(this.getNombre() + ": Perdi "+energia+" de energia");
		System.out.println(this.getNombre() + ": Energia actual "+this.getEnergia());
		System.out.println(this.getNombre() + ": Eso fue muy divertido!");
		if(this.getEnergia()<0){
			this.setEnergia(0);
		}
	}
}