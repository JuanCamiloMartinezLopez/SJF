package Logica;

import java.util.ArrayList;

public class Cola {
	private int procesos = 0;
	private Proceso raiz = new Proceso();
	private int rafagaTotal = 0;
	private int numProcesos = 0;
	public ArrayList<String[]> Data = new ArrayList<String[]>();
	private int tiempo = 0;

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

	public void mostrar() {
		Proceso aux = raiz;
		while (aux.sig != raiz) {
			aux = aux.sig;
			System.out.println(aux.id + " | " + aux.tllegada + " | " + aux.rafaga + " | " + aux.tcomienzo + " | "
					+ aux.tfinal + " | " + aux.tretorno + " | " + aux.tespera);
		}
	}

	public String[] Data() {

		Proceso aux = raiz;
		while (aux.sig != raiz) {
			aux = aux.sig;
		}
		String[] data = { String.valueOf(aux.id), String.valueOf(aux.tllegada), String.valueOf(aux.rafaga),
				String.valueOf(aux.tcomienzo), String.valueOf(aux.tfinal), String.valueOf(aux.tretorno),
				String.valueOf(aux.tespera) };
		return data;
	}

	public ArrayList<String[]> allData() {
		Proceso aux = raiz;
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

	public void ordenar() {
		Proceso aux;
		Proceso menor;
		while (true) {
			aux = raiz.sig;
			boolean ordenar = false;
			if (procesos <= 1) {
				break;
			}
			for (int i = 1; i < procesos; i++) {
				if (aux.rafaga > aux.sig.rafaga) {
					if (aux.sig.rafaga != -1) {
						Proceso padre = buscarp(aux);
						padre.sig = aux.sig;
						Proceso aux1 = aux.sig.sig;
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


	Proceso buscarp(Proceso actual) {
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
		numProcesos++;
		Proceso nuevo = new Proceso();
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

	public void atender() {
		Proceso at = raiz.sig;
		if (at == raiz) {
			System.out.println("No hay nadie en la cola");
		} else {
			if (at.rafaga <= 5) {
				raiz.sig = at.sig;
				at.sig = null;
				System.out.println("Cliente atendido");
			} else {
				Proceso aux = raiz;
				Proceso paux = raiz;
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
	
	public ArrayList<String[]> Gestion() {
		Cola cola2 = new Cola();
		int cuenta = 0;
		
		while(true) {
			Proceso a = raiz.sig;
			while (a != raiz) {
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
			if (cuenta == procesos) {
				break;
			}
		}
		return cola2.allData();

	}

}
