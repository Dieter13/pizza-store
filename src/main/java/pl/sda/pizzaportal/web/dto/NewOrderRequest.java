package pl.sda.pizzaportal.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class NewOrderRequest {

    @NotEmpty
    private String recipient;

    @NotEmpty
    private String address;

    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp = "[0-9]+")
    private String phoneNumber;

    public NewOrderRequest() {
    }


    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
