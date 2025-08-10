/*to sort an array of integers in decending order*/
import java.util.Scanner;
class Sort
{
 public static void main(String args[])
  {
   Scanner sc=new Scanner(System.in);
   int i,j,temp;
   System.out.println("Enter the no. of elements of the array:");
   int n=sc.nextInt();
   int ar[]=new int[n];
   System.out.println("Enter the elements:");
   for(i=0;i<n;i++)
       {
	      ar[i]=sc.nextInt();      
       }
   System.out.println("The sort array in decending order is:");
   for(i=0;i<n-1;i++)
       {
         for(j=0;j<n-1-i;j++)
	        {
	          if(ar[j]<ar[j+1])
	            {
	              temp=ar[j];
	              ar[j]=ar[j+1];
                ar[j+1]=temp;
              }
	        }
       }
   for(i=0;i<n;i++)
       {
        System.out.print(" "+ar[i]);
       }


  }
}

