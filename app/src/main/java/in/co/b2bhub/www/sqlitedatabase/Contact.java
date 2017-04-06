package in.co.b2bhub.www.sqlitedatabase;

/**
 * Created by B2B Android on 4/6/2017.
 */

public class Contact {
    int id;
    String name;
    String phone_number;
    String address;
    public Contact() {
    }
    public Contact(String name, String phone_number, String address) {
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
    }
    public Contact(int id, String name, String phone_number, String address) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
