package java_proj.tuple_space_admin;
import java_proj.Globals;
import java.io.InputStream;
import lights.*;
import lights.interfaces.*;

public class Tuple_Space_Admin extends Thread {
    public void run() {
        System.out.println("TupleSpaceAdmin thread is running...");


        IField f1 = new Field().setType(Character.class);
        IField f2 = new Field().setType(Integer.class);
        //creo la tupla
        ITuple p = new Tuple();
        //inserisco i campi nella tupla
        p.add(f1);
        p.add(f2);

        while (true) {
            try {
                ITuple result = Globals.ts.in(p);
                System.out.println(result + "  " + result.get(0));
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
    }
}