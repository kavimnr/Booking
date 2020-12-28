import java.util.*;
class Screen
{
	
	Map<Integer,Show> shows=new HashMap<Integer,Show>();
	int screenNo;
	Show show1,show2;
	public Screen()
	{
		show1=new Show();
		show2=new Show();
		
		show1.showTime="4PM";
		show2.showTime="8PM";
		
		show1.showNo=1;
		show2.showNo=2;
		
		shows.put(1,show1);
		shows.put(2,show2);	
	}
}