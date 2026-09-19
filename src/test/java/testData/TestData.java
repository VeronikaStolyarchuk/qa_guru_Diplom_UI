package testData;

import com.github.javafaker.Faker;
import java.util.Locale;
import static utils.RandomUtils.*;

public class TestData {
    Faker faker = new Faker(new Locale("ru_RU"));
    public String userEmail = faker.internet().emailAddress();
    public String userPhone = getRandomPhone();
    public String USER_INVALID_PHONE = "12345";
    public String SUCCESS_TEXT_OF_ORDER = "Спасибо, Ваша заявка принята: ";
    public String ERROR_TEXT_OF_PHONE_NUMBER = "Пожалуйста, Введите ваш мобильный телефон";
    public String ERROR_TEXT_OF_EMPTY_PASSWORD = "Please fill out this field.";
    public String REMOVE_CONFIRMATION_MESSAGE  = "Вы уверены, что хотите удалить этот товар из корзины?";
    public String EMPTY_CART_MESSAGE  = "Чтобы продолжить оформление заказа пожалуйста добавьте в заказ букет или цветы из";
}
