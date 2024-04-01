package com.nhnacademy.edu.springframework.service;

import com.nhnacademy.edu.springframework.repository.RateTableRepository;
import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class BasicDataLoadService implements DataLoadService {
    private final RateTableRepository rateTableRepository;

    public BasicDataLoadService(RateTableRepository rateTableRepository) {
        this.rateTableRepository = rateTableRepository;
    }

    @Override
    public void load(String fileName) {
        try {
            File file = ResourceUtils.getFile("classpath:" + fileName);
            rateTableRepository.parse(file.getPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
