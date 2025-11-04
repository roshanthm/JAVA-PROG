import java.io.*;
public class DisplayFileWithLineNumbers {
    public static void main(String[] args) {
        try{
        FileReader fr = new FileReader("sample.txt");
        BufferedReader br = new BufferedReader(fr);

        int num=1;
        String line;

        while((line=br.readLine())!=null){
            System.out.println(num+"  "+line);
            num+=1;
        }
        br.close();
    }catch(Exception e){
        System.out.println("error"+e);
    }

    }
}
