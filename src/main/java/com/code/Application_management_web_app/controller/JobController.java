package com.code.Application_management_web_app.controller;

import com.code.Application_management_web_app.Entity.JobManagement;
import com.code.Application_management_web_app.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/add")
    public JobManagement addJob(@RequestBody JobManagement job) {
        return jobService.saveJob(job);
    }

    @GetMapping("/allJobs")
    public ResponseEntity<List<JobManagement>> allJob(){
        return jobService.getAllJobs();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable int id) {
        return jobService.deleteJobById(id);
    }
}
