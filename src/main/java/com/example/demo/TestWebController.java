package com.example.demo;

import com.example.demo.dto.ghn.DistrictItem;
import com.example.demo.dto.ghn.ServiceItem;
import com.example.demo.dto.ghn.WardItem;
import com.example.demo.dto.request.CheckFeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestWebController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${GHN.url}")
    private String ghnUrl;

    @Value("${GHN.token}")
    private String ghnToken;

    @Value("${GHN.shop_id}")
    private int ghnShopId;

    @Value("${GHN.districtId}")
    private int fromDistrict;

    @GetMapping("/order")
    public String viewWeb(Model model) {
        List<DistrictItem> districtItemList = new ArrayList<>();
        List<ServiceItem> serviceItemList = new ArrayList<>();
        List<WardItem> wardList = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", ghnToken);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity responseEntity = restTemplate.exchange(ghnUrl + "master-data/province", HttpMethod.GET, entity, Object.class);
            Map resp = (Map) responseEntity.getBody();
            List<Map> dMapData = (List<Map>) resp.get("data");
            for (Map dMap : dMapData) {
                DistrictItem districtItem = new DistrictItem();
                districtItem.setCode((int) dMap.get("ProvinceID"));
                districtItem.setName((String) dMap.get("ProvinceName"));
                districtItemList.add(districtItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Map serviceRequest = new LinkedHashMap();
            serviceRequest.put("shop_id", ghnShopId);
            serviceRequest.put("from_district", fromDistrict);
            serviceRequest.put("to_district", fromDistrict);
            entity = new HttpEntity<>(serviceRequest, headers);
            ResponseEntity responseEntity = restTemplate.exchange(ghnUrl + "v2/shipping-order/available-services", HttpMethod.POST, entity, Object.class);
            Map resp = (Map) responseEntity.getBody();
            List<Map> dMapData = (List<Map>) resp.get("data");
            for (Map dMap : dMapData) {
                ServiceItem districtItem = new ServiceItem();
                districtItem.setCode((int) dMap.get("service_id"));
                districtItem.setName((String) dMap.get("short_name"));
                districtItem.setServiceTypeId((int) dMap.get("service_type_id"));
                serviceItemList.add(districtItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Map serviceRequest = new LinkedHashMap();
            serviceRequest.put("district_id", 1566);
            entity = new HttpEntity<>(serviceRequest, headers);
            ResponseEntity responseEntity = restTemplate.exchange(ghnUrl + "master-data/ward?district_id", HttpMethod.POST, entity, Object.class);
            Map resp = (Map) responseEntity.getBody();
            List<Map> dMapData = (List<Map>) resp.get("data");
            for (Map dMap : dMapData) {
                WardItem wardItem = new WardItem();
                wardItem.setCode((String) dMap.get("WardCode"));
                wardItem.setName((String) dMap.get("WardName"));
                wardList.add(wardItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        districtItemList.add(new DistrictItem(1, "Ho Chi Minh"));
        model.addAttribute("districts", districtItemList);
        model.addAttribute("services", serviceItemList);
        model.addAttribute("wards", wardList);
        model.addAttribute("order", new CheckFeeRequest());
        return "orderTest";
    }

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new LinkedHashMap<>());
        return "greeting";
    }
}
