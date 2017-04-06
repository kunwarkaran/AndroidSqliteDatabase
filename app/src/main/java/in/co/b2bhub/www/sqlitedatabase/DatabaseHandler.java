package in.co.b2bhub.www.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by B2B Android on 4/6/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    //Database Version
    private static final int version=1;
    //Database Name
    private static final String name="ContactsManager";
    //Table Name
    private static final String Table_Contacts="contacts";
    //Tables name
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_PHONE_NO="phone_number";
    private static final String KEY_ADDRESS="address";

    public DatabaseHandler(Context context) {
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Database Table in Database //

        db.execSQL(" CREATE TABLE " + Table_Contacts + " (" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_NAME + " TEXT NOT NULL, " +
                KEY_PHONE_NO + " TEXT NOT NULL, " +
                KEY_ADDRESS + " TEXT NOT NULL );");

    }

    // Upgrading in Database //
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //delete table if exists//
        db.execSQL("DROP TABLE IF EXITS" + Table_Contacts);
        onCreate(db);

    }
    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version,
                           DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
    //adding new contacts//
    void addContact(Contact contact){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_NAME,contact.getName());
        contentValues.put(KEY_PHONE_NO,contact.getPhone_number());
        contentValues.put(KEY_ADDRESS,contact.getAddress());
        db.insert(Table_Contacts,null,contentValues);
    }
    Contact getContact(int id){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(Table_Contacts,new String[]{KEY_ID ,KEY_NAME,KEY_PHONE_NO,KEY_ADDRESS},KEY_ID + "=?",
                new String[]{ String.valueOf(id)},null,null,null,null);
        if (cursor !=null)
            cursor.moveToFirst();
        Contact contact=new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),
                cursor.getString(3));
        return contact;

    }
    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Table_Contacts;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhone_number(cursor.getString(2));
                
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE_NO, contact.getPhone_number());

        // updating row
        return db.update(Table_Contacts, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" delete from " + Table_Contacts + " Where name = '" +KEY_NAME +" ' ");
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + Table_Contacts;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


}
