package Interfaz;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import Logica.Cola;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


public class GUI {
	protected Cola cola;
	protected Shell shell;
	private Table table;
	private TableColumn proceso;
	private TableColumn tllegada;
	private TableColumn rafaga;
	private TableColumn tcomienzo;
	private TableColumn tfinal;
	private TableColumn tretorno;
	private TableColumn tespera;
	private Table gantt;
	private ArrayList<String[]> alldata = new ArrayList<String[]>();
	private int id = 1;

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
		cola=new Cola();
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(600, 490);
		shell.setText("Algoritmo SJF");
		
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
		
		Label lblTLlegada = new Label(shell, SWT.NONE);
		lblTLlegada.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTLlegada.setBounds(83, 72, 55, 15);
		lblTLlegada.setText("T. llegada:");
		
		
		Spinner Tllegada = new Spinner(shell, SWT.BORDER);
		Tllegada.setBounds(159, 69, 47, 22);
		Tllegada.setMinimum(0);
		Tllegada.setSelection(0);
		
		
		Button btnGestionar = new Button(shell, SWT.NONE);
		btnGestionar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				gantt.setVisible(true);
				cola.ordenar();
				clearGanttItems();
				ConstruirGantt(cola.getrafagaTotal());
				llenarTablaGestionada();
			}
		});
		btnGestionar.setBounds(247, 224, 75, 25);
		btnGestionar.setText("Gestionar");
		btnGestionar.setVisible(false);
		//gantt.setRedraw(false);
	    
		btnAgregar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int rafaga= nRecibos.getSelection();
				int tllegada= Tllegada.getSelection();
				btnGestionar.setVisible(true);
				cola.insertar(id, rafaga,tllegada);
				id++;
				llenarTabla();
			}
		});
		
		
	}
	
	public void llenarTabla() {
		TableItem item= new TableItem(table,SWT.NONE);
		String[] fila=cola.Data();
		item.setText(0, fila[0]);
		item.setText(1, fila[1]);
		item.setText(2, fila[2]);
		
	}
	
	public void llenarTablaGestionada() {
		TableItem[] items=table.getItems();
		for(int i=0;i<alldata.size();i++) {
			items[i].setText(this.alldata.get(i));
		}
		
	}
	
	public void ConstruirGantt(int rafagaTotal) {
		clearGanttColumns();
		int wTable=564;
		int wganttcolumn=wTable/rafagaTotal;
		System.out.println(wganttcolumn);
		for(int i=0;i<rafagaTotal;i++) {
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
		this.alldata.clear();
		while ( gantt.getItemCount() > 0 ) {
		    gantt.getItems()[ 0 ].dispose();
		}
	}
	
	public void llenarGantt() {
		clearGanttItems();
		//String[] Data=cola.Data();
		//this.alldata.add(new String[] {Data[1],Data[5],Data[6]});
		this.alldata=cola.Gestion();
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
