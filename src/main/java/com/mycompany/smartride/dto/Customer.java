
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
public class Customer implements Serializable{
    private int customerId;
    private String identityCard;
    private String identityCardImage;
    private String issuedOnDate;
    private String expDate;
    private String typeCard;
    private int typeId;
    private int accountId;
}

