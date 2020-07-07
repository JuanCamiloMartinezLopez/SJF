package Logica;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ColaProcesos cola1 = new ColaProcesos ();
		int tiempo = 0; 
		//int id, int ra, int tllegada
		cola1.insertar(1,8,0);
		cola1.insertar(2,4,1);
		cola1.insertar(3,9,2);
		cola1.insertar(4,5,3);
		cola1.insertar(5,2,4);
		
		cola1.mostrarConsola();
		
		cola1.atender();
		
		cola1.mostrarConsola();
		


	}

}
