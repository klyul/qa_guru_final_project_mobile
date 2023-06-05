package screens;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainScreen {

    final Logger logger = LoggerFactory.getLogger(MainScreen.class);
    private final SelenideElement closeSplashButton = $(id("ru.tutu.tutu_emp:id/skip"));
    private final SelenideElement fromField = $$(id("ru.tutu.tutu_emp:id/buttonHintTextView")).findBy(Condition.text("From"));
    private final SelenideElement toField = $$(id("ru.tutu.tutu_emp:id/buttonHintTextView")).findBy(Condition.text("To"));

    private final SelenideElement routesButton = $(id("ru.tutu.tutu_emp:id/progress_button_layout"));

    private final ElementsCollection cityListItems = $$(id("ru.tutu.tutu_emp:id/tvCity"));
    private final ElementsCollection mainPagetexts = $$(id("ru.tutu.tutu_emp:id/buttonTextView"));

    @Step("Закрыть сплэш-скрин")
    public void closeSplashScreen() {
        closeSplashButton.click();
    }

    @Step("Выбрать город From {0}")
    public void chooseCityFrom(String cityName) {
        fromField.click();
        cityListItems.findBy(Condition.text(cityName)).click();
    }

    @Step("Выбрать город To {0}")
    public void chooseCityTo(String cityName) {
        toField.click();
        cityListItems.findBy(Condition.text(cityName)).click();
    }

    @Step("Проверить что указанные города выбрались в From и To")
    public void checkChosenCities(String fromCity, String toCity) {
        mainPagetexts.shouldHave(sizeGreaterThan(0));
        logger.info("The texts are: " + mainPagetexts.texts());
        assertTrue(mainPagetexts.texts().contains(fromCity), "Город заданный для From не найден на главной странице");
        assertTrue(mainPagetexts.texts().contains(toCity), "Город заданный для To не найден на главной странице");
    }

    @Step("Перейти на предложения маршрутов")
    public void goToRoutesScreen() {
        routesButton.click();
    }


}

