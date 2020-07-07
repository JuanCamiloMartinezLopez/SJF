package Logica;

import java.util.ArrayList;

public class GestionProcesos {
	
	private ColaProcesos procesos;
	private ColaProcesos bloqueados;
	private int tiempo=0;
	
	public GestionProcesos(){
		procesos= new ColaProcesos();
		bloqueados= new ColaProcesos();
	}
	
	public int rPrimerProceso() {
		return procesos.getRaiz().sig.rafaga;
	}
	
	public int tComienzoPrimer() {
		return procesos.getRaiz().sig.tcomienzo;
	}
	
	public void ingresarProceso(int id,int rafaga,int tllegada) {
		
		procesos.insertar(id, rafaga, tllegada);
	}
	
	public int getrafagaTotal() {
		return this.procesos.getrafagaTotal();
	}
	
	public void ordenar() {
		Proceso raiz=procesos.getRaiz();
		int numPorcesos=procesos.getProcesos();
		Proceso aux;
		while (true) {
			aux = raiz.sig;
			boolean ordenar = false;
			if (numPorcesos<= 1) {
				break;
			}
			for (int i = 1; i < numPorcesos; i++) {
				if (aux.rafaga > aux.sig.rafaga) {
					if (aux.sig.rafaga != -1) {
						Proceso padre = this.procesos.buscarp(aux);
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

	public ArrayList<String[]> infoProcesos() {
		ArrayList<String[]> IP=new ArrayList<String[]>();
		Proceso raiz=procesos.getRaiz();
		Proceso aux = raiz;
		while (aux.sig != raiz){
			aux = aux.sig;
			String[] process = { String.valueOf(aux.id), String.valueOf(aux.tllegada), String.valueOf(aux.rafaga),
					String.valueOf(aux.tcomienzo), String.valueOf(aux.tfinal), String.valueOf(aux.tretorno),
					String.valueOf(aux.tespera) };
			IP.add(process);
		}

		return IP;
	}
	
	public void atenderProceso() {
		System.out.println("Atendido");
		this.procesos.atender();
	}
	
	public Boolean desbloquearProceso(int tiempo) {
		Proceso raizB=bloqueados.getRaiz();
		Proceso aux=raizB;
		while(aux.sig!=raizB) {
			aux=aux.sig;
			int tb=aux.tbloqueo;
			int ter=aux.tesperaRetorno;
			if((tb+ter)==tiempo) {
				procesos.insertar(aux.id, aux.rafaga, tiempo);
				return true;
			}
		}
		return false;
	}
	
	public int tfinalUltimo() {
		Proceso raiz=this.procesos.getRaiz();
		Proceso aux = raiz;
		while (aux.sig != raiz){
			aux = aux.sig;
		}
		return aux.tfinal;
	}
	
	public void procesobloqueado(int tbloqueo) {
		System.out.println("Bloqueado");
		Proceso bloqueadoProceso=procesos.atender();
		bloqueadoProceso.rafaga-=(tbloqueo-bloqueadoProceso.tcomienzo);
		bloqueadoProceso.tbloqueo=tbloqueo;
		bloqueadoProceso.tesperaRetorno=3;
		bloqueados.insertar(bloqueadoProceso);
	}
	
	public ArrayList<String[]> Gestion() {
		//ordenar();
		ColaProcesos Ordenamiento = new ColaProcesos();
		int numProcesos=this.procesos.getProcesos();
		int cuenta = 0;
		Proceso raiz= this.procesos.getRaiz();
		while(true) {
			Proceso a = raiz.sig;
			while (a != raiz) {
				if (a.tllegada <= tiempo) {
					if (Ordenamiento.buscar(a.id) == false) {
						Ordenamiento.insertar(a.id, a.rafaga, a.tllegada);
						cuenta++;
						break;
					}
					
				}
				a = a.sig;
			}
			tiempo = tiempo+a.rafaga;
			if (cuenta == numProcesos) {
				break;
			}
		}
		procesos= Ordenamiento;
		return this.infoProcesos();

	}
	
	public String[] infoProceso() {
		Proceso raiz=this.procesos.getRaiz();
		Proceso aux = raiz;
		while (aux.sig != raiz){
			aux = aux.sig;
		}
		String[] process = { String.valueOf(aux.id), String.valueOf(aux.tllegada), String.valueOf(aux.rafaga),
				String.valueOf(aux.tcomienzo), String.valueOf(aux.tfinal), String.valueOf(aux.tretorno),
				String.valueOf(aux.tespera) };

		return process;
		
	}
	
	public ArrayList<String[]> infoProcesosBloquedos() {
		ArrayList<String[]> IP=new ArrayList<String[]>();
		Proceso raiz=bloqueados.getRaiz();
		Proceso aux = raiz;
		while (aux.sig != raiz){
			aux = aux.sig;
			String[] process = { String.valueOf(aux.id), String.valueOf(aux.rafaga), String.valueOf(aux.tesperaRetorno)};
			IP.add(process);
		}

		return IP;
	}

}
