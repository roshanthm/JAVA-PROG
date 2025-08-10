/*Search an element using binarysearch */
import java.util.Scanner;
class BinarySearch
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int n,u,k,l=1,mid=0,temp=0,i,j,flag=0;
        int ar[]=new int[50];
        System.out.println("Enter the no. of elements :");
        n=sc.nextInt();
        u=n;
        System.out.println("Enter Elements of the array : ");
        for(i=1;i<=n;i++)
            ar[i]=sc.nextInt();

        for(i=1;i<=n;i++)
        {
            for(j=1;j<=n-i;j++)
            {
                if(ar[j]>ar[j+1])
                {
                    temp=ar[j];
                    ar[j]=ar[j+1];
                    ar[j+1]=temp;
                }
            }
        }

        System.out.println("Sorted Array : ");
        for(i=1;i<=n;i++)
            System.out.print(ar[i]+" ");
        System.out.println();

        System.out.print("Enter the element to search : "); 
        k=sc.nextInt();
        while(l<=u)
        {
            mid=(l+u)/2;
            if(k==ar[mid])
            {
                flag=1;
                break;
            }
            else if(k<ar[mid])
            {
                u=mid-1;
            }
            else
                l=mid+1;
        }
        if(flag==0)
            System.out.println("Element not found");
        else
            System.out.println("Element Found at index "+mid);
    }
}