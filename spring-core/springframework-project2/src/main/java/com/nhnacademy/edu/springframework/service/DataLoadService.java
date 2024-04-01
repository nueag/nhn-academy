package com.nhnacademy.edu.springframework.service;

import org.springframework.stereotype.Service;

@Service
public interface DataLoadService {
    void load(String fileName);
}
