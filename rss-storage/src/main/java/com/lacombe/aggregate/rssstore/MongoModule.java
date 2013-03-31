package com.lacombe.aggregate.rssstore;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.mongodb.DB;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

import static com.lacombe.aggregate.rssstore.conf.ConfigurationKeys.*;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
public class MongoModule extends AbstractModule {
    @Override
    protected void configure() {
    }

    @Provides
    public MongoClient provideMongoClient(@Named(MONGO_SERVER_HOST) String host,
                                          @Named(MONGO_SERVER_PORT) int port) throws UnknownHostException {
        return new MongoClient(host, port);
    }

    @Provides
    @Named(AGGREGATOR_DB)
    public DB provideDB(MongoClient mongoClient,
                        @Named(AGGREGATOR_DB) String dbName) {
        return mongoClient.getDB(dbName);
    }
}
