package java_proj.output_master;

import java_proj.Globals;
import lights.Field;
import lights.Tuple;
import lights.interfaces.IField;
import lights.interfaces.ITuple;

public class Output_Agent extends Thread {

    private static final char chiudi_sx = 'A'; // chiude il servomotore ed il led sx
    private static final char chiudi_dx = 'B'; // chiude il servomotore ed il led dx

    private static final char chiudi_all = 'C'; // chiude il servomotore, il led dx & sx

    private static final char apri_sx = 'X'; // apre il servomotore ed il led sx
    private static final char apri_dx = 'Y'; // apre il servomotore ed il led dx



    private static final  int sx_open = 0; //flag sx aperto & servomotore aperto
    private static final  int dx_open = 0; //flag dx aperto & servomotore aperto
    private static final  int sx_close = 1; //flag sx chiuso & servomotore chiuso
    private static final  int dx_close = 1; //flag dx chiuso & servomotore chiuso


    private void serialWrite(char cmd) {
//        Character a;
//        a=cmd;
        try{
            Globals.sp_output.getOutputStream().write(cmd);
            //System.out.println("Sent Command: " + cmd);
            Globals.sp_output.getOutputStream().flush();
        }catch (Throwable err){
            System.out.println("Impossibile scrivere sulla linea seriale");
            err.printStackTrace();
        }
    }


    public void run() {
        System.out.println("OutputAgent thread is running...");
        int stato_sx, stato_dx;
        stato_dx = stato_sx = sx_open;
        int flag = 0;

        IField f1 = new Field().setType(Character.class);
        IField f2 = new Field().setType(Character.class);
        char id,cmd;
        //creo la tupla
        ITuple p = new Tuple();
        //inserisco i campi nella tupla
        p.add(f1);
        p.add(f2);
        ITuple actual_tuple;
        while(true){

            try {
                actual_tuple = Globals.ts.in(p);
                id = actual_tuple.get(0).toString().charAt(0);
                cmd = actual_tuple.get(1).toString().charAt(0);
                System.out.println(actual_tuple + "  " + id + "  " + cmd);
                if (flag == 0){
                    //se è la prima volta che esegue
                    stato_dx = stato_sx = sx_open;
                    flag = 1;
                }

                if(id == Globals.ID_DX && cmd == Globals.OPEN && stato_dx == dx_close){
                    /* se ho un messaggio per il sensore di destra e la soglia è inferiore
                     apro il servomotore ed il led di destra se esso non è già chiuso*/
                    stato_dx =dx_open;
                    serialWrite(apri_dx);
                }
                if(id == Globals.ID_DX && cmd == Globals.CLOSE && stato_dx ==dx_open){
                    /* se ho un messaggio per il sensore di destra e la soglia è maggiore
                     chiudo il servomotore ed il led di destra se esso non è già chiuso*/
                    stato_dx =dx_close;
                    serialWrite(chiudi_dx);
                }



                if(id == Globals.ID_SX && cmd == Globals.OPEN && stato_sx == sx_close){
                    /* se ho un messaggio per il sensore di sinistra e la soglia è inferiore
                     apro il servomotore ed il led di sinistra se esso non è già chiuso*/
                    stato_sx =sx_open;
                    serialWrite(apri_sx);
                }

                if(id == Globals.ID_SX && cmd == Globals.CLOSE && stato_sx == sx_open){
                    /* se ho un messaggio per il sensore di sinistra e la soglia è maggiore
                     chiudo il servomotore ed il led di sinistra se esso non è già chiuso*/
                    stato_sx =sx_close;
                    serialWrite(chiudi_sx);
                }

            }catch (Throwable err) {
                err.printStackTrace();
            }
        }
    }
}
