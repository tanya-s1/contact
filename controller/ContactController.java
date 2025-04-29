package controller;
import services.ContactService;
import entity.Contact;
import java.util.List;
import java.util.Scanner;

public class ContactController {
    private ContactService contactService = new ContactService();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n--- Contact Management Application ---");
            System.out.println("1. Add Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Search Contacts by Name");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addContact();
                case 2 -> viewContacts();
                case 3 -> updateContact();
                case 4 -> deleteContact();
                case 5 -> searchContacts();
                case 6 -> {
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addContact() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        contactService.addContact(name, phone);
    }

    private void viewContacts() {
        List<Contact> contacts = contactService.viewAllContacts();
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
        } else {
            for (Contact c : contacts) {
                System.out.println(c);
            }
        }
    }

    private void updateContact() {
        System.out.print("Enter existing contact name to update: ");
        String oldName = scanner.nextLine();
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String newPhone = scanner.nextLine();

        if (contactService.updateContactByName(oldName, newName, newPhone)) {
            System.out.println("Contact updated successfully!");
        } else {
            System.out.println("Contact not found with name: " + oldName);
        }
    }

    private void deleteContact() {
        System.out.print("Enter contact name to delete: ");
        String name = scanner.nextLine();

        if (contactService.deleteContactByName(name)) {
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found with name: " + name);
        }
    }

    private void searchContacts() {
        System.out.print("Enter name prefix to search: ");
        String prefix = scanner.nextLine();
        List<Contact> results = contactService.searchContactsByName(prefix);

        if (results.isEmpty()) {
            System.out.println("No contacts found starting with \"" + prefix + "\".");
        } else {
            System.out.println("Matching Contacts:");
            for (Contact contact : results) {
                System.out.println(contact);
            }
        }
    }

}
