package common;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AbstractPage {

	public void openAnyUrl(WebDriver driver, String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void back(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void clickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();	
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(value);	
	}
	
	public void selectItemInDropdown(WebDriver driver, String locator, String textItem) {
		WebElement element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		select.deselectByVisibleText(textItem);	
	}
	
	public String getFirstItemSelected(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();	
	}
	
	public String getAttributeValue(WebDriver driver, String locator, String attribuite) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attribuite);	
	}
	
	public String getTextElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();	
	}
	
	public int getSizeElement(WebDriver driver, String locator) {
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		return elements.size();	
	}
	
	public void checkToTheCheckbox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(!element.isSelected()) {
			element.click();	
		}
	}
	
	public void uncheckToTheCheckbox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(element.isSelected()) {
			element.click();	
		}
	}
	
	public boolean isControlDisplayed(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();		
	}
	
	public boolean isControSelected(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();		
	}
	
	public boolean isControEnabled(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isEnabled();		
	}
	
	public void acceptAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.accept();		
	}
	
	public void cancelAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();		
	}
	
	public String getTextAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		return alert.getText();		
	}
	
	public void sendKeyToAlert(WebDriver driver, String value) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(value);		
	}
	
	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void switchToChildWindow(WebDriver driver, String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public boolean closeAllWithoutParentWindows(WebDriver driver, String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}
	
	public void switchToIframe(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		driver.switchTo().frame(element);		
	}
	
	public void doubleClickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.doubleClick(element);
	}
	
	public void hoverMouseToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.moveToElement(element);
	}
	
	public void righClickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.contextClick(element);
	}
	
	public void dragAndDropElement(WebDriver driver, String draggable, String droptarget ) {
		WebElement sourceElement = driver.findElement(By.xpath(draggable));
		WebElement tagetElement = driver.findElement(By.xpath(droptarget));
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, tagetElement).build().perform();
		action.release();
	}
	
	public void keyPressElement(WebDriver driver, String locator, int index) {
		List<WebElement> listNumbers = driver.findElements(By.xpath(locator));
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).build().perform();
		listNumbers.get(index).click();
		action.keyUp(Keys.CONTROL).build().perform();
	}

	public void uploadBySendkeys_API(WebDriver driver, String locator, String imagePath) {
		String workingDirectory = System.getProperty("user.dir");
		String filePath = workingDirectory + imagePath;
		WebElement addFiles = driver.findElement(By.xpath(locator));
		addFiles.sendKeys(filePath);
	}

	@Test
	public void uploadByAutoIT(WebDriver driver, String locator, String imagePath, String autoITBrowserPath) throws Exception {
		String workingDirectory = System.getProperty("user.dir");
		String filePath = workingDirectory + imagePath;
		WebElement addFiles = driver.findElement(By.xpath(locator));
		addFiles.click();
		Runtime.getRuntime().exec(new String[] {autoITBrowserPath, filePath });
	}
	
	@Test
	public void uploadByRobot(WebDriver driver, String locator, String imagePath) throws Exception {
		String workingDirectory = System.getProperty("user.dir");
		String filePath = workingDirectory + imagePath;
		StringSelection select = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		WebElement addFiles = driver.findElement(By.cssSelector(".fileinput-button"));
		addFiles.click();
		Robot robot = new Robot();
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public Object executeForBrowserElement(WebDriver driver, String javaSript) {
        try {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    return js.executeScript(javaSript);
        } catch (Exception e) {
                    e.getMessage();
                    return null;
        }
}
	
	public Object clickByWebElement(WebDriver driver, WebElement element) {
        try {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    return js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
                    e.getMessage();
                    return null;
        }
}
	
	public void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='6px groove red'", element);
}
	
	public Object removeAttributeInDOM(WebDriver driver, WebElement element, String attribute) {
        try {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
        } catch (Exception e) {
                    e.getMessage();
                    return null;
        }
}
	
	public Object scrollToBottomPage(WebDriver driver) {
        try {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        } catch (Exception e) {
                    e.getMessage();
                    return null;
        }
}
	
	public void waitForControlPresence(WebDriver driver, String locator) {
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	public void waitForControlVisible(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForControlClickable(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForControlInvisible(WebDriver driver, String locator) {
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	public void waitForAlertPresence(WebDriver driver, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
	}
		
}