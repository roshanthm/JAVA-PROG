import java.util.Scanner;
class transpose
{
public static void main(String args[])
{
int r,c,i,j;
Scanner sc=new Scanner(System.in);
System.out.println("Enter the no. of rows:");
r=sc.nextInt();
System.out.println("Enter the no. of columns:");
c=sc.nextInt();
int ar[][]=new int[r][c];
int tr[][]=new int[c][r];
System.out.println("Enter the elements:");
for(i=0;i<r;i++)
{
	for(j=0;j<c;j++)
	{
	ar[i][j]=sc.nextInt();
	}
}

for(i=0;i<r;i++)
{
	for(j=0;j<c;j++)
	{
	 tr[j][i]=ar[i][j];
	}
}
System.out.println("The original matrix is:");
for(i=0;i<r;i++)
{
	for(j=0;j<c;j++)
	{
		System.out.print(" "+ar[i][j]);
	}
	System.out.println();
}
System.out.println("The transpose matrix is:");
for(i=0;i<c;i++)
{
	for(j=0;j<r;j++)
	{
		System.out.print(" "+tr[i][j]);
	}
	System.out.println();
}
}
}