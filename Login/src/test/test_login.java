package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class test_login {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String path_drive ="D:\\Chanh\\drive\\chromedriver.exe";
		String[] userNamelist ={"test1", "student","student"};
		String[] passWordlist ={"Password123", "Password12","Password123"};
		WebElement userName_Element;
		WebElement passWord_Element;
		WebElement message;
		String url = "https://practicetestautomation.com/practice-test-login/";	
		String xpathUser = "//*[@id=\"username\"]";
		String xpathPass = "//*[@id=\"password\"]";
		String xpathLogin = "//*[@id=\"submit\"]";
		String xpathError = "//*[@id=\"error\"]";
		
		System.getProperty("webdriver.chrome.driver",path_drive);
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		userName_Element = driver.findElement(By.xpath(xpathUser));
		passWord_Element = driver.findElement(By.xpath(xpathPass));
		message = driver.findElement(By.xpath(xpathError));
		
		for (int i = 0; i < userNamelist.length; i++) {
		String userName = userNamelist[i];
		String passWord = passWordlist[i];
		userName_Element.sendKeys(userName);
		Thread.sleep(1000);
		passWord_Element.sendKeys(passWord);
		Thread.sleep(1000);
		WebElement buttonLogin = driver.findElement(By.xpath(xpathLogin));
		buttonLogin.click();
		Thread.sleep(1000);
		try {
			if (message.getText().contains("invalid")) {
				System.out.println(userName);
				System.out.println(message.getText());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(userName);
			System.out.println("Login Sucess");
		}
		
		}
	}

}
