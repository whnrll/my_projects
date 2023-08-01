package com.muke.service;

import com.muke.entity.AdEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.handler.EntryHandler;

@Component
public class ADHandle implements EntryHandler<AdEntity> {
    @Autowired
    private ADService adService;

    @Override
    public void insert(AdEntity adEntity) {
        adService.modify(adEntity);
    }

    @Override
    public void update(AdEntity before, AdEntity after) {
        adService.modify(after);
    }

    @Override
    public void delete(AdEntity adEntity) {
        adService.delete(adEntity.getId());
    }
}
