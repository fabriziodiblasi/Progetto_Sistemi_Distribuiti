import com.fazecast.jSerialComm.*;
import java.io.IOException;
import java.io.InputStream;
import lights.*;
import lights.interfaces.ITupleSpace;
//import lights.interfaces.ITupleSpace;


public class Prova_Seriale {
    public static void main(String[] args) throws IOException, InterruptedException {
//        System.out.println(SerialPort.getCommPorts());

        SerialPort sp = SerialPort.getCommPort("COM4"); // device name TODO: must be changed
        sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written

        if (sp.openPort()) {
            System.out.println("Port is open :)");
        } else {
            System.out.println("Failed to open port :(");
            return;
        }
        //pagina 6 del pdf
        ITupleSpace ts = new TupleSpace("Authors");


        try {
            while (true)
            {
                while (sp.bytesAvailable() < 5)
                //while (sp.bytesAvailable() == 0)
                    Thread.sleep(20);
                InputStream in = sp.getInputStream();
                System.out.println((char)in.read());
                in.close();
            }
        } catch (Exception e) { e.printStackTrace(); }
        sp.closePort();
    }
}
