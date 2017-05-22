package leoquigao.phonebookey;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ContactsLoader {

    private static final String TAG = ContactsLoader.class.getSimpleName();
    private final int BATCH_SIZE = 1000;

    public void loadContacts(Context context, PhoneContactsDAO phoneContactsDAO) {

        List<PhoneContact> contactsToLoad = new ArrayList<>();
        Log.d(TAG, "Before load number of contacts: " + phoneContactsDAO.getRecordCount());

        try {
            long timeStart = System.currentTimeMillis();

            InputStream is = context.getAssets().open("contacts.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            int counter = 0;

            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split("|");
                contactsToLoad.add(new PhoneContact(rowData[0], rowData[1]));

                // if counter reaches 20k insert current batch and reset the collection
                if (counter == BATCH_SIZE) {
                    phoneContactsDAO.insertPhoneContacts(contactsToLoad);
                    counter = 0;
                    contactsToLoad.clear();
                }

                counter++;
            }

            // insert the final batch
            if (contactsToLoad.size() > 0) {
                phoneContactsDAO.insertPhoneContacts(contactsToLoad);
                contactsToLoad.clear();
            }

            long timeEnd = System.currentTimeMillis();
            long timeElapsed = (timeEnd - timeStart) / 1000;
            Log.d(TAG, "Time elapsed: " + timeElapsed);

            reader.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "After load number of contacts: " + phoneContactsDAO.getRecordCount());

    }

}
