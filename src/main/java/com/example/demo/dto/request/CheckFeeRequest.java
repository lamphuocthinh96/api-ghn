package com.example.demo.dto.request;

import lombok.Data;

@Data
public class CheckFeeRequest {
    public int from_district_id;
    public int service_id;
    public Object service_type_id;
    public int to_district_id;
    public String to_ward_code;
    public int height;
    public int length;
    public int weight;
    public int width;
    public int insurance_value;
    public String coupon;
}
