package tema5.ejemplosSwing.modelos;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.Vector;

public class PruebaJTable {
	static private JTable tabla;
	static private DefaultTableModel modeloTabla;
	static private Vector<Vector<String>> datos;
	static private Vector<String> columnas;
	static private void init() {
		JFrame v = new JFrame();
		datos = new Vector<Vector<String>>();
		columnas = new Vector<String>();
		columnas.add( "Nombre" ); columnas.add( "Apellidos" );
		Vector<String> fila1 = new Vector<String>();
		Vector<String> fila2 = new Vector<String>();
		datos.add( fila1 );
		datos.add( fila2 );
		fila1.add( "Pepe" ); fila1.add( "Pérez" );
		fila2.add( "Ana" ); fila2.add( "López" );
		modeloTabla = new DefaultTableModel( datos, columnas );
		tabla = new JTable( modeloTabla );
		modeloTabla.addTableModelListener( new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				System.out.println( "Modificación en coord: " + e.getFirstRow() + " , " + e.getColumn() );
			}
		});
		v.add( new JScrollPane(tabla), BorderLayout.CENTER );
		v.setSize( 400, 300 );
		v.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		v.setVisible( true );
	}
	static public void main( String[] s ) {
		try {
			SwingUtilities.invokeAndWait( new Runnable() {
				@Override
				public void run() {
					init();
				}
			} );
		} catch (Exception e) {
			e.printStackTrace();
		}
		try { Thread.sleep( 2000 ); } catch (InterruptedException e) {}
		
		Vector<String> nuevaFila = new Vector<String>();
		nuevaFila.add( "Juan" ); nuevaFila.add( "Ramírez" );
		modeloTabla.addRow( nuevaFila );  // Así swing se entera del cambio
		try { Thread.sleep( 2000 ); } catch (InterruptedException e) {}
		
		nuevaFila = new Vector<String>();
		nuevaFila.add( "Luis" ); nuevaFila.add( "Martín" );
		datos.add( nuevaFila );  // Así swing no se entera
		System.out.println( "Fin" );
		// tabla.repaint();  // no es suficiente si se cambia a "mano" el vector
		// tabla.revalidate();  // eso sí
	}
}
