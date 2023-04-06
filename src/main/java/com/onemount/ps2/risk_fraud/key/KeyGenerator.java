package com.onemount.ps2.risk_fraud.key;

import com.onemount.ps2.risk_fraud.model.source.Transaction;


public class KeyGenerator {

    public static AccountCampKey generateAccountCampKey(Transaction transaction) {
        return new AccountCampKey(transaction.getDebitAccountNumber(), transaction.getCampaignId());
    }
}
