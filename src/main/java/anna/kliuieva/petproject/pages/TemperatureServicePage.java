package anna.kliuieva.petproject.pages;

import anna.kliuieva.petproject.utils.Waiters;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TemperatureServicePage extends AbstractPage {

    @AndroidFindBy(id = "unesena_vrednost")
    private WebElement inputField;

    @AndroidFindBy(id = "jedinica")
    private WebElement oneButton;

    @AndroidFindBy(id = "dvojka")
    private WebElement twoButton;

    @AndroidFindBy(id = "trojka")
    private WebElement threeButton;

    @AndroidFindBy(id = "cetvorka")
    private WebElement fourButton;

    @AndroidFindBy(id = "petica")
    private WebElement fiveButton;

    @AndroidFindBy(id = "sestica")
    private WebElement sixButton;

    @AndroidFindBy(id = "sedmica")
    private WebElement sevenButon;

    @AndroidFindBy(id = "osmica")
    private WebElement eightButton;

    @AndroidFindBy(id = "devetka")
    private WebElement nineButton;

    @AndroidFindBy(id = "nula")
    private WebElement zeroButton;

    @AndroidFindBy(id = "zarez")
    private WebElement commaButton;

    @AndroidFindBy(id = "delete")
    private WebElement backButton;

    @AndroidFindBy(id = "clear")
    private WebElement clearButton;

    @AndroidFindBy(id = "plusminus")
    private WebElement plusMinusButton;

    @AndroidFindBy(id = "spustitastaturu")
    private WebElement okButton;

    @AndroidFindBy(id = "imageView16")
    private WebElement calculatorIcon;

    @AndroidFindBy(id = "vrednost_text_view")
    private List<WebElement> degreeValues;

    @AndroidFindBy(id = "tocak")
    private WebElement selectDergee;

    @AndroidFindBy(id = "ime_jedinice_text_view")
    private List<WebElement> temperatureIcons;

    @AndroidFindBy(id = "action_bar_title")
    private WebElement actionBar;

    @AndroidFindBy(id = "home")
    private WebElement homeButton;


    public TemperatureServicePage() {
        super();
        waitForLoad();
    }

    @Override
    public void waitForLoad() {
        if (!isLoaded()) {
            Waiters.waitForAppear(getLoadableElement());
            removeCommercialWindow();
        }
    }

    @Override
    public boolean isLoaded() {
        return Waiters.isElementDisplayed(getLoadableElement());
    }

    @Override
    public WebElement getLoadableElement() {
        return inputField;
    }

    public void enterValue(String number) {
        for (int i = 0; i < number.length(); i++) {
            switch (number.charAt(i)) {
                case '1':
                    oneButton.click();
                    continue;
                case '2':
                    twoButton.click();
                    continue;
                case '3':
                    threeButton.click();
                    continue;
                case '4':
                    fourButton.click();
                    continue;
                case '5':
                    fiveButton.click();
                    continue;
                case '6':
                    sixButton.click();
                    continue;
                case '7':
                    sevenButon.click();
                    continue;
                case '8':
                    eightButton.click();
                    continue;
                case '9':
                    nineButton.click();
                    continue;
                case '0':
                    zeroButton.click();
                    continue;
                case '.':
                    commaButton.click();
                    continue;
                default:
                    break;
            }
        }

    }

    public String getEnteredNumber() {
        return inputField.getText();
    }

    public void openPanelWithNumbers() {
        calculatorIcon.click();
    }

    public void closePanelWithNumbers() {
        okButton.click();
    }

    public void clickPlusMinusButton() {
        plusMinusButton.click();
    }

    public void clickClearButton() {
        clearButton.click();
    }

    public void clickBackButton() {
        backButton.click();
    }

    public double getCelsiusDegreeValue() {
        return Double.parseDouble(degreeValues.get(0).getText());
    }

    public double getFahrenheitDegreeValue() {
        return Math.floor(Double.parseDouble(degreeValues.get(1).getText()) * 10) / 10;
    }

    public double getKelvinDegreeValue() {
        return Math.ceil(Double.parseDouble(degreeValues.get(2).getText()) * 100) / 100;
    }

    public String getCelsiusDegreeIcon() {
        return temperatureIcons.get(0).getText();
    }

    public String getFahrenheitDegreeIcon() {
        return temperatureIcons.get(1).getText();
    }

    public String getKelvinDegreeIcon() {
        return temperatureIcons.get(2).getText();
    }


    public boolean isPanelWithNumbersDisplayed() {
        return Waiters.isElementDisplayed(oneButton);
    }

    public String getActionBarTitle() {
        return actionBar.getText();
    }

    public LeftMenuPage clickHomeButton() {
        homeButton.click();
        return new LeftMenuPage();
    }
}
