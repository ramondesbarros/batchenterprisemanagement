package br.com.batchenterprisemanagement.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PeopleItemProcessor implements ItemProcessor<People, People> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PeopleItemProcessor.class);

    @Override
    public People process(final People coffee) throws Exception {
//        String brand = coffee.getBrand().toUpperCase();
//        String origin = coffee.getOrigin().toUpperCase();
//        String chracteristics = coffee.getCharacteristics().toUpperCase();
//
//        People transformedCoffee = new People(brand, origin, chracteristics);
//        LOGGER.info("Converting ( {} ) into ( {} )", coffee, transformedCoffee);
//
//        return transformedCoffee;
    	
    	return null;
    }
}
