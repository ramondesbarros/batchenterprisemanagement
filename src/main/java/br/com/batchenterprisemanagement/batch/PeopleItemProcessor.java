package br.com.batchenterprisemanagement.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PeopleItemProcessor implements ItemProcessor<People, People> {
	
	private static final Logger LOG = LoggerFactory.getLogger(PeopleItemProcessor.class);

    @Override
    public People process(final People people) throws Exception {
    	
    	System.out.println("Inserting employee : " + people);
    	
    	return people;
    
    }
}
