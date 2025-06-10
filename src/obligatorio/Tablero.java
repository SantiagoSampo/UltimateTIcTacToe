package obligatorio;
import java.util.*;

public class Tablero {
    private Object[][] tableroPrinc = new Object[3][3];
    public String[][] A1;
    public String[][] A2;
    public String[][] A3;
    public String[][] B1;
    public String[][] B2;
    public String[][] B3;
    public String[][] C1;
    public String[][] C2;
    public String[][] C3;


    public Tablero(){
        iniciarTablero();
    }
    
    
    public void iniciarTablero(){
        A1 = new String[][] {{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "}};
        A2 = new String[][] {{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "}};
        A3 = new String[][] {{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "}};
        B1 = new String[][] {{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "}};
        B2 = new String[][] {{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "}};
        B3 = new String[][] {{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "}};
        C1 = new String[][] {{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "}};
        C2 = new String[][] {{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "}};
        C3 = new String[][] {{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "},{"-","+","-","+","-"},{" ","|"," ","|"," "}};
        
        
        tableroPrinc[0][0]= A1;
        tableroPrinc[0][1]= A2;
        tableroPrinc[0][2]= A3;
        tableroPrinc[1][0]= B1;
        tableroPrinc[1][1]= B2;
        tableroPrinc[1][2]= B3;
        tableroPrinc[2][0]= C1;
        tableroPrinc[2][1]= C2;
        tableroPrinc[2][2]= C3;

        
     
    }
    
    public void imprimirTablero(boolean[][] tableroEnJuego) {
        String reset = "\u001B[0m";
        int filasPorSubtablero = 5;
        int columnasPorSubtablero = 5;

    // Primera fila
        for (int i = 0; i < 3; i++) {
            boolean enJuego = tableroEnJuego[0][i];
            String color = colorDeSubtablero(enJuego);
            System.out.print(color + "******" + reset); 
            if (i < 1) {  
                System.out.print(color + "*" + reset); 
            }
        }
        System.out.println(); 

    // Filas internas 
        for (int filaTablero = 0; filaTablero < 3; filaTablero++) {
            for (int filaSubtablero = 0; filaSubtablero < filasPorSubtablero; filaSubtablero++) {
                for (int colTablero = 0; colTablero < 3; colTablero++) {
                    String[][] subTableroActual = (String[][]) tableroPrinc[filaTablero][colTablero];

                    // Borde izquierdo del tablero en juego
                    if (colTablero == 0) {
                        boolean enJuego = tableroEnJuego[filaTablero][colTablero];
                        String color = colorDeSubtablero(enJuego);
                        System.out.print(color + "*" + reset); 
                    }

                    // Contenido del subtablero
                    for (int columnaSubtablero = 0; columnaSubtablero < columnasPorSubtablero; columnaSubtablero++) {
                        System.out.print(subTableroActual[filaSubtablero][columnaSubtablero]);
                    }

                    // Borde derecho entre subtableros
                    if (colTablero < 2) {
                        boolean enJuegoDerecho = tableroEnJuego[filaTablero][colTablero] || tableroEnJuego[filaTablero][colTablero + 1];
                        String color = colorDeSubtablero(enJuegoDerecho);
                        System.out.print(color + "*" + reset); 
                    } else {
                        boolean enJuegoDerecho = tableroEnJuego[filaTablero][colTablero];
                        String color = colorDeSubtablero(enJuegoDerecho);
                        System.out.print(color + "*" + reset); 
                    }
                }
            System.out.println(); 
        }

        // Fila de asteriscos entre los subtableros
            if (filaTablero < 2) {
                for (int i = 0; i < 3; i++) {
                    boolean enJuego = tableroEnJuego[filaTablero][i] || tableroEnJuego[filaTablero + 1][i];
                    String color = colorDeSubtablero(enJuego);
                    System.out.print(color + "******" + reset); 
                    if (i < 1) {  
                        System.out.print(color + "*" + reset); 
                    }
                }
                System.out.println(); 
            }
        }

        // Borde inferior)
        for (int i = 0; i < 3; i++) {
            boolean enJuego = tableroEnJuego[2][i];
            String color = colorDeSubtablero(enJuego);
            System.out.print(color + "******" + reset); 
            if (i < 1) {  
                System.out.print(color + "*" + reset); 
            }
        }
        System.out.println("");
    }

public String colorDeSubtablero(boolean enJuego){
    if (enJuego) {
        return "\u001B[43m"; 
    } else {
        return "\u001B[42m"; 
    }
}

public String[][] obtenerSubtablero(String nombreSubtablero) {
        switch (nombreSubtablero) {
            case "A1": return A1;
            case "A2": return A2;
            case "A3": return A3;
            case "B1": return B1;
            case "B2": return B2;
            case "B3": return B3;
            case "C1": return C1;
            case "C2": return C2;
            case "C3": return C3;
            default: return null;  
        }
    }
    

public String obtenerNombreSubtablero(String[][] subtablero) {
    
    if (subtablero == A1) {
        return "A1";
    } else if (subtablero == A2) {
        return "A2";
    } else if (subtablero == A3) {
        return "A3";
    } else if (subtablero == B1) {
        return "B1";
    } else if (subtablero == B2) {
        return "B2";
    } else if (subtablero == B3) {
        return "B3";
    } else if (subtablero == C1) {
        return "C1";
    } else if (subtablero == C2) {
        return "C2";
    } else if (subtablero == C3) {
        return "C3";
    } else {
        return null; 
    }
}

public int[] obtenerCoordenadasSubTablero(String nombre){
    if (nombre.equals("A1")) {
        return new  int[]{0,0};
    } else if (nombre.equals("A2")) {
        return new  int[]{0,1};
    } else if (nombre.equals("A3")) {
        return new  int[]{0,2};
    } else if (nombre.equals("B1")) {
        return new  int[]{1,0};
    } else if (nombre.equals("B2")) {
        return new  int[]{1,1};
    } else if (nombre.equals("B3")){
        return new  int[]{1,2};
    } else if (nombre.equals("C1")){
        return new  int[]{2,0};
    } else if (nombre.equals("C2")){
        return new  int[]{2,1};
    } else if (nombre.equals("C3")){
        return new  int[]{2,2};
    } else {
        return null; 
    }
}
}


    
    
    
    
    
    
    
    
    
    
    
