package obligatorio;
import java.util.*;

public class Jugador {
    private String nombre;
    private String alias;
    private int edad;
    private int ganadas;

    public static ArrayList<Jugador> listaJugadores = new ArrayList<>();
   
    public Jugador(String nom,String ali, int ed, int ganadas){
        this.nombre = nom;
        this.alias = ali;
        this.edad = ed;
        this.ganadas= ganadas;
    }
    public String getNombre(){
        return nombre;
    }
    public String getAlias(){
        return alias;
    }
    public int getEdad(){
        return edad;
    }
    public int getGanadas(){
        return ganadas;
    }
    public void setGanadas(int ganadas){
        this.ganadas=ganadas;
    }
    
    public static void ordenarPorGanadas(){
        Collections.sort(listaJugadores, new Comparator<Jugador>(){
        @Override
        public int compare(Jugador j1, Jugador j2){
            return Integer.compare(j2.getGanadas(), j1.getGanadas());
        }
    });
    }
    
}
