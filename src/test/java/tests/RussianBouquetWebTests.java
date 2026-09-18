package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Owner("Veronika Stolyarchuk")
@Feature ("UI Тестирование")
@DisplayName("Проверка rus-buket.ru")
@Tag("WEB")
public class RussianBouquetWebTests extends BaseTest {

    @Test
    @Tag("smoke")
    @Tag("main-page")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка открытия главной страницы")
    void mainPageOpenTest() {
        mainPage
                .openPage()
                .acceptCookies()
                .verifyMainMenuItems();
    }

    @Test
    @Tag("smoke")
    @Tag("main-page")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Проверка отображения логотипа на главной странице")
    void mainPageLogoTest() {
        mainPage
                .openPage()
                .acceptCookies()
                .verifyLogoIsVisible();
    }

    @Test
    @Tag("localization")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка отображения хедера при переключении языка")
    void headerAfterLanguageChangeTest() {
        mainPage
                .openPage()
                .acceptCookies()
                .selectLanguage()
                .verifyHeaderItemsInEnglish();
    }

    @Test
    @Tag("smoke")
    @Tag("catalog")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка отображения основных разделов в Каталоге")
    void mainPageCatalogTest() {
        mainPage
                .openPage()
                .acceptCookies()
                .openCatalog()
                .verifyCatalogMenuItems();
    }

    @ParameterizedTest(name = "Поиск товара: {0}")
    @ValueSource(strings = {"Лилии", "Альстромерии", "Ирисы"})
    @Tag("smoke")
    @Tag("search")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка поиска товаров")
    void searchProductTestNotBeEmpty(String flower) {
        mainPage
                .openPage()
                .acceptCookies();
        searchPage
                .searchForProduct(flower)
                .verifyResultMatches(flower);
    }

    @ParameterizedTest(name = "Выбор города доставки: {0}")
    @ValueSource(strings = {"Москва", "Уфа", "Липецк"})
    @Tag("delivery")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка отображения выбранного города доставки")
    void verifyDeliveryCityDisplay(String deliveryCity) {
        mainPage
                .openPage()
                .acceptCookies()
                .selectDeliveryCity(deliveryCity)
                .shouldDisplaySelectedCity(deliveryCity);
    }

    @Test
    @Tag("smoke")
    @Tag("cart")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка добавления товара в корзину")
    void addProductToCartTest() {
        mainPage
                .openPage()
                .acceptCookies()
                .openCatalog();
        cartPage
                .selectBouquetsCategory()
                .scrollToBottom()
                .addSelectedBouquetToCart()
                .openShoppingCart()
                .verifyProductIsInCart();
    }

    @Test
    @Tag("cart")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка удаления товара из корзины")
    void removeProductFromCartTest() {
        mainPage
                .openPage()
                .acceptCookies()
                .openCatalog();
        cartPage
                .selectBouquetsCategory()
                .scrollToBottom()
                .addSelectedBouquetToCart()
                .openShoppingCart()
                .verifyProductIsInCart()
                .removeProductFromCart()
                .verifyRemoveConfirmationText(testData.REMOVE_CONFIRMATION_MESSAGE)
                .clickRemoveButton()
                .verifyProductIsRemoved(testData.EMPTY_CART_MESSAGE);
    }

    @Test
    @Tag("smoke")
    @Tag("quick-order")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Оформить заказ в 1 клик")
    void checkQuickOrderStatusTest() {
        mainPage
                .openPage()
                .acceptCookies()
                .openCatalog()
                .openCatalogDropdown();
        quickOrderPage
                .addToQuickOrder()
                .typeUserNumber(testData.userPhone)
                .sendOrderRequest()
                .verifyModalMessage(testData.SUCCESS_TEXT_OF_ORDER);
    }

    @Test
    @Tag("feedback")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Ввести некорректный номер для обратной связи")
    void phoneFieldRejectsInvalidNumberTest() {
        mainPage
                .openPage()
                .acceptCookies()
                .hoverContactPhone()
                .openCallbackForm()
                .setInvalidPhoneNumber(testData.USER_INVALID_PHONE)
                .submitPhoneRequest()
                .shouldOpenErrorModal(testData.ERROR_TEXT_OF_PHONE_NUMBER);
    }

    @Test
    @Tag("auth")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Авторизация с пустым паролем")
    void loginWithEmptyPasswordTest() {
        mainPage
                .openPage()
                .acceptCookies();
        authPage
                .clickLoginField()
                .setEmailLogin(testData.userEmail)
                .clickLogInButton()
                .assertPasswordFieldValidationMessage();
    }
}

