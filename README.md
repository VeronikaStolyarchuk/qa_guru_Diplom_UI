# Автоматизация тестирования сайта [Русский букет](https://rus-buket.ru)

<p align="center">
  <img src="media/logo/rus-buket.svg" width="550" alt="Русский букет">
</p>

## Про проект
В рамках проекта реализуется тестирование веб‑сайта [Русский букет](https://rus-buket.ru) с применением комбинации автоматизированных UI‑тестов и ручных проверок. Проект базируется на современном технологическом стеке, предусматривает интеграцию CI/CD и организацию системы формирования отчётности.

## Технологический стек

<p align="center">
<a href="https://www.java.com/"><img src="media/logo/java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://www.jetbrains.com/idea/"><img src="media/logo/intellij-idea.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://gradle.org/"><img src="media/logo/gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://junit.org/junit5/"><img src="media/logo/junit5.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://github.com/"><img src="media/logo/github.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://selenide.org/"><img src="media/logo/selenide.svg" width="50" height="50"  alt="Selenide"/></a>
<a href="https://aerokube.com/selenoid/"><img src="media/logo/selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="media/logo/allure.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://qameta.io/"><img src="media/logo/allureTO.svg" width="50" height="50"  alt="Allure TestOps"/></a>
<a href="https://www.jenkins.io/"><img src="media/logo/jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://www.atlassian.com/ru/software/jira"><img src="media/logo/jira.svg" width="50" height="50"  alt="Atlassian Jira"/></a>
</p>

Автотесты реализованы на Java с применением Selenide и паттерна Page Object. Сборка проекта — Gradle, запуск тестов — на базе JUnit 5. Для сокращения шаблонного кода используются аннотации Lombok.
CI/CD-пайплайн развёрнут в Jenkins; результаты прогонов визуализируются в Allure Report, а критические статусы автоматически отправляются в Telegram. Предусмотрены интеграции с Allure TestOps и Jira для управления дефектами и тестовой документацией.

## Автотесты, реализованные в проекте
### <img src="media/logo/smoke.svg" width="30" alt="Smoke"> Smoke-проверки (критичный функционал)
Эти тесты запускаются при каждом CI‑прогоне и быстро подтверждают работоспособность основных сценариев:
- Открытие главной страницы и наличие элементов меню.
- Отображение каталога и основных разделов.
- Поиск товаров по популярным запросам («Лилии», «Альстромерии», «Ирисы»).
- Добавление товара в корзину и проверка наличия в корзине.
- Оформление заказа в 1 клик (быстрый заказ).

### <img src="media/logo/functional-testing.svg" width="30" alt="Functional testing"> Функциональные проверки (детализация и валидация)
Более узкие сценарии, проверяющие корректность поведения и валидацию данных:
- Отображение логотипа на главной странице.
- Переключение языка интерфейса и проверка хедера.
- Выбор города доставки и отображение выбранного города.
- Удаление товара из корзины.
- Валидация полей: некорректный номер телефона в форме обратной связи, пустой пароль при авторизации.

## Запуск автотестов:

### Локальный запуск:
```
gradle clean test
```
### Удалённый запуск через Jenkins:
```
clean test
-Dbrowser=$BROWSER
-DbrowserVersion=$BROWSER_VERSION
-DbrowserSize=$BROWSER_SIZE
-DbaseUrl=$BASE_URL
-Dheadless=$HEADLESS
```

<a id="сборка-в-jenkins"></a>

## <img width="4%" style="vertical-align:middle" title="Jenkins" src="media/logo/jenkins.svg"> [Сборка в Jenkins](https://jenkins.qa.guru/job/41_VeronikaStolyarchuk_guru-qa_DiplomUI/)

Для запуска сборки необходимо перейти в раздел <code>Собрать с параметрами</code> и нажать кнопку <code>Собрать</code>.
<p align="center">
<img title="Jenkins Build" src="media/screens/jenkins.png">
</p>
После завершения сборки в блоке «История сборок» рядом с номером сборки отображаются значки Allure Report и Allure TestOps. При нажатии на значок Allure Report открывается отчёт по тестам, а при клике на Allure TestOps — страница с тестовой документацией.

____
<a id="allureReport"></a>
## <img width="30" style="vertical-align:middle" title="Allure Report" src="media/logo/allure.svg"> </a> Пример <a target="_blank" href="https://jenkins.qa.guru/job/41_VeronikaStolyarchuk_guru-qa_DiplomUI/12/allure/"> Allure-отчета </a>
Allure Report используется для отображения результатов выполнения тестов, шагов тестирования и вложений.
<p align="center">
<img title="Allure Overview" src="media/screens/allure-report.png">
<img title="Allure Overview" src="media/screens/allure.png">
</p>

## <img width="30" style="vertical-align:middle" title="Allure TestOps" src="media/logo/allureTO.svg"> Система управления тест-кейсами [TestOps](https://allure.qa.guru/launch/56408)

Система TestOps предназначена для управления тест‑кейсами. Функционал включает отображение тест‑кейсов и информацию о запусках с актуальными статусами.

<p align="center">
<img title="Testops" src="media/screens/AllureTestOps.png"><br>
<img title="Testops-suites" src="media/screens/Allure-Results.png"><br>
</p>

---
## <img width="30" style="vertical-align:middle" title="Allure TestOps" src="media/logo/telegram.svg"> Получение уведомлений в Telegram с использованием бота

Система TestOps предназначена для управления тест‑кейсами. Функционал включает отображение тест‑кейсов и информацию о запусках с актуальными статусами.

<p align="center">
<img title="Telegram" src="media/screens/telegram.png"><br>
</p>

---

### <img width="30" style="vertical-align:middle" title="Selenoid" src="media/logo/selenoid.svg"> Видео выполнения тестов
В отчетах Allure для каждого теста прикреплен видео-скриншот прохождения теста.

<p align="center">
  <img src="media/screens/Video.gif" width="800">
  </img>
</p>

---
