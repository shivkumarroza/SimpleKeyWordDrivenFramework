package qsp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Demo {
	static
	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
	}
	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost/login.do");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait= new WebDriverWait(driver, 20);
		String p="./data/input.xlsx";
		int rc=Excel.getRowCount(p, "sheet1");
		System.out.println(rc);
		for(int i=0;i<=rc;i++) {
			String action=Excel.getData(p, "sheet1", i, 0);
			String xp=Excel.getData(p, "sheet1", i, 1);
			String input=Excel.getData(p, "sheet1", i, 2);
			System.out.println(action+"\n"+xp+"\n"+input);
			if(action.equals("enter")) {
				driver.findElement(By.xpath(xp)).sendKeys(input);
			}
			else if(action.equals("click"))
			{
				while(true)
				{
					try {
						driver.findElement(By.xpath(xp)).click();
						break;
					}
					catch (Exception e) {

					}
				}
			}
		}
		driver.close();
	}

}
