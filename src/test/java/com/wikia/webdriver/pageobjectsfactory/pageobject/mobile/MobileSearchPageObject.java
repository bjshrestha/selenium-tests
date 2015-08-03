package com.wikia.webdriver.pageobjectsfactory.pageobject.mobile;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class MobileSearchPageObject extends MobileBasePageObject {

  public MobileSearchPageObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(css = "ul#wkResultUl li.result p a")
  WebElement searchResultList;
  @FindBy(css = "#wkResultNext")
  WebElement searchNextPageButton;
  @FindBy(css = "#wkResultPrev")
  WebElement searchPreviousPageButton;
  @FindBys(@FindBy(css = ".result"))
  List<WebElement> resultList;

  public void verifySearchResultsList() {
    wait.forElementVisible(searchResultList);
    PageObjectLogging.log("verifySearchResultsList", "search results list verified", true, driver);
  }

  public void verifyNextPageButtonIsVisible() {
    wait.forElementVisible(searchNextPageButton);
  }

  public void verifyPreviousPageButtonIsVisible() {
    wait.forElementVisible(searchPreviousPageButton);
  }

  public void clickOnSearchNextPageButton() {
    searchNextPageButton.click();
  }

  public void clickOnSearchPreviousPageButton() {
    searchPreviousPageButton.click();
  }

  public List<String> getResult() {
    List<String> listTitle = new ArrayList<String>();
    for (WebElement elem : resultList) {
      listTitle.add(elem.findElement(By.cssSelector("a")).getText());
    }
    return listTitle;
  }

  public void compareResultsEquals(List<String> beforePagination, List<String> afterPagination) {
    Assertion.assertNumber(afterPagination.size(), beforePagination.size(), "checking length");
    for (int i = 0; i < beforePagination.size(); i++) {
      Assertion.assertEquals(beforePagination.get(i), afterPagination.get(i),
                             "list's elements are not equals");
    }
  }

  public void compareResultsNotEquals(List<String> beforePagination, List<String> afterPagination) {
    Assertion.assertTrue(beforePagination.size() != afterPagination.size(),
                         "sizes of lists are the same");
    for (int i = 0; i < beforePagination.size(); i++) {
      Assertion.assertNotEquals(beforePagination.get(i), afterPagination.get(i),
                                "list's elements are equals");
    }
  }
}
