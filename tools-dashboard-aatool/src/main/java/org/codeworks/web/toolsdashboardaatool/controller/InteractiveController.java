package org.codeworks.web.toolsdashboardaatool.controller;

import org.codeworks.web.toolsdashboardaatool.dto.interactive.InteractiveApplyListDTO;
import org.codeworks.web.toolsdashboardaatool.dto.interactive.InteractiveRegisterListDTO;
import org.codeworks.web.toolsdashboardaatool.dto.interactive.InteractiveShareListDTO;
import org.codeworks.web.toolsdashboardaatool.service.InteractiveService;
import org.codeworks.web.toolsdashboardaatool.utils.ArrayPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("interactive")
@RestController
public class InteractiveController {

    @Autowired
    InteractiveService interactiveService;

    @GetMapping("share/list")
    public Response share(@RequestParam String start,
                          @RequestParam String end,
                          @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                          @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        List<InteractiveShareListDTO> result = interactiveService.getShareList(
                LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .parallelStream().sorted(
                        Comparator.comparing(InteractiveShareListDTO::getDate).reversed())
                .collect(Collectors.toList());

        ArrayPage page = new ArrayPage(result.toArray(), pageIndex, pageSize);
        return Response.ok("body", page.getDisplayResult()).add("total",page.getTotalCount());
    }

    @GetMapping("apply/list")
    public Response apply(@RequestParam String start,
                          @RequestParam String end,
                          @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                          @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        List<InteractiveApplyListDTO> result = interactiveService.getApplyList(
                LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .parallelStream().sorted(
                        Comparator.comparing(InteractiveApplyListDTO::getDate).reversed())
                .collect(Collectors.toList());

        ArrayPage page = new ArrayPage(result.toArray(), pageIndex, pageSize);
        return Response.ok("body", page.getDisplayResult()).add("total",page.getTotalCount());
    }

    @GetMapping("register/list")
    public Response register(@RequestParam String start,
                             @RequestParam String end,
                             @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                             @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        List<InteractiveRegisterListDTO> result = interactiveService.getRegisterList(
                LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .parallelStream().sorted(
                        Comparator.comparing(InteractiveRegisterListDTO::getDate).reversed())
                .collect(Collectors.toList());

        ArrayPage page = new ArrayPage(result.toArray(), pageIndex, pageSize);
        return Response.ok("body", page.getDisplayResult()).add("total",page.getTotalCount());
    }
}
