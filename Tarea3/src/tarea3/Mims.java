package tarea3;
import java.util.ArrayList;
import java.util.Scanner;	

public class Mims {
	public static final Scanner lectura = new Scanner(System.in);
	public static ArrayList<Personaje>personajes = new ArrayList<Personaje>();
	public static ArrayList<CabroChico>chicos = new ArrayList<CabroChico>();
	public static ArrayList<Adulto>adultos = new ArrayList<Adulto>();
	public static ArrayList<Familia>familias = new ArrayList<Familia>();	
	public static int res2;
	public static void main(String[] args) {
			
		//Los 4 personajes iniciales
		Adulto nuevo1 = new Adulto("xav", "hombre",0, 18, 100, 30, 0);
		nuevo1.estudios=0;
		personajes.add(nuevo1);
		adultos.add(nuevo1);
		Adulto nuevo2 = new Adulto("mili", "mujer",0, 18, 100, 30, 0);
		nuevo2.estudios=0;
		personajes.add(nuevo2);
		adultos.add(nuevo2);
		Adulto nuevo3 = new Adulto("mario", "hombre",0, 18, 100, 30, 0);
		nuevo3.estudios=0;
		personajes.add(nuevo3);
		adultos.add(nuevo3);
		Adulto nuevo4 = new Adulto("peach", "mujer",0, 18, 100, 30, 0);
		nuevo4.estudios=0;
		personajes.add(nuevo4);
		adultos.add(nuevo4);
		int res=6;
		res2=8;
		while(res!=5){
			System.out.println("1-Elegir personaje");
			System.out.println("2-Elegir familia");
			System.out.println("3-Agregar personaje");
			System.out.println("4-Casar personajes");
			System.out.println("5-Salir");
			int index=100;
			int indexper=100;
			res= lectura.nextInt();
			String per ="";
			String fam="";
			lectura.nextLine();	
			if (res==1) {
				try{
					for (int i = 0; i < personajes.size(); i++) {
						System.out.println(personajes.get(i).getNombre());
						
					}
					System.out.println("Ingrese nombre");
					per = lectura.nextLine();
					for (int i = 0; i < personajes.size(); i++) {
						if (personajes.get(i).getNombre().equals(per)){
							indexper = i;
						}
					}
					if (adultos.contains(personajes.get(indexper))){
						index=adultos.indexOf(personajes.get(indexper));
						while(res2!=7){
							System.out.println("1-Estudiar");
							System.out.println("2-Trabajar");
							System.out.println("3-Comer");
							System.out.println("4-Dormir");
							System.out.println("5-Comprar comida");
							System.out.println("6-Ver estado");
							System.out.println("7-Salir");
							res2= lectura.nextInt();
							lectura.nextLine();
							if(res2==1){
								if(adultos.get(index).getEnergia()<50){
									System.out.println("No tengo suficiente energia. Estoy chikito, deberia dormir");
								}else{
									if (adultos.get(index).getFuerza()>1){
										adultos.get(index).estudiar();
									}
									else{
										System.out.println(adultos.get(index).getNombre() + ": No me siento bien :c");
										System.out.println(adultos.get(index).getNombre() + ": Murio");
										for (int i = 0; i < familias.size();i++) {
											for (int j = 0; j < familias.get(i).hijos.size(); j++) {
												if(familias.get(i).hijos.get(j).getNombre().equals(adultos.get(index).getNombre())){
													familias.get(i).hijos.remove(j);
												}
											}
										}
										personajes.remove(indexper);
										adultos.remove(index);
										res2=7;
									}
								}	
							}
							else if(res2==2){
								if(adultos.get(index).estudios==1){
									System.out.println("Horas de trabajo");
									int horas= lectura.nextInt();
									lectura.nextLine();
									if(adultos.get(index).getEnergia()==0){
										System.out.println("Estoy muy cansado, deberia dormir");
									}else{
										adultos.get(index).trabajo.trabajar(adultos.get(index), horas);
									}
								}
								else{
									System.out.println("Primero deberia estudiar algo");
								}
							}
							else if(res2==3){
								adultos.get(index).comer();
							}
							else if(res2==4){
								if (adultos.get(index).getFuerza()>1){
									adultos.get(index).dormir();
								}
								else{
									System.out.println(adultos.get(index).getNombre() + ": No me siento bien :c");
									System.out.println(adultos.get(index).getNombre() + ": Murio");
									for (int i = 0; i < familias.size();i++) {
										for (int j = 0; j < familias.get(i).hijos.size(); j++) {
											if(familias.get(i).hijos.get(j).getNombre().equals(adultos.get(index).getNombre())){
												familias.get(i).hijos.remove(j);
											}
										}
									}
									personajes.remove(indexper);
									adultos.remove(index);
									res2=7;
								}
								
								
							}
							else if(res2==5){
								System.out.println("Dinero a intercambiar: multiplo de 4");
								int dinero= lectura.nextInt();
								lectura.nextLine();
								adultos.get(index).dameAlimento(dinero);
							}
							else if(res2==6){
								System.out.println("Estado de mario:");
								System.out.println("---Nombre: " + adultos.get(index).getNombre());
								System.out.println("---Sexo: " + adultos.get(index).getSexo());
								System.out.println("---Edad: " + adultos.get(index).getEdad());
								System.out.println("---Energia: " + adultos.get(index).getEnergia());
								System.out.println("---Fuerza: " + adultos.get(index).getFuerza());
								System.out.println("---Comida: " + adultos.get(index).getComida());
								System.out.println("---Dinero: " + adultos.get(index).getDinero());
							}
						}	
						
					}
					else{
						index=chicos.indexOf(personajes.get(indexper));
						while(res2!=6){
							System.out.println("1-Jugar");
							System.out.println("2-Comer");
							System.out.println("3-Dormir");
							System.out.println("4-Pedir comida");
							System.out.println("5-Ver estado");
							System.out.println("6-Salir");
							res2= lectura.nextInt();
							lectura.nextLine();
							if(res2==1){
								if(chicos.get(index).getEnergia()==0){
									System.out.println("Estoy muy cansado, deberia dormir");
								}else{
									if(chicos.get(index).getFuerza()>1){
										chicos.get(index).jugar();
									}
									else{
										System.out.println(chicos.get(index).getNombre() + ": No me siento bien :c");
										System.out.println(chicos.get(index).getNombre() + ": Murio");
										for (int i = 0; i < familias.size();i++) {
											for (int j = 0; j < familias.get(i).hijos.size(); j++) {
												if(familias.get(i).hijos.get(j).getNombre().equals(chicos.get(index).getNombre())){
													familias.get(i).hijos.remove(j);
												}
											}
										}
										personajes.remove(indexper);
										chicos.remove(index);
										res2=6;
									}
								}
							}
							else if(res2==2){
								chicos.get(index).comer();
							}
							else if(res2==3){

								if(chicos.get(index).getFuerza()>1){
									chicos.get(index).dormir();
								}
								else{
									System.out.println(chicos.get(index).getNombre() + ": No me siento bien :c");
									System.out.println(chicos.get(index).getNombre() + ": Murio");
									for (int i = 0; i < familias.size();i++) {
										for (int j = 0; j < familias.get(i).hijos.size(); j++) {
											if(familias.get(i).hijos.get(j).getNombre().equals(chicos.get(index).getNombre())){
												familias.get(i).hijos.remove(j);
											}
										}
									}
									personajes.remove(indexper);
									chicos.remove(index);
									
									res2=6;
								}
							}
								
							else if(res2==4){
								System.out.println("Dinero a intercambiar: multiplo de 4");
								int dinero= lectura.nextInt();
								lectura.nextLine();
								chicos.get(index).dameAlimento(dinero);
							}
							else if(res2==5){
								System.out.println("Estado de: "+ chicos.get(index).getNombre());
								System.out.println("---Nombre: " + chicos.get(index).getNombre());
								System.out.println("---Sexo: " + chicos.get(index).getSexo());
								System.out.println("---Edad: " + chicos.get(index).getEdad());
								System.out.println("---Energia: " + chicos.get(index).getEnergia());
								System.out.println("---Fuerza: " + chicos.get(index).getFuerza());
								System.out.println("---Comida: " + chicos.get(index).getComida());
								System.out.println("---Dinero: " + chicos.get(index).getDinero());
							}
						}	
					}		
				}catch (Exception e) {
					System.out.println("Personaje no encontrado.");
				}	
			}



			else if(res==2){
				index=100;
				try{
					for (int i = 0; i < familias.size(); i++) {
						System.out.println(familias.get(i).getNombre());
					}
					System.out.println("Ingrese nombre");
					fam = lectura.nextLine();
					for (int i = 0; i < familias.size(); i++) {
						if (familias.get(i).getNombre().equals(fam)){
							index = i;
						}
					}
					Familia famm = familias.get(index);
					System.out.println("1-Alimentar hijos");
					System.out.println("2-Hacer hijo");
					System.out.println("3-Acostar hijos");
					res2=lectura.nextInt();
					lectura.nextLine();
					if(res2==1){
						System.out.println("Nombre del padre que alimentara al hijo");
						String padre=lectura.nextLine();
						System.out.println("Cantidad de comida por hijo");
						int cantidad=lectura.nextInt();
						lectura.nextLine();
						famm.alimentarHijos(padre, cantidad);
					}
					else if(res2==2){
						System.out.println("Nombre del nuevo hijo");
						String nombre=lectura.nextLine();
						famm.hacerHijo(nombre);
					}
					else if(res2==3){
						famm.acostarHijo();
					}
				}catch (Exception e) {
					System.out.println("Familia no encontrada");
				}
			}
			else if(res==3){
				agregarAdulto();
			}
			else if(res==4){
				try{
					System.out.println("Nombre del primer adulto");
					String nAdulto1= lectura.nextLine();
					System.out.println("Nombre del segundo adulto");
					String nAdulto2= lectura.nextLine();
					casarse(nAdulto1, nAdulto2);
				}catch (Exception e) {
					System.out.println("Uno o mas personajes erroneos");
				}
			}
			res2=8;
		}
		lectura.close();
	}
	/*
	Metodo: agregarAdulto
	Descripcion: Metodo que crea un objeto adulto con todos sus valores pedidos por consola y lo añade a la lista personajes y adultos
	*/
	private static void agregarAdulto(){
		try{
			System.out.println("---Creacion de personaje");
			System.out.println("Ingrese nombre del personaje");
			String nom = lectura.nextLine();
			System.out.println("Ingrese sexo del personaje");
			String gen = lectura.nextLine();
			int din = 0;
			System.out.println("Ingrese edad del personaje");
			int edad =lectura.nextInt();
			lectura.nextLine();
			System.out.println("Ingrese energia del personaje");
			int ene =lectura.nextInt();
			lectura.nextLine();
			System.out.println("Ingrese fuerza del personaje");
			int fo =lectura.nextInt();
			lectura.nextLine();
			System.out.println("Ingrese comida del personaje");
			int food =lectura.nextInt();
			lectura.nextLine();
			Adulto nuevo = new Adulto(nom, gen,din, edad, ene, fo, food);
			nuevo.estudios=0;
			personajes.add(nuevo);
			adultos.add(nuevo);
		}catch (Exception e) {
			System.out.println("Ocurrio un problema al ingresar el adulto indicado");
			lectura.nextLine();
		}

		
		
	}
	/*
	Metodo: casarse
	Descripcion: Metodo que crea una nueva familia a partir de 2 adultos
	*/
	private static void casarse(String nAdulto1, String nAadulto2){
		int index = 100;
		for (int i = 0; i < adultos.size(); i++) {
			if (personajes.get(i).getNombre().equals(nAdulto1)){
				index = i;
			}
		}
		Adulto adulto1 = adultos.get(index);
		index=100;
		for (int x = 0; x < adultos.size(); x++) {
			if (personajes.get(x).getNombre().equals(nAadulto2)){
				index = x;
			}
		}
		Adulto adulto2 = adultos.get(index);
		for (int i = 0; i < familias.size(); i++) {
			for (int j = 0; j < familias.get(i).hijos.size(); j++) {
				if(familias.get(i).hijos.get(j).getNombre().equals(adulto1.getNombre())){
					familias.get(i).hijos.remove(j);
				}
				if(familias.get(i).hijos.get(j).getNombre().equals(adulto2.getNombre())){
					familias.get(i).hijos.remove(j);
				}
			}
		}
		if (adulto1.getCasado()==0 && adulto2.getCasado()==0){
			String nombre=adulto1.getNombre()+adulto2.getNombre();
			Familia nueva = new Familia(nombre, adulto1, adulto2);
			familias.add(nueva);
			adulto1.setCasado(1);
			adulto2.setCasado(1);
			System.out.println("Con el poder que me concede el reino de java: Los declaro adulto y adulto. Somos una sociedad inclusiva");
		}
		else{
			System.out.println("Uno de los adultos ya esta casado, se cancela la boda!");
		}
		
	}
}

