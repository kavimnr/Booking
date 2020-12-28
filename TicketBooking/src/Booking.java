import java.util.*;
public class Booking
{
	public static void main(String[] args)
	{
		int choice=0;
		Scanner sc=new Scanner(System.in);
		Actions actions=new Actions();
		Multiplex.screens.put(1,new Screen());
		Multiplex.screens.put(2,new Screen());
		try
		{
			char c='Y';
			while(c=='Y'||c=='y')
			{
				System.out.println("Enter the below choice...\n");
				System.out.println("\t1.Booking\n\t2.Cancel\n\t3.Booking summary\n\t4.Overall summary");
				choice=sc.nextInt();
				switch(choice)
				{
					case 1:
							actions.booking();
							break;
					case 2:
							actions.cancellation();
							break;
					case 3:
							actions.summary();
							break;
					case 4:
							actions.overallSummary();
							break;
					default:
							System.out.println("\nInvalid selection....Please select correct choice...\n");
							continue;
				
			}
			System.out.println("Do you want to continue (Y/N)");
			c=sc.next().charAt(0);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception occurred.Please Try again with valid inputs....");
		}
	}
}