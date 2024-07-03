package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@TableName("poi_table")
@Data
public class Poi {
    private String name;
    private String description;
    private Integer id;
    private String coverUrl;
    private Float lat;
    private Float lng;
}
