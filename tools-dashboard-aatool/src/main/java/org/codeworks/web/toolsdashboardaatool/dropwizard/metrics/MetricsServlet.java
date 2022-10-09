package org.codeworks.web.toolsdashboardaatool.dropwizard.metrics;

import com.codahale.metrics.servlets.AdminServlet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@Slf4j
@WebServlet(urlPatterns = "/admin-metrics/*")
public class MetricsServlet extends AdminServlet {

    @Override
    public void init() throws ServletException {
        log.info("MetricsServlet init..");
        super.init();
    }
}
