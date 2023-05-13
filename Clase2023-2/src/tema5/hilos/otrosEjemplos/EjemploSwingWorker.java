package tema5.hilos.otrosEjemplos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

// Un SwingWorker es una clase genérica que recoge dos parámetros:
//   - A: El tipo de datos que va a devolver el hilo trabajador (ej. AL<String>)
//   - B: El tipo de dato que se va a ENCOLAR durante el proceso (ej. Integer)
public class EjemploSwingWorker extends SwingWorker<ArrayList<String>,Integer> {

	// Para este ejemplo el worker guarda el modelo de la lista, la progressbar,
	// y los botones con los que va a trabajar
	private DefaultListModel<String> modeloLista;
	private JProgressBar barraProgreso;
	private JButton botonInicio, botonParada;
	public  EjemploSwingWorker(DefaultListModel<String>  modeloLista,  JProgressBar barraProgreso, 
		JButton botonInicio, JButton botonParada) {
		this.modeloLista = modeloLista;
		this.barraProgreso = barraProgreso;
		this.botonInicio = botonInicio;
		this.botonParada = botonParada;
    }
	
	// doInBackground() es el trabajo que hace el hilo trabajador
	//    isCancelled() devuelve true si se ha llamado a cancel()
	// LO LLAMA UN HILO INDEPENDIENTE
    @Override
	protected ArrayList<String> doInBackground() { 
	    Integer valorTemp = new Integer(1); 
	    ArrayList<String> lista = new ArrayList<String>(); 
	    for (int i = 0; i < 100 && !isCancelled(); i++) { 
	        for (int j = 0; j < 100 && !isCancelled(); j++) { 
	           valorTemp = encuentraSiguientePrimo( valorTemp.intValue() );
	        } 
	        // publish manda información transitoria del proceso a la COLA (tipo B)
	        publish( new Integer(i) ); 
	        lista.add( "Primo " + ((i+1)*100) + ": " + valorTemp ); 
	    } 
	    return lista; 
	}
	  
	// process(List<B>) es llamado por Swing durante el proceso
    // y recoge toda la COLA que va lanzando publish() 
    // (la que haya en ese momento)
    // EL HILO QUE EJECUTA PROCESS ES EL HILO DE GESTION DE EVENTOS
    @Override
    protected void process(java.util.List<Integer> lista) {
        Integer parteCompletada = lista.get(lista.size() - 1); 
        barraProgreso.setValue(parteCompletada.intValue());
        System.out.println( " * Llamada a process con cola en " + parteCompletada );
    }
	    
    // done() se llama al final y con get() puede coger lo que
    // ha devuelto doInBackground() - tipo A
    // EL HILO QUE EJECUTA DONE ES EL HILO DE GESTION DE EVENTOS
    @Override
    protected void done() {
        if(!isCancelled()) {
            try { 
                ArrayList<String> results = get();  
                for (String s : results) 
                    modeloLista.addElement( s );	
			} catch (ExecutionException ex) {
			    ex.printStackTrace();
			} catch (InterruptedException ex) {
			    ex.printStackTrace();
			}
		    this.botonParada.setEnabled(false);
	    	this.botonInicio.setEnabled(true);
    	}
    }

    private Integer encuentraSiguientePrimo(int num ) {
		do  { 
			if(num % 2 == 0)
			    num++; 
			else 
			    num = num + 2; 
		} while (!esPrimo(num)); 
		return new Integer(num); 
	}

	private boolean esPrimo(int num) {
		int i; 
		for (i = 2; i <= num / 2; i++ ) { 
			if(num % i == 0)
			    return false; 
		} 
		return true; 
	}             

	public static void main( String[] s) {
		SwingUtilities.invokeLater(new Runnable() { 
	        public void run() { 
	            new VentanaPrincipal(); 
	        } 
	    } ); 
	}
	
}


class VentanaPrincipal extends JFrame implements ActionListener {
	private JButton botonInicio, botonParada; 
    private JScrollPane panelDesplazamiento; 
    private JList<String> lista;
    private DefaultListModel<String> modeloLista;
    private JProgressBar barraProgreso; 
    private EjemploSwingWorker generaPrimos; 
	private static final long serialVersionUID = 4051468182736865142L;
    public VentanaPrincipal() { 
        super("Multitarea Swing - de 100 en 100 primos");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        this.getContentPane().setLayout(new BorderLayout());
        JPanel pIzquierda = new JPanel();  // FlowLayout
        this.botonInicio = this.construyeBoton( pIzquierda, "Iniciar" ); 
        this.botonParada = this.construyeBoton( pIzquierda, "Parar" ); 
        this.botonParada.setEnabled(false); 
        this.barraProgreso = this.construyeBarraProgreso( pIzquierda, 0, 99 ); 
        this.modeloLista = new DefaultListModel<String>();
        this.lista = new JList<String>(this.modeloLista); 
        this.panelDesplazamiento = new JScrollPane();
        this.panelDesplazamiento.setViewportView( this.lista );
        this.getContentPane().add( pIzquierda, BorderLayout.WEST );
        this.getContentPane().add( this.panelDesplazamiento, BorderLayout.CENTER ); 
        this.pack(); 
        this.setVisible(true); 
    }
    
    private JButton construyeBoton( JPanel p, String titulo ) { 
        JButton b = new JButton(titulo); 
        b.setActionCommand(titulo);
        b.addActionListener(this);
        p.add(b);
        return b; 
    }
    
    private JProgressBar construyeBarraProgreso(JPanel p, int min, int max) { 
        JProgressBar progressBar = new JProgressBar(); 
        progressBar.setMinimum(min); 
        progressBar.setMaximum(max); 
        progressBar.setStringPainted(true); 
        progressBar.setBorderPainted(true); 
        p.add(progressBar); 
        return progressBar; 
    }
    
    public void actionPerformed(ActionEvent e) { 
       if("Iniciar".equals(e.getActionCommand())) {
            this.modeloLista.clear();
            this.botonInicio.setEnabled(false); 
            this.botonParada.setEnabled(true); 
            this.generaPrimos = new EjemploSwingWorker( this.modeloLista, 
            		this.barraProgreso, this.botonInicio, this.botonParada );
            // EXECUTE lanza un swingworker
            this.generaPrimos.execute(); 
        } 
        else if("Parar".equals(e.getActionCommand())) { 
            this.botonInicio.setEnabled(true); 
            this.botonParada.setEnabled(false);
            // CANCEL cancela un swingworker  (activa el flag isCancelled())
            this.generaPrimos.cancel(true);
            this.generaPrimos = null; 
        } 
    }
}
