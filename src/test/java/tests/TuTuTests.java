package tests;

import components.ScreensMenuComponent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screens.HelpScreen;
import screens.MainScreen;
import screens.ProfileScreen;
import screens.RoutesScreen;

import static com.codeborne.selenide.Selenide.closeWebDriver;


public class TuTuTests extends TestBaseCommon {

    MainScreen mainScreen = new MainScreen();
    RoutesScreen routesScreen = new RoutesScreen();
    ScreensMenuComponent screensMenuComponent = new ScreensMenuComponent();
    ProfileScreen profileScreen = new ProfileScreen();
    HelpScreen helpScreen = new HelpScreen();


    @BeforeEach
    void setUp() {
        mainScreen.closeSplashScreen();
    }

    @Test
    void testSelectingFromAndToCities() {
        String cityFrom = "Moscow";
        String cityTo = "Sochi";

        mainScreen.chooseCityFrom(cityFrom);
        mainScreen.chooseCityTo(cityTo);
        mainScreen.checkChosenCities(cityFrom, cityTo);
    }


    @Test
    void testHotelsAndTicketsLocalization() {
        String cityFrom = "Moscow";
        String cityTo = "Sochi";

        mainScreen.chooseCityFrom(cityFrom);
        mainScreen.chooseCityTo(cityTo);
        mainScreen.goToRoutesScreen();
        routesScreen.checkThatThereAreNoRussianWords();
    }


    @Test
    void checkingHelpScreenMenuItems() {
        screensMenuComponent.clickHelpButton();
        helpScreen.checkScreenMainMenuItems();
    }

    @Test
    void checkingProfileMenuItems() {
        screensMenuComponent.clickProfileButton();
        profileScreen.checkScreenMainMenuItems();
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

}
