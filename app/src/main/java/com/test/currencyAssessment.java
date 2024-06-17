package com.test;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class currencyAssessment {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        ArrayList<String> testCase = new ArrayList<>();

        //Test Case 1: Valid Conversion
        driver.get("https://www.xe.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("amount")).sendKeys("1.00");
        driver.findElement(By.cssSelector("#midmarketFromCurrency")).click();
        driver.findElement(By.cssSelector("#midmarketFromCurrency-option-0")).click();
        driver.findElement(By.cssSelector("#midmarketToCurrency")).click();
        driver.findElement(By.cssSelector("#midmarketToCurrency-option-1")).click();
        driver.findElement(By.cssSelector("#__next > div:nth-child(4) > div.sc-2b1c5c79-0.frAgUY > section > div:nth-child(2) > div > main > div > div.sc-4f0f6f94-2.chRjcw > button")).click();
        
        Thread.sleep(5000);

        //Get output
        String TC1 = driver.findElement(By.cssSelector("#__next > div:nth-child(4) > div.sc-2b1c5c79-0.frAgUY > section > div:nth-child(2) > div > main > div > div:nth-child(2) > div:nth-child(1) > div > p.sc-295edd9f-1.jqMUXt")).getText();

        if (TC1.contains("0.93")) 
        {
            testCase.add("Test Case 1: Valid Conversion Passed");
        }
        else
        {
            testCase.add("Test Case 1: Valid Conversion Failed. Actual Value:" + TC1);
        }

        driver.close();

        //Test Case 2: Invalid Conversion
        driver = new ChromeDriver(options);
        driver.get("https://www.xe.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("amount")).sendKeys("-1.00");
        driver.findElement(By.cssSelector("#midmarketFromCurrency")).click();
        driver.findElement(By.cssSelector("#midmarketFromCurrency-option-0")).click();
        driver.findElement(By.cssSelector("#midmarketToCurrency")).click();
        driver.findElement(By.cssSelector("#midmarketToCurrency-option-1")).click();  

        Thread.sleep(5000);

        //Get error message
        String TC2 = driver.findElement(By.cssSelector("#__next > div:nth-child(4) > div.sc-2b1c5c79-0.frAgUY > section > div:nth-child(2) > div > main > div > div.sc-4f0f6f94-0.diOrXh > div.sc-4f0f6f94-1.xeMov")).getText();

        if (TC2.equals("Please enter an amount greater than 0")) 
        {
            testCase.add("Test Case 2: Invalid Conversion Passed");
        }
        else
        {
            testCase.add("Test Case 2: Invalid Conversion Failed");
        }

        driver.close();

        // Test Case 3: Swap currencies button functionality
        driver = new ChromeDriver(options);
        driver.get("https://www.xe.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("amount")).sendKeys("1.00");
        driver.findElement(By.cssSelector("#midmarketFromCurrency")).click();
        driver.findElement(By.cssSelector("#midmarketFromCurrency-option-0")).click();
        driver.findElement(By.cssSelector("#midmarketToCurrency")).click();
        driver.findElement(By.cssSelector("#midmarketToCurrency-option-1")).click();
        driver.findElement(By.cssSelector("#__next > div:nth-child(4) > div.sc-2b1c5c79-0.frAgUY > section > div:nth-child(2) > div > main > div > div.sc-4f0f6f94-2.chRjcw > button")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("#__next > div:nth-child(4) > div.sc-2b1c5c79-0.frAgUY > section > div:nth-child(2) > div > main > div > div.sc-4f0f6f94-0.diOrXh > div.sc-4f0f6f94-3.gLhOsR > button")).click(); 
        
        String TC3From = driver.findElement(By.cssSelector("#midmarketFromCurrency")).getText();
        String TC3To = driver.findElement(By.cssSelector("#midmarketToCurrency")).getText();

        if (TC3From.contains("EUR") && TC3To.contains("USD"))
        {
            testCase.add("Test Case 3: Swap currencies button functionality Passed");
        }
        else
        {
            testCase.add("Test Case 3: Swap currencies button functionality Failed");
        }

        driver.close();

        // Test Case 4: No Input
        driver = new ChromeDriver(options);
        driver.get("https://www.xe.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("amount")).sendKeys(" ");
        driver.findElement(By.cssSelector("#midmarketFromCurrency")).click();
        driver.findElement(By.cssSelector("#midmarketFromCurrency-option-0")).click();
        driver.findElement(By.cssSelector("#midmarketToCurrency")).click();
        driver.findElement(By.cssSelector("#midmarketToCurrency-option-1")).click();
        
        String TC4 = driver.findElement(By.cssSelector("#__next > div:nth-child(4) > div.sc-2b1c5c79-0.frAgUY > section > div:nth-child(2) > div > main > div > div.sc-4f0f6f94-0.diOrXh > div.sc-4f0f6f94-1.xeMov")).getText();
        
        if (TC4.equals("Please enter a valid amount"))
        {
            testCase.add("Test Case 4: No Input Passed");
        }
        else
        {
            testCase.add("Test Case 4: No Input Failed");
        }

        driver.close();

        // Test Case 5: Same currency conversion
        driver = new ChromeDriver(options);
        driver.get("https://www.xe.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("amount")).sendKeys("1.00");
        driver.findElement(By.cssSelector("#midmarketFromCurrency")).click();
        driver.findElement(By.cssSelector("#midmarketFromCurrency-option-0")).click();
        driver.findElement(By.cssSelector("#midmarketToCurrency")).click();
        driver.findElement(By.cssSelector("#midmarketToCurrency-option-0")).click();
        driver.findElement(By.cssSelector("#__next > div:nth-child(4) > div.sc-2b1c5c79-0.frAgUY > section > div:nth-child(2) > div > main > div > div.sc-4f0f6f94-2.chRjcw > button")).click();
        
        Thread.sleep(5000);
        //Get output
        String TC5 = driver.findElement(By.cssSelector("#__next > div:nth-child(4) > div.sc-2b1c5c79-0.frAgUY > section > div:nth-child(2) > div > main > div > div:nth-child(2) > div:nth-child(1) > div > p.sc-295edd9f-1.jqMUXt")).getText();
        
        if (TC5.contains("1.00"))
        {
            testCase.add("Test Case 5: Same currency conversion Passed");
        }
        else
        {
            testCase.add("Test Case 5: Same currency conversion Failed");
        }

        driver.close();

        // Test Case 6: Letters or special character amount input
        driver = new ChromeDriver(options);
        driver.get("https://www.xe.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("amount")).sendKeys("abc!@#");
        driver.findElement(By.cssSelector("#midmarketFromCurrency")).click();
        driver.findElement(By.cssSelector("#midmarketFromCurrency-option-0")).click();
        driver.findElement(By.cssSelector("#midmarketToCurrency")).click();
        driver.findElement(By.cssSelector("#midmarketToCurrency-option-1")).click();
        
        String TC6 = driver.findElement(By.cssSelector("#__next > div:nth-child(4) > div.sc-2b1c5c79-0.frAgUY > section > div:nth-child(2) > div > main > div > div.sc-4f0f6f94-0.diOrXh > div.sc-4f0f6f94-1.xeMov")).getText();
        
        if (TC6.equals("Please enter a valid amount"))
        {
            testCase.add("Test Case 6: Letters or special character amount input Passed");
        }
        else
        {
            testCase.add("Test Case 6: Letters or special character amount input Failed");
        }
        
        driver.close();


        for (String x : testCase) {
            System.out.println(x);
        }
    }
}
