package com.ptc.licensing.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories
class ApplicationConfig extends AbstractMongoConfiguration {
	
   @Value("${spring.data.mongodb.host}")
   private String mongoHost;

   @Value("${spring.data.mongodb.port}")
   private String mongoPort;

  @Override
  public Mongo mongo() throws Exception {
    return new MongoClient(mongoHost, Integer.parseInt(mongoPort));
  }

  @Override
  protected String getDatabaseName() {
    return "springdata";
  }
}
