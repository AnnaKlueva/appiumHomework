import anna.kliuieva.petproject.pages.TemperatureServicePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TemperatureServiceTest extends TestNGRunner {

    public static final String ALL_NUMBERS = "1023456789.1";
    public static final String POSITIVE_TEMPERATURE = "45";
    public static final double KELVIN_COEFFICIENT = 273.15;
    public static final double FAHRENHEIT_COEFFICIENT = 32;

    @Test(groups = "temperatureService")
    public void verifyThatAllNumberButtonAreClickable(){
        TemperatureServicePage temperatureServicePage = menuPage.selectTemperatureService();
        temperatureServicePage.openPanelWithNumbers();
        temperatureServicePage.enterValue(ALL_NUMBERS);

        Assert.assertEquals(temperatureServicePage.getEnteredNumber(), ALL_NUMBERS, "Not all buttons with numbers are working correctly");
    }

    @Test(groups = "temperatureService")
    public void verifyClearButtonFunctionality(){
        TemperatureServicePage temperatureServicePage = menuPage.selectTemperatureService();
        temperatureServicePage.openPanelWithNumbers();
        temperatureServicePage.enterValue(POSITIVE_TEMPERATURE);
        temperatureServicePage.clickClearButton();

        Assert.assertEquals(temperatureServicePage.getEnteredNumber(), "1", "Clear button is working correctly");
    }

    @Test(groups = "temperatureService")
    public void verifyBackButtonFunctionality(){
        String expectedValue = POSITIVE_TEMPERATURE.substring(0, POSITIVE_TEMPERATURE.length()-1);

        TemperatureServicePage temperatureServicePage = menuPage.selectTemperatureService();
        temperatureServicePage.openPanelWithNumbers();
        temperatureServicePage.enterValue(POSITIVE_TEMPERATURE);
        temperatureServicePage.clickBackButton();

        Assert.assertEquals(temperatureServicePage.getEnteredNumber(), expectedValue, "Back button is working correctly");
    }

    @Test(groups = "temperatureService")
    public void verifyPlusMinusButtonFunctionality(){
        TemperatureServicePage temperatureServicePage = menuPage.selectTemperatureService();
        temperatureServicePage.openPanelWithNumbers();
        temperatureServicePage.enterValue(POSITIVE_TEMPERATURE);
        temperatureServicePage.clickPlusMinusButton();
        Assert.assertEquals(temperatureServicePage.getEnteredNumber(), "-"+ POSITIVE_TEMPERATURE, "After clicking on +/- button at first time the number didn't become negative");

        temperatureServicePage.clickPlusMinusButton();
        Assert.assertEquals(temperatureServicePage.getEnteredNumber(), POSITIVE_TEMPERATURE, "After clicking on +/- button at second time the number didn't become positive");
    }

    @Test(groups = "temperatureService")
    public void verifyOkButtonFunctionality(){
        TemperatureServicePage temperatureServicePage = menuPage.selectTemperatureService();
        temperatureServicePage.openPanelWithNumbers();
        temperatureServicePage.closePanelWithNumbers();

        Assert.assertFalse(temperatureServicePage.isPanelWithNumbersDisplayed(),  "After clicking on 'Ok' button the panel with number wasn't disappear");
    }

    @Test(groups = "temperatureService")
    public void verifyThatPositiveTemperatureConvertedCorrectly(){
        TemperatureServicePage temperatureServicePage = menuPage.selectTemperatureService();
        temperatureServicePage.openPanelWithNumbers();
        temperatureServicePage.enterValue(POSITIVE_TEMPERATURE);
        temperatureServicePage.closePanelWithNumbers();

        verifyThatTemperatureConvertedCorrectly(temperatureServicePage, POSITIVE_TEMPERATURE);
    }

    @Test(groups = "temperatureService")
    public void verifyThatNegativeTemperatureConvertedCorrectly(){
        TemperatureServicePage temperatureServicePage = menuPage.selectTemperatureService();
        temperatureServicePage.openPanelWithNumbers();
        temperatureServicePage.enterValue(POSITIVE_TEMPERATURE);
        temperatureServicePage.clickPlusMinusButton();
        temperatureServicePage.closePanelWithNumbers();

        verifyThatTemperatureConvertedCorrectly(temperatureServicePage,  "-"+POSITIVE_TEMPERATURE);
    }

    @Test(groups = "temperatureService")
    public void verifyThatDegreeIconsDisplayedCorrectly(){
        TemperatureServicePage temperatureServicePage = menuPage.selectTemperatureService();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(temperatureServicePage.getCelsiusDegreeIcon(), "°C", "Celsius degree icon is displayed INCORRECTLY");
        softAssert.assertEquals(temperatureServicePage.getFahrenheitDegreeIcon(), "°F", "Fahrenheit degree icon is displayed INCORRECTLY");
        softAssert.assertEquals(temperatureServicePage.getKelvinDegreeIcon(), "K", "Kelvin degree icon is displayed INCORRECTLY");
        softAssert.assertAll();
    }

    private void verifyThatTemperatureConvertedCorrectly(TemperatureServicePage temperatureServicePage, String temperature) {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(temperatureServicePage.getCelsiusDegreeValue(), calculateCelsiusDegree(temperature), "Convertation to Celsius degree was INCORRECT");
        softAssert.assertEquals(temperatureServicePage.getFahrenheitDegreeValue(), calculateFahrenheitDegree(temperature), "Convertation to Fahrenheit degree was INCORRECT");
        softAssert.assertEquals(temperatureServicePage.getKelvinDegreeValue(), calculateKelvinDegree(temperature), "Convertation to Kelvin degree was INCORRECT");

        softAssert.assertAll();
    }

    private double calculateKelvinDegree(String temperature) {
        return Math.ceil((Double.parseDouble(temperature)+KELVIN_COEFFICIENT)*100)/100;
    }

    private double calculateFahrenheitDegree(String temperature) {
        return Math.ceil((Double.parseDouble(temperature)*1.8+FAHRENHEIT_COEFFICIENT)*10)/10;
    }

    private double calculateCelsiusDegree(String temperature) {
        return Double.parseDouble(temperature);
    }

}
