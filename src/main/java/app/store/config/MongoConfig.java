//package app.store.config;
//
//import com.mongodb.MongoClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.MongoTransactionManager;
//import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
//
//@Configuration
//public class MongoConfig extends AbstractMongoConfiguration {
//
//    @Bean
//    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
//        return new MongoTransactionManager(dbFactory);
//    }
//
//    @Override
//    public MongoClient mongoClient() {
//        return null;
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return null;
//    }
//}
