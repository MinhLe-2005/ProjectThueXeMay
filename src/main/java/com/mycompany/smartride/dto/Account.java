
package com.mycompany.smartride.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Account implements Serializable{
    private int accountId;
    private String firstName;
    private String lastName;
    private String gender;
    private String dob;
    private String address;
    private String phoneNumber;
    private String image;
    private String email;
    private String userName;
    private String passWord;
    private int roleID;
}

