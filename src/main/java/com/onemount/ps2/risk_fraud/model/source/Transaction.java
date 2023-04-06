package com.onemount.ps2.risk_fraud.model.source;


import com.dslplatform.json.CompiledJson;
import com.onemount.ps2.risk_fraud.key.AccountCampKey;
import lombok.AllArgsConstructor;
import lombok.Data;


//@Data
//@AllArgsConstructor
@CompiledJson
public class Transaction {

    private String debitAccountNumber;

    private String campaignId;

    private Integer amount;

    private Long eventTime;

    public Transaction() {
    }

    public Transaction(String debitAccountNumber, String campaignId, Integer amount, Long eventTime) {
        this.debitAccountNumber = debitAccountNumber;
        this.campaignId = campaignId;
        this.amount = amount;
        this.eventTime = eventTime;
    }

    public String getDebitAccountNumber() {
        return debitAccountNumber;
    }

    public void setDebitAccountNumber(String debitAccountNumber) {
        this.debitAccountNumber = debitAccountNumber;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getEventTime() {
        return eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }

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
