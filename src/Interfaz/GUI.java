package Interfaz;

import java.util.ArrayList;
import java.util.Timer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import Logica.GestionProcesos;
import org.eclipse.swt.widgets.Text;
import java.util.TimerTask;


public class GUI {
	
	protected Shell shell;
	private Table table;
	private TableColumn proceso;
	private TableColumn tllegada;
	private TableColumn rafaga;
	private TableColumn tcomienzo;
	private TableColumn tfinal;
	private TableColumn tretorno;
	private TableColumn tespera;
	private TableColumn procesoB;
	private TableColumn trfaltante;
	private TableColumn tesperaRetorno;
	private Table gantt;
	private GestionProcesos gp =new GestionProcesos();
	private ArrayList<String[]> alldata = new ArrayList<String[]>();
	private int id = 1;
	private Text Tiempo;
	private int t=0;
	private int trafaga=0;
	private boolean Pbloqueado=false;
	Timer timer;
	private Table tableBloqueados;

	public static void main(String[] args) {
		try {
			GUI window = new GUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		Display display=Display.getDefault();
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(600, 652);
		shell.setText("Algoritmo SJF");
		
		Tiempo = new Text(shell, SWT.BORDER);
		Tiempo.setBounds(352, 224, 47, 21);
		Tiempo.setEnabled(false);
		Tiempo.setVisible(false);
		
		Label lblNumeroDeRecibos = new Label(shell, SWT.NONE);
		lblNumeroDeRecibos.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNumeroDeRecibos.setFont(SWTResourceManager.getFont("Consolas", 9, SWT.NORMAL));
		lblNumeroDeRecibos.setBounds(247, 73, 65, 15);
		lblNumeroDeRecibos.setText("Rafaga:");
		
		Button btnAgregar = new Button(shell, SWT.NONE);
		
		btnAgregar.setBounds(410, 67, 75, 25);
		btnAgregar.setText("Agregar");
		
		Spinner nRecibos = new Spinner(shell, SWT.BORDER);
		nRecibos.setMinimum(1);
		nRecibos.setSelection(1);
		nRecibos.setBounds(314, 69, 47, 22);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(SWTResourceManager.getFont("Consolas", 18, SWT.NORMAL));
		lblNewLabel.setTextDirection(3355);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(119, 0, 340, 55);
		lblNewLabel.setText("Gestion de procesos-SJF no exclusivo");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setBounds(278, 229, 47, 15);
		lblNewLabel_1.setText("tiempo:");
		lblNewLabel_1.setVisible(false);
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		table.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		table.setBounds(10, 98, 564, 120);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
	    proceso = new TableColumn(table, SWT.CENTER);
	    proceso.setText("Proceso");
	    proceso.setWidth(75);
	    proceso.setAlignment(SWT.CENTER);
	    tllegada = new TableColumn(table, SWT.CENTER);
	    tllegada.setText("T.llegada");
	    tllegada.setWidth(75);
	    tllegada.setAlignment(SWT.CENTER);
	    rafaga = new TableColumn(table, SWT.CENTER);
	    rafaga.setText("Rafaga");
	    rafaga.setWidth(75);
	    rafaga.setAlignment(SWT.CENTER);
	    tcomienzo = new TableColumn(table, SWT.CENTER);
	    tcomienzo.setText("T.comienzo");
	    tcomienzo.setWidth(75);
	    tcomienzo.setAlignment(SWT.CENTER);
	    tfinal = new TableColumn(table, SWT.CENTER);
	    tfinal.setText("T.final");
	    tfinal.setWidth(80);
	    tfinal.setAlignment(SWT.CENTER);
	    tretorno = new TableColumn(table, SWT.CENTER);
	    tretorno.setText("T.retorno");
	    tretorno.setWidth(75);
	    tretorno.setAlignment(SWT.CENTER);
	    tespera = new TableColumn(table, SWT.CENTER);
	    tespera.setText("T.espera");
	    tespera.setWidth(75);
	    tespera.setAlignment(SWT.CENTER);
	   
	    table.setHeaderVisible(true);
	    //table.setEnabled(false);
	    
	    gantt = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		gantt.setHeaderBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		gantt.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		gantt.setBounds(10, 269, 564, 160);
		gantt.setHeaderVisible(true);
		gantt.setLinesVisible(true);
		gantt.setVisible(false);
		gantt.setCursor(null);
		gantt.setCapture(false);
		gantt.setTouchEnabled(false);
		
		tableBloqueados = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tableBloqueados.setHeaderBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		tableBloqueados.setBounds(10, 483, 564, 120);
		tableBloqueados.setHeaderVisible(true);
		tableBloqueados.setLinesVisible(true);
		//tableBloqueados.setHeaderBackground(display.getSystemColor(SWT.));
		tableBloqueados.setVisible(false);
		
		procesoB = new TableColumn(tableBloqueados, SWT.CENTER);
	    procesoB.setText("Proceso");
	    procesoB.setWidth(100);
	    procesoB.setAlignment(SWT.CENTER);
	    trfaltante = new TableColumn(tableBloqueados, SWT.CENTER);
	    trfaltante.setText("T. rafaga faltante");
	    trfaltante.setWidth(230);
	    trfaltante.setAlignment(SWT.CENTER);
	    tesperaRetorno = new TableColumn(tableBloqueados, SWT.CENTER);
	    tesperaRetorno.setText("T. espera retorno");
	    tesperaRetorno.setWidth(230);
	    tesperaRetorno.setAlignment(SWT.CENTER);
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Consolas", 15, SWT.BOLD));
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setBounds(234, 449, 116, 28);
		lblNewLabel_2.setText("Bloqueados");
		lblNewLabel_2.setVisible(false);
		
		Label lblTLlegada = new Label(shell, SWT.NONE);
		lblTLlegada.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTLlegada.setBounds(83, 72, 55, 15);
		lblTLlegada.setText("T. llegada:");
		
		
		Spinner Tllegada = new Spinner(shell, SWT.BORDER);
		Tllegada.setBounds(159, 69, 47, 22);
		Tllegada.setMinimum(0);
		Tllegada.setSelection(0);
		
		Button btnBloquear = new Button(shell, SWT.NONE);
		btnBloquear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				timer.cancel();
				lblNewLabel_2.setVisible(true);
				tableBloqueados.setVisible(true);
				Pbloqueado=true;
				gp.procesobloqueado(t);
				llenarTablaGestionada();
				ConstruirGantt();
				llenarTablaBloqueado();
			}
		});
		btnBloquear.setBounds(474, 224, 75, 25);
		btnBloquear.setText("Bloquear");
		btnBloquear.setVisible(false);
		
		Button btnAtender = new Button(shell, SWT.NONE);
		
		
		btnAtender.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				trafaga=t+gp.rPrimerProceso()-1;
				timer= new Timer();
				TimerTask tt= new TimerTask() {
					@Override
					public void run() {
						display.asyncExec(new Runnable() {

							@Override
							public void run() {
								if(t==trafaga) {
								System.out.println(Pbloqueado);	
									gp.atenderProceso();
									timer.cancel();
								}
								Pbloqueado=gp.desbloquearProceso(t);
								llenarTablaGestionada();
								ConstruirGantt();
								llenarTablaBloqueado();
								Tiempo.setText(String.valueOf(t));
								Tllegada.setMinimum(t);
		                        t++;
							}
						});
					}
					
				};
				timer.schedule(tt, 0,1000);
			}
		});
		btnAtender.setText("Atender");
		btnAtender.setBounds(186, 224, 75, 25);
		btnAtender.setVisible(false);
		//gantt.setRedraw(false);
		
		Button btnGestionar = new Button(shell, SWT.NONE);
		btnGestionar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				gantt.setVisible(true);
				btnAtender.setVisible(true);
				btnBloquear.setVisible(true);
				lblNewLabel_1.setVisible(true);
				Tiempo.setVisible(true);
				gp.ordenar();
				gp.Gestion();
				clearGanttItems();
				llenarTablaGestionada();
				ConstruirGantt();
				
			}
		});
		btnGestionar.setBounds(47, 224, 75, 25);
		btnGestionar.setText("Gestionar");
		btnGestionar.setVisible(false);
		
		btnAgregar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int rafaga= nRecibos.getSelection();
				int tllegada= Tllegada.getSelection();
				btnGestionar.setVisible(true);
				gp.ingresarProceso(id, rafaga,tllegada);
				id++;
				llenarTabla();
			}
		});
		
		
	}
	
	public void llenarTabla() {
		TableItem item= new TableItem(table,SWT.NONE);
		String[] fila=gp.infoProceso();
		item.setText(0, fila[0]);
		item.setText(1, fila[1]);
		item.setText(2, fila[2]);
		
	}
	
	public void llenarTablaBloqueado() {
		ArrayList<String[]> pbloqueados=gp.infoProcesosBloquedos();
		TableItem[] items =new TableItem[pbloqueados.size()];
		tableBloqueados.removeAll();
		for(int i=0;i<pbloqueados.size();i++) {
			items[i]= new TableItem(tableBloqueados,SWT.NONE);
			items[i].setText(pbloqueados.get(i));
		}	
	}
	
	public void llenarTablaGestionada() {
		System.out.println("Pbloqueado "+Pbloqueado);
		table.removeAll();
		this.alldata.clear();
		if(!Pbloqueado) {
			this.alldata=gp.Gestion();
		}else {
			this.alldata=gp.infoProcesos();
		}
		TableItem[] items =new TableItem[alldata.size()];
		for(int i=0;i<alldata.size();i++) {
			items[i]= new TableItem(table,SWT.NONE);
			items[i].setText(alldata.get(i));
		}
		
	}
	
	public void ConstruirGantt() {
		clearGanttColumns();
		int wTable=564;
		int wganttcolumn;
		int comienzo=0;
		int rafagaTotal=gp.tfinalUltimo();
		if(rafagaTotal==0) {
			wganttcolumn=wTable;
		}else {
			wganttcolumn=wTable/rafagaTotal;
		}
		for(int i=comienzo;i<rafagaTotal;i++) {
			 TableColumn ganttcolumn = new TableColumn(gantt, SWT.CENTER);
			 ganttcolumn.setText(String.valueOf(i));
			 if(wganttcolumn<25) {
				 ganttcolumn.setWidth(25);
			 }else {
				 ganttcolumn.setWidth(wganttcolumn); 
			 }
			 ganttcolumn.setAlignment(SWT.CENTER);
		}
		llenarGantt();
		
	}
	
	public void clearGanttColumns() {
		while ( gantt.getColumnCount() > 0 ) {
		    gantt.getColumns()[ 0 ].dispose();
		}
	}
	
	public void clearGanttItems() {
		while ( gantt.getItemCount() > 0 ) {
		    gantt.getItems()[ 0 ].dispose();
		}
	}
	
	public void llenarGantt() {
		clearGanttItems();
		Display display = Display.getDefault();
		int index=0;
		while(index<this.alldata.size()) {
			TableItem item= new TableItem(gantt,SWT.NONE);
			int tllegada=Integer.valueOf(this.alldata.get(index)[1]);
			int tretorno=Integer.valueOf(this.alldata.get(index)[5]);
			int tespera=Integer.valueOf(this.alldata.get(index)[6]);
			int i=tllegada;
			while(tretorno>0) {
				if(tespera>0) {
					item.setBackground(i, display.getSystemColor(SWT.COLOR_YELLOW));
					tespera--;
				}else {
					item.setBackground(i, display.getSystemColor(SWT.COLOR_BLUE));
				}
				i++;
				tretorno-=1;
			}
			item.setChecked(false);
			item.setGrayed(false);
			index++;
		}
	}
}
