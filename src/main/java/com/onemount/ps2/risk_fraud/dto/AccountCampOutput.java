package com.onemount.ps2.risk_fraud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountCampOutput {
    private String accountNumber;


    @Override
    public String toString() {
        return "AccountCampOutput{" +
                "accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
