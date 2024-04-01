package com.nhnacademy.edu.springframework.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WaterBill {
    private int traiffNum;
    private String city;
    private String sector;
    private int level;
    private int startSection;
    private int endSection;
    private int unitPrice;
    private int billTotal;

    public WaterBill() {

    }

    public WaterBill(int traiffNum, String city, String sector, int level, int startSection, int endSection,
                     int unitPrice, int billTotal) {
        this.traiffNum = traiffNum;
        this.city = city;
        this.sector = sector;
        this.level = level;
        this.startSection = startSection;
        this.endSection = endSection;
        this.unitPrice = unitPrice;
        this.billTotal = billTotal;
    }

    @Override
    public String toString() {
        return "WaterBill{" +
                "city='" + city + '\'' +
                ", sector='" + sector + '\'' +
                ", unitPrice=" + unitPrice +
                ", billTotal='" + billTotal + '\'' +
                '}';
    }

    public void setBillTotal(int billTotal) {
        this.billTotal = billTotal;
    }

    @JsonProperty("순번")
    public int getTraiffNum() {
        return traiffNum;
    }

    @JsonProperty("지자체명")
    public String getCity() {
        return city;
    }

    @JsonProperty("업종")
    public String getSector() {
        return sector;
    }

    @JsonProperty("단계")
    public int getLevel() {
        return level;
    }

    @JsonProperty("구간시작(세제곱미터)")
    public int getStartSection() {
        return startSection;
    }

    @JsonProperty("구간끝(세제곱미터)")
    public int getEndSection() {
        return endSection;
    }

    @JsonProperty("구간금액(원)")
    public int getUnitPrice() {
        return unitPrice;
    }

    @JsonProperty("단계별 기본요금(원)")
    public int getBillTotal() {
        return billTotal;
    }
}
