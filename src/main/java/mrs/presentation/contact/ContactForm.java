package mrs.presentation.contact;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ContactForm implements Serializable {
    private static final long serialVersionUID = 1L;

    private String contactId;

    @NotNull
    private String details;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

}
