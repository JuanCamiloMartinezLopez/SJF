package Logica;

import java.util.ArrayList;

public class Cola {
	private int procesos = 0;
	private Nodo raiz = new Nodo();
	private int id = 1;
	private int rafagaTotal = 0;
	private int numProcesos = 0;
	public ArrayList<String[]> Data = new ArrayList<String[]>();

	public Cola() {
		raiz.rafaga = -1;
		raiz.id = -1;
		raiz.sig = raiz;
	}
	

	public int getProcesos() {
		return procesos;
	}


	public void setProcesos(int procesos) {
		this.procesos = procesos;
	}


	public Nodo getRaiz() {
		return raiz;
	}


	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
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
			System.out.println(aux.id + " | " + aux.tllegada + " | " + aux.rafaga + " | " + aux.tcomienzo + " | "
					+ aux.tfinal + " | " + aux.tretorno + " | " + aux.tespera);
		}
	}

	public String[] Data() {

		Nodo aux = raiz;
		while (aux.sig != raiz) {
			aux = aux.sig;
		}
		String[] data = { String.valueOf(aux.id), String.valueOf(aux.tllegada), String.valueOf(aux.rafaga),
				String.valueOf(aux.tcomienzo), String.valueOf(aux.tfinal), String.valueOf(aux.tretorno),
				String.valueOf(aux.tespera) };
		return data;
	}

	public ArrayList<String[]> allData() {
		Nodo aux = raiz;
		while (aux.sig != raiz) {
			aux = aux.sig;
			String[] process = { String.valueOf(aux.id), String.valueOf(aux.tllegada), String.valueOf(aux.rafaga),
					String.valueOf(aux.tcomienzo), String.valueOf(aux.tfinal), String.valueOf(aux.tretorno),
					String.valueOf(aux.tespera) };
			Data.add(process);
		}

		return Data;
	}

	public int getrafagaTotal() {
		return rafagaTotal;
	}

	public int getnumProcesos() {
		return numProcesos;
	}
	void recorrer() {
		
	}

	void ordenar() {
		Nodo aux;
		while (true) {
			aux = raiz.sig;
			boolean ordenar = false;
			if (procesos <= 1) {
				break;
			}
			for (int i = 1; i < procesos; i++) {
				if (aux.rafaga > aux.sig.rafaga) {
					if (aux.sig.rafaga != -1) {
						Nodo padre = buscarp(aux);
						padre.sig = aux.sig;
						Nodo aux1 = aux.sig.sig;
						aux.sig = aux1;
						padre.sig.sig = aux;
						ordenar = true;
					}
				}
				aux = aux.sig;
				System.out.println(procesos);
			}
			System.out.println();
			System.out.println(ordenar);
			if (ordenar == false) {
				break;
			}
		}
	}

	Nodo buscarp(Nodo actual) {
		Nodo padre = raiz;
		Nodo aux = raiz;
		while (aux.sig != actual) {
			aux = aux.sig;
			padre = aux;
		}
		return padre;
	}

	public void insertar(int ra, int tllegada) {
		rafagaTotal += ra;
		numProcesos++;
		Nodo nuevo = new Nodo();
		nuevo.rafaga = ra;
		nuevo.id = id;
		nuevo.tllegada = tllegada;
		id++;

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
		procesos++;
	}

	public void atender() {
		Nodo at = raiz.sig;
		if (at == raiz) {
			System.out.println("No hay nadie en la cola");
		} else {
			if (at.rafaga <= 5) {
				raiz.sig = at.sig;
				at.sig = null;
				System.out.println("Cliente atendido");
			} else {
				Nodo aux = raiz;
				Nodo paux = raiz;
				while (aux.sig != raiz) {
					aux = aux.sig;
					paux = aux;
				}
				raiz.sig = at.sig;
				paux.sig = at;
				at.sig = raiz;
				at.rafaga -= 5;
			}
		}
		System.out.println();
		mostrar();
		System.out.println();
		procesos -= 1;
	}

}
