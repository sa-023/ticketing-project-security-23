package com.company.dto;
import com.company.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.validation.constraints.*;
/*
 * 🦋 Validation
 * · @NotBlank: Enforces non-nullity and requires at least one non-whitespace character.
 * · @NotNull: Ensures a field is not null. However, the field(s) can be empty.
 * · @Email: Ensures a field contains a valid email address format.
 * · @Size(max = 15, min = 2): Validates if a string or collection size is within a specific range.
 * · @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}"): Verifies if a field matches the provided regular expression.
 * · @Pattern(regexp = "^\\d{10}$"): Verifies if a field matches the provided regular expression.
 * · @NotEmpty: Guarantees that collections or arrays are not empty.
 * · @Positive:
 *
 * ❗️@Valid: Triggers validation of nested objects or properties.
 * · We apply the @Valid annotation to a method parameter, Spring Boot automatically triggers validation for that parameter before the method is invoked.
 *   If the data fails validation, Spring Boot will automatically generate validation error messages and associate them with the appropriate fields in the input data.
 *   These validation errors are typically captured in a BindingResult object, which you can access to analyze and handle validation failures.
 * · When placed before a method argument, Spring’s validation mechanism inspects the object for any constraints (like @NotNull, @Size, etc.) and ensures that they are satisfied.
 *
 * 🖍️...
 * · We can handle the exception and set the message, error codes to messages defined in messages.properties file.
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    private Long id;

    @NotBlank
    @Size(max = 15, min = 2)
    private String firstName;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank
    @Size(max = 15, min = 2)
    private String lastName;

    @NotBlank
    @Email
    private String userName;

    @NotBlank
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}")
    private String passWord;

    @NotNull
    private String confirmPassWord;

    private boolean enabled;

    @NotBlank
    @Pattern(regexp = "^\\d{10}$")
    private String phone;

    @NotNull
    private RoleDTO role;

    @NotNull
    private Gender gender;

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
        checkConfirmPassWord();
    }

    public String getConfirmPassWord() {
        return confirmPassWord;
    }

    public void setConfirmPassWord(String confirmPassWord) {
        this.confirmPassWord = confirmPassWord;
        checkConfirmPassWord();
    }

    private void checkConfirmPassWord() {
        if(this.passWord == null || this.confirmPassWord == null){
            return;
        }else if(!this.passWord.equals(confirmPassWord)){
            this.confirmPassWord = null;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
