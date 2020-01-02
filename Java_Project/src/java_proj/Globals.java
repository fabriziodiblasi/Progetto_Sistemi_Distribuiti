package java_proj;

import com.fazecast.jSerialComm.SerialPort;
import lights.*;
import lights.TupleSpace;
import lights.interfaces.ITupleSpace;


public class Globals {
   public static SerialPort sp_input;
   public static ITupleSpace ts = new TupleSpace("Tuple_space");
   public static final int MAX_MEASURE_VALUE = 250;
}
