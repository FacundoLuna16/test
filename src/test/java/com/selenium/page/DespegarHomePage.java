package com.selenium.page;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.selenium.MetodosUtiles.Utiles;

public class DespegarHomePage {
	WebDriver driver;
	WebElement btnNoLogin;
	LocalDate fechaActual;
	int diaActual;
	int mesActual;
	int anioActual;
	String fecha;
	String xpathIda;
	String xpathVuelta;
	WebElement selectFechaIda;
	WebElement selectFechaVuelta;
	WebElement btnAplicarPeriodo;
	WebElement habitaciones;

	@FindBy(xpath = "//span[@class='login-incentive--button login-incentive--button-close shifu-3-btn-ghost']//em[@class='btn-text'][normalize-space()='No quiero beneficios']")
	private WebElement btnEscLogin;
	@FindBy(xpath = "//input[@placeholder='Ingresa hacia dónde viajas']")
	private WebElement txtDestino;
	@FindBy(xpath = "//input[@placeholder='Ingresa desde dónde viajas']")
	private WebElement txtOrigen;
	@FindBy(xpath = "//*[@class='lgpd-banner--button shifu-3-btn -white -md']")
	private WebElement btnCookies;
	@FindBy(xpath = "//input[@placeholder='Ida']")
	private WebElement fechaIda;
	@FindBy(xpath = "//input[@placeholder='Vuelta']")
	private WebElement fechaVuelta;
	@FindBy(xpath = "//em[normalize-space()='Buscar']")
	private WebElement btnBuscar;

	public DespegarHomePage(WebDriver ldriver) {
		this.driver = ldriver;
		fechaActual = LocalDate.now();
		diaActual = fechaActual.getDayOfMonth();
		mesActual = fechaActual.getMonthValue();
		anioActual = fechaActual.getYear();
	}

	public void cerrarLogin() {
		btnEscLogin.click();
		Utiles.reportes("Cerramos el login a la pagina");

		/*
		 * si falla quitar este comentario y buscar el id de btnNologin
		 * 
		 * if (btnEscLogin.isDisplayed() ) { btnEscLogin.click(); }else {
		 * btnNoLogin.click(); }comentarioParaAgregarer
		 */
	}

	public void aceptarCookies() {
		btnCookies.click();
		Utiles.reportes("Aceptamos  las Cookies.");
	}

	public void ingresarSalida(String salida) throws Exception {
		Assert.assertTrue(txtOrigen.isDisplayed(), "La caja de texto Origen, NO esta Visible");
		txtOrigen.click();
		txtOrigen.clear();
		txtOrigen.sendKeys(salida);
		Thread.sleep(2000);
		txtDestino.sendKeys(Keys.CONTROL);
		Thread.sleep(2000);
		Utiles.reportes("fallo en la linea 83 DespegarHomePage.java");
		txtDestino.sendKeys(Keys.ENTER);
		Utiles.reportes("Ingresamos el destino: " + salida);
	}

	public void ingresarDestino(String destino) throws Exception {
		Assert.assertTrue(txtDestino.isDisplayed(), "La caja de texto Destino, NO esta Visible");
		txtDestino.click();
		txtDestino.sendKeys(destino);
		Thread.sleep(2000);
		txtDestino.sendKeys(Keys.CONTROL);
		Thread.sleep(2000);
		txtDestino.sendKeys(Keys.ENTER);
		Utiles.reportes("Ingresamos el destino:" + destino);
	}

	private String validarEstructuraFecha(int dia, int mes, int anio) {
	    if (dia >= 25) {
	        if (mes ==12) {
	        	fecha = String.format("%04d-%02d", anio+1, 1);
	        }
	        fecha = String.format("%04d-%02d", anio,mes + 1);
	        
	    } else {
	        fecha = String.format("%04d-%02d", anio, mes);
	    }
	    return fecha;
	}


	public void seleccionarFechaIda(int salida,int duracion) throws Exception {
		String fechaida = validarEstructuraFecha(diaActual + salida, mesActual, anioActual);

		//seteamos las variables a utilizar
		if (diaActual >25) {
			mesActual ++;
			fechaida= String.format("%04d-%02d", anioActual , mesActual) ; 
			//diaActual++;
			xpathIda = "//div[@data-month='" + fechaida
					+ "']//div[@class='sbox5-monthgrid-datenumber-number'][normalize-space()='" + (5)
					+ "']";
		}else {
		fechaida= String.format("%04d-%02d", anioActual , mesActual) ; 
		//diaActual++;
		xpathIda = "//div[@data-month='" + fechaida
				+ "']//div[@class='sbox5-monthgrid-datenumber-number'][normalize-space()='" + (diaActual + salida)
				+ "']";
		}
		selectFechaIda = driver.findElement(By.xpath(xpathIda));
		//Assert.assertTrue(selectFechaIda.isDisplayed(),"la fecha ida no es visible");

		//Assert.assertTrue(selectFechaVuelta.isDisplayed(),"la fecha Vuelta no es visible");

		// Seleccionamos la fecha de ida
		fechaIda.click();
		Thread.sleep(2000);
		Assert.assertTrue(selectFechaIda.isEnabled(), "La grilla de fecha Ida NO esta disponible");
		selectFechaIda.click();
		Utiles.reportes("Seleccionamos la fecha de ida: " + fechaida + "-" + (diaActual + salida));
	}

	public void seleccionFechaVuelta(int salida,int duracion)throws Exception {
				
		fecha = validarEstructuraFecha(diaActual + salida, mesActual, anioActual);
		Utiles.reportes(fecha);
		xpathVuelta = "//div[@data-month='" + fecha
				+ "']//div[@class='sbox5-monthgrid-datenumber-number'][normalize-space()='"
				+ (diaActual+salida+duracion) + "']";
		selectFechaVuelta = driver.findElement(By.xpath(xpathVuelta));
		
		fechaVuelta.click();
		Assert.assertTrue(selectFechaVuelta.isEnabled(),"La grilla de fecha vuelta NO esta disponible");
		selectFechaVuelta.click();
		Utiles.reportes("Seleccionamos la fecha de vuelta:" + fecha +"-"+(diaActual+duracion+salida));
		
		//Aplicamos las fechas seleccionadas
		btnAplicarPeriodo = driver.findElement(By.xpath("//button[@class='sbox5-3-btn -primary -md']"));
		Assert.assertTrue(btnAplicarPeriodo.isDisplayed(),"El boton Aplicar NO esta disponible");
		btnAplicarPeriodo.click();
		Utiles.reportes("Aceptamos");
	}
	public void realizarBusqueda() {
		btnBuscar.click();
		Utiles.reportes("Clickeamos el boton Buscar");
	}
	public void verificarUrlBusqueda(WebDriverWait wait) {
		wait.until(ExpectedConditions.urlContains("results"));
		Utiles.reportes("Los resultados de la busqueda se realizaron de forma exitosa!");
	}

}
