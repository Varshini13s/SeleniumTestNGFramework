package com.automation.pages;

import com.automation.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ReviewPage extends BasePage {

    @FindBy(id = "finish")
    WebElement finishBtn;

    @FindBy(className = "inventory_item_price")
    List<WebElement> listOfPriceInCheckout;

    @FindBy(className = "summary_tax_label")
    WebElement taxAmount;

    @FindBy(className = "summary_total_label")
    WebElement totalAmount;

    public boolean isReviewPageDisplayed() {
        return finishBtn.isDisplayed();
    }

    public void clickOnFinishBtn() {
        finishBtn.click();
    }

    public void givesSumOfPrices() {
        double sumOfPrices=0;
        for (int i = 0; i < listOfPriceInCheckout.size(); i++) {
            double price = Double.parseDouble(listOfPriceInCheckout.get(i).getText().replace("$", ""));
            //System.out.println(listOfPrice.get(i).getText());
            sumOfPrices+=price;
        }
        ConfigReader.setConfigValue("review.sumOfProductPrices", Double.toString(sumOfPrices));
    }

    public double givesSumOfPricesAndTax() {
        double tax = Double.parseDouble(taxAmount.getText().replace("Tax: $", ""));
        String priceString = ConfigReader.getConfigValue("review.sumOfProductPrices");
        double sumOfProductPrices = Double.parseDouble(priceString) + tax;
        return sumOfProductPrices;
    }

    public Double givesTotalAmount(){
        Double total = Double.parseDouble(totalAmount.getText().replace("Total: $",""));
        //System.out.println(total);
        return total;
    }
}
