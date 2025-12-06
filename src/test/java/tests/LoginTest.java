package tests;

import base.BaseTest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.JsonDataReader;

public class LoginTest extends BaseTest {

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws IOException {
	    List<Map<String,String>> dataList =
	            JsonDataReader.getData("src/test/resources/testdata/users.json");

	    Object[][] data = new Object[dataList.size()][2];

	    for (int i = 0; i < dataList.size(); i++) {
	        data[i][0] = dataList.get(i).get("username");
	        data[i][1] = dataList.get(i).get("password");
	    }
	    return data;
	}

	@Test(dataProvider = "loginData", groups = {"smoke", "regression"})
	public void loginTest(String username, String password){
	    LoginPage login = new LoginPage();
	    login.goToLoginPage();
	    login.login(username, password);
	    Assert.assertTrue(login.getTitle().contains("Swag Labs"));
	}
	
	
	
	@DataProvider(name = "excelLogin")
	public Object[][] excelLoginData() throws IOException {
	    return ExcelUtils.getSheetData("src/test/resources/testdata/login.xlsx", "Sheet1");
	}

	@Test(dataProvider = "excelLogin")
	public void loginWithExcel(String username, String password) {
	    LoginPage login = new LoginPage();
	    login.goToLoginPage();
	    login.login(username, password);
	    // keep assertion same or adjust for locked user check
	    Assert.assertTrue(login.getTitle().contains("Swag Labs"));
	}


}
