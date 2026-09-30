# OpenCart UI Test Automation

Training project developed during the QA Automation Engineer program at AIT Technology School. It demonstrates end-to-end UI test automation for the [OpenCart demo store](https://opencart.abstracta.us/) with Java, Selenium WebDriver, TestNG and the Page Object Model.

## Covered scenarios

- product search with positive and negative cases
- category navigation with TestNG DataProvider
- billing and delivery address validation
- shipping and payment selection
- guest checkout up to the order summary
- product return form validation and successful submission

## Technology stack

- Java 21
- Selenium WebDriver 4
- TestNG
- Gradle
- WebDriverManager
- Page Object Model
- explicit waits

## Project structure

- `src/main/java/de/opencart/pages` — page objects for search, categories, checkout and returns
- `src/test/java/de/opencart` — TestNG tests grouped by feature
- `src/test/resources/testng.xml` — complete test suite configuration

## Run the tests

Prerequisites: JDK 21 and Chrome installed locally.

```bash
./gradlew test
```

## Notes

This is a training project, not a production or client application. The automated tests depend on the availability and current markup of the public OpenCart demo website.
