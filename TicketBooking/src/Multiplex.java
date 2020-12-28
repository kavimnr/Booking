import java.util.*;
class Multiplex
{
	public static Map<Integer,Screen> screens=new HashMap<Integer,Screen>();
	static int total=0;
	public static Map<Integer,BookingDetails> totalBookings=new HashMap<Integer,BookingDetails>();
	public static Map<Integer,BookingDetails> totalCancelledBookings=new HashMap<Integer,BookingDetails>();
	static int bookId=1;
	void show()
	{
		int sno=1;
		for(Map.Entry availableScreens:screens.entrySet())
		{
			int screenNo=(Integer)availableScreens.getKey();
			Screen screen=(Screen)availableScreens.getValue();
			for(Map.Entry availableShows:screen.shows.entrySet())
			{
				int showNo=(Integer)availableShows.getKey();
				Show show=(Show)availableShows.getValue();
				System.out.println((sno++)+" : Screen "+screenNo+"  :  "+show.showTime+ " : " +show.first+"  (First Class)");
				System.out.println((sno++)+" : Screen "+screenNo+"  :  "+show.showTime+ " : " +show.second+" (Second Class)");
			}
		}
		sno=1;
	}
}