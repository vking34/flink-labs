package com.onemount.ps2.risk_fraud.state;

import com.onemount.ps2.risk_fraud.model.source.Transaction;

import java.util.LinkedList;
import java.util.List;

public class TransactionSumWindowState {

    private List<Transaction> transactions = new LinkedList<>();

    private int sum = 0;


    public TransactionSumWindowState addTransaction(Transaction transaction, long timeWindow) {
        transactions.add(transaction);
        sum += transaction.getAmount();

        long windowStart = transaction.getEventTime() - timeWindow;
        while (transactions.get(0).getEventTime() < windowStart) {
            sum -= transactions.get(0).getAmount();
            transactions.remove(0);
        }

        return this;
    }

    public TransactionSumWindowState() {
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
