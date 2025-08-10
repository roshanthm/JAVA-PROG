
import java.util.Scanner;
class Evensum
{
public static void main(String args[]) 
{
    int i,sum=0;
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter the no. of elements of the array");
    int n=sc.nextInt();
    int ar[]=new int[n];
    System.out.println("Enter the elements of the array:");
    for(i=0;i<n;i++)
    {
        ar[i]=sc.nextInt();
    }
   for(i=0;i<n;i++)
   {
    if(ar[i]%2==0)
      sum=sum+ar[i];
   } 
   System.out.println("The sum of even no. is:"+sum);
}
}
