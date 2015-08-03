package com.wikia.webdriver.pageobjectsfactory.pageobject.mobile;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MobileEditPreviewPageObject extends MobileBasePageObject {

  public MobileEditPreviewPageObject(WebDriver driver) {
    super(driver);
  }

  @FindBy(css = "#wkSummary")
  private WebElement summaryTextBox;
  @FindBy(css = "#wkContinueEditing")
  private WebElement keepEditingButton;
  @FindBy(css = "#wkSave")
  private WebElement publishButton;
  @FindBy(css = "#wkMdlCnt #mw-content-text > p")
  private WebElement articleText;
  @FindBy(css = "#wkMdlTlBar > span")
  private WebElement selectedPageHeader;
  @FindBy(css = "#wkMdlWrp.open")
  private WebElement previewZoomer;

  public void verifyEditModeContent(String targetText) {
    Assertion.assertStringContains(targetText, articleText.getText());
    PageObjectLogging.log(
        "verifyEditModeContent",
        "The article shows " + targetText,
        true
    );
  }

  public void verifyPreviewPageHeader(String targetText) {
    Assertion.assertStringContains(targetText, selectedPageHeader.getText());
    PageObjectLogging.log(
        "verifyPreviewPageHeader",
        "The summary shows " + targetText,
        true
    );
  }

  public MobileEditModePageObject clickKeepEditing() {
    wait.forElementVisible(keepEditingButton);
    waitForElementClickableByElement(keepEditingButton);
    jsActions.click(keepEditingButton);
    return new MobileEditModePageObject(driver);
  }

  public MobileArticlePageObject clickPublish() {
    wait.forElementVisible(publishButton);
    waitForElementClickableByElement(publishButton);
    jsActions.click(publishButton);
    return new MobileArticlePageObject(driver);
  }

  public void typeSummaryText(String text) {
    summaryTextBox.sendKeys(text);
  }

  public void verifySummaryText(String targetText) {
    wait.forElementVisible(previewZoomer);
    waitForValueToBePresentInElementsAttributeByElement(
        summaryTextBox,
        "value",
        targetText
    );
    PageObjectLogging.log(
        "verifySummaryText",
        "The summary shows " + targetText,
        true
    );
  }
}
