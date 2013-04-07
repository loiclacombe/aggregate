package com.lacombe.aggregate.rssstore.service;

import javax.ejb.Local;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 07/04/13
 * Time: 17:55
 */
@Local
public interface CollectorsManagement {
    void createCollectorTimer(Collector collector, long interval);
}
