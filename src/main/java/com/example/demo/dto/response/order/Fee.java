package com.example.demo.dto.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fee {
    public int main_service;
    public int insurance;
    public int station_do;
    public int station_pu;
    @JsonProperty("return")
    public int returnVal;
    public int r2s;
    public int coupon;
}
