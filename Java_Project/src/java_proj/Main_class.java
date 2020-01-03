package java_proj;

import com.fazecast.jSerialComm.*;
import java.io.IOException;
import java_proj.input_master.Measurements_Agent;
import java_proj.tuple_space_admin.Tuple_Space_Admin;
import java_proj.output_master.Output_Agent;
//import lights.interfaces.ITupleSpace;


public class Main_class {
    public static void main(String[] args) throws IOException, InterruptedException {
//        System.out.println(SerialPort.getCommPorts());

        Globals.sp_input = SerialPort.getCommPort("COM4"); // device name TODO: must be changed
        Globals.sp_input.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
        Globals.sp_input.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written

        if (Globals.sp_input.openPort()) {
            System.out.println("Port is open :)");
        } else {
            System.out.println("Failed to open port :(");
            return;
        }
        //pagina 6 del pdf
//        ITupleSpace ts = new TupleSpace("Authors");
        Measurements_Agent measure_th = new Measurements_Agent();
        Tuple_Space_Admin tuple_th = new Tuple_Space_Admin();
        Output_Agent output_th =new Output_Agent();
        measure_th.start();
        tuple_th.start();
        output_th.start();

    }
}
