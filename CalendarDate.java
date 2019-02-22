package CalendarNoteGUI;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class CalendarDate
{
	int CALENDAR_WIDTH = 7; int CALENDAR_HEIGHT = 6;
	int[][] calDates = new int[CALENDAR_HEIGHT][CALENDAR_WIDTH];
	int year, month, day, lastDate;
	final int[] lastDateOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; 
	Calendar today = Calendar.getInstance();
	
	Calendar cal;
	{
		this.year = today.get(1); this.month = today.get(2); this.day = today.get(5); initializeCalendarData(today);
	}
	
	private void initializeCalendarData(Calendar cal) {
		
		int calStartingPos = (cal.get(CALENDAR_WIDTH) + CALENDAR_WIDTH - cal.get(5) % CALENDAR_WIDTH) % CALENDAR_WIDTH;
		if (this.month == 1) {
			this.lastDate = (this.lastDateOfMonth[this.month] + checkLeapYear(this.year));
		}
		else
		{
			this.lastDate = this.lastDateOfMonth[this.month];
		}
		for (int i = 0; i < CALENDAR_HEIGHT; i++) {
			for (int j = 0; j < CALENDAR_WIDTH; j++) {
				this.calDates[i][j] = 0; }
		}
		
		int i = 0;
		int num = 1;
		
		for (int k = 0; i < CALENDAR_HEIGHT; i++) {
			if (i == 0) 
				k = calStartingPos; 
			else
				k = 0;
			for (int j = k; j < CALENDAR_WIDTH; j++) {
				if (num <= this.lastDate) 
					this.calDates[i][j] = (num++); 
			} 
		}
	}
	
	
	private int checkLeapYear(int year) {
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) 
			return 1; 
		return 0; 
	}
	
	
	public void moveMonth(int mon) {
		month += mon; 
		if (month > 11) {
			while (month > 11) {
				year++;
				month -= 12; 
			}
		}
		else if (month < 0) {
			while (month < 0) {
				year--;
				month += 12; 
				}
		}
		cal = new GregorianCalendar(year, month, day); 
		initializeCalendarData(cal);
	} 
}
