package com.novo.controllers;

import com.novo.model.jobtypes.JobType;
import com.novo.model.jobtypes.service.JobTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("jobs")
public class JobTypesController {

    @Autowired
    private JobTypeService jobTypeService;

    @GetMapping
    public String getJobTypes (Model model){
        model.addAttribute("fpath", "fragments/jobTypes");
        model.addAttribute("fname", "jobTypes");
        model.addAttribute("jobTypes", jobTypeService.findAll());
        return "index";
    }


    @GetMapping("add")
    public String getAddJobType (Model model){
        model.addAttribute("fpath", "fragments/jobs-add");
        model.addAttribute("fname", "add");
        model.addAttribute("action", "/jobs/add");
        model.addAttribute("jobType", new JobType());
        return "index";
    }


    @PostMapping("add")
    public String postAddJobType (@ModelAttribute JobType jobType){
        log.debug(jobType.toString());
        jobTypeService.save(jobType);
        return "redirect:/jobs";
    }
}
