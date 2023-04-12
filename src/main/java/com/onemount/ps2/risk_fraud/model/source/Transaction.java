package com.onemount.ps2.risk_fraud.model.source;


import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;


//@Data
//@AllArgsConstructor
@CompiledJson
public class Transaction {

    protected String debitAccountNumber;

    protected String campaignId;

    protected Integer amount;

    protected Long eventTime;

    public Transaction() {
    }

    public Transaction(String debitAccountNumber, String campaignId, Integer amount, Long eventTime) {
        this.debitAccountNumber = debitAccountNumber;
        this.campaignId = campaignId;
        this.amount = amount;
        this.eventTime = eventTime;
    }

    @JsonAttribute(name = "debit_account_number")
    public String getDebitAccountNumber() {
        return debitAccountNumber;
    }

    public void setDebitAccountNumber(String debitAccountNumber) {
        this.debitAccountNumber = debitAccountNumber;
    }

    @JsonAttribute(name = "campaign_id")
    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    @JsonAttribute(name = "amount")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @JsonAttribute(name = "event_time")
    public Long getEventTime() {
        return eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }


    public long getProcessingTime() {
        return System.currentTimeMillis();
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
