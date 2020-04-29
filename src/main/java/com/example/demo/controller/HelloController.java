package com.example.demo.controller;

import com.example.demo.Tune;
import com.example.demo.TuneRepository;
import com.example.demo.service.TuneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo")
public class HelloController {

    @Autowired
    private TuneService tuneService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TuneRepository tuneRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewTune (@RequestParam String name
            , @RequestParam String defaultKey) {

        return tuneService.addNewTune(name, defaultKey);
    }

    @GetMapping(path="/show")
    public @ResponseBody Iterable<Tune> getSomeTunes(@RequestParam String name) {
        return tuneService.getSomeTunes(name);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Tune> getAllTunes() {
        return tuneService.getAllTunes();
    }

}