package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class AddContactToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if(app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Михаил").withLastname("Волков").withGroup("test1"), true);
    }
  }


  @Test
  public void testAddContactToGroup() {
    ContactData cBefore = app.db().contacts().iterator().next();
    GroupData gBefore = app.db().groups().iterator().next();
    app.goTo().gotoHomePage();
    app.contact().addContactToGroup(cBefore, gBefore);
    app.goTo().gotoHomePage();

    ContactData cAfter = app.db().contacts().iterator().next();
    GroupData gAfter= app.db().groups().iterator().next();

    MatcherAssert.assertThat(cAfter.getGroups(), hasItem(gBefore));
    MatcherAssert.assertThat(gAfter.getContacts(), hasItem(cBefore));
  }
}
