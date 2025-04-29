package services;

import entity.Contact;
import repository.ContactRepository;

import java.util.ArrayList;
import java.util.List;

public class ContactService {
    private ContactRepository contactRepository = new ContactRepository();
    private int idCounter = 1;

    public ContactService() {
        addContact("Tanya", "9876543210");
        addContact("Tanmay", "9123456789");
        addContact("Anshika", "9988776655");
    }

    public void addContact(String name, String phoneNumber) {
        if (phoneNumber.length() != 10) {
            System.out.println("Error: Phone number must be exactly 10 digits!");
            return;
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                System.out.println("Error: Phone number must contain digits only!");
                return;
            }
        }
        // for (Contact c : contactRepository.getAllContacts()) {
        //     if (c.getName().equalsIgnoreCase(name)) {
        //         System.out.println("Error: Contact with same name already exists!");
        //         return;
        //     }
        //     // if (c.getPhoneNumber().equals(phoneNumber)) {
        //     //     System.out.println("Error: Contact with same phone number already exists!");
        //     //     return;
        //     // }
        // }

        Contact contact = new Contact(idCounter++, name, phoneNumber);
        contactRepository.addContact(contact);
        System.out.println("Contact added successfully!");
    }

    public List<Contact> viewAllContacts() {
        return contactRepository.getAllContacts();
    }

    public Contact findContactById(int id) {
        for (Contact contact : contactRepository.getAllContacts()) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }

    public boolean updateContact(int id, String newName, String newPhoneNumber) {
        Contact contact = findContactById(id);
        if (contact != null) {
            contact.setName(newName);
            contact.setPhoneNumber(newPhoneNumber);
            return true;
        }
        return false;
    }

    public boolean deleteContact(int id) {
        Contact contact = findContactById(id);
        if (contact != null) {
            contactRepository.deleteContact(contact);
            return true;
        }
        return false;
    }

    public List<Contact> searchContactsByName(String prefix) {
        List<Contact> matchedContacts = new ArrayList<>();
        for (Contact contact : contactRepository.getAllContacts()) {
            if (contact.getName().toLowerCase().contains(prefix.toLowerCase())) {
                matchedContacts.add(contact);
            }
        }
        return matchedContacts;
    }
    public List<Contact> searchContactsByNumber(String prefix) {
        List<Contact> matchedContacts = new ArrayList<>();
        for (Contact contact : contactRepository.getAllContacts()) {
            if (contact.getPhoneNumber().contains(prefix)) {
                matchedContacts.add(contact);
            }
        }
        return matchedContacts;
    }

    public Contact findContactByName(String name) {
        for (Contact contact : contactRepository.getAllContacts()) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public boolean updateContactByName(String name, String newName, String newPhoneNumber) {
        Contact contact = findContactByName(name);
        if (contact != null) {
            contact.setName(newName);
            contact.setPhoneNumber(newPhoneNumber);
            return true;
        }
        return false;
    }

    public boolean deleteContactByName(String name) {
        Contact contact = findContactByName(name);
        if (contact != null) {
            contactRepository.deleteContact(contact);
            return true;
        }
        return false;
    }
}
