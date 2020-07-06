package Logica;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cola cola1 = new Cola ();
		int tiempo = 0; 
		//int id, int ra, int tllegada
		cola1.insertar(1,8,0);
		cola1.insertar(2,4,1);
		cola1.insertar(3,9,2);
		cola1.insertar(4,5,3);
		cola1.insertar(5,2,4);
		
		//cola1.ordenar();
		cola1.mostrar();
		
		Cola cola2 = new Cola();
		int cuenta = 0;
		
		while(true) {
			Proceso a = cola1.getRaiz().sig;
			while (a != cola1.getRaiz()) {
				System.out.println(a.rafaga);
				if (a.tllegada <= tiempo) {
					if (cola2.buscar(a.id) == false) {
						cola2.insertar(a.id, a.rafaga, a.tllegada);
						cuenta++;
						break;
					}
					
				}
				a = a.sig;
			}
			tiempo = tiempo+a.rafaga;
			System.out.println(tiempo + " tiempo");
			if (cuenta == cola1.getProcesos()) {
				break;
			}
		}

		System.out.println();
		cola2.mostrar();


	}

}
