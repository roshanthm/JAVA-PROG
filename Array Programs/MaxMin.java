/*wajp to find the largest nd smallest elements in an unsorted array */
import java.util.Scanner;
class MaxMin
{
    public static void main(String args[])
    {
        int i,max,min;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the no. of elements of the array");
        int n=sc.nextInt();
        int ar[]=new int[n];
        System.out.println("Enter the elements of the array:");
        for(i=0;i<n;i++)
         {
          ar[i]=sc.nextInt();
         }
	max=ar[0];
	min=ar[0];
        for(i=0;i<n;i++)
        {
            if(ar[i]>max)
                max=ar[i];
            else if(ar[i]<min)
                min=ar[i];
        }
        System.out.println("The largest is:"+max);
	System.out.println("The smallest is:"+min);
    }
}