package repository;

import entity.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public Contact getContactById(int id) {
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }

    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }
}
