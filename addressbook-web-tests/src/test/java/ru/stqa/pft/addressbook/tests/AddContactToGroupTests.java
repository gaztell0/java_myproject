package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Михаил").withLastname("Волков").withGroup("test1"), true);
    }

    if (app.db().contacts().iterator().next().getGroups().size() == app.db().groups().size()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }

  }

  @Test
  public void testAddContactToGroup() {
    app.goTo().gotoHomePage();
    Contacts cBefore = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData addedToGroupContact = cBefore.iterator().next();
    Groups alreadyUsedGroups = addedToGroupContact.getGroups(); //удаление групп, уже привязанных к контакту
    for (GroupData group : alreadyUsedGroups) {
      groups.remove(group);
    }
    GroupData groupToAddedContact = groups.iterator().next();
    app.contact().addContactToGroup(addedToGroupContact, groupToAddedContact);
    Contacts cAfter = app.db().contacts();
    assertThat(cAfter.iterator().next().getGroups(), equalTo(cBefore.iterator().next().getGroups().withAdded(groupToAddedContact)));
    verifyContactListInUi();
  }
}
