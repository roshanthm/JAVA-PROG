import java.util.Scanner;
class Flip
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int r,c,i,j,k=0;
        int MAT[][]=new int[50][50];
        int NEWMAT[][]=new int[50][50];
        System.out.println("Enter the no. of rows and columns : ");
        r=sc.nextInt();
        c=sc.nextInt();
        System.out.println("Enter the elements of the Matrix : ");
        for(i=0;i<r;i++)
        {
            for(j=0;j<c;j++)
            {
                MAT[i][j]=sc.nextInt();
            }
        }
        System.out.println("The Matrix is : ");
        for(i=0;i<r;i++)
        {
            for(j=0;j<c;j++)
            {
                System.out.print(MAT[i][j]+" ");
            }
            System.out.println();
        }

        for(i=0;i<r;i++)
        {
            for(j=0;j<c;j++)
            {
                int temp=MAT[i][0];
                MAT[i][0]=MAT[i][c-1];
                MAT[i][c-1]=temp;
            }

        }
        for(i=0;i<r;i++)
        {
            for(j=0;j<c;j++)
            {
                int tem=MAT[0][j];
                MAT[0][j]=MAT[r-1][j];
                MAT[r-1][j]=tem;
            }

        }
        System.out.println("The Resultant Matrix is : ");
        for(i=0;i<r;i++)
        {
            for(j=0;j<c;j++)

            {
                System.out.print(MAT[i][j]+" ");
            }
            System.out.println();
        }
        
    }
}