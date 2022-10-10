package com.food.ShareTable.configuration;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;


@Configuration
public class MongoConfig {

    @Bean
    public MongoTransactionManager transactionManager(MongoDatabaseFactory mongoDatabaseFactory) {
        TransactionOptions txnOptions = TransactionOptions.builder().readPreference(ReadPreference.primary())
                .readConcern(ReadConcern.SNAPSHOT).writeConcern(WriteConcern.MAJORITY).build();
        return new MongoTransactionManager(mongoDatabaseFactory, txnOptions);
    }


}
