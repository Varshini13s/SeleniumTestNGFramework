package com.automation.test;

import com.automation.utils.ConfigReader;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest{
    @Test
    public void verifyAppSavesState(){
        loginPage.openWebsite();
        loginPage.doLogin(ConfigReader.getConfigValue("login.username"), ConfigReader.getConfigValue("login.password"));

        Assert.assertTrue(homePage.isHomePageDisplayed());
        homePage.clickOnAddToCartOfFirstItem();
        Assert.assertEquals(homePage.shoppingCartBadgeText(),"1");
        homePage.doLogout();
        //Allure.step("test Pass", Status.PASSED);

        Assert.assertTrue(loginPage.verifyLoginBtn());
        loginPage.doLogin(ConfigReader.getConfigValue("login.username"), ConfigReader.getConfigValue("login.password"));

        Assert.assertTrue(homePage.isHomePageDisplayed());
        Assert.assertEquals(homePage.shoppingCartBadgeText(),"1");

    }
}
