package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.opencsv.CSVWriter;

public class AppTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/chromedrivers/chromedriver_104");
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void setDown() {
        this.driver.quit();
    }

    @Test
    public void testePesquisa() throws IOException {
        driver.get("https://link.springer.com/article/10.1007/s10664-020-09881-0");
        List<WebElement> artigos = driver
                .findElements(By.xpath("//section[@data-title='Results']//a[@data-test='citation-ref']"));

        List<String[]> csvData = new ArrayList<String[]>();
        CSVWriter writer = new CSVWriter(new FileWriter("titulos.csv"));

        try {
            for (WebElement artigo : artigos) {
                String tituloArtigo = artigo.getAttribute("title");
                csvData.add(tituloArtigo.split("https://"));
            }

            writer.writeAll(csvData);
            writer.close();
            System.out.println("CSV file created succesfully.");
        } catch (Exception e) {
            System.out.println("exception :" + e.getMessage());
        }
    }

}