package ru.ncedu.java.tasks ;

import java.util.Scanner;
import java.util.*;

/**
 * ЦЕЛИ ЗАДАЧИ:
 * - Научиться разбирать (parse) потоки InputStreams с помощью java.util.Scanner 
 * - Разобраться с java.util.Calendar и java.util.regexp.
 * 
 * @author Kharichkin Alexander
 */
public interface BusinessCard{
	/**
	 * This method obtains (via Scanner) information from input stream 
	 *  that contains the following information:
	 * Name - String
	 * Last name - String
	 * Department - String 
	 * Birth date - String in format: "DD-MM-YYYY", where DD - two-digits birth date, 
	 *					MM - two-digits month of birth, YYYY - year of birth
	 * Gender : F or M - Character
	 * Salary : number from 100 to 100000
	 * Phone number : 10-digit number
	 * All entries are separated with ";" sign
	 * The format of input is the following:
	 * <Name>;<Last name>;<Department>;<Birth date>;<Gemder>;<Salary>;<Phone number>
	 * 
	 * @param Scanner
	 * @return Business Card
	 * @throws InputMismatchException Is thrown if input value 
	 *   does not correspond do declared data type (for example, 
	 *   if phone number is like "AAA", or date format is incorrect, or salary is too high)
	 * @throws NoSuchElementException Is thrown if input stream hasn't enough values 
	 *   to construct a BusinessCard
	 * 
	 * The constructor should store received data to be able to get it by getters methods
	 */
	public BusinessCard getBusinessCard (Scanner scanner) throws InputMismatchException, NoSuchElementException;
	/*
	 * For example, input stream contains following
	 *
	 * Chuck;Norris;DSI;10-04-1940;M;1000;1234567890
	 *
	 * And business card should look like this
	 * 
	 * Employee: Chuck Norris
	 * Department: DSI
	 * Salary: 1000
	 * Age: 69
	 * Gender: Male
	 * Phone: +7 123-456-78-90
	 */
	 
	/**
	 * @return Employee name and last name, like "Chuck Norris"
	 */
	public String getEmployee();
	
	/**
	 * @return Employee Department name, like "DSI"
	 */	
	public String getDepartment();
	
	/**
	 * @return Employee salary, like 1000
	 */	
	public int getSalary();
	
	/**
	 * @return Employee age, like 69
	 */	
	public int getAge();
	
	/**
	 * @return Employee gender, like "Female" or "Male"
	 */	
	public String getGender();
	
	/**
	 * @return Employee Phone Number in String, like "+7 123-456-78-90"
	 */	
	public String getPhoneNumber();
	
}