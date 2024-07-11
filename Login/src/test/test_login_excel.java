package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class test_login_excel {
	public static void main(String[] args) throws InterruptedException, IOException {
		String path_drive = "D:\\Chanh\\drive\\chromedriver.exe";
		String path_data = "D:\\Chanh\\Login\\data\\dataTest.xlsx";
		String sheet_data ="data";
		WebDriver driver = new ChromeDriver();
		WebElement userName_Element;
		WebElement passWord_Element;
		WebElement message;
		WebElement buttonLogin;
		String url = "https://practicetestautomation.com/practice-test-login/";	
		String xpathUser = "//*[@id=\"username\"]";
		String xpathPass = "//*[@id=\"password\"]";
		String xpathLogin = "//*[@id=\"submit\"]";
		String xpathError = "//*[@id=\"error\"]";
		
		File file = new File(path_data); //tạo biến tạm file để làm việc với file excel
		FileInputStream file_data = null; //đọc file excel
		file_data = new FileInputStream(file);
		XSSFWorkbook workbook = null;
		workbook = new XSSFWorkbook(file_data);
		XSSFSheet sheet = workbook.getSheet(sheet_data);
		int rowNum = sheet.getLastRowNum();
		for (int i =1; i<= rowNum; i++) {
			XSSFRow rowData = sheet.getRow(i);
			XSSFCell cellUser = rowData.getCell(1);
			XSSFCell cellPassword = rowData.getCell(2);
			XSSFCell cellSTT = rowData.getCell(0);
			double STT = cellSTT.getNumericCellValue();
			String valueUser = cellUser.getStringCellValue();
			String valuePassword = cellPassword.getStringCellValue();
			
			System.getProperty("webdriver.chrome.driver",path_drive);
			driver.get(url);
			userName_Element = driver.findElement(By.xpath(xpathUser));
			passWord_Element = driver.findElement(By.xpath(xpathPass));
			buttonLogin = driver.findElement(By.xpath(xpathLogin));
			message = driver.findElement(By.xpath(xpathError));
			
			userName_Element.sendKeys(valueUser);
			Thread.sleep(1000);
			passWord_Element.sendKeys(valuePassword);
			Thread.sleep(1000);
			buttonLogin.click();
			Thread.sleep(1000);
			if (message !=null) {
				try {
					if (message.getText().contains("invalid")) {
						System.out.println(STT);
						System.out.println(message.getText());
						System.out.println("----------");
						rowData.createCell(4);
						rowData.getCell(4).setCellValue(message.getText());
						rowData.createCell(3);
						rowData.getCell(3).setCellValue("F");
					}
					
				} catch (Exception e) {
						System.out.println(STT);
						System.out.println("Login Sucess");
						System.out.println("----------");
						rowData.createCell(3);
						rowData.getCell(3).setCellValue("P");
				}	
			}
			
		}
		
		FileOutputStream fileData = new FileOutputStream(file);
		workbook.write(fileData);
		fileData.close();
		driver.close();
}
}

