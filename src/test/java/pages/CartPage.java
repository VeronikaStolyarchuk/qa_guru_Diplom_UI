package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Страница корзины")
public class CartPage {
    private final ElementsCollection categoryMenuItems = $$(".rb-menu__head-item");
    private final SelenideElement bouquetProductCard = $(".rb-product-card__buy");
    private final SelenideElement cartButton = $(".header-cart");
    private final ElementsCollection cartItems = $$(".cart-product");
    private final SelenideElement removeButton = $(".cart-product__btn");
    private final SelenideElement confirmationMessage = $(".tingle-modal-box__content");
    private final SelenideElement confirmationButton = $(".tingle-modal--visible .rb-btn_success");
    private final SelenideElement emptyCartText = $(".cart__empty");

    @Step("Выбрать категорию «Букеты»")
    public CartPage selectBouquetsCategory(){
        categoryMenuItems.findBy(text("Букеты")).click();
        return this;
    }

    @Step("Прокрутить страницу вниз")
    public CartPage scrollToBottom(){
        executeJavaScript("window.scrollBy(0, 500)");
        return this;
    }

    @Step("Добавить выбранный букет в корзину")
    public CartPage addSelectedBouquetToCart(){
        bouquetProductCard.shouldBe(visible).click();
        return this;
    }

    @Step("Открыть корзину")
    public CartPage openShoppingCart(){
        cartButton.click();
        return this;
    }

    @Step("Проверить, что товар добавлен в корзину")
    public CartPage verifyProductIsInCart(){
        cartItems.shouldHave(CollectionCondition.sizeGreaterThan(0));
        return this;
    }

    @Step("Нажать кнопку удаления товара")
    public CartPage removeProductFromCart(){
        removeButton.click();
        return this;
    }

    @Step("Проверить текст подтверждения удаления: {value}")
    public CartPage verifyRemoveConfirmationText(String value){
        confirmationMessage.shouldHave(text(value));
        return this;
    }

    @Step("Подтвердить удаление товара")
    public CartPage clickRemoveButton() {
        confirmationButton.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить, что корзина пуста: {value}")
    public void verifyProductIsRemoved(String value){
        emptyCartText.shouldHave(text(value));
    }
}
