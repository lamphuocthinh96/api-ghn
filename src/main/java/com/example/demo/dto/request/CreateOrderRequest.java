package com.example.demo.dto.request;

import com.example.demo.dto.request.order.Item;

import java.util.List;

public class CreateOrderRequest {
    public int payment_type_id;
    public String note;
    public String required_note;
    public String return_phone;
    public String return_address;
    public int return_district_id;
    public String return_ward_code;
    public String client_order_code;
    public String to_name;
    public String to_phone;
    public String to_address;
    public String to_ward_code;
    public int to_district_id;
    public int cod_amount;
    public String content;
    public int weight;
    public int length;
    public int width;
    public int height;
    public int pick_station_id;
    public Object deliver_station_id;
    public int insurance_value;
    public int service_id;
    public int service_type_id;
    public int order_value;
    public Object coupon;
    public List<Integer> pick_shift;
    public List<Item> items;
}
