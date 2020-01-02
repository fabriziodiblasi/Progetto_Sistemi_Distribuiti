package java_proj.input_measurements;

import java_proj.Globals;

import java.io.InputStream;
import lights.*;
import lights.interfaces.*;
import java.io.*;


public class Measurements_Agent extends Thread {
    private int measureIntValue(String s){
        int val=0;
        String str_num="";
        s=s.trim();
        for(int i = 0; i< s.length(); i++){
            if(s.charAt(i)!='D' && s.charAt(i)!='S' && s.charAt(i)!='_'){
                str_num += s.charAt(i);
            }
        }

        val = Integer.parseInt(str_num);
        if (val >=1000) val =-1;
        return val;
    }

    public void run(){
        System.out.println("thread is running...");
        String complete_str="";
        char  char_read;
        int result;
        try {
            while (true)
            {
                //while (Globals.sp.bytesAvailable() < 5)
                while (Globals.sp_input.bytesAvailable() == 0)
                    Thread.sleep(20);
                InputStream in = Globals.sp_input.getInputStream();
                char_read = (char) in.read();
                complete_str += char_read;
                //elimino gli spazi vuoti
                complete_str = complete_str.trim();
                /*
                * se la stringa letta inizia con i descrittori corretti, e l'ultimo carattere letto è quello di fine
                * messaggio, allora:
                * creo la tupla per la misurazione e la inserisco nello spazio di tuple.
                * */
                if (char_read == '_' && ( complete_str.charAt(0) == 'S' || complete_str.charAt(0) == 'D')){
                    /*
                    IField f1 = new Field().setValue("Paolo");
                    IField f2 = new Field().setValue(new Integer(10));
                    and then assembled in a tuple:
                    ITuple t1 = new Tuple();
                    t1.add(f1);
                    t1.add(f2);
                    * */
                    result = measureIntValue(complete_str);
                    if (result != -1) {
                        System.out.println(complete_str.charAt(0) + " " + result);
                        //imposto i campi della tupla
                        IField f1 = new Field().setValue("" + complete_str.charAt(0));
                        IField f2 = new Field().setValue(result);
                        //creo la tupla
                        ITuple t1 = new Tuple();
                        //inserisco i campi nella tupla
                        t1.add(f1);
                        t1.add(f2);
                        //pubblico la tupla
                        Globals.ts.outg(new ITuple[] = {t1, t2});

                    }
                    //System.out.println(complete_str.trim());
                    complete_str="";
                }
                in.close();

            }
        } catch (Exception e) { e.printStackTrace(); }
        Globals.sp_input.closePort();
    }
}
