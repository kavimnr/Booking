import java.util.*;
class Show
{
	
	Map<Integer,BookingDetails> bookings=new HashMap<Integer,BookingDetails>();
	Map<Integer,BookingDetails> cancelledBookings=new HashMap<Integer,BookingDetails>();
	boolean arr[]=new boolean[15];
	double totalIncome=0;
	int showNo;
	int first,second;
	int firstPointer=0;
	int secondPointer=5;
	int thirdPointer=10;
	//Changed
	int id=0;
	String showTime="";
	public Show()
	{
		first=5;
		second=10;
		Arrays.fill(arr,false);
	}
}