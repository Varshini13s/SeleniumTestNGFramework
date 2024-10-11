package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    WebElement shoppingCartLink;

    @FindBy(xpath = "//button[contains(@id,'add-to-cart')]")
    List<WebElement> addToCartBtnList;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    WebElement shoppingCartBadge;

    @FindBy(id = "react-burger-menu-btn")
    WebElement menu;

    @FindBy(id = "logout_sidebar_link")
    WebElement logout;

    @FindBy(xpath = "//div[@class='inventory_item_name ']")
    List<WebElement> listOfproductName;

    @FindBy(className = "product_sort_container")
    WebElement filterSelectBtn;

    @FindBy(className = "inventory_item_price")
    List<WebElement> listOfPrice;

    @FindBy(xpath = "//button[contains(@id,'remove-sauce-')]")
    List<WebElement> listOfRemoveBtn;


    public boolean isHomePageDisplayed() {
        return shoppingCartLink.isDisplayed();
    }

    public void clickOnAddToCartOfFirstItem() {
        addToCartBtnList.get(0).click();
    }

    public void clickOnShoppingCartLink() {
        shoppingCartLink.click();
    }

    public String shoppingCartBadgeText() {
        return shoppingCartBadge.getText();

    }

    public void doLogout() {
        menu.click();
        logout.click();

    }

    public List<String> givesListOfProducts() {
        List<String> product = new ArrayList<>();
        for (int i = 0; i < listOfproductName.size(); i++) {
            product.add(listOfproductName.get(i).getText());
            //System.out.println(productName.get(i).getText());
        }
        return product;

    }

    public void clickOnFilterOptions(String value) {
        Select selectElement = new Select(filterSelectBtn);
        selectElement.selectByValue(value);
    }

    public List<Double> givesListOfPrices() {
        List<Double> price = new ArrayList<>();
        for (int i = 0; i < listOfPrice.size(); i++) {
            price.add(Double.parseDouble(listOfPrice.get(i).getText().replace("$", "")));
            //System.out.println(listOfPrice.get(i).getText());
        }
        return price;

    }

    public void clickOnAddToCartBtn() {
        //System.out.println(addToCartBtnList.size());
        for (WebElement button : addToCartBtnList) {
                button.click();
        }
    }

    public int givesNoOfRemoveButton(){
        return listOfRemoveBtn.size();
    }
}
