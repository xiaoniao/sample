package com.example.protobuf;


import com.example.tutorial.AddressBookProtos.AddressBook;
import com.example.tutorial.AddressBookProtos.Person;

import java.io.*;

class AddPerson {

    /**
     * Read the existing address book.
     */
    private static AddressBook.Builder readFromFile(String fileName) throws IOException {
        AddressBook.Builder addressBook = AddressBook.newBuilder();
        try {
            FileInputStream input = new FileInputStream(fileName);
            try {
                addressBook.mergeFrom(input);
            } finally {
                try {
                    input.close();
                } catch (Throwable ignore) {
                    ignore.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(fileName + ": File not found.  Creating a new file.");
            File file = new File(fileName);
            boolean isCreateFile = file.createNewFile();
            System.out.println("create a new file " + isCreateFile);
        }
        return addressBook;
    }

    /**
     * This function fills in a Person message based on user input.
     */
    private static Person PromptForAddress(BufferedReader stdin, PrintStream stdout) throws IOException {
        Person.Builder person = Person.newBuilder();

        stdout.print("Enter person ID: ");
        person.setId(Integer.valueOf(stdin.readLine()));

        stdout.print("Enter name: ");
        person.setName(stdin.readLine());

        stdout.print("Enter email address (blank for none): ");
        String email = stdin.readLine();
        if (email.length() > 0) {
            person.setEmail(email);
        }

        while (true) {
            stdout.print("Enter a phone number (or leave blank to finish): ");
            String number = stdin.readLine();
            if (number.length() == 0) {
                break;
            }

            Person.PhoneNumber.Builder phoneNumber = Person.PhoneNumber.newBuilder().setNumber(number);

            stdout.print("Is this a mobile, home, or work phone? ");
            String type = stdin.readLine();
            switch (type) {
                case "mobile":
                    phoneNumber.setType(Person.PhoneType.MOBILE);
                    break;
                case "home":
                    phoneNumber.setType(Person.PhoneType.HOME);
                    break;
                case "work":
                    phoneNumber.setType(Person.PhoneType.WORK);
                    break;
                default:
                    stdout.println("Unknown phone type.  Using default.");
                    break;
            }
            person.addPhones(phoneNumber);
        }

        return person.build();
    }

    /**
     * Main function: Reads the entire address book from a file, adds one person based on user input, then writes it back out to the same file.
     */
    public static void main(String[] args) throws Exception {
        String fileName = "/Users/xiezx/file/addressBook";
        AddressBook.Builder addressBook = readFromFile(fileName);
        if (addressBook == null) {
            return;
        }

        // Add an address.
        addressBook.addPeople(PromptForAddress(new BufferedReader(new InputStreamReader(System.in)), System.out));

        // Write the new address book back to disk.
        try (FileOutputStream output = new FileOutputStream(fileName)) {
            addressBook.build().writeTo(output);
        }
    }

}