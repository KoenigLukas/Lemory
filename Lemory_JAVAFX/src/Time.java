import java.io.*;

public class Time {

    public void printTime(){
        try (BufferedWriter bw= new BufferedWriter(new FileWriter("start.time"))){
            bw.write(""+System.nanoTime());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int readTime(){

        long differenz;
        long end = System.nanoTime();
        long start=0;


        try (BufferedReader br= new BufferedReader(new FileReader("start.time"))){
            start=Long.valueOf(br.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        differenz =end-start;
        differenz = differenz / 1000000000;
        return (int)differenz;
    }

}
