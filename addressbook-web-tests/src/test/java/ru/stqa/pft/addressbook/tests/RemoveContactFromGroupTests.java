package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class RemoveContactFromGroupTests extends TestBase {

  private Contacts contacts;
  private Groups groups;

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
    ContactData contact = app.db().contacts().iterator().next();
    if (contact.getGroups().size() == 0) {
      GroupData group = app.db().groups().iterator().next();
      app.goTo().gotoHomePage();
      app.contact().addContactToGroup(contact, group);
    }
  }

  @Test
  public void testRemoveContactFromGroup() {
    contacts = app.db().contacts();
    groups = app.db().groups();
    ContactData cRemove = contacts.iterator().next();
    GroupData gRemove = groups.iterator().next();
    app.goTo().gotoHomePage();
    app.contact().contactGroupPage(cRemove);
    app.contact().removeFromGroup(cRemove);

    app.db().cycleByGroups(gRemove);
    app.db().cycleByContacts(cRemove);

    assertThat(cRemove.getGroups(), not(cRemove));
    assertThat(gRemove.getContacts(), not(gRemove));
  }

}
