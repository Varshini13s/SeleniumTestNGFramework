package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderConfirmationPage extends BasePage{

    @FindBy(css = ".complete-header")
    WebElement orderConfirmationMsg;

    @FindBy(id = "back-to-products")
    WebElement backToHomeBtn;

    public boolean isOrderConfirmationMsgDisplayed() {
        System.out.println(orderConfirmationMsg.getText());
        return orderConfirmationMsg.isDisplayed();
    }

    public void clickOnBackToHomeBtn(){
        backToHomeBtn.click();
    }
}
