package leoquigao.phonebookey;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private PhoneContactsDAO phoneContactsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeDatabase();

        // load the contacts
        ContactsLoader contactsLoader = new ContactsLoader();
        contactsLoader.loadContacts(this, phoneContactsDAO);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initializeDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        phoneContactsDAO = new PhoneContactsDAO(db);
    }

}
