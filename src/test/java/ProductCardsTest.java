import filters.Filters;
import filters.Size;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProductCardsTest extends TestBase {

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
//        WebDriverManager.edgedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        driver = new ChromeDriver(options);
//        driver = new FirefoxDriver(options);
//        driver = new EdgeDriver(options);
        driver.get(getUrl + "catalog/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        filters = new Filters(driver);
        size = new Size(driver);
        basket = new Basket(driver);
    }


    //Переход в раздел колец, в фильтре выбираем кольцо, далее переходим в карточку товара и переключаемся между размерами
    //смотрим чтобы менялся размер, кладем в корзину и проверяем что верный размер попал в корзину

    //с размером 14,5
    @Test
    public void changeSize145() {
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToFirstSizeButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        size.clickOnImageLink();
        String firstCurrentSize = size.getCurrentSize();
        size.clickToFirstCurrentSizeButton();
        String secondCurrentSize = size.getCurrentSize();
        size.clickToSecondCurrentSizeButton();
        String thirdCurrentSize = size.getCurrentSize();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        String sizeHeader = size.getSizeHeader();
        assertNotEquals(firstCurrentSize, secondCurrentSize);
        assertNotEquals(secondCurrentSize, thirdCurrentSize);
        assertNotEquals(firstCurrentSize, thirdCurrentSize);
        assertEquals("Размер: " + thirdCurrentSize, sizeHeader);
    }

    //с размером 15,5
    @Test
    public void changeSize155() {
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToSecondSizeButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        size.clickOnImageLink();
        String firstCurrentSize = size.getCurrentSize();
        size.clickToFirstCurrentSizeButton();
        String secondCurrentSize = size.getCurrentSize();
        size.clickToSecondCurrentSizeButton();
        String thirdCurrentSize = size.getCurrentSize();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        String sizeHeader = size.getSizeHeader();
        assertNotEquals(firstCurrentSize, secondCurrentSize);
        assertNotEquals(secondCurrentSize, thirdCurrentSize);
        assertNotEquals(firstCurrentSize, thirdCurrentSize);
        assertEquals("Размер: " + thirdCurrentSize, sizeHeader);
    }

    //с размером 16,5
    @Test
    public void changeSize165() {
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToThirdSizeButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        size.clickOnImageLink();
        String firstCurrentSize = size.getCurrentSize();
        size.clickToFirstCurrentSizeButton();
        String secondCurrentSize = size.getCurrentSize();
        size.clickToSecondCurrentSizeButton();
        String thirdCurrentSize = size.getCurrentSize();
        System.out.println(firstCurrentSize);
        System.out.println(secondCurrentSize);
        System.out.println(thirdCurrentSize);
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        String sizeHeader = size.getSizeHeader();
        assertNotEquals(firstCurrentSize, secondCurrentSize);
        assertNotEquals(secondCurrentSize, thirdCurrentSize);
        assertNotEquals(firstCurrentSize, thirdCurrentSize);
        assertEquals("Размер: " + thirdCurrentSize, sizeHeader);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
