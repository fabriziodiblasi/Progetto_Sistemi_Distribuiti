package java_proj.output_master;

import java_proj.Globals;
import lights.Field;
import lights.Tuple;
import lights.interfaces.IField;
import lights.interfaces.ITuple;

public class Output_Agent extends Thread {
    public void run() {
        System.out.println("OutputAgent thread is running...");
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
                

            }catch (Throwable err) {
                err.printStackTrace();
            }
        }
    }
}
