package mrs.domain.model.contact;

import java.util.List;

/**
 * 問い合わせ一覧
 */
public class ContactList {
    List<Contact> value;

    public ContactList(List<Contact> list) {
        value = list;
    }

    public int size() {
        return value.size();
    }

    public List<Contact> asList() {
        return value;
    }
}
