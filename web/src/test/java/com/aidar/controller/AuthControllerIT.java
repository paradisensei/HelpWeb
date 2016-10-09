package com.aidar.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by paradise on 03.05.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/it-config.xml" })
public class AuthControllerIT {

    private WebDriver driver;

    @Value("${sign_in}")
    private String signInUrl;

    @Value("${sign_in_error}")
    private String signInWithErrorUrl;

    @Before
    public void setup() throws IOException {
        driver = new HtmlUnitDriver();
    }

    @Test
    public void signInWithValidCredentialsShouldRedirectToHomePage() throws IOException {
        driver.get(signInUrl);
        driver.findElement(By.id("email")).sendKeys("kobe@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("sign_in")).click();
        assertEquals("Home", driver.getTitle());
    }

    @Test
    public void signInWithInvalidCredentialsShouldRedirectToSignInPageWithErrorParameter() {
        driver.get(signInUrl);
        driver.findElement(By.id("email")).sendKeys("asd@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("sign_in")).click();
        assertTrue(driver.getCurrentUrl().startsWith(signInUrl));
        assertTrue(driver.getCurrentUrl().endsWith("error=true"));
    }

}
