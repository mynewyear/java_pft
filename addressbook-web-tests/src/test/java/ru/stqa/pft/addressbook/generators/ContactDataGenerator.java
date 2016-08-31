package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IEUser on 8/30/2016.
 */
public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public  int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {

        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jcommander = new JCommander(generator);
        try{
            jcommander.parse(args);
        }catch (ParameterException ex){
            jcommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        save(contacts, new File(file));
    }

    private void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for(ContactData contact : contacts){
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(),
                    contact.getAddress(), contact.getPhone(), contact.getMobile(),
                    contact.getWorkPhone(), contact.getEmail(), contact.getEmail2(), contact.getEmail3()));

        }
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withName(String.format("name %s", i))
                    .withLastName(String.format("lastName %s", i))
                    .withAddress(String.format("address %s", i))
                    .withPhone(String.format("1234567 %s", i))
                    .withMobile(String.format("23345678 %s", i))
                    .withWorkPhone(String.format("3456789 %s", i))
                    .withEmail(String.format("email@test.com %s", i))
                    .withEmail2(String.format("email2@test.com %s", i))
                    .withEmail3(String.format("email3@test.com %s", i))

            );
        }
        return contacts;
    }
}
