package util;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Title:        Universal Date object.
 * Description:  An object to encapsulate all date functions ever known to mankind!
 *               If what you want ain't in here ... add it!
 *               When dealing with parts of a date or time, the following tokens
 *               should be used.
 *
 *      Symbol   Meaning                 Presentation        Example
 *      ------   -------                 ------------        -------
 *      G        era designator          (Text)              AD
 *      y        year                    (Number)            1996
 *      M        month in year           (Text & Number)     July & 07
 *      d        day in month            (Number)            10
 *      h        hour in am/pm (1~12)    (Number)            12
 *      H        hour in day (0~23)      (Number)            0
 *      m        minute in hour          (Number)            30
 *      s        second in minute        (Number)            55
 *      S        millisecond             (Number)            978
 *      E        day in week             (Text)              Tuesday
 *      D        day in year             (Number)            189
 *      F        day of week in month    (Number)            2 (2nd Wed in July)
 *      w        week in year            (Number)            27
 *      W        week in month           (Number)            2
 *      a        am/pm marker            (Text)              PM
 *      k        hour in day (1~24)      (Number)            24
 *      K        hour in am/pm (0~11)    (Number)            0
 *      z        time zone               (Text)              Pacific Standard Time
 *      '        escape for text         (Delimiter)
 *      ''       single quote            (Literal)           '
 *
 * @author dotJ Software
 * @version 1.0
 */

public class UDate implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Some standard formats
	public static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
	public static final String DATESTAMP = "yyyy-MM-dd";
	public static final String TIME = "HH:mm:ss";


	//*******************************************************************************************
	//    Attributo aggiunto da Gaetanino Paolone Marzo 2006
	//*******************************************************************************************    
	public static String formatoData = "dd/MM/yyyy";



	// adjust() method units

	public static final short UNIT_DAYS = 1;

	public static final short UNIT_MONTHS = 2;

	public static final short UNIT_YEARS = 3;

	public static final short UNIT_HOURS = 4;

	public static final short UNIT_MINUTES = 5;

	public static final short UNIT_SECONDS = 6;

	public static final short UNIT_MILLISECONDS = 7;


	protected GregorianCalendar cal = null;

	protected void initialize(GregorianCalendar inCal) {
		cal = inCal;
	}

	/**
	 *  Construct based on the current date and time.
	 */
	public UDate() {
		cal = new GregorianCalendar();
		this.initialize(cal);
	}

	/**

	 * Construct from a long value. The long value is the number of milliseconds

	 * since January 1, 1970 00:00:00 GMT.

	 * @param longValue Number of milliseconds since January 1, 1970 00:00:00 GMT

	 * @return new UDate initialized to the specified time, <code>null</code> if longValue < 0

	 */

	public static UDate newInstance(long longValue) {

		if (longValue < 0) {

			return null;

		} else {

			Date dt = new Date(longValue);

			return UDate.newInstance(dt);

		}

	}



	/**
	 * Construct from another UDate object.

	 * @param date  Another UDate object

	 * @return Copy of the UDate object.
	 */
	public static UDate newInstance(UDate date) {
		if (date == null) {
			return null;
		} else {
			UDate newDate = new UDate();
			newDate.initialize(date.cal);
			return newDate;
		}
	}

	/**
	 * Construct from a java.util.Date object.  This includes any subclasses as
	 * well, such as java.sql.Date and java.sql.Timestamp.

	 * @param inDate    Date as a java.util.Date or java.sql.Date

	 * @return New UDate object set to the specified date and time.

	 */
	public static UDate newInstance(java.util.Date inDate) {
		if (inDate == null) {
			return null;
		} else {
			UDate newDate = new UDate();
			newDate.cal.setTime(inDate);
			newDate.initialize(newDate.cal);
			return newDate;
		}
	}

	/**
	 * Construct based on a specified format and value.  See class documentation for valid format parts.

	 * @param dateFormat    Format of specified date string (e.g. MM/dd/yyyy)

	 * @param inDate    Date as a string.  Should match the specified date format.

	 * @throws ParseException
	 */
	public static UDate newInstance(String inDate) throws ParseException {
		if (inDate == null)
			return null;
		else if (inDate.trim().equals(""))
			return null;
		else {
			SimpleDateFormat sdf = new SimpleDateFormat(formatoData);
			sdf.setLenient(false);
			java.util.Date dteUtilDate = sdf.parse(inDate.trim());
			return UDate.newInstance(dteUtilDate);
		}
	}

	/**

	 * Get the month corresponding to this date.

	 * @return 1 - 12

	 */

	public int getMonth() { return cal.get(MONTH) + 1; }


	/**

	 * Get the day of the month for this date.

	 * @return 1 - 31

	 */

	public int getDay() { return cal.get(DAY_OF_MONTH); }


	/**

	 * Get the year for this date.

	 * @return Year (with century)

	 */

	public int getYear() { return cal.get(YEAR); }


	/**

	 * Get the hour for this date/time.

	 * @return 0 - 23

	 */

	public int getHour() { return cal.get(HOUR); }


	/**

	 * Get the minute for this date/time.

	 * @return 0 - 59

	 */

	public int getMinute() { return cal.get(MINUTE); }


	/**

	 * Get the minute for this date/time.

	 * @return 0 - 59

	 */

	public int getSecond() { return cal.get(SECOND); }


	/**

	 * Get the minute for this date/time.

	 * @return 0 - 999

	 */

	public int getMillisecond() { return cal.get(MILLISECOND); }

	/**
	 * Get the date in the default format (yyyy-MM-dd HH:mm:ss).
	 */
	public String toString() {
		return getDate();
	}

	/**

	 * Get the date as a Timestamp object.

	 * @return Date as a Timestamp object.

	 */

	public Timestamp getDateAsTimestamp() {

		java.util.Date dt = cal.getTime();

		Timestamp ts = new Timestamp(dt.getTime());

		return ts;

	}



	/**

	 * Get the date as a java.sql.Date object.<br><br>

	 * <b>Note that <code>java.sql.Date</code> can be used wherever

	 * <code>java.util.Date</code> can be used.</b>

	 * @return Date as a Date object.

	 */

	public java.sql.Date getDateAsDate() {

		java.sql.Date dt = new java.sql.Date(cal.getTime().getTime());

		return dt;

	}



	/**
	 * Get the date in the default format (yyyy-MM-dd HH:mm:ss).
	 * @return Date as a string.

	 */
	public String getDate() {
		return getDate(TIMESTAMP);
	}

	/**
	 * Get the date in the specified format.
	 * @param format    Date format (e.g. MM/dd/yyyy)

	 * @return Date as a string.

	 */
	public String getDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	/**
	 * Get the date in the default format (yyyy-MM-dd HH:mm:ss).
	 * @return Date as a string or null if the input date is null.

	 */
	public static String getDateForUIcontrolla(UDate inDate) {
		return (inDate == null) ? "" : inDate.getDate();
	}

	/**
	 * Get the date in the specified format.
	 * Return an empty string if the object is null.

	 * @param inDate    Date to return for UI.
	 * @param format    Date format (e.g. MM/dd/yyyy)

	 * @return Date as a string or null if the input date is null.

	 */
	public static String getDateForUI(UDate inDate) {
		return (inDate == null) ? "" : inDate.getDate(formatoData);
	}

	/**
	 * Get the date in the default format (yyyy-MM-dd HH:mm:ss).
	 * @return Date as a string wrapped in single quotes.

	 */
	public String getDateForDB() {
		return "'" + this.getDate() + "'";
	}

	/**
	 * Get the date in the default format (yyyy-MM-dd HH:mm:ss).
	 * @param inDate    Date to return for the database     */
	public static String getDateForDB(UDate inDate) {
		return (inDate == null) ? "null" : "'" + inDate.getDate() + "'";
	}

	/**
	 * Get the date in the specified format.
	 * @param format    Date format (e.g. MM/dd/yyyy)

	 * @return Date as a string wrapped in single quotes.

	 */
	public String getDateForDB(String format) {
		return "'" + this.getDate(format) + "'";
	}

	/**
	 * Get the date in the specified format.
	 * @param inDate    Date to return for the database.

	 * @param format    Date format (e.g. MM/dd/yyyy)

c     */
	public static String getDateForDB(UDate inDate, String format) {
		return (inDate == null) ? "null" : "'" + inDate.getDate(format) + "'";
	}

	/**
	 * Get the time in the default format (HH:mm:ss).

	 * @return Time as a string.
	 */
	public String getTime() {
		return getTime(TIME);
	}

	/**
	 * Get the time in the specified format.
	 * @param format    Time format (e.g. HH:mm:ss)

	 * @return Time as a string in the given format.

	 */
	public String getTime(String format) {
		return getDate(format);
	}

	/**
	 * Get the time in the default format (HH:mm:ss).
	 * @param inDate    Date of string to return.

	 * @return An empty string if the object is null, otherwise the time as a

	 * string in the default format (HH:mm:ss)

	 */
	public static String getTimeForUI(UDate inDate) {
		return (inDate == null) ? "" : inDate.getTime();
	}

	/**
	 * Get the time in the default format (HH:mm:ss).
	 * @return The time wrapped in single quotes.
	 */
	public String getTimeForDB() {
		return "'" + this.getTime() + "'";
	}

	/**
	 * Get the time in the default format (HH:mm:ss).
	 * @param inDate    Date of string to return.

	 * @return An empty string if the object is null, otherwise the time as a

	 * string in the default format (HH:mm:ss) wrapped in single quotes.

	 */
	public static String getTimeForDB(UDate inDate) {
		return (inDate == null) ? "null" : "'" + inDate.getTime() + "'";
	}

	/**
	 * Get the time in the specified format.
	 * Return an empty string if the object is null.
	 * @param inDate    Date of string to return.

	 * @param format    Time format (e.g. hh:mm:ss)

	 * @return An empty string if the object is null, otherwise the time as a

	 * string in the specified format

	 */
	public static String getTimeForUI(UDate inDate, String format) {
		return (inDate == null) ? "" : inDate.getTime(format);
	}

	/**
	 * Get the time in the specified format.
	 * Return the string 'null' if the object is null.
	 * @param inDate    Date of string to return.

	 * @param format    Time format (e.g. hh:mm:ss)

	 * @return Date as a string wrapped in single quotes or "null" if input date is null.

	 */
	public static String getTimeForDB(UDate inDate, String format) {
		return (inDate == null) ? "null" : "'" + inDate.getTime(format) + "'";
	}

	/**
	 * Get the time in the specified format.
	 * Return the time wrapped in single quotes.
	 * @param format    Time format (e.g. hh:mm:ss)

	 * @return Date as a string wrapped in single quotes.

	 */
	public String getTimeForDB(String format) {
		return "'" + this.getTime(format) + "'";
	}


	/**

	 * Adjust the current date/time by the specified increment.

	 * @param unit

	 * <ul>

	 * <li>UNIT_DAYS</li>

	 * <li>UNIT_MONTHS</li>

	 * <li>UNIT_YEARS</li>

	 * <li>UNIT_HOURS</li>

	 * <li>UNIT_MINUTES</li>

	 * <li>UNIT_SECONDS</li>

	 * <li>UNIT_MILLISECONDS</li>

	 * </ul>

	 * <font size=5 color='red'><b>All other values are ignored.</b></font>

	 * @param amount    Number of units to adjust by.  Can be negative or positive.

	 * @return Nothing.

	 */

	public void adjust(short unit, int amount) {

		if (unit == UNIT_DAYS) {

			cal.add(Calendar.DAY_OF_YEAR, amount);

		} else if (unit == UNIT_MONTHS) {

			cal.add(Calendar.MONTH, amount);

		} else if (unit == UNIT_YEARS) {

			cal.add(Calendar.YEAR, amount);

		} else if (unit == UNIT_HOURS) {

			cal.add(Calendar.HOUR, amount);

		} else if (unit == UNIT_MINUTES) {

			cal.add(Calendar.MINUTE, amount);

		} else if (unit == UNIT_SECONDS) {

			cal.add(Calendar.SECOND, amount);

		} else if (unit == UNIT_MILLISECONDS) {

			cal.add(Calendar.MILLISECOND, amount);

		}

	}
	

	/**
	 *Converte una data di tipo java.util.Date in una data java.sql.Date
	 */
	public static java.sql.Date converti(Date data){
		

		return new java.sql.Date(data.getTime());
		
	}

	//*******************************************************************************************
	//    Inizio Sezione aggiunta da Maurizio Di Michele a Marzo 2006
	//*******************************************************************************************    

	/**
	 *Questo metodo recupera una data del DB;Riceve in ingresso una stringa 
	 *e restituisce una data.
	 */

	public static Date estraiData(String ingresso)throws java.text.ParseException
	{
		Date uscita;
		SimpleDateFormat sdf=new SimpleDateFormat(TIMESTAMP);

		if(ingresso == null)
		{
			uscita=null;
		}
		else
		{
			uscita=sdf.parse(ingresso);
		}
		return uscita;
	}

	/**
	 *Questo metodo consente l'acquisizione di una data inserita 
	 *da tastiera; riceve in ingresso una stringa nel formato indicato
	 *e restituisce una data; il metodo restituisce un messaggio di
	 *errore nel caso in cui la data inserita non e' corretta.
	 */

	public static Date ctrlData(String ingresso)throws java.text.ParseException
	{
		Date uscita=null;
		SimpleDateFormat sdf=new SimpleDateFormat(formatoData);
		sdf.setLenient(false);

		if(ingresso.equals(""))
		{
			uscita=null;
		}
		else
		{
			uscita=sdf.parse(ingresso);
		}

		return uscita;
	}

	/**
	 *Questo metodo consente l'inserimento di una data nel DB;
	 *Riceve in ingresso una data e restituisce una stringa.
	 */
	public static String inserisciStringa(Date ingresso)
	{
		String uscita;
		SimpleDateFormat sdf=new SimpleDateFormat(formatoData);
		if(ingresso == null)
		{
			uscita=null;
		}
		else
		{
			uscita=sdf.format(ingresso);
		}
		return uscita;
	}

	/**
	 *Questo metodo consente la stampa di una data;
	 *riceve in ingresso una data e restituisce una stringa.
	 */
	public static String ctrlStringa(Date ingresso)
	{
		String uscita;
		SimpleDateFormat sdf=new SimpleDateFormat(formatoData);
		if(ingresso==null)
		{
			uscita="";	
		}
		else
		{
			uscita=sdf.format(ingresso);
		}
		return uscita;
	}

	//*******************************************************************************************
	//    Fine Sezione aggiunta da Maurizio Di Michele a Marzo 2006
	//*******************************************************************************************    

	//*******************************************************************************************
	//    Inizio Sezione aggiunta da Maurizio Di Michele il 14 Marzo 2006
	//*******************************************************************************************    
	/**
	 *Questo metodo consente la formattazione della stringa
	 *inserita nel campo data; sono previste diverse modalit�
	 *di inserimento della data.
	 */
	public static String formatta(String strData)
	{

		String formatData = "";
		String dd = "";
		String MM = "";
		String yy = "";
		if(strData.equals(""))
		{
			formatData = "";
		}
		else if(strData.length()==6)
		{
			//se strData � d.M.yy, oppure d-M-yy, ecc.
			if(strData.substring(1,2).equals("/")|strData.substring(1,2).equals(".")|strData.substring(1,2).equals("-")|strData.substring(1,2).equals(" "))
			{
				dd = strData.substring(0,1);
				MM = strData.substring(2,3);
				yy = strData.substring(4,6);
				formatData = "0" + dd + "/0" + MM + "/" + yy;
			}
			//se strData � ddMMyy
			else
			{
				dd = strData.substring(0,2);
				MM = strData.substring(2,4);
				yy = strData.substring(4,6);
				formatData = dd + "/" + MM + "/" + yy;
			}
		}
		else if(strData.length()==7)
		{
			//se strData � d.MM.yy, oppure d-MM-yy, ecc
			if(strData.substring(1,2).equals("/")|strData.substring(1,2).equals(".")|strData.substring(1,2).equals("-")|strData.substring(1,2).equals(" "))
			{
				dd = strData.substring(0,1);
				MM = strData.substring(2,4);
				yy = strData.substring(5,7);
				formatData = "0" + dd + "/" + MM + "/" + yy;
			}
			//se strData � dd.M.yy, oppure dd-M-yy, ecc
			else
			{
				dd = strData.substring(0,2);
				MM = strData.substring(3,4);
				yy = strData.substring(5,7);
				formatData = dd + "/0" + MM + "/" + yy;
			}
		}
		else if(strData.length()==8)
		{
			//se strData � d.M.yyyy, oppure d-M-yyyy, ecc
			if(strData.substring(1,2).equals("/")|strData.substring(1,2).equals(".")|strData.substring(1,2).equals("-")|strData.substring(1,2).equals(" "))
			{
				dd = strData.substring(0,1);
				MM = strData.substring(2,3);
				yy = strData.substring(4,8);
				formatData = "0" + dd + "/0" + MM + "/" + yy;
			}
			//se strData � dd.MM.yy, oppure dd-MM-yy, ecc.
			else if(strData.substring(2,3).equals("/")|strData.substring(2,3).equals(".")|strData.substring(2,3).equals("-")|strData.substring(2,3).equals(" "))
			{
				dd = strData.substring(0,2);
				MM = strData.substring(3,5);
				yy = strData.substring(6,8);
				formatData = dd + "/" + MM + "/" + yy;
			}
			//se strData � ddMMyyyy
			else
			{
				dd = strData.substring(0,2);
				MM = strData.substring(2,4);
				yy = strData.substring(4,8);
				formatData = dd + "/" + MM + "/" + yy;
			}
		}
		else if(strData.length()==9)
		{
			//se strData � d.MM.yyyy, oppure d-MM-yyyy, ecc
			if(strData.substring(1,2).equals("/")|strData.substring(1,2).equals(".")|strData.substring(1,2).equals("-")|strData.substring(1,2).equals(" "))
			{
				dd = strData.substring(0,1);
				MM = strData.substring(2,4);
				yy = strData.substring(5,9);
				formatData = "0" + dd + "/" + MM + "/" + yy;
			}
			//se strData � dd.M.yyyy, oppure dd-M-yyyy, ecc
			else
			{
				dd = strData.substring(0,2);
				MM = strData.substring(3,4);
				yy = strData.substring(5,9);
				formatData = dd + "/0" + MM + "/" + yy;
			}
		}
		// se del tipo 'yyyy-MM-dd'
		else if (strData.length() == 10 && isInteger(strData.substring(0,4)))
		{
			dd = strData.substring(8,10);
			MM = strData.substring(5,7);
			yy = strData.substring(0,4);
			formatData = dd + "/" + MM + "/" + yy;
		}
		//se strData � dd.MM.yyyy, oppure dd-MM-yyyy, ecc.
		else if(strData.length()==10)
		{
			dd = strData.substring(0,2);
			MM = strData.substring(3,5);
			yy = strData.substring(6,10);
			formatData = dd + "/" + MM + "/" + yy;
		}
		//se del tipo 'yyyy-mm-dd hh:mm:ss'
		//@ Review by Mirko
		else if(strData.length()==19 && isInteger(strData.substring(0,4)) )
		{
			dd = strData.substring(8,10);
			MM = strData.substring(5,7);
			yy = strData.substring(0,4);
			formatData = dd + "/" + MM + "/" + yy;
		}
		return formatData;
	}

	private static boolean isInteger(String substring) {
		boolean isInteger = true;
		try {
			Integer.parseInt(substring);
		} catch (NumberFormatException e) {
			isInteger = false;
		}
		return isInteger;
	}

	//*******************************************************************************************
	//    Fine Sezione aggiunta da Maurizio Di Michele il 14 Marzo 2006
	//*******************************************************************************************    

	//*******************************************************************************************
	//    Metodo aggiunto da Gaetanino Paolone Marzo 2006
	//*******************************************************************************************    
	/*public static String formatta (String dataIn)
	{
		String dataOut = dataIn;

		return dataOut;


	}*/





}