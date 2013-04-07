package com.lacombe.aggregate.rssstore.service;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.lacombe.commons.TimestampGenerator;
import com.sun.ejb.containers.EJBTimerServiceWrapper;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import java.util.Date;

import static com.google.inject.Guice.createInjector;
import static org.easymock.EasyMock.*;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 07/04/13
 * Time: 15:18
 * To change this template use File | Settings | File Templates.
 */
public class CollectorsManagementTest {
    private IMocksControl control = createStrictControl();
    private TimerService timerService = control.createMock(EJBTimerServiceWrapper.class);
    private TimestampGenerator timestampGenerator = control.createMock(TimestampGenerator.class);
    private Collector collector = control.createMock(Collector.class);
    private Timer timer = control.createMock(Timer.class);

    private final Injector injector = createInjector(new AbstractModule() {
        @Override
        protected void configure() {
            bind(TimerService.class).toInstance(timerService);
            bind(TimestampGenerator.class).toInstance(timestampGenerator);
        }
    });
    private CollectorsManagementImpl collectorsManagement;

    @Before
    public void setUp() throws Exception {
        collectorsManagement = injector.getInstance(CollectorsManagementImpl.class);
        control.reset();
    }

    @Ignore
    @Test
    public void testCreateCollectorTimer() throws Exception {
        Date date = new Date();
        expect(timestampGenerator.generate()).andReturn(date);


        expect(timerService.createIntervalTimer(eq(date), eq(5000), (TimerConfig) anyObject())).andReturn(timer);
        control.replay();
        collectorsManagement.createCollectorTimer(collector, 5000);
        control.verify();
    }

    private TimerConfig createTimeConfig() {
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setPersistent(true);
        timerConfig.setInfo(collector);
        return timerConfig;
    }

    @Test
    public void testScheduled() throws Exception {
        expect(timer.getInfo()).andReturn(collector);
        collector.collect();
        control.replay();
        collectorsManagement.scheduled(timer);
        control.verify();
    }
}
