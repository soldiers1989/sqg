package com.soft.tbk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft.tbk.dao.MmMbuserMapper;
import com.soft.tbk.model.MmMbuser;
import com.soft.tbk.service.MmMbuserService;

@Service
public class MmMbuserServiceImpl implements MmMbuserService{
    
    @Autowired
    private MmMbuserMapper mmMbuserMapper;

    @Override
    public void save(MmMbuser user) {
        mmMbuserMapper.insert(user);
    }
    
}
