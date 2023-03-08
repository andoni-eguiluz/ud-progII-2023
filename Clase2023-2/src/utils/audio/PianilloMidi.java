package utils.audio;

import java.util.ArrayList;
import java.util.Arrays;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;

/**
 * Utilidad para tocar notas basadas en estándar MIDI incorporada en Java
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class PianilloMidi {
	
	private static ArrayList<String> notes = new ArrayList<>( Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B") );
	private static Synthesizer synth;  // Sintetizador midi con el que se va a reproducir sonido
	private static MidiChannel[] canales;  // Canales midi del sintetizador
	private static int INSTRUMENTO = 0; // canal 0 por defecto es un piano
	private static int volumen = 80;  // Volumen (entre 0 y 127) - 80 por defecto
	
	/** Prueba de un par de ejemplos de sonido
	 * @param args	No utilizado
	 */
	public static void main( String[] args ) {
		ejemplo1();
		ejemplo2();
	}
	
	private static void ejemplo1() {
		int unidad = 800; // Msegs
		inicio();
		reproduceYEspera( "3C", unidad );
		reproduceYEspera( "3G", unidad );
		reproduceYEspera( "3A", unidad );
		reproduceYEspera( "3E", unidad );
		reproduceYEspera( "3F", unidad );
		reproduceYEspera( "3C", unidad );
		reproduceYEspera( "3F", unidad );
		reproduceYEspera( "3G", unidad );
		fin();
	}
	
	private static void ejemplo2() {
		inicio();	
		setVolumen( 127 );
		reproduceNota( "4C" );
		espera( 1000 );
		reproduceNota( "5C" );
		reproduceNota( "5E" );
		espera( 1000 );
		reproduceNota( "5C" );
		reproduceNota( "5E" );
		reproduceNota( "5G" );
		espera( 1000 );
		acabaNota( "5C" );
		acabaNota( "5E" );
		acabaNota( "5G" );
		reproduceNota( "5C" );
		reproduceNota( "5E" );
		reproduceNota( "5G" );
		espera( 200 );
		acabaNota( "5C" );
		acabaNota( "5E" );
		acabaNota( "5G" );
		espera( 1000 );
		fin();
	}
	
	/** Inicia el sistema de reproducción de notas musicales
	 */
	public static void inicio() {
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			canales = synth.getChannels();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	/** Finaliza el sistema de reproducción de notas musicales
	 */
	public static void fin() {
		synth.close();
		synth = null;
	}
	
	/**
	 * Reproduce la nota dada con la duración indicada, esperando hasta que la duración acaba
	 * @param nota	Código de la nota a reproducir (en la forma "EN" siendo E la octava (0-8) y la nota C, C#, D, D#, E, F, F#, G, G#, A, A#, B
	 * @param duracionMilis	Duración en milisegundos
	 */
	public static void reproduceYEspera(String nota, int duracionMilis) {
		if (synth==null) {
			return;
		}
		canales[INSTRUMENTO].noteOn(idMIDI(nota), volumen );  // Empieza reproducción
		try {
			Thread.sleep( duracionMilis );
		} catch (InterruptedException e) { } // Si se interrumpe externamente, se para
		canales[INSTRUMENTO].noteOff(idMIDI(nota)); // Parar reproducción
	}
	
	/**
	 * Reproduce la nota dada y devuelve inmediatamente el control (la nota se mantendrá reproduciéndose hasta que se pare: {@link #paraNota()}
	 * @param nota	Código de la nota a reproducir (en la forma "EN" siendo E la octava (0-8) y la nota C, C#, D, D#, E, F, F#, G, G#, A, A#, B
	 */
	public static void reproduceNota( String nota ) {
		if (synth==null) {
			return;
		}
		canales[INSTRUMENTO].noteOn(idMIDI(nota), volumen );  // Empieza reproducción
	}
	
	/** Para la reproducción de una nota
	 * @param nota	Código de la nota a parar (en la forma "EN" siendo E la octava (0-8) y la nota C, C#, D, D#, E, F, F#, G, G#, A, A#, B
	 */
	public static void acabaNota( String nota ) {
		if (synth==null) {
			return;
		}
		canales[INSTRUMENTO].noteOff(idMIDI(nota)); // Parar reproducción
	}
	
	/**	Descanso de la duración indicada
	 * @param duracionMilis	Duración en milisegundos
	 */
	public static void espera(int duracionMilis) {
		try {
			Thread.sleep( duracionMilis );
		} catch (InterruptedException e) { } // Si se interrumpe externamente, se para
	}
	
	/**
	 * Devuelve el id MIDI de una nota dada (por ejmplo, nota 4C = id 60)
	 * @param nota	Código de la nota a reproducir (en la forma "EN" siendo E la octava (0-8) y la nota C, C#, D, D#, E, F, F#, G, G#, A, A#, B
	 * @return
	 */
	public static int idMIDI( String nota )
	{
		int octava = Integer.parseInt(nota.substring(0, 1));
		return notes.indexOf(nota.substring(1)) + 12 * octava + 12;	
	}
	
	/** Modifica el volumen de sonido
	 * @param volumen	Valor de volumen nuevo, entre 0 y 127
	 */
	public static void setVolumen( int volumen ) {
		PianilloMidi.volumen = volumen;
	}
	
}