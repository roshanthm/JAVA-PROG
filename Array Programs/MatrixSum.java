import java.util.Scanner;
class MatrixSum
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int r,c,i,j;
        int MAT1[][]=new int[50][50];
        int MAT2[][]=new int[50][50];
        int MATSUM[][]=new int[50][50];
        System.out.println("Enter the no. of rows and columns : ");
        r=sc.nextInt();
        c=sc.nextInt();
        System.out.println("Enter the elements of Matrix 1 : ");
        for(i=0;i<r;i++)
        {
            for(j=0;j<c;j++)
            {
                MAT1[i][j]=sc.nextInt();
            }
        }
        System.out.println("Enter the elements of Matrix 2 : ");
        for(i=0;i<r;i++)
        {
            for(j=0;j<c;j++)
            {
                MAT2[i][j]=sc.nextInt();
            }
        }
        /*Matrix Addition */
        
        for(i=0;i<r;i++)
        {
            for(j=0;j<c;j++)
            {
                MATSUM[i][j]=MAT1[i][j]+MAT2[i][j];
            }
        }
        System.out.println("The Resultant Matrix is : ");
        for(i=0;i<r;i++)
        {
            for(j=0;j<c;j++)
            {
                System.out.print(MATSUM[i][j]+" ");
            }
            System.out.println();
        }

    }
}