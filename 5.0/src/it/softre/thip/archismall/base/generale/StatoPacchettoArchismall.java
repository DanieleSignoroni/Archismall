package it.softre.thip.archismall.base.generale;

public class StatoPacchettoArchismall {
	
	public static final char DA_CONSERVARE = '0';
	public static final char CONSERVATO = '1';
	public static final char PRESO_IN_CARICO = '3';
	public static final char NEGATIVO = '4';
	public static final char DA_VERIFICARE = '5';
	public static final char IN_QUEUE = '6';
	public static final char WORKER_ERROR = '7';
	public static final char ERRORE_FIRMA = '8';
	public static final char FIRMA_IN_CORSO = '9';
	
	//sarebbe il 10 loro pero' il nostro db non accetta 10 nel value dell'enum
	public static final char INV_IN_CONSERVAZIONE = 'A'; 
	
}
