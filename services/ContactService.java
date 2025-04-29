package services;

import entity.Contact;
import repository.ContactRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactService {
    private ContactRepository contactRepository = new ContactRepository();
    private int idCounter = 1;

    public ContactService() {
        // Hardcoded sample contacts
        addContact("Tanya", "9876543210");
        addContact("Tanmay", "9123456789");
        addContact("Anshika", "9988776655");
    }

    public void addContact(String name, String phoneNumber) {
        // Check for duplicate name or phone number
        boolean exists = contactRepository.getAllContacts()
                .stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(name) || c.getPhoneNumber().equals(phoneNumber));

        if (exists) {
            System.out.println("Error: Contact with same name or phone number already exists!");
            return;
        }

        Contact contact = new Contact(idCounter++, name, phoneNumber);
        contactRepository.addContact(contact);
        System.out.println("Contact added successfully!");
    }

    public List<Contact> viewAllContacts() {
        return contactRepository.getAllContacts();
    }

    public Optional<Contact> findContactById(int id) {
        return contactRepository.getContactById(id);
    }

    public boolean updateContact(int id, String newName, String newPhoneNumber) {
        Optional<Contact> contactOpt = contactRepository.getContactById(id);
        if (contactOpt.isPresent()) {
            Contact contact = contactOpt.get();
            contact.setName(newName);
            contact.setPhoneNumber(newPhoneNumber);
            return true;
        }
        return false;
    }

    public boolean deleteContact(int id) {
        Optional<Contact> contactOpt = contactRepository.getContactById(id);
        if (contactOpt.isPresent()) {
            contactRepository.deleteContact(contactOpt.get());
            return true;
        }
        return false;
    }

    public List<Contact> searchContactsByName(String prefix) {
        List<Contact> matchedContacts = new ArrayList<>();
        for (Contact contact : contactRepository.getAllContacts()) {
            if (contact.getName().toLowerCase().startsWith(prefix.toLowerCase())) {
                matchedContacts.add(contact);
            }
        }
        return matchedContacts;
    }

    public Optional<Contact> findContactByName(String name) {
        return contactRepository.getAllContacts()
                .stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public boolean updateContactByName(String name, String newName, String newPhoneNumber) {
        Optional<Contact> contactOpt = findContactByName(name);
        if (contactOpt.isPresent()) {
            Contact contact = contactOpt.get();
            contact.setName(newName);
            contact.setPhoneNumber(newPhoneNumber);
            return true;
        }
        return false;
    }

    public boolean deleteContactByName(String name) {
        Optional<Contact> contactOpt = findContactByName(name);
        if (contactOpt.isPresent()) {
            contactRepository.deleteContact(contactOpt.get());
            return true;
        }
        return false;
    }

}
