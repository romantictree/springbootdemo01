package com.example.springbootdemo01.batch;

import com.example.springbootdemo01.bean.Assess;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserMapItemProcessor implements ItemProcessor<Assess, Assess> {

    UserMapItemProcessor(){}

//    UserMapItemProcessor(ProcessStatus processStatus){}

    private ProcessStatus processStatus;
    public UserMapItemProcessor(ProcessStatus processStatus) {
        this.processStatus = processStatus;
    }

    @Override
    public Assess process(Assess assess) throws Exception {
        return null;
    }

    protected enum ProcessStatus {
        IMPORT,
        UPDATE;
    }
}
