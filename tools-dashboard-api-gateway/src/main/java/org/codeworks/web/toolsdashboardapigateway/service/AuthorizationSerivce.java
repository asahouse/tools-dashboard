package org.codeworks.web.toolsdashboardapigateway.service;

import org.codeworks.web.toolsdashboardapigateway.dto.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "TD-AUTHORIZATION")
public interface AuthorizationSerivce {

    @PostMapping("login")
    Response login(@RequestParam("name") String name,
                   @RequestParam("password") String password);

    @GetMapping("logout")
    Response logout(@RequestParam("token") String token);

    @GetMapping("haslogin")
    Boolean haslogin(@RequestParam("token") String token);

}
