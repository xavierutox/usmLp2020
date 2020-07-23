package tarea3;
import java.util.*;
import java.util.Random;

public class Familia {
    public ArrayList<Personaje>hijos = new ArrayList<Personaje>();
    private Adulto adulto1;
    private Adulto adulto2;
    private String nombre;
    //construcor de la clase familia
    Familia(String nombre, Adulto adulto1,Adulto adulto2){
        this.setAdulto1(adulto1);
        this.setAdulto2(adulto2);
        this.setNombre(nombre);
    }
    //getters de la clase Familia
    public Adulto getAdulto1(){
		return this.adulto1;
	}
	public Adulto getAdulto2(){
		return this.adulto2;
    }
    public String getNombre(){
		return this.nombre;
    }
    //setters de la clase Familia
    public void setAdulto1(Adulto adulto1){
		this.adulto1 = adulto1;
	}

	public void setAdulto2(Adulto adulto2){
		this.adulto2 = adulto2;
	}
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    /*
    Parametros: String adulto, int cantidad
	Metodo: alimentarHijos
	Descripcion: Metodo que permite alimentar a todos los hijos con una cantidad "cantidad" de alimentos, los cuales se extraen de los alimentos del adulto "adulto"
	*/
    public void alimentarHijos(String adulto, int cantidad){
        for (int i = 0; i < Mims.personajes.size(); i++) {
            if (Mims.personajes.get(i).getNombre().equals(adulto)){
                Personaje per=Mims.personajes.get(i);
                if (per.getFuerza()==0){
                    System.out.println(per.getNombre() + ": No me siento bien :c");
                    System.out.println(per.getNombre() + ": Murio");
                    Mims.personajes.remove(per);
                    Mims.adultos.remove(per);
                    Mims.res2=7;
                    return;
                    
                }else{
                    per.setFuerza(per.getFuerza()-2);
                    System.out.println(per.getNombre() + ": Perdi 2 de fuerza");
                    System.out.println(per.getNombre()+": Ahora tengo "+per.getFuerza()+" de fuerza");
                    int acciones= per.getAcciones();
                    if (acciones<4){
                        per.setAcciones(acciones+1);
                    }else{
                        per.setAcciones(0);
                        int vida= per.getEdad();
                        vida++;
                        if (vida<80){
                            per.setEdad(vida);
                            System.out.println(per.getNombre() + ": Ahora tengo "+vida+" años");
                        }else{
                            System.out.println(per.getNombre() + ": No me siento bien :c");
                            System.out.println(per.getNombre() + ": Murio");
                            for (int k = 0; k < Mims.familias.size();k++) {
                                for (int j = 0; j < Mims.familias.get(i).hijos.size(); j++) {
                                    if(Mims.familias.get(k).hijos.get(j).equals(per)){
                                        Mims.familias.get(k).hijos.remove(j);
                                    }
                                }
                            }
                            Mims.personajes.remove(per);
                            Mims.adultos.remove(per);
                            Mims.res2=7;
                            return;
                        }
                    }
                }
                for (int x = hijos.size()-1; x >=0;x--) {
                    if(per.getComida()-cantidad>=0){
                        per.setComida(per.getComida()-cantidad);
                        hijos.get(x).setComida(hijos.get(x).getComida()+cantidad);
                        System.out.println(per.getNombre() + ": Gaste "+cantidad+" de comida para alimentar a "+hijos.get(x).getNombre());
                        System.out.println(per.getNombre() + ": Ahora me queda "+per.getComida()+" de comida");
                    }
                    else{
                        System.out.println(per.getNombre() + ": No tengo suficiente comida para alimentar a "+hijos.get(x).getNombre());
                    }

                }
            }
        }
    }
    /*
    Parametros: String nombre
	Metodo: hacerHijo
	Descripcion: Metodo que crea un nuevo hijo y lo añade al array de hijos
	*/
    public void hacerHijo(String nombre){
        if(this.getAdulto1().getFuerza()<2){
            System.out.println(this.getAdulto1().getNombre() + ": No me siento bien :c");
			System.out.println(this.getAdulto1().getNombre() + ": Murio");
            for (int i = 0; i < Mims.adultos.size(); i++) {
                if(Mims.adultos.get(i).getNombre().equals(adulto1.getNombre())){
                    Mims.personajes.remove(getAdulto1());
                    Mims.adultos.remove(i);
                }
            }
        }else{
            this.getAdulto1().setFuerza(this.getAdulto1().getFuerza()-2);
            System.out.println(this.getAdulto1().getNombre() + ": Perdi 2 de fuerza");
			System.out.println(this.getAdulto1().getNombre()+": Ahora tengo "+this.getAdulto1().getFuerza()+" de fuerza");
        }
        if(this.getAdulto2().getFuerza()<2){
            System.out.println(this.getAdulto2().getNombre() + ": No me siento bien :c");
			System.out.println(this.getAdulto2().getNombre() + ": Murio");
            for (int i = 0; i < Mims.adultos.size(); i++) {
                if(Mims.adultos.get(i).getNombre().equals(adulto2.getNombre())){
                    Mims.personajes.remove(getAdulto2());
                    Mims.adultos.remove(i);
                }
            }
        }else{
            this.getAdulto2().setFuerza(this.getAdulto2().getFuerza()-2);
            System.out.println(this.getAdulto2().getNombre() + ": Perdi 2 de fuerza");
			System.out.println(this.getAdulto2().getNombre()+": Ahora tengo "+this.getAdulto2().getFuerza()+" de fuerza");
        }

        if (this.getAdulto1().getAcciones()<4){
            this.getAdulto1().setAcciones(this.getAdulto1().getAcciones()+1);
        }else{
            this.getAdulto1().setAcciones(0);
            int vida= this.getAdulto1().getEdad();
            vida++;
            if (vida<80){
                this.getAdulto1().setEdad(vida);
                System.out.println(this.getAdulto1().getNombre() + ": Ahora tengo "+vida+" años");
            }else{
                System.out.println(this.getAdulto1().getNombre() + ": No me siento bien :c");
                System.out.println(this.getAdulto1().getNombre() + ": Murio");
                Mims.personajes.remove(getAdulto1());
                Mims.adultos.remove(getAdulto1());
            }
        }
        if (this.getAdulto2().getAcciones()<4){
            this.getAdulto2().setAcciones(this.getAdulto1().getAcciones()+1);
        }else{
            this.getAdulto2().setAcciones(0);
            int vida= this.getAdulto2().getEdad();
            vida++;
            if (vida<80){
                this.getAdulto2().setEdad(vida);
                System.out.println(this.getAdulto2().getNombre() + ": Ahora tengo "+vida+" años");
            }else{
                System.out.println(this.getAdulto2().getNombre() + ": No me siento bien :c");
                System.out.println(this.getAdulto2().getNombre() + ": Murio");
                Mims.personajes.remove(getAdulto2());
                Mims.adultos.remove(getAdulto2());
            }
        }
        Random rand = new Random();
        String sexo="";
        if (rand.nextInt(100) < 50){
            sexo="Hombre";
        }
        else{
            sexo="Mujer";
        }
        CabroChico hijo = new CabroChico(nombre, sexo, 0, 0, 100, 30, 0);
        hijos.add(hijo);
        Mims.personajes.add(hijo);
        Mims.chicos.add(hijo);  

        
    }
    /*
	Metodo: acostarHijo
	Descripcion: Metodo que pone a dormir  todos los niños
	*/
    public void acostarHijo(){
        if(this.getAdulto1().getFuerza()<2){
            System.out.println(this.getAdulto1().getNombre() + ": No me siento bien :c");
			System.out.println(this.getAdulto1().getNombre() + ": Murio");
            for (int i = 0; i < Mims.adultos.size(); i++) {
                if(Mims.adultos.get(i).getNombre().equals(adulto1.getNombre())){
                    Mims.personajes.remove(getAdulto1());
                    Mims.adultos.remove(i);
                }
            }
        }else{
            this.getAdulto1().setFuerza(this.getAdulto1().getFuerza()-2);
            System.out.println(this.getAdulto1().getNombre() + ": Perdi 2 de fuerza");
			System.out.println(this.getAdulto1().getNombre()+": Ahora tengo "+this.getAdulto1().getFuerza()+" de fuerza");
        }
        if(this.getAdulto2().getFuerza()<2){
            System.out.println(this.getAdulto2().getNombre() + ": No me siento bien :c");
			System.out.println(this.getAdulto2().getNombre() + ": Murio");
            for (int i = 0; i < Mims.adultos.size(); i++) {
                if(Mims.adultos.get(i).getNombre().equals(adulto2.getNombre())){
                    Mims.personajes.remove(getAdulto2());
                    Mims.adultos.remove(i);
                }
            }
        }else{
            this.getAdulto2().setFuerza(this.getAdulto2().getFuerza()-2);
            System.out.println(this.getAdulto2().getNombre() + ": Perdi 2 de fuerza");
			System.out.println(this.getAdulto2().getNombre()+": Ahora tengo "+this.getAdulto2().getFuerza()+" de fuerza");
        }

        for (int i = 0; i < hijos.size();i++) {
            hijos.get(i).dormir();

        }
    }

}