package java_proj.tuple_space_admin;
import java_proj.Globals;
import java.io.InputStream;
import lights.*;
import lights.interfaces.*;

public class Tuple_Space_Admin extends Thread {

    private void scriviTupla(char id, char command){
        IField f1 = new Field().setValue(id);
        IField f2 = new Field().setValue(command);
        //creo la tupla
        ITuple t1 = new Tuple();
        //inserisco i campi nella tupla
        t1.add(f1);
        t1.add(f2);
        //pubblico la tupla
        //System.out.println("pubblico la tupla");
        try{
            Globals.ts.out(t1);
        }catch(Throwable err){
            err.printStackTrace();
        }
    }


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
        ITuple actual_tuple;
        int stato_sistema = -1;
        while (true) {
            try {
                actual_tuple = Globals.ts.in(p);
                val_letto = Integer.parseInt(actual_tuple.get(1).toString());
                id_sensore = actual_tuple.get(0).toString().charAt(0);
                System.out.println(actual_tuple + "  " + actual_tuple.get(0) + "  " + actual_tuple.get(1));

                //implemento la logica di cambio di stato
                //se supera la soglia il sensore di sinistra
                if(val_letto > Globals.MAX_MEASURE_VALUE && id_sensore == Globals.ID_SX){
                    System.out.println("attento valore soglia sensore sx superata");
                    System.out.println("sensore : " + id_sensore);
                    stato_sistema = 1;
                }

                //se supera la soglia il sensore di destra
                if(val_letto > Globals.MAX_MEASURE_VALUE && id_sensore == Globals.ID_DX){
                    System.out.println("attento valore soglia sensore dx superata");
                    System.out.println("sensore : " + id_sensore);
                    stato_sistema = 2;
                }

                //se è inferiore alla soglia il sensore di sinistra
                if(val_letto < Globals.MAX_MEASURE_VALUE && id_sensore == Globals.ID_SX){
                    System.out.println("sensore sx okay");
                    stato_sistema = 3;
                }

                //se è inferiore alla soglia il sensore di destra
                if(val_letto < Globals.MAX_MEASURE_VALUE && id_sensore == Globals.ID_DX){
                    System.out.println("sensore dx okay");
                    stato_sistema = 4;
                }

                switch (stato_sistema){
                    case 1:
                        //pubblico la tupla per far chiudere il led a sx ed il servomotore
                        scriviTupla(Globals.ID_SX,Globals.CLOSE);
                        break;
                    case 2:
                        //pubblico la tupla per far chiudere il led a dx ed il servomotore
                        scriviTupla(Globals.ID_DX,Globals.CLOSE);
                        break;
                    case 3:
                        //pubblico la tupla per far aprire il led a sx ed il servomotore
                        scriviTupla(Globals.ID_SX,Globals.OPEN);
                        break;
                    case 4:
                        //pubblico la tupla per far aprire il led a dx ed il servomotore
                        scriviTupla(Globals.ID_DX,Globals.OPEN);
                        break;
                }

            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
    }
}