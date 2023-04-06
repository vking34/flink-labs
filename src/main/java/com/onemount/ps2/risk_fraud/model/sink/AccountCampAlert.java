package com.onemount.ps2.risk_fraud.model.sink;

import com.dslplatform.json.CompiledJson;

@CompiledJson
public class AccountCampAlert {
    private String accountNumber;

    public AccountCampAlert() {
    }

    public AccountCampAlert(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "AccountCampAlert{" +
                "accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
