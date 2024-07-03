package com.example.demo.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.exception.PoiExpection;
import com.example.demo.form.PoiForm;
import com.example.demo.pojo.Pic;
import com.example.demo.pojo.Poi;
import com.example.demo.service.IPicService;
import com.example.demo.service.IPoiService;
import com.example.demo.vo.PoiVo;
import com.example.demo.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("poi")
public class PoiController {
    private static final Logger log = LoggerFactory.getLogger(PoiController.class);
    @Autowired
    private IPoiService poiService;
    @Autowired
    private IPicService picService;
    @GetMapping("/list")
    public Result list (@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "50") int pageSize){
        Page<Poi> page = new Page<>(pageNum, pageSize);
        IPage<Poi> pageResult = poiService.page(page);
        List<Poi> poiList = pageResult.getRecords();
        List voList = new ArrayList();
        for (Poi poi : poiList){
            PoiVo poiVo = new PoiVo();
            QueryWrapper query = new QueryWrapper();
            query.eq("poi_id",poi.getId());
            List<Pic> pics =  picService.list(query);
            BeanUtils.copyProperties(poi,poiVo);
            poiVo.setPics(pics);
            voList.add(poiVo);
        }
        pageResult.setRecords(voList);
        return Result.success(pageResult);
    }
    @GetMapping("/detail/{id}")
    public Result detail (@PathVariable int id){
        log.info("id:{} ", id);
        Poi poi = poiService.getById(id);
        if (poi == null){
            throw PoiExpection.NotFound();
        }
        PoiVo poiVo = new PoiVo();
        QueryWrapper query = new QueryWrapper();
        query.eq("poi_id",poi.getId());
        List<Pic> pics = picService.list(query);
        BeanUtils.copyProperties(poi,poiVo);
        poiVo.setPics(pics);

        return Result.success(poiVo);
    }
    @PostMapping("/add")
    public Result add (@RequestBody PoiForm poiForm){
        Poi poi = new Poi();
        BeanUtils.copyProperties(poiForm,poi);
        poiService.saveMain(poi,poiForm.getPics());
        return detail(poi.getId());
    }
     @PutMapping("/edit/{id}")
    public Result edit (@RequestBody PoiForm poiForm ,@PathVariable int id){
        Poi poi = new Poi();
        BeanUtils.copyProperties(poiForm,poi);
        poi.setId(id);
        poiService.updateMain(poi,poiForm.getPics());
        return detail(id);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete (@PathVariable int id){
        poiService.deleteMain(id);
        return Result.success();
    }
}
