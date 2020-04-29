package com.example.demo.service;

import com.example.demo.Tune;
import com.example.demo.TuneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TuneService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TuneRepository tuneRepository;

    public Iterable<Tune> getAllTunes() {
        return tuneRepository.findAll();
    }

    public Iterable<Tune> getSomeTunes(String name) {
        return tuneRepository.findByName(name);
    }

    public String addNewTune (String name, String defaultKey) {
        Tune n = new Tune();
        n.setName(name);
        n.setDefaultKey(defaultKey);
        tuneRepository.save(n);
        logger.info("Saving new tune...");
        return "Saved";
    }
}
