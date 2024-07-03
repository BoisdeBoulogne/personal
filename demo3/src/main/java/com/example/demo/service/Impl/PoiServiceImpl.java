package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.exception.PoiExpection;
import com.example.demo.mapper.PicMapper;
import com.example.demo.mapper.PoiMapper;
import com.example.demo.pojo.Pic;
import com.example.demo.pojo.Poi;
import com.example.demo.service.IPoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoiServiceImpl extends ServiceImpl<PoiMapper,Poi> implements IPoiService {
    @Autowired
    PicMapper picMapper;
    @Autowired
    PoiMapper poiMapper;
    @Override

    public void saveMain(Poi poi, List<Pic> pics) {
        int check = poiMapper.insert(poi);
        if (check == 0) {
            throw PoiExpection.OperateFail();
        }
        if (pics != null)
        for (Pic pic : pics){
            pic.setPoiId(poi.getId());
            check = picMapper.insert(pic);
            if (check == 0) {
                throw PoiExpection.OperateFail();
            }
        }
    }
    @Override
    public void deleteMain(Integer id){
        int row = poiMapper.deleteById(id);
        if (row == 0) {
            throw PoiExpection.OperateFail();
        }
        picMapper.deleteByPoiId(id);

    }
    @Override
    public void updateMain(Poi poi, List<Pic> pics){
        int row = poiMapper.updateById(poi);
        if (row == 0) {
            throw PoiExpection.OperateFail();
        }
        picMapper.deleteByPoiId(poi.getId());
        if (pics != null){
            for (Pic pic : pics){
                pic.setPoiId(poi.getId());
                row = picMapper.insert(pic);
                if (row == 0) {
                    throw PoiExpection.OperateFail();
                }
            }
        }
    }
}
