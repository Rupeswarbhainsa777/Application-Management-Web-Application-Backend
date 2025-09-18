
package com.code.Application_management_web_app.service;
import com.code.Application_management_web_app.Entity.JobManagement;
import com.code.Application_management_web_app.repositiory.JobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {



    @Autowired
    private JobRepository jobRepository;

    public JobManagement saveJob(JobManagement job) {
        return jobRepository.save(job);
    }



    public ResponseEntity<List<JobManagement>> getAllJobs() {
        try {
            return new ResponseEntity<>(jobRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteJobById(int id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return ResponseEntity.ok("Job with ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Job with ID " + id + " not found.");
        }
    }

    public ResponseEntity<JobManagement> updateJob(int id, JobManagement updatedJob) {
        return jobRepository.findById(id).map(existingJob -> {
            // Update fields
            existingJob.setCompanyName(updatedJob.getCompanyName());
            existingJob.setJobType(updatedJob.getJobType());
            existingJob.setDate(updatedJob.getDate());
            existingJob.setCompanyType(updatedJob.getCompanyType());
            existingJob.setStatus(updatedJob.getStatus());

            JobManagement savedJob = jobRepository.save(existingJob);
            return new ResponseEntity<>(savedJob, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
