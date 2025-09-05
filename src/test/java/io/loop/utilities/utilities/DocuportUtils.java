package io.loop.utilities.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class DocuportUtils {

    /**
     * login to docuport application
     *
     * @param driver, which is initialized in the test base
     * @param role,   comes from docuport constants
     *                *author name nsh
     */

    public static void login(WebDriver driver, String role) throws InterruptedException {
        driver.get(ConfigurationReader.getProperties("docuportBETA"));

        WebElement username = driver.findElement(By.xpath("//input[@placeholder='Enter username or email']"));
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        switch (role.toLowerCase()) {
            case "client":
                username.sendKeys(DocuportConstants.USERNAME_CLIENT);
                password.sendKeys(DocuportConstants.PASSWORD);
                break;
            case "supervisor":
                username.sendKeys(DocuportConstants.USERNAME_SUPERVISOR);
                password.sendKeys(DocuportConstants.PASSWORD);
                break;
            case "advisor":
                username.sendKeys(DocuportConstants.USERNAME_ADVISOR);
                password.sendKeys(DocuportConstants.PASSWORD);
                break;
            case "employee":
                username.sendKeys(DocuportConstants.USERNAME_EMPLOYEE);
                password.sendKeys(DocuportConstants.PASSWORD);
                break;
            default:
                throw new InterruptedException("There is no such role: " + role);
        }

        loginButton.click();

        if (role.equalsIgnoreCase("client")) {
            Thread.sleep(3000);
            WebElement cont = driver.findElement(By.xpath("//button[@type='submit']"));
            cont.click();
            Thread.sleep(3000);
        }
    }

    public static void logout(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement userIcon = driver.findElement(By.xpath("//div[@class='v-avatar primate']"));
        userIcon.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement logOut = driver.findElement(By.xpath("//span[contains(text(),'Log out')]")); // ✅ Fixed bracket
        logOut.click();

    }

    /**
     * returns any fields from table by given email
     *
     * @param driver
     * @param emailAddress
     * @param field
     * @return author nsh
     */

    public static String returnAnyFieldFromTable(WebDriver driver, String emailAddress, String field) {
        WebElement element = null;
        String xpath = "";
        xpath = "//to[2][text()= " + emailAddress + "']/preceding-sibling::to//span[2]";
        element = driver.findElement(By.xpath(xpath));



        return element.getText().trim();

    }
    }


