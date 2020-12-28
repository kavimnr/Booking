import java.util.*;
class InputValidator
{
	static boolean openionValidator(int openion)
	{
		if(openion<=8&&openion>=1)
			return true;
		return false;
	}
	static boolean couponValidator(String coupon)
	{
		if(coupon.length()==1)
		{
			if(coupon.equals("0"))
				return true;
		}
		else if(coupon.charAt(0)=='d'||coupon.charAt(0)=='D')
		{
			int n=Integer.parseInt(coupon.substring(1));
			if(n==10||n==20||n==30||n==40||n==50)
				return true;
		}
		return false;
	}
	static int selectScreen(int openion)
	{
		int selectedScreen=0;
		if(openion<=4&&openion>=1)
		{
			selectedScreen=1;
		}
		else if(openion<=8&&openion>=4)
		{
			selectedScreen=2;
		}
		return selectedScreen;
	}
	static int selectShow(int openion)
	{
		int selectedShow=0;
		if(openion<=4&&openion>=1)
		{
			if(openion==1||openion==2)
				selectedShow=1;
			else
				selectedShow=2;
		}
		else if(openion<=8&&openion>=4)
		{
			if(openion==5||openion==6)
				selectedShow=1;
			else
				selectedShow=2;
		}
		return selectedShow;
	}
	
	static int getSecondRowCount(Show show)
	{
		int count=0;
		for(int i=5;i<10;i++)
		{
			if(!show.arr[i])
				count++;
		}
		return count;
	}
	static int getThirdRowCount(Show show)
	{
		int count=0;
		for(int i=10;i<15;i++)
		{
			if(!show.arr[i])
				count++;
		}
		return count;
	}
}