package com.example.demo.form;

import com.example.demo.pojo.Pic;
import lombok.Data;

import java.util.List;

@Data
public class PoiForm {
    private String name;
    private String description;
    private Integer id;
    private String coverUrl;
    private Float lat;
    private Float lng;
    private List<Pic> pics;
}
