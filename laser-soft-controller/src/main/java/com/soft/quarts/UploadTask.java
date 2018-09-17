package com.soft.quarts;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

@DisallowConcurrentExecution
public class UploadTask extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {

    }

}
