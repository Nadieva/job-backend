package com.nadieva.job.service;

import com.nadieva.job.entity.Job;
import com.nadieva.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    private static long idCounter = 0;

    public List<Job> findAllJobsByUsername(String username) {
        List<Job> jobList = new ArrayList<>();
        jobRepository.findAll().stream().filter(job -> job.getUsername().equalsIgnoreCase(username)).forEach(jobList::add);
        return jobList ;
    }

    public Job deleteJob(String username, long id) {
        Job job = jobRepository.findById(id).get();
        if (job == null || !job.getUsername().equalsIgnoreCase(username)) {
            return null;
        }
        jobRepository.deleteById(id);
        return job;

    }

    public Job saveJob(Job job) {
        job.setLastUpdatedDateTime(new Date());
        jobRepository.save(job);
      /*  if (job.getId() == -1 || job.getId() == 0) {
            job.setId(++idCounter);
            job.setLastUpdatedDateTime(new Date());
            jobRepository.save(job);

        } else {
            jobRepository.deleteById(job.getId());
            jobRepository.save(job);
            job.setLastUpdatedDateTime(new Date());
        }*/
        return job;
    }

    public Job findJobByIdAndUsername(String username, long id) {
        Job job = jobRepository.findById(id).get();
        return (job != null && job.getUsername().equalsIgnoreCase(username) ) ? job : null;
    }
}