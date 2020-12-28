import java.util.*;
class BookingDetails
{
	LinkedList<Integer> seats=new LinkedList<Integer>();
	double amount=0,discount=0;
	int selectedScreen=0,selectedShow=0,id=0;
	public BookingDetails(int openion,int count,String coupen,int selectedScreen,int selectedShow,int id)
	{
		this.selectedScreen=selectedScreen;
		this.selectedShow=selectedShow;
		this.id=id;
		if(openion%2!=0)
		{
			amount=count*120;
			Show show=Multiplex.screens.get(selectedScreen).shows.get(selectedShow);
			int i=0;
			while(count>0)
			{
				while(!show.arr[i]&&i<5)
				{
					seats.add(i+1);
					show.arr[i]=true;
					show.firstPointer++;
					count--;
				}
				i++;
			}
			if(coupen.equals("0"))
			{
				discount=0;
			}
			else
			{
				String sub=coupen.substring(1);
				int num=Integer.parseInt(sub);
				amount-=discount;
				discount=(amount*num)/100;
			}
			amount-=discount;
		}
		else if(openion%2==0)
		{
			amount=count*100;
			Show show=Multiplex.screens.get(selectedScreen).shows.get(selectedShow);
			int availableSeatsRow2=InputValidator.getSecondRowCount(show);
			int availableSeatsRow3=InputValidator.getThirdRowCount(show);
			
			if(availableSeatsRow2>count)
			{
				int i=5;
				while(count>0)
				{
					while(!show.arr[i]&&i<10)
					{
						seats.add(i+1);
						show.arr[i]=true;
						show.secondPointer++;
						count--;
					}
					i++;
				}
			}
			else if(availableSeatsRow3>count)
			{
				int i=10;
				while(count>0)
				{
					while(!show.arr[i]&&i<15)
					{
						seats.add(i+1);
						show.arr[i]=true;
						show.thirdPointer++;
						count--;
					}
					i++;
				}
			}
			else if(count>availableSeatsRow2&&availableSeatsRow2!=0&&count!=3)
			{
				int i=5;
				count-=availableSeatsRow2;
				if(count==1&&(availableSeatsRow2+availableSeatsRow3)!=2)
				{
					count++;
					availableSeatsRow2--;
				}
				while(availableSeatsRow2>0)
				{
					while(!show.arr[i]&&i<10)
					{
						seats.add(i+1);
						show.arr[i]=true;
						show.secondPointer++;
						availableSeatsRow2--;
					}
					i++;
				}
				i=10;
				while(count>0)
				{
					while(i<15&&!show.arr[i])
					{
						seats.add(i+1);
						show.arr[i]=true;
						show.thirdPointer++;
						count--;
					}
					i++;
				}
			}
			else if(count>availableSeatsRow3&&availableSeatsRow3!=0&&count!=3)
			{
				count-=availableSeatsRow3;
				if(count==1&&(availableSeatsRow2+availableSeatsRow3)!=2)
				{
					count++;
					availableSeatsRow3--;
				}
				int i=10;
				while(availableSeatsRow3>0)
				{
					while(!show.arr[i]&&i<15)
					{
						seats.add(i+1);
						show.arr[i]=true;
						show.thirdPointer++;
						availableSeatsRow3--;
					}
					i++;
				}
				i=5;
				while(count>0)
				{
					while(!show.arr[i]&&i<10)
					{
						seats.add(i+1);
						show.arr[i]=true;
						show.secondPointer++;
						count--;
					}
					i++;
				}
			}
			else if(count==3&&(availableSeatsRow2>=3||availableSeatsRow3>=3))
			{
				int i;
				if(availableSeatsRow2>=3)
				{
					i=5;
					while(count>0)
					{
						while(!show.arr[i]&&i<10)
						{
							seats.add(i+1);
							show.arr[i]=true;
							show.secondPointer++;
							count--;
						}
						i++;
					}
				}
				else
				{
					i=10;
					while(count>0)
					{
						while(!show.arr[i]&&i<15)
						{
							seats.add(i+1);
							show.arr[i]=true;
							show.thirdPointer++;
							count--;
						}
						i++;
					}
				}
				
			}
			else
			{
				int i=5;
				while(count>0)
				{
					if(show.secondPointer<=9)
					{
						if(!show.arr[i]&&i<10)
						{
							seats.add(i+1);
							show.arr[i]=true;
							show.secondPointer++;
							count--;
						}
					}
					else
					{
						if(!show.arr[i]&&i<15)
						{
							seats.add(i+1);
							show.arr[i]=true;
							show.thirdPointer++;
							count--;
						}
					}
					i++;
				}
			}
			if(coupen.equals("0"))
			{
				discount=0;
			}
			else
			{
				String sub=coupen.substring(1);
				int num=Integer.parseInt(sub);
				amount-=discount;
				discount=(amount*num)/100;
			}
			amount-=discount;
		}
	}
}