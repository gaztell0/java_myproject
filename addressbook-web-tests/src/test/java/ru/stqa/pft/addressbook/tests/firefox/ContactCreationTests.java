package ru.stqa.pft.addressbook.tests.firefox;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.gotoAddnewPage();
    app.fillContactForm(new ContactData("Михаил", "Петрович", "Жуков", "Bug", "ADT Inc.", "Россия, г. Москва, ул. Аргуновская 35", "84957757575", "89262881045", "+74951231212", "ivanmercury@yandex.ru", "ivanmercury@gmail.com", "1989"));
    app.initContactCreation();
    app.returnToHomePage();
    app.logout();
  }

}
