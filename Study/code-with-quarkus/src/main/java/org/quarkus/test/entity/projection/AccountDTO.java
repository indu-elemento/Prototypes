package org.quarkus.test.entity.projection;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class AccountDTO {

    private String name;

    private String accId;

    private String region;

    public AccountDTO(String name, String accId, String region) {
        this.name =  name;
        this.accId = accId;
        this.region = region;
    }

    @Override
    public String toString() {

        return "AccountDTO{" +
                "name='" + name + '\'' +
                ", accId='" + accId + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
