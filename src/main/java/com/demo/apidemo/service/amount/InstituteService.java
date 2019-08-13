package com.demo.apidemo.service.amount;

import com.demo.apidemo.entity.Institute;
import com.demo.apidemo.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InstituteService {
    @Autowired
    private InstituteRepository instituteRepository;

    public List<Institute> findAll() {
        return instituteRepository.findAll();
    }
}
