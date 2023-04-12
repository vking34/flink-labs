package com.onemount.ps2.risk_fraud.model.source;

import com.dslplatform.json.CompiledJson;
import com.onemount.ps2.risk_fraud.model.source.Account;
import com.onemount.ps2.risk_fraud.model.source.Transaction;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.util.BeanUtil;

@CompiledJson
public class DetailTransaction extends Transaction {

    private Account debitAccount;

    public DetailTransaction() {
    }

    public DetailTransaction(Transaction transaction, Account account) throws Exception {
        BeanUtils.copyProperties(this, transaction);
        this.debitAccount = account;
    }

    public Account getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Account debitAccount) {
        this.debitAccount = debitAccount;
    }

    @Override
    public String toString() {
        return "DetailTransaction{" +
                super.toString() +
                ", debitAccount=" + debitAccount +
                "}";
    }
}
