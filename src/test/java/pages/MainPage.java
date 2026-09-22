package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Главная страница")
public class MainPage {
    private final SelenideElement cookiesAcceptButton = $("#js_gdpr__btn-close");
    private final ElementsCollection mainMenuItems = $$(".header-top__menu-item").filter(visible);
    private final SelenideElement languageSelector = $(".header-top__locales");
    private final SelenideElement englishLanguageOption = $("div[data-lang='en']");
    private final SelenideElement logoHeader = $(".logo");
    private final SelenideElement openCatalogBtn = $("#js_header-middle__btn-catalog");
    private final ElementsCollection catalogGroups = $$(".rb-menu__head-group-name");
    private final SelenideElement deliveryCityInput = $(".js_inter-country__autocomplete");
    private final SelenideElement deliveryCityDropdown = $(".autocomplete-suggestions.autocomplete-suggestions_show");
    private final SelenideElement selectedCityLabel = $(".desktop-city__name");
    private final SelenideElement russianStyleBouquetsLink = $("a[data-alias='russian-style-bouquets']");
    private final SelenideElement buttonContactPhone = $(".desktop-contacts__phone");
    private final SelenideElement requestCallbackBtn = $(".desktop-contacts__callback");
    private final SelenideElement inputPhoneNumber = $("#js_modal-contacts__callback-input");
    private final SelenideElement submitCallbackBtn = $("#js_modal-contacts__callback-btn");
    private final SelenideElement errorNotification = $(".notyf__message");

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open("/russia");
        return this;
    }

    @Step("Принять куки")
    public MainPage acceptCookies() {
        cookiesAcceptButton.shouldBe(visible).click();
        return this;
    }
    @Step("Проверить пункты главного меню на русском языке")
    public void verifyMainMenuItems() {
        mainMenuItems.shouldHave(texts("О нас", "Доставка", "Оплата",
                "Гарантии", "Для продавцов", "Корп. клиентам", "Контакты", "Помощь", "Сервисы"));
    }

    @Step("Сменить язык интерфейса на английский")
    public MainPage selectLanguage() {
        languageSelector.click();
        englishLanguageOption.click();
        return this;
    }

    @Step("Проверить пункты меню после переключения на английский язык")
    public void verifyHeaderItemsInEnglish() {
        mainMenuItems.shouldHave(texts("About us", "Quality guarantee", "Payment",
                "For sellers", "Help & Contact", "FAQ", "Services"));
    }

    @Step("Убедиться, что логотип в шапке отображается")
    public void verifyLogoIsVisible() {
        logoHeader.scrollTo().shouldBe(visible);
    }

    @Step("Открыть каталог товаров")
    public MainPage openCatalog() {
        openCatalogBtn.click();
        return this;
    }

    @Step("Проверить группы категорий в каталоге")
    public void verifyCatalogMenuItems() {
        catalogGroups.shouldHave(texts("Популярное", "Цветы", "Подарки",
                "Другое"));
    }

    @Step("Выбрать город доставки: {value}")
    public MainPage selectDeliveryCity(String value) {
        deliveryCityInput.setValue(value);
        deliveryCityDropdown.hover().shouldBe(visible, enabled).click();
        return this;
    }

    @Step("Проверить, что выбран корректный город: {resultCity}")
    public void shouldDisplaySelectedCity(String resultCity) {
        selectedCityLabel.shouldHave(text(resultCity));
    }

    @Step("Открыть выпадающий список каталога (ссылка «Русские букеты»)")
    public void openCatalogDropdown() {
        russianStyleBouquetsLink.click();
    }

    @Step("Навести курсор на кнопку с телефоном контактов")
    public MainPage hoverContactPhone() {
        buttonContactPhone.hover();
        return this;
    }

    @Step("Открыть форму обратного звонка")
    public MainPage openCallbackForm() {
        requestCallbackBtn.click();
        return this;
    }

    @Step("Ввести некорректный номер телефона: {value}")
    public MainPage setInvalidPhoneNumber(String value) {
        inputPhoneNumber.setValue(value);
        return this;
    }

    @Step("Отправить форму обратного звонка")
    public MainPage submitPhoneRequest() {
        submitCallbackBtn.click();
        return this;
    }

    @Step("Проверить появление уведомления об ошибке: {value}")
    public void shouldOpenErrorModal(String value) {
        errorNotification.shouldHave(Condition.text(value));
    }
}
