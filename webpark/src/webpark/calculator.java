package webpark;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class calculator {
	public static void main (String[] args) throws IOException, InterruptedException {
		//khai bao cac bien
		String pathDrive = "D:\\Chanh\\drive\\chromedriver.exe";
		String pathData = "D:\\Chanh\\webpark\\data\\data-calculator.xlsx";
		String sheetData = "calculator";
		ChromeDriver driver = new ChromeDriver();
		WebElement parkingLotElement;
		WebElement entryDateElement;
		WebElement entryTimeElement;
		WebElement exitDateElement;
		WebElement exitTimeElement;
		WebElement calculateButton;
		WebElement msg;
		WebElement result;
		String url ="https://practice.expandtesting.com/webpark";
		String xpathLot = "//*[@id=\"parkingLot\"]";
		String xpathEntryDate ="//*[@id=\"entryDate\"]";
		String xpathEntryTime ="//*[@id=\"entryTime\"]";
		String xpathExitDate ="//*[@id=\"exitDate\"]";
		String xpathExitTime ="//*[@id=\"exitTime\"]";
		String xpathButton ="//*[@id=\"calculateCost\"]";
		String xpathMsg ="//*[@id=\"resultMessage\"]";
		String xpathResult ="//*[@id=\"resultValue\"]";
		
		
		File file = new File(pathData); //tạo biến tạm file để làm việc với file excel
		FileInputStream fileData = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fileData);
		XSSFSheet sheet = workbook.getSheet(sheetData);
		int rowNum = sheet.getLastRowNum();
		
		for (int i = 1; i <= rowNum; i++) {
			XSSFRow rowData = sheet.getRow(i);
			XSSFCell cellLot = rowData.getCell(1);
			XSSFCell cellEntryDate = rowData.getCell(2);
			XSSFCell cellEntryTime = rowData.getCell(3);
			XSSFCell cellExitDate = rowData.getCell(4);
			XSSFCell cellExitTime = rowData.getCell(5);
			
			String valueLot = cellLot.getStringCellValue();
			String valueEntryDate = cellEntryDate.getStringCellValue();
			String valueEntryTime = cellEntryTime.getStringCellValue();
			String valueExitDate = cellExitDate.getStringCellValue();
			String valueExitTime = cellExitTime.getStringCellValue();
			
			System.getProperty("webdriver.chrome.driver",pathDrive);
			driver.get(url);
			
			parkingLotElement = driver.findElement(By.xpath(xpathLot));
			Select dropLot = new Select(parkingLotElement);
			dropLot.selectByValue(valueLot);
			Thread.sleep(1000);
			
			entryDateElement = driver.findElement(By.xpath(xpathEntryDate));
			entryDateElement.clear();
			entryDateElement.sendKeys(valueEntryDate);
			Thread.sleep(1000);
			
			entryTimeElement = driver.findElement(By.xpath(xpathEntryTime));
			entryTimeElement.clear();
			entryTimeElement.sendKeys(valueEntryTime);
			Thread.sleep(1000);
			
			exitDateElement = driver.findElement(By.xpath(xpathExitDate));
			exitDateElement.clear();
			exitDateElement.sendKeys(valueExitDate);
			Thread.sleep(1000);
			
			exitTimeElement = driver.findElement(By.xpath(xpathExitTime));
			exitTimeElement.clear();
			exitTimeElement.sendKeys(valueExitTime);
			Thread.sleep(1000);
			
			calculateButton = driver.findElement(By.xpath(xpathButton));
			calculateButton.click();
			Thread.sleep(1000);
			
			msg = driver.findElement(By.xpath(xpathMsg));
			try {
				result = driver.findElement(By.xpath(xpathResult));
				if(result.isDisplayed()) {
					rowData.createCell(7);
					rowData.getCell(7).setCellValue(result.getText());
				}	
			} catch (NoSuchElementException e) {
				rowData.createCell(8);
				rowData.getCell(8).setCellValue(msg.getText());
				rowData.createCell(9);
				rowData.getCell(9).setCellValue("F");
			}
				
		}
		
		FileOutputStream fileData1 = new FileOutputStream(file);
		workbook.write(fileData1);
		fileData1.close();
		driver.close();
	}

}
