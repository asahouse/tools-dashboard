package org.codeworks.web.toolsdashboardwebfacade.service;

import org.codeworks.web.toolsdashboardwebfacade.dto.Response;
import org.codeworks.web.toolsdashboardwebfacade.dto.aatool.RecordCountAndCreateTime;
import org.codeworks.web.toolsdashboardwebfacade.dto.aatool.RecordOpenIdAndAda;
import org.codeworks.web.toolsdashboardwebfacade.service.fallback.AAToolServiceFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "TD-AATOOL", fallbackFactory = AAToolServiceFallbackFactory.class)
public interface AAToolService {

    @RequestMapping(value = "interactive/share/list", method = RequestMethod.GET)
    Response share(@RequestParam("start") String start,
                   @RequestParam("end") String end,
                   @RequestParam(required = false, defaultValue = "1", value="pageIndex")
                           Integer pageIndex,
                   @RequestParam(required = false, defaultValue = "10", value="pageSize")
                           Integer pageSize);

    @RequestMapping(value = "interactive/apply/list", method = RequestMethod.GET)
    Response apply(@RequestParam("start") String start,
                   @RequestParam("end") String end,
                   @RequestParam(required = false, defaultValue = "1", value="pageIndex")
                           Integer pageIndex,
                   @RequestParam(required = false, defaultValue = "10", value="pageSize")
                           Integer pageSize);

    @RequestMapping(value = "interactive/register/list", method = RequestMethod.GET)
    Response register(@RequestParam("start") String start,
                      @RequestParam("end") String end,
                      @RequestParam(required = false, defaultValue = "1", value="pageIndex")
                              Integer pageIndex,
                      @RequestParam(required = false, defaultValue = "10", value="pageSize")
                              Integer pageSize);

    @RequestMapping(value = "material/template/graph", method = RequestMethod.GET)
    Response templateGraph(@RequestParam("templateIds") List<Integer> templateIds,
                           @RequestParam("start") String start,
                           @RequestParam("end") String end,
                           @RequestParam(required = false, defaultValue = "1", value="pageIndex")
                                   Integer pageIndex,
                           @RequestParam(required = false, defaultValue = "10", value="pageSize")
                                   Integer pageSize);

    @RequestMapping(value = "material/template/list", method = RequestMethod.GET)
    Response templateList(@RequestParam("templateIds") List<Integer> templateIds,
                          @RequestParam("start") String start,
                          @RequestParam("end") String end,
                          @RequestParam(required = false, defaultValue = "1", value="pageIndex")
                                  Integer pageIndex,
                          @RequestParam(required = false, defaultValue = "10", value="pageSize")
                                  Integer pageSize);

    @RequestMapping(value = "campaign/list", method = RequestMethod.GET)
    Response campaignList(@RequestParam("start") String start,
                          @RequestParam("end") String end,
                          @RequestParam(required = false, defaultValue = "1", value="pageIndex")
                                  Integer pageIndex,
                          @RequestParam(required = false, defaultValue = "10", value="pageSize")
                                  Integer pageSize);

    @RequestMapping(value = "overview/digest", method = RequestMethod.GET)
    Response digest(@RequestParam("start") String start,
                    @RequestParam("end") String end);

    @RequestMapping(value = "overview/list", method = RequestMethod.GET)
    Response list(@RequestParam("start") String start,
                  @RequestParam("end") String end,
                  @RequestParam(required = false, defaultValue = "1", value="pageIndex")
                          Integer pageIndex,
                  @RequestParam(required = false, defaultValue = "10", value="pageSize")
                          Integer pageSize);







    @GetMapping("service/countCreator")
    List<RecordCountAndCreateTime> countCreator(@RequestParam("start") LocalDate start,
                                                @RequestParam("end") LocalDate end,
                                                @RequestParam("isGroupBy") Boolean isGroupBy);

    @GetMapping("service/countInviter")
    List<RecordCountAndCreateTime> countInviter(@RequestParam("start") LocalDate start,
                                                @RequestParam("end") LocalDate end,
                                                @RequestParam("isGroupBy") Boolean isGroupBy);

    @GetMapping("service/countJoiner")
    List<RecordCountAndCreateTime> countJoiner(@RequestParam("start") LocalDate start,
                                               @RequestParam("end") LocalDate end,
                                               @RequestParam("isGroupBy") Boolean isGroupBy);

    @GetMapping("service/countPublishCampaign")
    List<RecordCountAndCreateTime> countPublishCampaign(@RequestParam("start") LocalDate start,
                                                        @RequestParam("end") LocalDate end,
                                                        @RequestParam("isGroupBy") Boolean isGroupBy);

    @GetMapping("service/countAAWorkShop")
    List<RecordCountAndCreateTime> countAAWorkShop(@RequestParam("start") LocalDate start,
                                                   @RequestParam("end") LocalDate end,
                                                   @RequestParam("isGroupBy") Boolean isGroupBy);

    @GetMapping("service/countAAWorkShopActive")
    List<RecordCountAndCreateTime> countAAWorkShopActive(@RequestParam("start") LocalDate start,
                                                         @RequestParam("end") LocalDate end);

    @GetMapping("service/countTotalCampaign")
    List<RecordCountAndCreateTime> countTotalCampaign();

    @GetMapping("service/countTotalUser")
    List<RecordCountAndCreateTime> countTotalUser();

    @GetMapping("service/countUser")
    List<RecordCountAndCreateTime> countUser(@RequestParam("start") LocalDate start,
                                             @RequestParam("end") LocalDate end,
                                             @RequestParam("isGroupBy") Boolean isGroupBy);

    @GetMapping("service/countApply")
    List<RecordCountAndCreateTime> countApply(@RequestParam("start") LocalDate start,
                                              @RequestParam("end") LocalDate end,
                                              @RequestParam("isGroupBy") Boolean isGroupBy);

    @GetMapping("service/countApplyCampaign")
    List<RecordCountAndCreateTime> countApplyCampaign(@RequestParam("start") LocalDate start,
                                                      @RequestParam("end") LocalDate end,
                                                      @RequestParam("isGroupBy") Boolean isGroupBy);

    @GetMapping("service/countRegister")
    List<RecordCountAndCreateTime> countRegister(@RequestParam("start") LocalDate start,
                                                 @RequestParam("end") LocalDate end,
                                                 @RequestParam("isGroupBy") Boolean isGroupBy);

    @GetMapping("service/countRegisterTimes")
    List<RecordCountAndCreateTime> countRegisterTimes(@RequestParam("start") LocalDate start,
                                                      @RequestParam("end") LocalDate end,
                                                      @RequestParam("isGroupBy") Boolean isGroupBy);

    @GetMapping("service/countActiveCampaign")
    List<RecordCountAndCreateTime> countActiveCampaign(@RequestParam("start") LocalDate start,
                                                       @RequestParam("end") LocalDate end);

    @GetMapping("service/countInviteShareTimes")
    List<RecordCountAndCreateTime> countInviteShareTimes(@RequestParam("start") LocalDate start,
                                                         @RequestParam("end") LocalDate end,
                                                         @RequestParam("isGroupBy") Boolean isGroupBy);

    @GetMapping("service/countVisitorShareTimes")
    List<RecordCountAndCreateTime> countVisitorShareTimes(@RequestParam("start") LocalDate start,
                                                          @RequestParam("end") LocalDate end,
                                                          @RequestParam("isGroupBy") Boolean isGroupBy);

    @GetMapping("service/countABOShareTimes")
    List<RecordCountAndCreateTime> countABOShareTimes(@RequestParam("start") LocalDate start,
                                                      @RequestParam("end") LocalDate end,
                                                      @RequestParam("isGroupBy") Boolean isGroupBy);

    @GetMapping("service/countChannelApply")
    List<RecordCountAndCreateTime> countChannelApply(@RequestParam("start") LocalDate start,
                                                     @RequestParam("end") LocalDate end,
                                                     @RequestParam("isGroupBy") Boolean isGroupBy,
                                                     @RequestParam("isABOChannel") Boolean isABOChannel);

    @GetMapping("service/countCreateOpenIdAndAda")
    List<RecordOpenIdAndAda> countCreateOpenIdAndAda(@RequestParam("start") LocalDate start,
                                                           @RequestParam("end") LocalDate end);

    @GetMapping("service/countApplyOpenIdAndAda")
    List<RecordOpenIdAndAda> countApplyOpenIdAndAda(@RequestParam("start") LocalDate start,
                                                          @RequestParam("end") LocalDate end);

    @GetMapping("service/countShareOpenIdAndAda")
    List<RecordOpenIdAndAda> countShareOpenIdAndAda(@RequestParam("start") LocalDate start,
                                                    @RequestParam("end") LocalDate end);
    
}