package com.example.springbootdemo01.batch;

import com.example.springbootdemo01.bean.Assess;
import com.example.springbootdemo01.listener.JobCompletionNotificationListener;
import com.example.springbootdemo01.listener.JobListener;
import com.example.springbootdemo01.utils.DateUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Resource
    private JobBuilderFactory jobBuilderFactory;    //用于构建JOB

    @Resource
    private StepBuilderFactory stepBuilderFactory;  //用于构建Step

//    @Autowired
//    private ApplicationContext applicationContext;

    @Resource
    private JobListener jobListener;

    @Autowired
    private DataSource dataSource;

    @Bean
    public ItemReader<Assess> reader() {
        FlatFileItemReader<Assess> reader = new FlatFileItemReader<>();
        reader.setEncoding("UTF-8");
        reader.setResource(new ClassPathResource("/static/backup/t_assess.txt"));
        DefaultLineMapper<Assess> lineMapper = new DefaultLineMapper<Assess>();
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer(","));
        lineMapper.setFieldSetMapper(new FieldSetMapper<Assess>() {

            public Assess mapFieldSet(FieldSet fieldSet) throws BindException {
                Assess assess = new Assess();
                try {
                    assess.setAssessId(fieldSet.readLong(0));
                    assess.setCourse(null);
                    assess.setContext(fieldSet.readString(2));
                    assess.setCreateTime(DateUtil.parseDate(fieldSet.readString(3)));

                } catch (Exception e) {
                    e.getMessage();
                }
                return assess;
            }
        });
        reader.setLineMapper(lineMapper);
        System.out.println("this is "+reader.toString());
        return reader;
    }

    @Bean
    public JdbcBatchItemWriter<Assess> importWriter() {
        JdbcBatchItemWriter<Assess> writer = new JdbcBatchItemWriter<>();
        //DataSource dataSource = applicationContext.getBean(DataSource.class);
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        //writer.setSql("INSERT INTO user_map (uid,tag,type) VALUES (:uid, :tag,:type)");
        writer.setSql("insert into t_assess (assess_id,course_id,context,create_time) values (:assess_id,:course_id,:context,:create_time)");
        writer.setDataSource(dataSource);
        return writer;


    }

    @Bean
    public UserMapItemProcessor processor() {
        return new UserMapItemProcessor();
    }
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob").
                incrementer(new RunIdIncrementer()).
                start(importStep()).    //start是JOB执行的第一个step
                listener(jobListener).      //设置了一个简单JobListener
                build();
    }
    @Bean
    public Step importStep() {
        return stepBuilderFactory.get("importStep")
                .<Assess, Assess>chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(importWriter())
                .build();
    }
}
