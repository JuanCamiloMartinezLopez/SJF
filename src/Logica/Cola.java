package Logica;

public class Cola {
	private Nodo raiz = new Nodo();
	private int id = 1;
	private int llegada = 0;

	public Cola() {
		raiz.rafaga = -1;
		raiz.id = -1;
		raiz.sig = raiz;
	}

	public void insertar(int ra) {
		Nodo nuevo = new Nodo();
		nuevo.rafaga = ra;
		nuevo.id = id;
		nuevo.tllegada = llegada;
		id++;
		llegada++;

		if (raiz.sig == raiz) {
			nuevo.tcomienzo = 0;
			nuevo.tfinal = nuevo.rafaga + nuevo.tcomienzo;
			nuevo.tretorno = nuevo.tfinal - nuevo.tllegada;
			nuevo.tespera = nuevo.tretorno - nuevo.rafaga;
			raiz.sig = nuevo;
			nuevo.sig = raiz;
		} else {
			Nodo aux = raiz;
			Nodo paux = raiz;
			while (aux.sig != raiz) {
				aux = aux.sig;
				paux = aux;
			}
			nuevo.tcomienzo = paux.tfinal;
			nuevo.tfinal = nuevo.rafaga + nuevo.tcomienzo;
			nuevo.tretorno = nuevo.tfinal - nuevo.tllegada;
			nuevo.tespera = nuevo.tretorno - nuevo.rafaga;
			paux.sig = nuevo;
			nuevo.sig = raiz;
		}
	}

	public boolean isEmpty() {
		if (this.raiz.sig == this.raiz) {
			return true;
		}
		return false;
	}

	public void mostrar() {
		Nodo aux = raiz;
		while (aux.sig != raiz) {
			aux = aux.sig;
			System.out.println(aux.id + " | " + aux.tllegada+ " | " + aux.rafaga+ " | " + aux.tcomienzo+ " | " + aux.tfinal+ " | " + aux.tretorno+ " | " + aux.tespera);
		}
	}

	public String dibujar() {
		String colaActual = ": |";
		Nodo aux = raiz;
		while (aux.sig != raiz) {
			aux = aux.sig;
			colaActual += aux.rafaga + " | ";
		}
		return colaActual;
	}

}
