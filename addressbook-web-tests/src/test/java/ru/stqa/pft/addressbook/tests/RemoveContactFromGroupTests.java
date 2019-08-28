package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RemoveContactFromGroupTests extends TestBase {

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

    if (app.db().groups().iterator().next().getContacts().size() == 0) {
     Groups groups = app.db().groups();
     GroupData groupToAdded = groups.iterator().next();
     Contacts contacts = app.db().contacts();
     ContactData addedContact = contacts.iterator().next();
     app.contact().addContactToGroup(addedContact, groupToAdded);
    }
  }

  @Test
  public void testRemoveContactFromGroup() {
    app.goTo().gotoHomePage();
    Groups gBefore = app.db().groups();
    GroupData group = gBefore.iterator().next();
    ContactData contactToRemove = group.getContacts().iterator().next();
    app.contact().removeFromGroup(group, contactToRemove);
    Groups gAfter = app.db().groups();
    assertThat(gAfter.iterator().next().getContacts(), equalTo(gBefore.iterator().next().getContacts().withRemoved(contactToRemove)));
  }

}
