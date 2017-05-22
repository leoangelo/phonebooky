package leoquigao.phonebookey;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class PhoneContactsDAO {

    private SQLiteDatabase database;

    public PhoneContactsDAO(SQLiteDatabase database) {
        this.database = database;
    }

    public void insertPhoneContacts(List<PhoneContact> contactList) {
        cupboard().withDatabase(database).put(contactList);
    }

    public int getRecordCount() {
        Cursor records = cupboard().withDatabase(database).query(PhoneContact.class).getCursor();
        return records.getCount();
    }
}
