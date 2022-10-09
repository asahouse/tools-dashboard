package org.codeworks.web.toolsdashboardwebfacade.service;

import org.codeworks.web.toolsdashboardwebfacade.dto.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "TD-APLUS")
public interface APlusService {

    @GetMapping("data/organise")
    Response organise(@RequestParam("start") String start,
                      @RequestParam("end") String end);

    @GetMapping("data/template")
    Response templateListData(@RequestParam("start") String start,
                              @RequestParam("end") String end,
                              @RequestParam("page") Integer page,
                              @RequestParam("size") Integer size);

    @GetMapping("data/music")
    Response musicListData(@RequestParam("start") String start,
                           @RequestParam("end") String end,
                           @RequestParam("page") Integer page,
                           @RequestParam("size") Integer size);

    @GetMapping("data/organise/list")
    Response organiseDataList(@RequestParam("start") String start,
                              @RequestParam("end") String end,
                              @RequestParam("page") Integer page,
                              @RequestParam("size") Integer size);

    @GetMapping("data/gallery/list")
    Response galleryDataList(@RequestParam("start") String start,
                             @RequestParam("end") String end,
                             @RequestParam("page") Integer page,
                             @RequestParam("size") Integer size);

    @GetMapping("data/creator/ada")
    List<String> creatorAda(@RequestParam("start") String start,
                            @RequestParam("end") String end);
}
