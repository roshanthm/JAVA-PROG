/*WAJP to print the common elements of two arrays*/
import java.util.Scanner;
class Common
{
public static void main(String args[])
 {
   int i,j;
   Scanner sc=new Scanner(System.in);
   System.out.println("Enter the no. of elements of the array:");
   int n=sc.nextInt();
   int ar1[]=new int[n];
   int ar2[]=new int[n];
   System.out.println("Enter the elements of the 1st array:");
   for(i=0;i<n;i++)
      {
        ar1[i]=sc.nextInt();
      }
   System.out.println("Enter the elements of the 2nd array:");
   for(i=0;i<n;i++)
      {
        ar2[i]=sc.nextInt();
      }
   System.out.println("The Common Elements are:");
   for(i=0;i<n;i++)
      {
        for(j=0;j<n;j++)
           {
            if(ar1[i]==ar2[j])
              {
               System.out.print(ar1[i]+" ");
               break;
	           }	
           }
       
      }
   
   }
}
