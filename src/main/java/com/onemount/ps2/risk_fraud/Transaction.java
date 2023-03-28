package com.onemount.ps2.risk_fraud;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    private String creditAccountNumber;

    private String debitAccountNumber;

    private Integer amount;


    @Override
    public String toString() {
        return "Transaction{" +
                "creditAccountNumber='" + creditAccountNumber + '\'' +
                ", debitAccountNumber='" + debitAccountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
