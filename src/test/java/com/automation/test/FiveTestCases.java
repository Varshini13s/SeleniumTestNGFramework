package com.automation.test;


import com.automation.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FiveTestCases extends BaseTest{
    @Test
    public void verifyProductsSortFromZtoA(){
        loginPage.openWebsite();
        loginPage.doLogin(ConfigReader.getConfigValue("login.username"), ConfigReader.getConfigValue("login.password"));

        Assert.assertTrue(homePage.isHomePageDisplayed());
        List<String> expectedResult=homePage.givesListOfProducts();
        homePage.clickOnFilterOptions(ConfigReader.getConfigValue("filter.productsZtoA"));
        List<String> actualResult = homePage.givesListOfProducts();
        Collections.sort(expectedResult, Collections.reverseOrder());
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void verifyProductsAreSortedAccordingToPrice(){
        loginPage.openWebsite();
        loginPage.doLogin(ConfigReader.getConfigValue("login.username"), ConfigReader.getConfigValue("login.password"));

        Assert.assertTrue(homePage.isHomePageDisplayed());
        List<Double> expectedResult = homePage.givesListOfPrices();
        homePage.clickOnFilterOptions(ConfigReader.getConfigValue("filter.priceHighToLow"));
        List<Double> actualResult = homePage.givesListOfPrices();
        Collections.sort(expectedResult,Collections.reverseOrder());
        Assert.assertEquals(actualResult,expectedResult);

    }

    @Test
    public void verifyCartPageProductEqualsUserAction(){
        loginPage.openWebsite();
        loginPage.doLogin(ConfigReader.getConfigValue("login.username"), ConfigReader.getConfigValue("login.password"));

        Assert.assertTrue(homePage.isHomePageDisplayed());
        homePage.clickOnAddToCartBtn();
        Assert.assertEquals(homePage.shoppingCartBadgeText(),"6","Badge Test");
        homePage.clickOnShoppingCartLink();

        Assert.assertTrue(cartPage.isCartPageDisplayed());
        Assert.assertEquals( cartPage.displayNoOfItemsInCart(),6,"Cart Item Test");

    }

    @Test
    public void verifyTotalPriceInCheckout(){
        loginPage.openWebsite();
        loginPage.doLogin(ConfigReader.getConfigValue("login.username"), ConfigReader.getConfigValue("login.password"));

        Assert.assertTrue(homePage.isHomePageDisplayed());
        homePage.clickOnAddToCartBtn();
        homePage.clickOnShoppingCartLink();

        Assert.assertTrue(cartPage.isCartPageDisplayed());
        cartPage.clickOnCheckoutBtn();

        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed());
        checkoutPage.fillShippingInfo();
        checkoutPage.clickOnContinueBtn();

        reviewPage.givesSumOfPrices();
        Assert.assertEquals(reviewPage.givesSumOfPricesAndTax(),reviewPage.givesTotalAmount());

    }

    @Test
    public void verifyRemoveBtnNotDisplayedAfterOrderConfirmation(){
        loginPage.openWebsite();
        loginPage.doLogin(ConfigReader.getConfigValue("login.username"), ConfigReader.getConfigValue("login.password"));

        Assert.assertTrue(homePage.isHomePageDisplayed());
        homePage.clickOnAddToCartBtn();
        homePage.clickOnShoppingCartLink();

        Assert.assertTrue(cartPage.isCartPageDisplayed());
        cartPage.clickOnCheckoutBtn();

        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed());
        checkoutPage.fillShippingInfo();
        checkoutPage.clickOnContinueBtn();

        Assert.assertTrue(reviewPage.isReviewPageDisplayed());
        reviewPage.clickOnFinishBtn();

        orderConfirmationPage.clickOnBackToHomeBtn();

        Assert.assertTrue(homePage.isHomePageDisplayed());
        Assert.assertEquals(homePage.givesNoOfRemoveButton(),0);
    }

}
