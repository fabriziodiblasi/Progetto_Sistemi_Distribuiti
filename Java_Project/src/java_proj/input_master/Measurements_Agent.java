package java_proj.input_master;

import java_proj.Globals;

import java.io.InputStream;
import lights.*;
import lights.interfaces.*;


public class Measurements_Agent extends Thread {

    private int getIntMeasureValue(String s){
        int val=-1;
        String str_num="";
        s=s.trim();
        for(int i = 0; i< s.length(); i++){
            //if(s.charAt(i)!=Globals.ID_DX && s.charAt(i)!=Globals.ID_SX && s.charAt(i)!=Globals.END_MSG){
            if(s.charAt(i)>='0' && s.charAt(i)<='9'){
                str_num += s.charAt(i);
            }
        }

        if(str_num != "")  val = Integer.parseInt(str_num);
        //if (val >=1000) val =-1;
        return val;
    }

    public void run(){
        System.out.println("Measurements thread is running...");
        String measure_str="";
        char  char_read = ' ';
        char id_sensore = ' ';
        int measure;
        //int result;
        try {
            while (true)
            {
                //while (Globals.sp.bytesAvailable() < 5)
                while (Globals.sp_input.bytesAvailable() == 0)
                    Thread.sleep(20);
                InputStream in = Globals.sp_input.getInputStream();
                char_read = (char) in.read();

                if(char_read == Globals.ID_DX || char_read == Globals.ID_SX){
                    id_sensore = char_read;
                }

                if(char_read >= '0' && char_read <= '9'){
                    measure_str = measure_str + char_read;
                }

                if(char_read == Globals.END_MSG){
                    measure = getIntMeasureValue(measure_str.trim());
                    measure_str = "";

                    //imposto i campi della tupla
                    if(measure != -1) {
                        IField f1 = new Field().setValue(id_sensore);
                        IField f2 = new Field().setValue(measure);
                        //creo la tupla
                        ITuple t1 = new Tuple();
                        //inserisco i campi nella tupla
                        t1.add(f1);
                        t1.add(f2);
                        //pubblico la tupla
                        System.out.println("pubblico la tupla : <" + id_sensore + " , " + measure + ">");
                        try {
                            Globals.ts.out(t1);
                        } catch (Throwable err) {
                            err.printStackTrace();
                        }
                    }
                }

                in.close();

            }
        } catch (Exception e) { e.printStackTrace(); }
        Globals.sp_input.closePort();
    }
}
