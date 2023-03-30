package com.onemount.ps2.risk_fraud;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {

    private String debitAccountNumber;

    private String campaignId;

    private Integer amount;

    private Long eventTime;


    @Override
    public String toString() {
        return "Transaction{" +
                "debitAccountNumber='" + debitAccountNumber + '\'' +
                ", campaignId='" + campaignId + '\'' +
                ", amount=" + amount +
                ", eventTime=" + eventTime +
                '}';
    }
}
