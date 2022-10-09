package org.codeworks.web.toolsdashboardaatool.dropwizard.metrics;

import com.ryantenney.metrics.spring.servlets.MetricsServletsContextListener;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@Slf4j
@WebListener
public class MetricsListener extends MetricsServletsContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        log.info("MetricsListener init...");
        super.contextInitialized(event);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        log.info("MetricsListener destroyed!");
        super.contextDestroyed(event);
    }
}
