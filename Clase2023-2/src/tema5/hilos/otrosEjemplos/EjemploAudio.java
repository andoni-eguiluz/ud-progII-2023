package tema5.hilos.otrosEjemplos;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

/** Ejemplo de lanzamiento de audio con hilos
 * @author andoni.eguiluz at ingenieria.deusto.es
 *
 */
public class EjemploAudio {
	
	private static int QUE_PRUEBA = 1;  // 1 - audios sin hilo (en main)   2 - audios con hilos independientes
	public static void main(String[] args) {
		if (QUE_PRUEBA==1) {
			pruebaSinHilos(); 
		} else {
			pruebaConHilos();
		}
	}
	
	private static void pruebaSinHilos() {
		System.out.println( "Audios desde el main. Audio 1:" );
		lanzaAudio( "src/tema5/hilos/otrosEjemplos/timbre.wav" );
		System.out.println( "Audio 2:" );
		lanzaAudio( "src/tema5/hilos/otrosEjemplos/aplauso.wav" );
		System.out.println( "main solo acaba después de los dos audios");
	}

	private static void pruebaConHilos() {
		System.out.println( "Se va a lanzar un audio en un hilo, el otro en 2 segundos en otro hilo, y main se acaba");
		System.out.println( "Audio 1 en hilo 1:" );
		lanzaAudioEnHilo( "src/tema5/hilos/otrosEjemplos/timbre.wav" );
		try { Thread.sleep(2000); } catch (Exception e) {}
		System.out.println( "Audio 2 en hilo 2:" );
		lanzaAudioEnHilo( "src/tema5/hilos/otrosEjemplos/aplauso.wav" );
		System.out.println( "Fin de main" );
	}
	
	private static String ficAudioActual = null;
	public static void lanzaAudioEnHilo( String ficAudio ) {
		ficAudioActual = ficAudio; 
		Runnable r = new Runnable() {
			@Override
			public void run() {
				lanzaAudio( ficAudioActual );
				System.out.println( "Fin de audio " + ficAudio );
			}
		};
		(new Thread(r)).start();
	}

	/** Lanza un audio indicado en un fichero wav
	 * @param ficheroWav	Path correcto del fichero wav indicado
	 */
	public static void lanzaAudio( String ficheroWav ) {
	    int BUFFER_SIZE = 128000;
	    AudioInputStream flujoAudio = null;
	    AudioFormat formatoAudio = null;
	    SourceDataLine lineaDatosSonido = null;
	    File ficSonido = null;
        try {
            ficSonido = new File(ficheroWav);
            flujoAudio = AudioSystem.getAudioInputStream(ficSonido);
            formatoAudio = flujoAudio.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, formatoAudio);
            lineaDatosSonido = (SourceDataLine) AudioSystem.getLine(info);
            lineaDatosSonido.open(formatoAudio);
            lineaDatosSonido.start();
            int bytesLeidos = 0;
            byte[] bytesAudio = new byte[BUFFER_SIZE];
            while (bytesLeidos != -1) {
                try {
                    bytesLeidos = flujoAudio.read(bytesAudio, 0, bytesAudio.length);
                } catch (IOException e) { }
                if (bytesLeidos >= 0) {
                    lineaDatosSonido.write(bytesAudio, 0, bytesLeidos);
                }
            }
        } catch (Exception e) {
        	// Excepción si el fichero es nulo, erróneo, o wav incorrecto
        }
    	if (lineaDatosSonido != null) {
            lineaDatosSonido.drain();
            try {
            	lineaDatosSonido.close();
                flujoAudio.close();
            } catch (Exception e) {}
    	}
	}
	
}
