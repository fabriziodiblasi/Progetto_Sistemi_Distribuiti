package java_proj;

import com.fazecast.jSerialComm.SerialPort;
import lights.*;
import lights.TupleSpace;
import lights.interfaces.ITupleSpace;


public class Globals {
   public static SerialPort sp_input; // definizione della porta seriale di ingresso
   public static SerialPort sp_output; // definizione della porta seriale di ingresso

   public static ITupleSpace ts = new TupleSpace("Tuple_space"); //spazio delle tuple
   public static final int MAX_MEASURE_VALUE = 250; // massimo valore della misura di gas

   public static final char ID_SX = 'S'; // id del sensore di sinistra
   public static final char ID_DX = 'D'; // id del sensore di destra
   public static final char END_MSG = '_'; // terminatore del messaggio

   // variabili per il comando della chiusura delle paratie
   public static final char OPEN = 'O';// apre il condotto e quindi mette il led a verde ed apre il servomotore
   public static final char CLOSE = 'C'; // chiude il condotto e quindi mette il led a rosso e chiude il servomotore
}
