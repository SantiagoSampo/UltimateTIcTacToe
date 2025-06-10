package obligatorio;
import java.util.*;

public class Sistema {
    private Tablero tablero;
    private boolean[][] tableroEnJuego = {
            {false, false, false},
            {false, false, false},
            {false, false, false}
        };
    private String[][] tablerosGanados={
        {" ", " ", " "},
        {" ", " ", " "},
        {" ", " ", " "}
    };
   
    private HashMap<String, Boolean> tablerosFinalizados;
    private String subtableroActual;
    private String jugada;
    private String jugadorActual= "X";
    private boolean TaTeTiGanado=false;
    private String jugador1Alias="";
    private String jugador2Alias="";
    private String Ganador="";
    
    public Sistema(){
        this.tablero = new Tablero();
        tablerosFinalizados= new HashMap<>(Map.of(
        "A1", false,
        "A2", false,
        "A3", false,
        "B1", false,
        "B2", false,
        "B3", false,
        "C1", false,
        "C2", false,
        "C3", false
        ));
        
    }
    
    public static void main(String[] args) {
        Sistema sistema= new Sistema();
        sistema.mostrarBienvenida();
    
}
    
    public void mostrarBienvenida() {
        String mensaje = "B I E N V E N I D O S";
        for (int i = 0; i < mensaje.length(); i++) {
            System.out.print(mensaje.charAt(i));
            pausa(300);
        }
       
        System.out.println("");
        menu();
        
    
    }

    
    
    public void pausa(int milisegundos) {
        long inicio = System.currentTimeMillis();
        while (System.currentTimeMillis() - inicio < milisegundos) {
        //Se ejecuta hasta que se llegue al tiempo, luego aparece la letra
        }
}
    
    
    public void menu(){
        System.out.println("");
        Scanner in= new Scanner(System.in);
        System.out.println("MENU:");
        System.out.println("a) Registrar Jugador");
        System.out.println("b) Jugar al Gran Tateti entre dos personas");
        System.out.println("c) Jugar al Gran Tateti vs la Computadora");
        System.out.println("d) Ranking");
        System.out.println("e) Salir");
        System.out.println(" ");
        System.out.print("Ingrese una opcion: ");
        String letra= in.nextLine();
        if(letra.toLowerCase().equals("a")){
            System.out.println("");
            System.out.println("Seleccionaste Registrar Jugador");
            System.out.println("");
            agregarJugador();
            
        }else if(letra.toLowerCase().equals("b")){
            if(Jugador.listaJugadores.size()<2){
                System.out.println("Se necesita por lo menos dos jugadores registrados para jugar este modo");
                System.out.println("");
                menu();
            }else{
            System.out.println("");
            System.out.println("Seleccionaste jugar Gran Tateti entre dos personas");
            System.out.println("");
            TatetiDosPersonas();
            }
            
        }else if(letra.toLowerCase().equals("c")){
            if(Jugador.listaJugadores.size()==0){
                System.out.println("Se necesita por lo menos un jugador registrado para jugar este modo");
                System.out.println("");
                menu();
            }else{
            System.out.println("");
            System.out.println("Seleccionaste jugar Gran Tateti vs la Computadora");
            System.out.println("");
            UnoCMaquina();
            }
        
        }else if(letra.toLowerCase().equals("d")){
            System.out.println("");
            System.out.println("Seleccionaste ver el Ranking");
            System.out.println("");
            Ranking();
        
        }else if(letra.toLowerCase().equals("e")){
            System.out.println("");
            System.out.println("GRACIAS POR JUGAR!");
            System.exit(0);
        
        }else{
            System.out.println("No existe la opcion " + letra+", por favor ingrese una opcion valida");
            System.out.println("");
            menu();
        }
       
    }
    
   
    public static boolean verificarJugador(String alias){
        Iterator<Jugador> it = Jugador.listaJugadores.iterator();
        boolean esta=false;
        while(it.hasNext()){
            Jugador jugador = it.next();
            if(alias.toUpperCase().equals(jugador.getAlias().toUpperCase())){
                esta=true;
            }
        }
        return esta;
    }
    
    public void agregarJugador(){
        Scanner in= new Scanner(System.in);
        System.out.print("Ingrese nombre: ");
        String nombre= in.nextLine();
        System.out.print("Ingrese edad: ");
        int edad= in.nextInt();
        while(edad<=0){
            System.out.print("Ingrese edad valida: ");
            edad=in.nextInt();
            
        }
        in.nextLine();
        System.out.print("Ingese alias: ");
        String alias=in.nextLine();
        boolean esta=verificarJugador(alias);
        while(esta){
            System.out.print("Alias ya utilizado, ingese otro alias: ");
            alias=in.nextLine();
            esta=verificarJugador(alias);
        }
        Jugador nuevoJugador=new Jugador(nombre,alias,edad,0);
        Jugador.listaJugadores.add(nuevoJugador);
        System.out.println("Jugador " + nombre + " dado de alta correctamente!");
        System.out.println("");
        menu();
    
           
    }
    
    
    public static boolean verificarAlias(String alias){
        boolean esta= false;
        for(Jugador jugador: Jugador.listaJugadores){
            if(alias.equalsIgnoreCase(jugador.getAlias())){
                esta = true;
            }
        }
        return esta;
    }
    
    public static String cambiarAlias(String alias){
        for(Jugador jugador: Jugador.listaJugadores){
            if(alias.equalsIgnoreCase(jugador.getAlias())){
                return jugador.getAlias();
            }
        }
        return "";
    }
    
   
    
    private void TatetiDosPersonas(){
        tablero.iniciarTablero();
        inicializarJuego();
        TaTeTiGanado=false;
        jugadorActual="X";
        boolean esta1= false;
        boolean esta2=false;
        boolean MagX=true;
        boolean MagO=true;
        
        Scanner in = new Scanner(System.in);
        System.out.print("Jugadores disponibles: ");
        for(int i=0;i<Jugador.listaJugadores.size();i++){
                Jugador jugador = Jugador.listaJugadores.get(i);
                if(i<Jugador.listaJugadores.size()-1){
                    System.out.print(jugador.getAlias() + ", ");
                }else{
                    System.out.println(jugador.getAlias());
                }
            }
        System.out.print("Ingrese alias del Primer Jugador: ");
        String alias1= in.nextLine();
        esta1 = verificarAlias(alias1);
        
        while(!esta1){
            System.out.print("Ingrese alias valido para Primer Jugador: ");
            alias1= in.nextLine();
            esta1=verificarJugador(alias1);
        }
        
        if(esta1){
            alias1=cambiarAlias(alias1);
            jugador1Alias=alias1;
        }
        
        System.out.println("Jugadores disponibles: ");
        for(int i=0;i<Jugador.listaJugadores.size();i++){
                Jugador jugador = Jugador.listaJugadores.get(i);
                if(i<Jugador.listaJugadores.size()-1){
                    System.out.print(jugador.getAlias() + ", ");
                }else{
                    System.out.println(jugador.getAlias());
                }
        }
        
        System.out.print("Ingrese alias del Segundo Jugador: ");
        String alias2= in.nextLine();
        esta2= verificarJugador(alias2);
            while(!esta2||alias1.equalsIgnoreCase(alias2)){
            if(!esta2){
                System.out.print("Este alias no existe. Ingrese un alias registrado: ");
            }else{
                System.out.print("Alias ya utilizado por el Primer Jugador, ingrese otro alias registrado: ");
            }
            alias2= in.nextLine();
            esta2=verificarJugador(alias2);
            }
        
            if(esta2){
            alias2=cambiarAlias(alias2);
            jugador2Alias=alias2;
            }

        while(!TaTeTiGanado){
            int turno = 1;
            String dev="";
        
            while(!dev.toUpperCase().equals("X")){
                if(turno==1){
                    System.out.print("Jugador " + alias1 + " ingrese la primer jugada(Rendirse(X) y Jugada Magica(M) no disponibles en primera ronda): ");
                    dev=in.nextLine();
                    boolean tieneComa=dev.contains(",");
            
                    while(!tieneComa){
                        System.out.print("Ingrese jugada valida, ejemplo: a1,b2 : ");
                        dev=in.nextLine();
                        tieneComa=dev.contains(",");
                    }
           
                    String devJ[]= dev.split(",");
                    subtableroActual =devJ[0];
                    jugada = devJ[1];
            
                    while (!"A1A2A3B1B2B3C1C2C3".contains(jugada.toUpperCase()) || !"A1A2A3B1B2B3C1C2C3".contains(subtableroActual.toUpperCase())) {                   
                        System.out.println("Jugada invalida.");
                        System.out.print("Ingrese posicion donde desea colocar ficha(" + alias1 + "): ");
                        dev=in.nextLine();
                        devJ= dev.split(",");
                        subtableroActual =devJ[0];
                        jugada = devJ[1];
                    }
            
                colocarFicha();
                turno++;
                jugadorActual = "X";
            
                }else{
                    while(true){
                        if(turno%2 != 0&&turno!=1){
                            System.out.println("Turno de " + alias1 + "(Jugador 1)");
                            System.out.print("Ingrese posicion donde desea colocar ficha: ");
                            jugada = in.nextLine();
                            jugadorActual = "X";
                            while(!"A1A2A3B1B2B3C1C2C3XM".contains(jugada.toUpperCase())){
                                System.out.println("Jugada invalida.");
                                System.out.print("Ingrese posicion donde desea colocar ficha(" + alias1 + "): ");
                                jugada = in.nextLine();
                            }
                            
                            if("A1A2A3B1B2B3C1C2C3".contains(jugada.toUpperCase())){
                                while(!verificarLugarDisponible(subtableroActual, jugada)||tablerosFinalizados.get(jugada.toUpperCase())||!"A1A2A3B1B2B3C1C2C3".contains(jugada.toUpperCase())){
                                    System.out.println("Jugada invalida.");
                                    System.out.print("Ingrese CASILLERO donde desea colocar ficha(" + alias1 + "): ");
                                    jugada = in.nextLine();
                                    while(!"A1A2A3B1B2B3C1C2C3".contains(jugada.toUpperCase())){
                                        System.out.println("Jugada invalida.");
                                        System.out.print("Ingrese CASILLERO donde desea colocar ficha(" + alias1 + "): ");
                                        jugada = in.nextLine();
                                    }
                                }
                                colocarFicha();
                            }
                            
                            else if(jugada.toUpperCase().equals("M")&&MagX){
                                System.out.println("JUGADA MAGICA ACTIVADA POR " + alias1);
                                System.out.print("Seleccione casillero: ");
                                jugada = in.nextLine();
                                jugadaMag();
                                MagX=false;
                                while(!"A1A2A3B1B2B3C1C2C3".contains(jugada.toUpperCase())){
                                    System.out.println("Jugada invalida.");
                                    System.out.print("Ingrese posicion valida: ");
                                    jugada= in.nextLine();
                                }
                                while(!verificarLugarDisponible(subtableroActual, jugada)||tablerosFinalizados.get(jugada.toUpperCase())){
                                    System.out.println("Jugada invalida.");
                                    System.out.print("Ingrese posicion donde desea colocar ficha(" + alias1 + "): ");
                                    jugada = in.nextLine();
                                }
                                colocarFicha();
                            }
                            
                            else if(jugada.toUpperCase().equals("M")&&!MagX){
                                System.out.println("JUGADA MAGICA YA USADA POR " + alias1);
                                System.out.print("Seleccione casillero: ");
                                jugada = in.nextLine();
                                while(!"A1A2A3B1B2B3C1C2C3X".contains(jugada.toUpperCase())){
                                     System.out.println("Jugada invalida.");
                                     System.out.print("Ingrese posicion donde desea colocar ficha(" + alias1 + "): ");
                                     jugada = in.nextLine();
                                }
                                if(jugada.toUpperCase().equals("X")){
                                    jugadorActual="O";
                                    TaTeTiGanado=true;
                                    Ganador();
                                }else{
                                   while(!"A1A2A3B1B2B3C1C2C3".contains(jugada.toUpperCase())||tablerosFinalizados.get(jugada.toUpperCase())||!verificarLugarDisponible(subtableroActual, jugada)){
                                            System.out.println("Jugada invalida.");
                                            System.out.print("Ingrese posicion donde desea colocar ficha(" + alias1 + "), X no disponible: ");
                                            jugada = in.nextLine();
                                        } 
                                    }
                                    colocarFicha();     
                            }     
                            
                            else if(jugada.toUpperCase().equals("X")){
                                    jugadorActual="O";
                                    TaTeTiGanado=true;
                                    Ganador();
                                }
                                turno++;
                            }
                            
                        
                        else{
                           System.out.println("Turno de " + alias2 + "(Jugador 2)");
                            System.out.print("Ingrese posicion donde desea colocar ficha: ");
                            jugada = in.nextLine();
                            jugadorActual = "O";
                            while(!"A1A2A3B1B2B3C1C2C3XM".contains(jugada.toUpperCase())){
                                System.out.println("Jugada invalida.");
                                System.out.print("Ingrese posicion donde desea colocar ficha(" + alias2 + "): ");
                                jugada = in.nextLine();
                            }
                            
                            if("A1A2A3B1B2B3C1C2C3".contains(jugada.toUpperCase())){
                                while(!verificarLugarDisponible(subtableroActual, jugada)||tablerosFinalizados.get(jugada.toUpperCase())||!"A1A2A3B1B2B3C1C2C3".contains(jugada.toUpperCase())){
                                    System.out.println("Jugada invalida.");
                                    System.out.print("Ingrese CASILLERO donde desea colocar ficha(" + alias2 + "): ");
                                    jugada = in.nextLine();
                                    while(!"A1A2A3B1B2B3C1C2C3".contains(jugada.toUpperCase())){
                                        System.out.println("Jugada invalida.");
                                        System.out.print("Ingrese CASILLERO donde desea colocar ficha(" + alias2 + "): ");
                                        jugada = in.nextLine();
                                    }
                                }
                                colocarFicha();
                            }
                            
                            else if(jugada.toUpperCase().equals("M")&&MagO){
                                System.out.println("JUGADA MAGICA ACTIVADA POR " + alias2);
                                System.out.print("Seleccione casillero: ");
                                jugada = in.nextLine();
                                jugadaMag();
                                MagO=false;
                                while(!"A1A2A3B1B2B3C1C2C3".contains(jugada.toUpperCase())){
                                    System.out.println("Jugada invalida");
                                    System.out.print("Ingrese posicion valida: ");
                                    jugada= in.nextLine();
                                }
                                while(!verificarLugarDisponible(subtableroActual, jugada)||tablerosFinalizados.get(jugada.toUpperCase())){
                                    System.out.println("Jugada invalida.");
                                    System.out.print("Ingrese posicion donde desea colocar ficha(" + alias2 + "): ");
                                    jugada = in.nextLine();
                                }
                                colocarFicha();
                            }
                            
                            else if(jugada.toUpperCase().equals("M")&&!MagO){
                                System.out.println("JUGADA MAGICA YA USADA POR " + alias2);
                                System.out.print("Seleccione casillero: ");
                                jugada = in.nextLine();
                                while(!"A1A2A3B1B2B3C1C2C3X".contains(jugada.toUpperCase())){
                                     System.out.println("Jugada invalida.");
                                     System.out.print("Ingrese posicion donde desea colocar ficha(" + alias2 + "): ");
                                     jugada = in.nextLine();
                                }
                                if(jugada.toUpperCase().equals("X")){
                                    jugadorActual="X";
                                    TaTeTiGanado=true;
                                    Ganador();
                                }else{
                                   while(!"A1A2A3B1B2B3C1C2C3".contains(jugada.toUpperCase())||tablerosFinalizados.get(jugada.toUpperCase())||!verificarLugarDisponible(subtableroActual, jugada)){
                                            System.out.println("Jugada invalida.");
                                            System.out.print("Ingrese posicion donde desea colocar ficha(" + alias2 + "), X no disponible: ");
                                            jugada = in.nextLine();
                                        } 
                                    }
                                    colocarFicha();     
                            }     
                            
                            else if(jugada.toUpperCase().equals("X")){
                                    jugadorActual="X";
                                    TaTeTiGanado=true;
                                    Ganador();
                                }
                                turno++;
                            }
                
                    }
                }
            }
       }
    }
  
    


    private void colocarFicha(){
        String[][] subtablero= tablero.obtenerSubtablero(subtableroActual.toUpperCase());
        String rojo = "\u001B[31m";
        String azul = "\u001B[34m";
        String reset = "\u001B[0m";
        for (int i = 0; i < tableroEnJuego.length; i++) {
            for (int j = 0; j < tableroEnJuego[i].length; j++) {
                tableroEnJuego[i][j] = false;
            }
        }
           
        if(jugada.toUpperCase().equals("A1")){
            if(jugadorActual.equals("X")){
                subtablero[0][0]= rojo + jugadorActual + reset;
                tableroEnJuego[0][0]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="O";
            }else{
                subtablero[0][0]= azul + jugadorActual + reset;
                tableroEnJuego[0][0]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="X";
               }
        
        }else if(jugada.toUpperCase().equals("A2")){
            if(jugadorActual.equals("X")){
                subtablero[0][2]= rojo + jugadorActual + reset;
                tableroEnJuego[0][1]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="O";
            }else{
                subtablero[0][2]= azul + jugadorActual + reset;
                tableroEnJuego[0][1]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="X";
            }
           
        }else if(jugada.toUpperCase().equals("A3")){
            if(jugadorActual.equals("X")){
                subtablero[0][4]= rojo + jugadorActual + reset;
                tableroEnJuego[0][2]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="O";
            }else{
                subtablero[0][4]= azul + jugadorActual + reset;
                tableroEnJuego[0][2]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="X";
            }
           
        }else if(jugada.toUpperCase().equals("B1")){
            if(jugadorActual.equals("X")){
                subtablero[2][0]= rojo + jugadorActual + reset;
                tableroEnJuego[1][0]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="O";
            }else{
                subtablero[2][0]= azul + jugadorActual + reset;
                tableroEnJuego[1][0]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="X";
            }
           
        }else if(jugada.toUpperCase().equals("B2")){
            if(jugadorActual.equals("X")){
                subtablero[2][2]= rojo + jugadorActual + reset;
                tableroEnJuego[1][1]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="O";
            }else{
                subtablero[2][2]= azul + jugadorActual + reset;
                tableroEnJuego[1][1]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="X";
               }
           
        }else if(jugada.toUpperCase().equals("B3")){
            if(jugadorActual.equals("X")){
                subtablero[2][4]= rojo + jugadorActual + reset;
                tableroEnJuego[1][2]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="O";
            }else{
                subtablero[2][4]= azul + jugadorActual + reset;
                tableroEnJuego[1][2]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="X";
            }
            
        }else if(jugada.toUpperCase().equals("C1")){
            if(jugadorActual.equals("X")){
                subtablero[4][0]= rojo + jugadorActual + reset;
                tableroEnJuego[2][0]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="O";
            }else{
                subtablero[4][0]= azul + jugadorActual + reset;
                tableroEnJuego[2][0]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="X";
            }
           
        }else if(jugada.toUpperCase().equals("C2")){
            if(jugadorActual.equals("X")){
                subtablero[4][2]= rojo + jugadorActual + reset;
                tableroEnJuego[2][1]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="O";
            }else{
                subtablero[4][2]= azul + jugadorActual + reset;
                tableroEnJuego[2][1]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="X";
            }
           
        }else if(jugada.toUpperCase().equals("C3")){
            if(jugadorActual.equals("X")){
                subtablero[4][4]= rojo + jugadorActual + reset;
                tableroEnJuego[2][2]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="O";   
            }else{
                subtablero[4][4]= azul + jugadorActual + reset;
                tableroEnJuego[2][2]= true;
                verificarGanador(subtablero);
                verificarEmpate(subtablero);
                tablero.imprimirTablero(tableroEnJuego);
                jugadorActual="X";
               }
           }   
        subtableroActual=jugada.toUpperCase();       
    }
       
    public boolean verificarGanador(String[][] subTablero){
        String celda1= limpiarANSI(subTablero[0][0]);
        String celda2= limpiarANSI(subTablero[2][2]);
        String celda3= limpiarANSI(subTablero[4][4]);
        String celda4= limpiarANSI(subTablero[0][4]);
        String celda5= limpiarANSI(subTablero[4][0]);
        for(int i=0;i<=4;i+=2){
            String celdaLimpia1 = limpiarANSI(subTablero[i][0]);
            String celdaLimpia2 = limpiarANSI(subTablero[i][2]);
            String celdaLimpia3 = limpiarANSI(subTablero[i][4]);
            if(!celdaLimpia1.equals(" ")&& celdaLimpia1.equals(celdaLimpia2)&&celdaLimpia2.equals(celdaLimpia3)){
                pintarTableros(subTablero[i][0]);
                tablerosFinalizados.put(tablero.obtenerNombreSubtablero(subTablero), true);
                int[] coords = tablero.obtenerCoordenadasSubTablero(tablero.obtenerNombreSubtablero(subTablero));
                tablerosGanados[coords[0]][coords[1]]=limpiarANSI(subTablero[i][0]);
                ganadorGranTateti();
                return true;
            }
        }
        for(int i=0;i<=4;i+=2){
            String celdaLimpia1 = limpiarANSI(subTablero[0][i]);
            String celdaLimpia2 = limpiarANSI(subTablero[2][i]);
            String celdaLimpia3 = limpiarANSI(subTablero[4][i]);
            if(!celdaLimpia1.equals(" ")&&celdaLimpia1.equals(celdaLimpia2)&&celdaLimpia2.equals(celdaLimpia3)){
                pintarTableros(subTablero[0][i]);
                tablerosFinalizados.put(tablero.obtenerNombreSubtablero(subTablero), true);
                int[] coords = tablero.obtenerCoordenadasSubTablero(tablero.obtenerNombreSubtablero(subTablero));   
                tablerosGanados[coords[0]][coords[1]]=limpiarANSI(subTablero[0][i]);
                ganadorGranTateti();
                return true;
            }
        }
           
        if(!celda1.equals(" ")&&celda1.equals(celda2)&&celda2.equals(celda3)){
            pintarTableros(subTablero[0][0]);
            tablerosFinalizados.put(tablero.obtenerNombreSubtablero(subTablero), true);
            int[] coords = tablero.obtenerCoordenadasSubTablero(tablero.obtenerNombreSubtablero(subTablero));
            tablerosGanados[coords[0]][coords[1]]=limpiarANSI(subTablero[0][0]);
            ganadorGranTateti();
            return true;
        }
        if(!celda4.equals(" ")&&celda4.equals(celda2)&&celda2.equals(celda5)){
            pintarTableros(subTablero[0][4]);
            tablerosFinalizados.put(tablero.obtenerNombreSubtablero(subTablero), true);
            int[] coords = tablero.obtenerCoordenadasSubTablero(tablero.obtenerNombreSubtablero(subTablero));
            tablerosGanados[coords[0]][coords[1]]=limpiarANSI(subTablero[0][4]);
            ganadorGranTateti();
            return true;
        }
        return false;       
    }
       
    public void pintarTableros(String ganador) {
        String[][] subtablero= tablero.obtenerSubtablero(subtableroActual.toUpperCase());
        String rojo = "\u001B[31m"; 
        String azul = "\u001B[34m"; 
        String reset = "\u001B[0m"; 
        String color = "";
        String ganadorSinColor=limpiarANSI(ganador);
        if (ganadorSinColor.equals("X")) {
            color = rojo; 
        }else if (ganadorSinColor.equals("O")) {
            color = azul;
        }
        for (int i = 0; i < subtablero.length; i++) {
            for (int j = 0; j < subtablero[0].length; j++) {
                subtablero[i][j] = color + limpiarANSI(subtablero[i][j]) + reset;
            }
        }
    }
      
       
    public static String limpiarANSI(String input) {
        return input.replaceAll("\u001B\\[[;\\d]*m", "");
    }
           
    private boolean verificarLugarDisponible(String subtableroActual, String jugada) {
        String[][] subtablero = tablero.obtenerSubtablero(subtableroActual.toUpperCase());
        int fila = 0;
        int col = 0;

        switch (jugada.toUpperCase()) {
            case "A1": fila = 0; col = 0; break;
            case "A2": fila = 0; col = 2; break;
            case "A3": fila = 0; col = 4; break;
            case "B1": fila = 2; col = 0; break;
            case "B2": fila = 2; col = 2; break;
            case "B3": fila = 2; col = 4; break;
            case "C1": fila = 4; col = 0; break;
            case "C2": fila = 4; col = 2; break;
            case "C3": fila = 4; col = 4; break;
            case "X": return true;
            case "M": return true;
            default: return false; 
        }
    return subtablero[fila][col].equals(" "); 
    }
 
    
    private void jugadaMag(){
        String[][] subtablero= tablero.obtenerSubtablero(subtableroActual.toUpperCase());
        for(int i=0;i<=4;i+=2){
            for(int j=0;j<=4;j+=2){
                subtablero[i][j]=" ";
            }
        }
    }
    
    private void verificarEmpate(String[][] subTablero){
        boolean estaLleno=true;
        for(int i=0;i<subTablero.length;i++){
            for(int j=0;j<subTablero[0].length;j++){
                if(subTablero[i][j].equals(" ")){
                    estaLleno=false;
                }
            }
        }
        if(!verificarGanador(subTablero)&&estaLleno){
            tablerosFinalizados.put(tablero.obtenerNombreSubtablero(subTablero), true);
        }
        
        
    }
    
    private String ganadorGranTateti(){
        boolean hayGanador=false;
        for(int i=0;i<3;i++){
            if(!tablerosGanados[i][0].equals(" ")&& tablerosGanados[i][0].equals(tablerosGanados[i][1])&&tablerosGanados[i][0].equals(tablerosGanados[i][2])){
                jugadorActual = tablerosGanados[i][0];
                hayGanador=true;
                break;
            }
        }
        for(int i=0;i<3;i++){
            if(!tablerosGanados[0][i].equals(" ")&&tablerosGanados[0][i].equals(tablerosGanados[1][i])&& tablerosGanados[0][i].equals(tablerosGanados[2][i])){
                jugadorActual = tablerosGanados[0][i];
                hayGanador=true;
                break;
            }
        }
       if(!tablerosGanados[0][0].equals(" ") && tablerosGanados[0][0].equals(tablerosGanados[1][1]) && tablerosGanados[0][0].equals(tablerosGanados[2][2])) {
            jugadorActual = tablerosGanados[0][0];
            hayGanador=true;
        }
       if (!tablerosGanados[0][2].equals(" ") && tablerosGanados[0][2].equals(tablerosGanados[1][1]) &&tablerosGanados[0][2].equals(tablerosGanados[2][0])) {
           jugadorActual = tablerosGanados[0][2];
           hayGanador=true;
        }
  
        if(hayGanador){
            TaTeTiGanado=true;
            Ganador();
            return jugadorActual;
        }else{
            TaTeTiGanado=false;
            GranTateTiEmpate();
            return "";
        }
        
    }
    
    private void Ganador(){
        boolean ganadorEncontrado=false;
        if(jugadorActual.equals("X")){
            Ganador=jugador1Alias;   
        }else{
            Ganador=jugador2Alias;
        }
        Iterator<Jugador> it = Jugador.listaJugadores.iterator();
        while(it.hasNext()){
            Jugador jugador = it.next();
            if(Ganador.toUpperCase().equals(jugador.getAlias().toUpperCase())){
                int nuevasGanadas = jugador.getGanadas() + 1;
                tablero.imprimirTablero(tableroEnJuego);
                jugador.setGanadas(nuevasGanadas);
                System.out.println("EL GANADOR ES " + Ganador);  
                ganadorEncontrado=true;
            }
        }
        if(!ganadorEncontrado){
            System.out.println("LA COMPUTADORA GANA");
        }
        menu();    
    }
    
    private void inicializarJuego(){
       for(int i=0;i<tablerosGanados.length;i++){
           for(int j=0;j<tablerosGanados[0].length;j++){
               tablerosGanados[i][j] = " ";
            }
        }
       
       for(int i=0;i<tableroEnJuego.length;i++){
            for(int j=0;j<tableroEnJuego[0].length;j++){
                tableroEnJuego[i][j]=false;
           }
       }
       
       for(String key : tablerosFinalizados.keySet()) {
            tablerosFinalizados.put(key, false);
        }
          
    }
    
    private void Ranking(){
        System.out.println("");
        System.out.println("RANKING:");
        if(Jugador.listaJugadores.size()==0){
            System.out.println("No hay jugadores para realizar el Ranking");
            System.out.println("");
        }else{
            Jugador.ordenarPorGanadas();
            for(int i=0;i<Jugador.listaJugadores.size();i++){
                Jugador jugador = Jugador.listaJugadores.get(i);
                System.out.print(jugador.getAlias()+ " | ");
                for(int j=0;j<jugador.getGanadas();j++){
                    System.out.print("#");
                }
                System.out.println("");
            }
        }
        menu();
    }
    
    private void UnoCMaquina(){
        tablero.iniciarTablero();
        inicializarJuego();
        TaTeTiGanado=false;
        jugadorActual="X";
        jugador2Alias="la Computadora";
        String alias2="la Computadora";
        boolean esta1 = false;
        Scanner in = new Scanner(System.in);
        
        System.out.print("Jugadores disponibles: ");
        for(int i=0;i<Jugador.listaJugadores.size();i++){
            Jugador jugador = Jugador.listaJugadores.get(i);
            if(i<Jugador.listaJugadores.size()-1){
                System.out.print(jugador.getAlias() + ", ");
            }else{
                System.out.println(jugador.getAlias());
            }
        }
        
        System.out.print("Ingrese alias del Jugador: ");
        String alias1= in.nextLine();
        esta1 = verificarAlias(alias1);
        while(!esta1){
            System.out.print("Ingrese alias valido para Primer Jugador: ");
            alias1= in.nextLine();
            esta1=verificarJugador(alias1);
        }
        if(esta1){
            alias1=cambiarAlias(alias1);
            jugador1Alias=alias1;
        }
        
       
        while(!TaTeTiGanado){
            int turno = 1;
            String dev="";
        
            while(!dev.toUpperCase().equals("X")){
                if(turno==1){
                    System.out.print("Jugador " + alias1 + " ingrese la primer jugada(Rendirse(X) no disponible en primera ronda): ");
                    dev=in.nextLine();
                    boolean tieneComa=dev.contains(",");
            
                while(!tieneComa){
                    System.out.print("Ingrese jugada valida, ejemplo: a1,b2 : ");
                    dev=in.nextLine();
                    tieneComa=dev.contains(",");
                }
           
                String devJ[]= dev.split(",");
                subtableroActual =devJ[0];
                jugada = devJ[1];
                while (!"A1A2A3B1B2B3C1C2C3".contains(jugada.toUpperCase()) || !"A1A2A3B1B2B3C1C2C3".contains(subtableroActual.toUpperCase())) {                   
                    System.out.println("Jugada invalida.");
                    System.out.print("Ingrese posicion donde desea colocar ficha(" + alias1 + "): ");
                    dev=in.nextLine();
                    devJ= dev.split(",");
                    subtableroActual =devJ[0];
                    jugada = devJ[1];    
                }
            
                colocarFicha();
                turno++;
                jugadorActual = "X";
                }else{
                    while(true){
                        if(turno%2 != 0&&turno!=1){
                            System.out.println("Turno de " + alias1 + "(Jugador 1)");
                            System.out.print("Ingrese posicion donde desea colocar ficha: ");
                            jugada = in.nextLine();
                            jugadorActual = "X";
                            while(!"A1A2A3B1B2B3C1C2C3X".contains(jugada.toUpperCase())){
                                System.out.println("Jugada invalida");
                                System.out.print("Ingrese posicion donde desea colocar ficha(" + alias1 + "): ");
                                jugada = in.nextLine();
                            }
                            if(jugada.toUpperCase().equals("X")){
                                jugadorActual="O";
                                TaTeTiGanado=true;
                                Ganador();
                            }else{
                            while(!verificarLugarDisponible(subtableroActual, jugada)){
                                System.out.println("Jugada invalida ");
                                System.out.print("Ingrese otra jugada: ");
                                jugada=in.nextLine();
                            }
                            if(jugada.toUpperCase().equals("X")){
                                jugadorActual="O";
                                TaTeTiGanado=true;
                                Ganador();
                            }else if(!tablerosFinalizados.get(jugada.toUpperCase())){
                                colocarFicha();    
                            }else{
                                while(tablerosFinalizados.get(jugada.toUpperCase())){
                                    System.out.println("Jugada invalida");
                                    System.out.print("Ingrese nueva jugada: ");
                                    jugada=in.nextLine();
                                    if(!tablerosFinalizados.get(jugada.toUpperCase())){
                                        colocarFicha();
                                    }
                                }
                            }
                        turno++;
                        }
                        }else{
                            System.out.println("");
                            System.out.println("Turno de " + alias2);
                            jugada = generarMovimientoValido();
                            jugadorActual = "O";
                            while(!verificarLugarDisponible(subtableroActual, jugada)){
                                jugada=generarMovimientoValido();
                            }if(!tablerosFinalizados.get(jugada.toUpperCase())){
                                colocarFicha();    
                            }else{
                                while(tablerosFinalizados.get(jugada.toUpperCase())){
                                    jugada=generarMovimientoValido();
                                    if(!tablerosFinalizados.get(jugada.toUpperCase())){
                                        colocarFicha();
                                    }
                                }
                            }
                            System.out.println("La computadora coloca su ficha en: " + jugada);
                            System.out.println("");
                            turno++;
                        }
                
                    }
                }
            }
       }
    }
    
    private String generarMovimientoValido(){
        String letras = "ABC";
        int indice = (int) (Math.random() * 3);
        String letra = letras.charAt(indice) + "";
        int numero = (int) (Math.random() * 3) + 1;
        return (letra + numero);
    }
    
    private void GranTateTiEmpate(){
        boolean todosFinalizados=true;
        for(Map.Entry<String, Boolean> subT : tablerosFinalizados.entrySet()){
            if(!subT.getValue()){
                todosFinalizados = false;
            }
        }
        if(todosFinalizados){
            System.out.println("HAY EMPATE.");
            menu();
        }
    }
}


        
        
        
    
    
    
    
    
    
    
    


    
    
    
    

    
    
    




    
 
           
           
       
       
       
       
       
       
       
       



    
     
 

