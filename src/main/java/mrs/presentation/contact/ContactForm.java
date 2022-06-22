package mrs.presentation.contact;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ContactForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String details;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
