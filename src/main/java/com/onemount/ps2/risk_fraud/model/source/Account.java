package com.onemount.ps2.risk_fraud.model.source;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

@CompiledJson
public class Account {

    private String accountNumber;

    private String accountName;

    private Integer accountLevel;


    public Account() {
    }

    public Account(String accountNumber, String accountName, Integer accountLevel) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountLevel = accountLevel;
    }

    @JsonAttribute(name = "account_number")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @JsonAttribute(name = "account_name")
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @JsonAttribute(name = "account_level")
    public Integer getAccountLevel() {
        return accountLevel;
    }

    public void setAccountLevel(Integer accountLevel) {
        this.accountLevel = accountLevel;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountLevel=" + accountLevel +
                '}';
    }
}
