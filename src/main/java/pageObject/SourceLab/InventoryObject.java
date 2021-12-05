package pageObject.SourceLab;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUI.SourceLab.InventoryPageUI;
import pageUINopCommerce.HomePageUI;

public class InventoryObject extends BasePage{
	private WebDriver driver;
	
	public InventoryObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectSortType(WebDriver driver, String sortType) {
		waitForElementClickable(driver, InventoryPageUI.SORT_CONTAINER);
		selectItemInDropdown(driver, InventoryPageUI.SORT_CONTAINER, sortType);
	}

	public boolean isSortByNameAscending(WebDriver driver) {
		List<WebElement> productElements = driver.findElements(getByXpath(InventoryPageUI.INVENTORY_NAME));
		ArrayList<String> productNames = new ArrayList<String>();
		for (WebElement product : productElements) {
			productNames.add(product.getText());
		}
		
		System.out.println("Before sort name ascending: " + productNames);
		List<String> productNameClones = new ArrayList<String>();
		productNameClones = (ArrayList<String>) productNames.clone();
		
		Collections.sort(productNameClones);
		System.out.println("After sort name ascending: " + productNameClones);
		
		return productNames.equals(productNameClones);
	}
	
	public boolean isSortByNameDescending(WebDriver driver) {
		List<WebElement> productElements = driver.findElements(getByXpath(InventoryPageUI.INVENTORY_NAME));
		ArrayList<String> productNames = new ArrayList<String>();
		for (WebElement product : productElements) {
			productNames.add(product.getText());
		}
		
		System.out.println("Before sort name descending: " + productNames);
		List<String> productNameClones = new ArrayList<String>();
		productNameClones = (ArrayList<String>) productNames.clone();
		
		Collections.sort(productNameClones);
		Collections.reverse(productNameClones);
		
		System.out.println("After sort name descending: " + productNameClones);
		
		return productNames.equals(productNameClones);
	}
	
	public boolean isSortByPriceAscending(WebDriver driver) {
		List<WebElement> productPriceElements = driver.findElements(getByXpath(InventoryPageUI.INVENTORY_PRICE));
		ArrayList<Float> productPrices = new ArrayList<Float>();
		for (WebElement product : productPriceElements) {
			productPrices.add(Float.parseFloat(product.getText().replace("$", "")));
		}
		
		System.out.println("Before sort price ascending: " + productPrices);
		List<String> productPriceClones = new ArrayList<String>();
		productPriceClones = (ArrayList<String>) productPrices.clone();
		
		Collections.sort(productPriceClones);
		System.out.println("After sort price ascending: " + productPriceClones);
		
		return productPrices.equals(productPriceClones);
	}
	
	public boolean isSortByPriceDescending(WebDriver driver) {
		List<WebElement> productPriceElements = driver.findElements(getByXpath(InventoryPageUI.INVENTORY_PRICE));
		ArrayList<Float> productPrices = new ArrayList<Float>();
		for (WebElement product : productPriceElements) {
			productPrices.add(Float.parseFloat(product.getText().replace("$", "")));
		}
		
		System.out.println("Before sort price descending: " + productPrices);
		List<String> productPriceClones = new ArrayList<String>();
		productPriceClones = (ArrayList<String>) productPrices.clone();
		
		Collections.sort(productPriceClones);
		Collections.reverse(productPriceClones);
		System.out.println("After sort price descending: " + productPriceClones);
		
		return productPrices.equals(productPriceClones);
	}
}
