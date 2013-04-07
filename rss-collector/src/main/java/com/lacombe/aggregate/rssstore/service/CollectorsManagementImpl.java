package com.lacombe.aggregate.rssstore.service;

import com.lacombe.commons.TimestampGenerator;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 01/04/13
 * Time: 17:27
 */

@Stateless
public class CollectorsManagementImpl implements CollectorsManagement {
    private TimerService timerService;
    private TimestampGenerator timestampGenerator;

    @Inject
    public CollectorsManagementImpl(TimerService timerService, TimestampGenerator timestampGenerator) {
        this.timerService = timerService;
        this.timestampGenerator = timestampGenerator;
    }

    @Override
    public void createCollectorTimer(Collector collector, long interval) {
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setPersistent(true);
        timerConfig.setInfo(collector);
        Date date = timestampGenerator.generate();
        timerService.createIntervalTimer(date, interval, timerConfig);
    }

    @Timeout
    public void scheduled(Timer timer) throws Exception {
        Collector collector = (Collector) timer.getInfo();
        collector.collect();
    }
}
