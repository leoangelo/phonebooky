package leoquigao.phonebookey;

public class PhoneContact {

    public Long _id;
    public String phoneNumber;
    public String contactName;

    public PhoneContact() {
        this.phoneNumber = "";
        this.contactName = "";
    }

    public PhoneContact(String phoneNumber, String contactName) {
        this.phoneNumber = phoneNumber;
        this.contactName = contactName;
    }
}
