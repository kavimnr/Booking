import java.util.*;
class Actions
{
	public void booking()
	{
		int count,openion,selectedScreen=0,selectedShow=0;
		double discount=0,amount=0;
		String coupon;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of tickets....");
		count=sc.nextInt();
		while(count<=0||count>10)
		{
			System.out.println("Please Enter correct number of tickets....");
			count=sc.nextInt();
		}
		new Multiplex().show();
		boolean flag=false;
		while(flag==false)
		{
			System.out.println("Enter your Option (1/2/3/4/5/6/7/8)");
			openion=sc.nextInt();
			if(!InputValidator.openionValidator(openion))
			{
				System.out.println("Invalid opinion .. Please select within the list...");
				continue;
			}
			selectedScreen=InputValidator.selectScreen(openion);
			selectedShow=InputValidator.selectShow(openion);
			System.out.println("Enter Coupon Code if any Else 0 ?");
			coupon=sc.next();
			while(!InputValidator.couponValidator(coupon))
			{
				System.out.println("Please Enter correct Coupon Code if any Else 0 ?");
				coupon=sc.next();
			}
			Show show=Multiplex.screens.get(selectedScreen).shows.get(selectedShow);
			if(show!=null)
			{
				if((openion==1||openion==3||openion==5||openion==7)&&count<=show.first)
				{
					show.first-=count;
					BookingDetails b=new BookingDetails(openion,count,coupon,selectedScreen,selectedShow,++show.id);
					Multiplex.totalBookings.put(Multiplex.bookId,b);
					show.bookings.put(show.id,b);
					flag=true;
				}
				else if((openion==2||openion==4||openion==6||openion==8)&&count<=show.second)
				{
					show.second-=count;
					BookingDetails b=new BookingDetails(openion,count,coupon,selectedScreen,selectedShow,++show.id);
					Multiplex.totalBookings.put(Multiplex.bookId,b);
					show.bookings.put(show.id,b);
					flag=true;
				}
				else 
				{
					System.out.println("Sorry tickets not available....Please select other options....\n");
					//new Multiplex().show();
					continue;
				}
			}
			if(flag)
			{
				System.out.println("Booking no     : "+Multiplex.bookId);
				System.out.print("Seat Alloted   : ");
				BookingDetails bookingDetails=Multiplex.totalBookings.get(Multiplex.bookId);
				ListIterator i=Multiplex.totalBookings.get(Multiplex.bookId).seats.listIterator();
				while(i.hasNext())
				{
					System.out.print(i.next()+" ");
				}
				System.out.println();
				System.out.println("Screen "+selectedScreen+"       : "+show.showTime);
				System.out.printf("Cost price     : %.2f\n",(bookingDetails.amount+bookingDetails.discount));
				if(coupon.equals("0"))
				{
					System.out.print("Discount(0%)   : ");
					System.out.printf("%.2f\n",bookingDetails.discount);
				}
				else
				{
					String percentage=coupon.substring(1);
					System.out.print("Discount("+percentage+"%)  : ");
					System.out.printf("%.2f\n",bookingDetails.discount);
				}
				System.out.printf("Discount       : %.2f\n",bookingDetails.discount);
				System.out.printf("Total price    : %.2f\n",bookingDetails.amount);
				show.totalIncome+=bookingDetails.amount;
				Multiplex.bookId++;
			}
			if(flag)
				break;
		}
	}
	
	void cancellation()
	{
		Scanner sc=new Scanner(System.in);
		int screenNo=0,showNo=0;
		System.out.println("Enter booking no....");
		int bookId=sc.nextInt();
		if(Multiplex.totalBookings.containsKey(bookId))
		{
			BookingDetails totalBookings=Multiplex.totalBookings.get(bookId);
			screenNo=totalBookings.selectedScreen;
			showNo=totalBookings.selectedShow;
			Show show=Multiplex.screens.get(screenNo).shows.get(showNo);
			int no=totalBookings.id;
			double amount=show.bookings.get(no).amount;
			double deduct=amount/10;
			System.out.printf("Amount deducted : %.2f\n",deduct);
			System.out.printf("Refunded amount : %.2f\n",(amount-deduct));
			Multiplex.total+=deduct;
			BookingDetails b=show.bookings.get(no);
			Iterator i=b.seats.iterator();
			while(i.hasNext())
			{
				int p=(Integer)i.next();
				if(p<=5&&p>=1)
				{
					show.first++;
					show.firstPointer--;
				}
				else if((p<=10&&p>=5))
				{
					show.second++;
					show.secondPointer--;
				}
				else
				{
					show.second++;
					show.thirdPointer--;
				}
				show.arr[p-1]=false;
			}
			b.amount=deduct;
			totalBookings.amount=deduct;
			show.totalIncome-=(amount-deduct);
			show.cancelledBookings.put(no,b);
			show.bookings.remove(no);
			Multiplex.totalCancelledBookings.put(bookId,b);
			Multiplex.totalBookings.remove(bookId);
			System.out.println("\nBooking cancelled successfully......\n");
		}
		else
		{
			System.out.println("Invalid selection.... Returning to main menu..");
		}
	}
	void summary()
	{
		Scanner sc=new Scanner(System.in);
		int screenNo=0,showNo=0,no;
		boolean flag=false;
		System.out.println("Enter booking no....");
		int bookId=sc.nextInt();
		if(Multiplex.totalBookings.containsKey(bookId))
		{
			BookingDetails bookings=Multiplex.totalBookings.get(bookId);
			screenNo=bookings.selectedScreen;
			showNo=bookings.selectedShow;
			Show show=Multiplex.screens.get(screenNo).shows.get(showNo);
			no=bookings.id;
			BookingDetails b=show.bookings.get(no);
			Iterator i=b.seats.iterator();
			System.out.println("Screen "+screenNo+"       : "+show.showTime);
			System.out.print("Seats Alloted  : ");
			while(i.hasNext())
			{
				System.out.print(i.next()+" ");
			}
			System.out.println();
			System.out.printf("Cost price     : %.2f\n",(b.amount+b.discount));			
			System.out.printf("Discount       : %.2f\n",b.discount);
			System.out.printf("Total Price    : %.2f\n",b.amount);
			flag=true;
		}
		else if(Multiplex.totalCancelledBookings.containsKey(bookId))
		{
			BookingDetails totalBookings=Multiplex.totalCancelledBookings.get(bookId);
			screenNo=totalBookings.selectedScreen;
			showNo=totalBookings.selectedShow;
			Show show=Multiplex.screens.get(screenNo).shows.get(showNo);
			no=totalBookings.id;
			if(show.cancelledBookings.containsKey(no))
			{
				BookingDetails b=show.cancelledBookings.get(no);
				System.out.println("Screen "+screenNo+"       : "+show.showTime);
				System.out.println("Seats          : Cancelled");
				System.out.printf("Cancellation charge : %.2f\n",b.amount);
			}
			else
			{
				System.out.println("Invalid selection.... Returning to main menu..");
			}
		}
		else
		{
			System.out.println("Invalid selection.... Returning to main menu..");
		}
	}
	public void overallSummary()
	{
		for(Map.Entry multiplex:Multiplex.screens.entrySet())
		{
			
			int screenNo=(Integer)multiplex.getKey();
			Screen screen=(Screen)multiplex.getValue();
			for(Map.Entry shows:screen.shows.entrySet())
			{
				int showNo=(Integer)shows.getKey();
				Show show=(Show)shows.getValue();
				System.out.println("Screen "+screenNo+"       : "+show.showTime);
				System.out.print("Seats Available: ");
				for(int i=0;i<show.arr.length;i++)
				{
					if(!show.arr[i])
						System.out.print(i+1+" ");
				}
				System.out.println();
				System.out.printf("Total income   : %.2f\n",show.totalIncome);
				System.out.println();
			}
			System.out.println();
		}
	}
}