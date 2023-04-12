package com.onemount.ps2.risk_fraud.model.source;

import com.dslplatform.json.CompiledJson;

@CompiledJson
public class Campaign {
    private String campaignId;

    private String campaignName;

    private Integer campaignBudget;

    public Campaign() {
    }

    public Campaign(String campaignId, String campaignName, Integer campaignBudget) {
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.campaignBudget = campaignBudget;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public Integer getCampaignBudget() {
        return campaignBudget;
    }

    public void setCampaignBudget(Integer campaignBudget) {
        this.campaignBudget = campaignBudget;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "campaignId='" + campaignId + '\'' +
                ", campaignName='" + campaignName + '\'' +
                ", campaignBudget=" + campaignBudget +
                '}';
    }
}
