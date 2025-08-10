
import java.util.Scanner;
class RemoveDup
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int n,i,j,count=0;
        int ar[]=new int[20];
        System.out.print("Enter the no. of elements : ");
        n=sc.nextInt();
        System.out.println("Enter the elements : ");
        for(i=0;i<n;i++)
        {
            ar[i]=sc.nextInt();
        }
        System.out.println("New Array after removing duplicate : ");
        for(i=0;i<n;i++)
        {
            if (ar[i] != 0) 
            {
            count=0;
            for(j=0;j<i;j++)
            {
                if(ar[i]==ar[j])
                {
                 count=1;
                 break;
                }
                  
            }
            if(count==0)
              System.out.print(ar[i]+" ");
            }
        }
    }
}
       