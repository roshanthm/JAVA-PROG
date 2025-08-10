import java.util.Scanner;
class MatrixMul
{
	public static void main(String args[])
	{ 
	  int i,j,sum=0,k=0;
	  Scanner sc=new Scanner(System.in);
	  System.out.println("Enter the no. of rows and columns of the 1st matrix:");
	  int r1=sc.nextInt();
	  int c1=sc.nextInt();
	  System.out.println("Enter the no. of rows and columns of the 2nd matrix:");
	  int r2=sc.nextInt();
	  int c2=sc.nextInt();
	  int ar1[][]=new int[r1][c1];
	  int ar2[][]=new int[r2][c2];
	  int mul[][]=new int[r1][c2];
	  if(r2!=c1)
	  {
		  System.out.println("Matrix Multiplication not possible:");
	  }
	  else
	  {
		System.out.println("Enter the elements of Matrix 1:");
		for(i=0;i<r1;i++)
		{
			for(j=0;j<c1;j++)
			{
				ar1[i][j]=sc.nextInt();
			}
		}
		System.out.println("Enter the elements of Matrix 2:");
		for(i=0;i<r2;i++)
		{
			for(j=0;j<c2;j++)
			{
				ar2[i][j]=sc.nextInt();
			}
		}
		System.out.println("The Matrix 1:");
		for(i=0;i<r1;i++)
		{
			for(j=0;j<c1;j++)
			{
				System.out.print(ar1[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("The Matrix 2:");
		for(i=0;i<r2;i++)
		{
			for(j=0;j<c2;j++)
			{
				System.out.print(ar2[i][j]+" ");
			}
			System.out.println();
		}		
		//Matrix multiplication
		for(i=0;i<r1;i++)
		{	
			for(j=0;j<c2;j++)
			{
				for(k=0;k<r2;k++)
				{
				 mul[i][j]+=ar1[i][k]*ar2[k][j];
				}
			}
		}
		System.out.println("The Result is:");
		for(i=0;i<r1;i++)
		{
			for(j=0;j<c2;j++)
			{
				System.out.print(mul[i][j]+" ");
			}
			System.out.println();
		}
	  }
	}
}