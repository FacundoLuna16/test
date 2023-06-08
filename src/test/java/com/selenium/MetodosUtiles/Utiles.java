package com.selenium.MetodosUtiles;
import org.testng.Reporter;

public class Utiles {

	public static void reportes(String msg) {
		System.out.println(msg);
		Reporter.log(msg);
	}
	public static void separador() {
		String repeatedString = "-".repeat(40);
		System.out.println(repeatedString);
		System.out.println(repeatedString);
	}

}
