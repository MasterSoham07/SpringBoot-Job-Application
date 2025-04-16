package com.soham.FirstJobApp.Job.impl;

import com.soham.FirstJobApp.Job.Job;
import com.soham.FirstJobApp.Job.JobRepository;
import com.soham.FirstJobApp.Job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
//    private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
//    private Long nextId = 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
//        job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
       return jobRepository.findById(id).orElse(null);
    }

//    @Override
//    public boolean deleteJobById(Long id) {
//        Iterator<Job> iterator = jobs.iterator();
//        while(iterator.hasNext()){
//            Job job = iterator.next();
//            if(job.getId().equals(id)){
//                iterator.remove();
//                return true;
//            }
//        }
//
//        return false;
//    }

    @Override
    public boolean deleteJobById(Long id){
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job UpdatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
            if(jobOptional.isPresent()){
                Job job = jobOptional.get();
                job.setTitle(UpdatedJob.getTitle());
                job.setDescription(UpdatedJob.getDescription());
                job.setMaxSalary(UpdatedJob.getMaxSalary());
                job.setMinSalary(UpdatedJob.getMinSalary());
                job.setLocation(UpdatedJob.getLocation());
                jobRepository.save(job);
                return true;
        }
        return false;
    }
}
