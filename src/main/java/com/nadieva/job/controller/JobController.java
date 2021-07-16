package com.nadieva.job.controller;

import com.nadieva.job.entity.Job;
import com.nadieva.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/{username}/jobs")
    public List<Job> findAllJobs(@PathVariable String username) {
        return jobService.findAllJobsByUsername(username);
    }

    @GetMapping("/{username}/jobs/{id}")
    public Job findJob(@PathVariable String username, @PathVariable long id){
        return jobService.findJobByIdAndUsername(username,id);
    }

    @DeleteMapping("/{username}/jobs/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable String username, @PathVariable long id){
        Job job=jobService.deleteJob(username, id);
        //Job job = jobService.deleteById(id);
        if(job != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{username}/jobs/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable String username, @PathVariable long id,
                                           @RequestBody Job job){
        Job jobUpdated=jobService.saveJob(job);
        return new ResponseEntity<Job>(jobUpdated, HttpStatus.OK);

    }

    @PostMapping("/{username}/jobs")
    public ResponseEntity<Void> updateTodo(@PathVariable String username,
                                           @RequestBody Job job){
        Job jobCreated=jobService.saveJob(job);

        URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(jobCreated.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
}
