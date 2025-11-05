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
        fr.close();
        br.close();
        fr = new FileReader("sample.txt");

        int c;
        int nc=0;
        while((c=fr.read())!=-1){
            nc+=1;
            System.out.println(c);
        }
         System.out.println(nc);
        br.close();
    }catch(Exception e){
        System.out.println("error"+e);
    }

    }
}
