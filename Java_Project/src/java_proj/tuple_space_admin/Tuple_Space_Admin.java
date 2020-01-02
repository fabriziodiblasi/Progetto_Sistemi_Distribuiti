package java_proj.tuple_space_admin;
import java_proj.Globals;
import java.io.InputStream;
import lights.*;
import lights.interfaces.*;

public class Tuple_Space_Admin extends Thread {
    public void run() {
        System.out.println("TupleSpaceAdmin thread is running...");

        int val_letto =-1;
        char id_sensore =' ';

        IField f1 = new Field().setType(Character.class);
        IField f2 = new Field().setType(Integer.class);
        //creo la tupla
        ITuple p = new Tuple();
        //inserisco i campi nella tupla
        p.add(f1);
        p.add(f2);
        ITuple previous_tuple, actual_tuple;
        int stato_sistema = -1;
        while (true) {
            try {
                actual_tuple = Globals.ts.in(p);
                val_letto = Integer.parseInt(actual_tuple.get(1).toString());
                id_sensore = actual_tuple.get(0).toString().charAt(0);
                System.out.println(actual_tuple + "  " + actual_tuple.get(0) + "  " + actual_tuple.get(1));
            } catch (Throwable err) {
                err.printStackTrace();
            }

            switch(stato_sistema) {
                case -1:
                    if(val_letto > Globals.MAX_MEASURE_VALUE){
                        System.out.println("attento valore soglia superata");
                        System.out.println("sensore : " + id_sensore);
                        //stato_sistema
                    }
                    break;
                default:
            }
        }
    }
}