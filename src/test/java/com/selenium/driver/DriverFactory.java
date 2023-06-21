package com.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.openqa.selenium.firefox.FirefoxOptions;
import com.selenium.MetodosUtiles.*;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DriverFactory {

	private enum browsers {
		EDGE, FIREFOX, CHROME
	};

	/*** Metodo que abre un navegador y navega a la URL que le indiquemos ***/
	public static WebDriver LevantarBrowser(WebDriver driver, ITestContext context) {
		Utiles.separador();
		String browserName = context.getCurrentXmlTest().getParameter("NombreNavegador");
		String URL = context.getCurrentXmlTest().getParameter("Url");
		switch (browsers.valueOf(browserName)) {
		case CHROME: // Using WebDriver
		{
			System.setProperty("webdriver.chrome.driver", "Recursos/chromedriver.exe");
			Utiles.reportes("Abro browser");
			driver = new ChromeDriver();
			break;
		}
		case FIREFOX:// Using WebDriver
		{//TODO ejecutar con firefox
			System.setProperty("webdriver.gecko.driver", "Recursos/geckodriver32-2.exe");
			Utiles.reportes("Abro browser");
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(new FirefoxProfile());
			options.addPreference("network.proxy.type", 0); // Si no necesitas un proxy
			driver = new FirefoxDriver(options);
			break;
		}
		case EDGE:// Using WebDriver
		{
			System.setProperty("webdriver.edge.driver", "Recursos/msedgedriver.exe");
			Utiles.reportes("Abro browser");
			driver = new EdgeDriver();
			break;
		}
		default:
			Utiles.reportes("No selecciono ningun browser correcto, se le asignara Chrome");
			System.setProperty("webdriver.chrome.driver", "Recursos/chromedriver.exe");
			Utiles.reportes("Abro browser");
			driver = new ChromeDriver();
			break;
		}
		driver.manage().window().maximize();
		driver.get(URL);
		return driver;
	}

	public static void FinalizarBrowser(WebDriver driver) {
		Utiles.reportes("Cerrando el browser");
		Utiles.separador();
		driver.quit();
		driver = null;
	}
}
