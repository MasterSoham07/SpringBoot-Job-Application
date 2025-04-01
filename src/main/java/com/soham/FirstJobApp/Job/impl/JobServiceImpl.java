package com.soham.FirstJobApp.Job.impl;

import com.soham.FirstJobApp.Job.Job;
import com.soham.FirstJobApp.Job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        for(Job job : jobs){
            if(job.getId().equals(id)){
                return job;
            }
        }
        return null;
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
        return jobs.removeIf(job -> job.getId().equals(id));
    }

    @Override
    public boolean updateJob(Long id, Job UpdatedJob) {
        for(Job job : jobs){
            if(job.getId().equals(id)){
                job.setTitle(UpdatedJob.getTitle());
                job.setDescription(UpdatedJob.getDescription());
                job.setMaxSalary(UpdatedJob.getMaxSalary());
                job.setMinSalary(UpdatedJob.getMinSalary());
                job.setLocation(UpdatedJob.getLocation());
                return true;
            }
        }
        return false;
    }
}
