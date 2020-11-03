package sample;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class Logs {

    FileWriter fstream;
    BufferedWriter out;


    public void write(String text)  {
        try{
            fstream = new FileWriter("log.txt",true);
            out = new BufferedWriter(fstream);
            out.write(text + " -- "+ new Date() +"\n");
            out.close();
        }catch (Exception e){
            System.err.println("Error while writing to file: " +
                    e.getMessage());
        }
    }
    public void getAllNames() throws IOException{
        FileInputStream fistram = new FileInputStream("log.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fistram));
        String lines;
        while((lines=br.readLine())!=null)
            System.out.println(lines);


    }

}
