package Logica;


public class Proceso {
    int rafaga;
    int tllegada;
    int tcomienzo;
    int tfinal;
    int tretorno;
    int tespera;
    int id;
    Proceso sig;
    boolean listo;
    int tbloqueo;
    int tesperaRetorno;
    
    public Proceso(){
    	sig = null;
    }
}
