package Logica;

import java.util.ArrayList;

public class ColaProcesos {
	private int procesos = 0;
	private Proceso raiz;
	private int rafagaTotal = 0;
	public ArrayList<String[]> Data = new ArrayList<String[]>();

	public ColaProcesos() {
		this.raiz= new Proceso();
		this.raiz.rafaga = -1;
		this.raiz.id = -1;
		this.raiz.sig = raiz;
	}

	public int getProcesos() {
		return procesos;
	}

	public void setProcesos(int procesos) {
		this.procesos = procesos;
	}

	public Proceso getRaiz() {
		return raiz;
	}

	public void setRaiz(Proceso raiz) {
		this.raiz = raiz;
	}

	public boolean isEmpty() {
		if (this.raiz.sig == this.raiz) {
			return true;
		}
		return false;
	}
	
	public int getrafagaTotal() {
		return rafagaTotal;
	}

	public void mostrarConsola() {
		Proceso aux = raiz;
		while (aux.sig != raiz) {
			aux = aux.sig;
			System.out.println(aux.id + " | " + aux.tllegada + " | " + aux.rafaga + " | " + aux.tcomienzo + " | "
					+ aux.tfinal + " | " + aux.tretorno + " | " + aux.tespera);
		}
	}

	boolean buscar(int id) {
		Proceso aux = raiz;
		boolean bandera = false;
		while (aux.sig != raiz) {
			aux = aux.sig;
			if (aux.id == id) {
				return true;
			}
			
		}
		return bandera;
	}

	public Proceso buscarp(Proceso actual) {
		Proceso padre = raiz;
		Proceso aux = raiz;
		while (aux.sig != actual) {
			aux = aux.sig;
			padre = aux;
		}
		return padre;
	}

	public void insertar(int id, int ra, int tllegada) {
		rafagaTotal += ra;
		Proceso nuevo = new Proceso();
		nuevo.rafaga = ra;
		nuevo.id = id;
		nuevo.tllegada = tllegada;
		nuevo.listo=true;
		if (raiz.sig == raiz) {
			nuevo.tcomienzo = 0;
			nuevo.tfinal = nuevo.rafaga + nuevo.tcomienzo;
			nuevo.tretorno = nuevo.tfinal - nuevo.tllegada;
			nuevo.tespera = nuevo.tretorno - nuevo.rafaga;
			raiz.sig = nuevo;
			nuevo.sig = raiz;
		} else {
			Proceso aux = raiz;
			Proceso paux = raiz;
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
	
	public void insertar(Proceso proceso) {
		rafagaTotal += proceso.rafaga;
		if (raiz.sig == raiz) {
			raiz.sig=proceso;
			proceso.sig=raiz;
		} else {
			Proceso aux = raiz;
			Proceso paux = raiz;
			while (aux.sig != raiz) {
				aux = aux.sig;
				paux = aux;
			}
			proceso.tcomienzo = paux.tfinal;
			proceso.tfinal = proceso.rafaga + proceso.tcomienzo;
			proceso.tretorno = proceso.tfinal - proceso.tllegada;
			proceso.tespera = proceso.tretorno - proceso.rafaga;
			proceso.listo=false;
			paux.sig = proceso;
			proceso.sig = raiz;
		}
		procesos++;
	}

	public Proceso atender() {
		Proceso at = this.raiz.sig;
		if (at == raiz) {
			return null;
		} else {
			
			this.raiz.sig=at.sig;
		}
		//System.out.println();
		//mostrarConsola();
		//System.out.println();
		procesos -= 1;
		rafagaTotal-=at.rafaga;
		return at;
	}

}
