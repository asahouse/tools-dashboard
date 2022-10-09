package org.codeworks.web.toolsdashboardaatool.controller;

import org.codeworks.web.toolsdashboardaatool.dto.RecordCountAndCreateTime;
import org.codeworks.web.toolsdashboardaatool.dto.RecordOpenIdAndAda;
import org.codeworks.web.toolsdashboardaatool.service.dsl.DSLService;
import org.codeworks.web.toolsdashboardaatool.service.dsl.DSLServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("service")
@RestController
public class AAtoolController implements DSLServiceInterface {

    @Autowired
    DSLService dslService;

    @GetMapping("countCreator")
    public List<RecordCountAndCreateTime> countCreator(@RequestParam LocalDate start,
                                                       @RequestParam LocalDate end,
                                                       @RequestParam Boolean isGroupBy) {
        return dslService.countCreator(start, end, isGroupBy);
    }

    @GetMapping("countInviter")
    public List<RecordCountAndCreateTime> countInviter(@RequestParam LocalDate start,
                                                       @RequestParam LocalDate end,
                                                       @RequestParam Boolean isGroupBy) {
        return dslService.countInviter(start, end, isGroupBy);
    }

    @GetMapping("countJoiner")
    public List<RecordCountAndCreateTime> countJoiner(@RequestParam LocalDate start,
                                                      @RequestParam LocalDate end,
                                                      @RequestParam Boolean isGroupBy) {
        return dslService.countJoiner(start, end, isGroupBy);
    }

    @GetMapping("countPublishCampaign")
    public List<RecordCountAndCreateTime> countPublishCampaign(@RequestParam LocalDate start,
                                                               @RequestParam LocalDate end,
                                                               @RequestParam Boolean isGroupBy) {
        return dslService.countPublishCampaign(start, end, isGroupBy);
    }

    @GetMapping("countAAWorkShop")
    public List<RecordCountAndCreateTime> countAAWorkShop(@RequestParam LocalDate start,
                                                          @RequestParam LocalDate end,
                                                          @RequestParam Boolean isGroupBy) {
        return dslService.countAAWorkShop(start, end, isGroupBy);
    }

    @GetMapping("countAAWorkShopActive")
    public List<RecordCountAndCreateTime> countAAWorkShopActive(@RequestParam LocalDate start,
                                                                @RequestParam LocalDate end) {
        return dslService.countAAWorkShopActive(start, end);
    }

    @GetMapping("countTotalCampaign")
    public List<RecordCountAndCreateTime> countTotalCampaign() {
        return dslService.countTotalCampaign();
    }

    @GetMapping("countTotalUser")
    public List<RecordCountAndCreateTime> countTotalUser() {
        return dslService.countTotalUser();
    }

    @GetMapping("countUser")
    public List<RecordCountAndCreateTime> countUser(@RequestParam LocalDate start,
                                                    @RequestParam LocalDate end,
                                                    @RequestParam Boolean isGroupBy) {
        return dslService.countUser(start, end, isGroupBy);
    }

    @GetMapping("countApply")
    public List<RecordCountAndCreateTime> countApply(@RequestParam LocalDate start,
                                                     @RequestParam LocalDate end,
                                                     @RequestParam Boolean isGroupBy) {
        return dslService.countApply(start, end, isGroupBy);
    }

    @GetMapping("countApplyCampaign")
    public List<RecordCountAndCreateTime> countApplyCampaign(@RequestParam LocalDate start,
                                                             @RequestParam LocalDate end,
                                                             @RequestParam Boolean isGroupBy) {
        return dslService.countApplyCampaign(start, end, isGroupBy);
    }

    @GetMapping("countRegister")
    public List<RecordCountAndCreateTime> countRegister(@RequestParam LocalDate start,
                                                        @RequestParam LocalDate end,
                                                        @RequestParam Boolean isGroupBy) {
        return dslService.countRegister(start, end, isGroupBy);
    }

    @GetMapping("countRegisterTimes")
    public List<RecordCountAndCreateTime> countRegisterTimes(@RequestParam LocalDate start,
                                                             @RequestParam LocalDate end,
                                                             @RequestParam Boolean isGroupBy) {
        return dslService.countRegisterTimes(start, end, isGroupBy);
    }

    @GetMapping("countActiveCampaign")
    public List<RecordCountAndCreateTime> countActiveCampaign(@RequestParam LocalDate start,
                                                              @RequestParam LocalDate end) {
        return dslService.countActiveCampaign(start, end);
    }

    @GetMapping("countInviteShareTimes")
    public List<RecordCountAndCreateTime> countInviteShareTimes(@RequestParam LocalDate start,
                                                                @RequestParam LocalDate end,
                                                                @RequestParam Boolean isGroupBy) {
        return dslService.countInviteShareTimes(start, end, isGroupBy);
    }

    @GetMapping("countVisitorShareTimes")
    public List<RecordCountAndCreateTime> countVisitorShareTimes(@RequestParam LocalDate start,
                                                                 @RequestParam LocalDate end,
                                                                 @RequestParam Boolean isGroupBy) {
        return dslService.countVisitorShareTimes(start, end, isGroupBy);
    }

    @GetMapping("countABOShareTimes")
    public List<RecordCountAndCreateTime> countABOShareTimes(@RequestParam LocalDate start,
                                                             @RequestParam LocalDate end,
                                                             @RequestParam Boolean isGroupBy) {
        return dslService.countABOShareTimes(start, end, isGroupBy);
    }

    @GetMapping("countChannelApply")
    public List<RecordCountAndCreateTime> countChannelApply(@RequestParam LocalDate start,
                                                            @RequestParam LocalDate end,
                                                            @RequestParam Boolean isGroupBy,
                                                            @RequestParam Boolean isABOChannel) {
        return dslService.countChannelApply(start, end, isGroupBy, isABOChannel);
    }

    @GetMapping("countCreateOpenIdAndAda")
    public List<RecordOpenIdAndAda> countCreateOpenIdAndAda(@RequestParam LocalDate start,
                                                            @RequestParam LocalDate end) {
        return dslService.countCreateOpenIdAndAda(start, end);
    }

    @GetMapping("countApplyOpenIdAndAda")
    public List<RecordOpenIdAndAda> countApplyOpenIdAndAda(@RequestParam LocalDate start,
                                                            @RequestParam LocalDate end) {
        return dslService.countApplyOpenIdAndAda(start, end);
    }

    @GetMapping("countShareOpenIdAndAda")
    public List<RecordOpenIdAndAda> countShareOpenIdAndAda(@RequestParam LocalDate start,
                                                           @RequestParam LocalDate end) {
        return dslService.countShareOpenIdAndAda(start, end);
    }
}
