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
            char ch = phoneNumber.charAt(i);
            if (!Character.isDigit(ch)) {
                System.out.println("Error: Phone number must contain digits only!");
                return;
            }
        }
        for (Contact c : contactRepository.getAllContacts()) {
            if (c.getName().equalsIgnoreCase(name)) {
                System.out.println("Error: Contact with same name already exists!");
                return;
            }
            if (c.getPhoneNumber().equals(phoneNumber)) {
                System.out.println("Error: Contact with same phone number already exists!");
                return;
            }
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
        List<Contact> contacts = contactRepository.getAllContacts();
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return Optional.of(contact);
            }
        }
        return Optional.empty();
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
