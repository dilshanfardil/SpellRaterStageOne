/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellraterstageone;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Dilshan
 */


// Using Seleinium API

public class SiteOnAVarable {

    private static WebDriver driver;

    public static void main(String[] args) {
        try {
            System.setProperty("webdriver.chrome.driver", "D:\\Indrustrial Traning - 2016\\Projects\\SpellRater\\Stage 1\\Jar Files\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            
//            driver.getWindowHandle();
            driver.get("https://netbeans.org/community/guidelines/");

            //Web Page Source
            System.out.println(driver.getPageSource());
            System.out.println("----------------------------------------------");
//            WebElement findElement = driver.findElement(By.cssSelector("p"));
//            System.out.println(findElement);
            List<WebElement> el = driver.findElements(By.cssSelector("a"));
            for (int i = 0; i < el.size(); i++) {
                System.out.println(el.get(i).getText());
            }
            System.out.println("----------------------------------------------");
            
            
            System.out.println("---------------------------------------");
            getLinks();
            System.out.println("----------------------------------------");
            
//            String returnAllText = returnAllText(By.cssSelector("*"));
//            System.out.println(returnAllText);
            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String returnAllText(By element) {
        List<WebElement> all = driver.findElements(element);
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (WebElement elemenet : all) {
            sb.append(i++).append(": ").append(elemenet.getText());
        }
        return sb.toString();
    }
    
    public static void getLinks()throws Exception{
    try {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        int linkcount = links.size(); 
         System.out.println(links.size()); 
          for (WebElement myElement : links){
         String link = myElement.getText(); 
         System.out.println(link);
         System.out.println(myElement);   
        if (link !=""){
             myElement.click();
             Thread.sleep(2000);
             System.out.println("third");
            }
            //Thread.sleep(5000);
          } 
        }catch (Exception e){
            System.out.println("error "+e);
        }
    }

}
