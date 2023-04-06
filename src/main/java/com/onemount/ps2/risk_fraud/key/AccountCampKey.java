package com.onemount.ps2.risk_fraud.key;

import com.dslplatform.json.CompiledJson;

import java.util.Objects;

//@Data
//@AllArgsConstructor
@CompiledJson
public class AccountCampKey {

    private String accountNumber;

    private String campaignId;

    public AccountCampKey() {
    }

    public AccountCampKey(String accountNumber, String campaignId) {
        this.accountNumber = accountNumber;
        this.campaignId = campaignId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountCampKey that = (AccountCampKey) o;
        return Objects.equals(accountNumber, that.accountNumber) &&
                Objects.equals(campaignId, that.campaignId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, campaignId);
    }
}
