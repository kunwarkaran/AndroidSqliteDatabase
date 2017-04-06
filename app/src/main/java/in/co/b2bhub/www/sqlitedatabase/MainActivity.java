package in.co.b2bhub.www.sqlitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db=new DatabaseHandler(this);
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Karan", "9100000000","Jaipur"));
        db.addContact(new Contact("Kunwar", "9199999999","Mumbai"));
        db.addContact(new Contact("Raghav", "9522222222","Pune"));
        db.addContact(new Contact("Chutiya", "9533333333","Bangalore"));


        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: "+cn.getId()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhone_number() + ", Address: " +cn.getAddress();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }
}
