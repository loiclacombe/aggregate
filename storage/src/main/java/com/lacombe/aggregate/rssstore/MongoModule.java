package com.lacombe.aggregate.rssstore;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.net.UnknownHostException;

import static com.lacombe.aggregate.rssstore.conf.ConfigurationKeys.*;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
public class MongoModule {

    @Produces
    public MongoClient provideMongoClient(@Named(MONGO_SERVER_HOST) String host,
                                          @Named(MONGO_SERVER_PORT) int port) throws UnknownHostException {
        return new MongoClient(host, port);
    }

    @Produces
    @Named(AGGREGATOR_DB)
    public DB provideDB(MongoClient mongoClient,
                        @Named(AGGREGATOR_DB) String dbName) {
        return mongoClient.getDB(dbName);
    }
}
