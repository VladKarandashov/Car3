package com.example.car3.utils;

import com.example.car3.model.entity.StsEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;

@Slf4j
public class TitleComparator implements Comparator<StsEntity> {

    @Override
    public int compare(StsEntity o1, StsEntity o2) {
        try {
            var my = Long.parseLong(o1.getTitle());
            var you = Long.parseLong(o2.getTitle());
            return Long.signum(my-you);
        } catch (Exception e) {
            return Comparator.comparing(StsEntity::getTitle).compare(o1, o2);
        }
    }
}