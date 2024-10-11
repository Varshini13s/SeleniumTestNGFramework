package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(id = "checkout")
    WebElement checkOutBtn;

    @FindBy(className = "inventory_item_name")
    List<WebElement> cartItemsList;

    public boolean isCartPageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(checkOutBtn));
        return checkOutBtn.isDisplayed();
    }

    public void clickOnCheckoutBtn() {
        checkOutBtn.click();
    }

    public int displayNoOfItemsInCart() {
        return cartItemsList.size();
    }
}
