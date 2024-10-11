package com.automation.test;

import com.automation.utils.DriverManager;
import com.automation.utils.ExcelUtils;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class DataDrivenTest extends BaseTest{

    @Test(dataProvider = "getLoginData")
    public void verifyUserCanLoginWithDifferentData(String username,String password){
        loginPage.openWebsite();
        loginPage.doLogin(username,password);
        Allure.addAttachment("screenshot", DriverManager.takeScreenshotAsInputStream());
        Assert.assertTrue(homePage.isHomePageDisplayed());
    }
    @DataProvider
    public Object[][] getLoginData() {
            ExcelUtils excelUtils = new ExcelUtils("Credentials.xlsx", "ValidData");
            List<List<String>> tableData = excelUtils.getData();
            Object[][] data = new Object[tableData.size()][tableData.get(0).size()];

            for (int i = 0; i < tableData.size(); i++) {
                List<String> row = tableData.get(i);
                for (int j = 0; j < row.size(); j++) {
                    data[i][j] = tableData.get(i).get(j);
                }
            }

          return data;
    }
}
