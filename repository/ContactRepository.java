package repository;

import entity.Contact;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactRepository {
    private List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public Optional<Contact> getContactById(int id) {
        return contacts.stream().filter(c -> c.getId() == id).findFirst();
    }

    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }
}
