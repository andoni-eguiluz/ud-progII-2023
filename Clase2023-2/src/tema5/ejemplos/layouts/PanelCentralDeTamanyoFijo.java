package tema5.ejemplos.layouts;

import java.awt.*;
import javax.swing.*;

/** Ejemplo de manipulación fina de espacios en la ventana
 * con GridBagLayout, para hacer una especie de borderlayout donde el 
 * panel central tenga tamaño fijo
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class PanelCentralDeTamanyoFijo {
	
    public static void main( String[] args ) {
        JFrame ventana = new JFrame( "Componente central de tamaño fijo" );
        ventana.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

        // Definimos un gridbaglayout de 3x3 donde la casilla central tenga tamaño fijo
        // y los laterales ocupen el 100% disponible adicional
        ventana.setLayout( new GridBagLayout() );
        GridBagConstraints gbc = new GridBagConstraints();

        // Añadir componente norte
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        ventana.add(new JButton("Norte"), gbc);

        // Añadir componente Oeste
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        ventana.add(new JButton("Oeste"), gbc);

        // Añadir componente central de tamaño fijo
        JButton fixedSizeButton = new JButton("Botón central de tamaño fijo");
        Dimension fixedSize = new Dimension(200, 100);
        fixedSizeButton.setPreferredSize(fixedSize);
        fixedSizeButton.setMinimumSize(fixedSize);
        fixedSizeButton.setMaximumSize(fixedSize);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        ventana.add(fixedSizeButton, gbc);

        // Añadir componente Este
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        ventana.add(new JButton("Este"), gbc);

        // Añadir componente Sur
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        ventana.add(new JButton("Sur"), gbc);

        ventana.pack();
        ventana.setLocationRelativeTo(null);
        
        ventana.setVisible(true);
    }
}
