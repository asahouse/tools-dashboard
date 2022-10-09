package org.codeworks.web.toolsdashboardwebfacade.service.fallback;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.codeworks.web.toolsdashboardwebfacade.dto.Response;
import org.codeworks.web.toolsdashboardwebfacade.dto.aatool.RecordCountAndCreateTime;
import org.codeworks.web.toolsdashboardwebfacade.dto.aatool.RecordOpenIdAndAda;
import org.codeworks.web.toolsdashboardwebfacade.service.AAToolService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AAToolServiceFallbackFactory implements FallbackFactory<AAToolService> {

    @Override
    public AAToolService create(Throwable cause) {
        return new AAToolService() {
            @Override
            public Response share(String start, String end, Integer pageIndex, Integer pageSize) {
                return Response.error("Error fallback!; reason was: " + cause.getMessage());
            }

            @Override
            public Response apply(String start, String end, Integer pageIndex, Integer pageSize) {
                return Response.error("Error fallback!; reason was: " + cause.getMessage());
            }

            @Override
            public Response register(String start, String end, Integer pageIndex, Integer pageSize) {
                return Response.error("Error fallback!; reason was: " + cause.getMessage());
            }

            @Override
            public Response templateGraph(List<Integer> templateIds, String start, String end, Integer pageIndex, Integer pageSize) {
                return Response.error("Error fallback!; reason was: " + cause.getMessage());
            }

            @Override
            public Response templateList(List<Integer> templateIds, String start, String end, Integer pageIndex, Integer pageSize) {
                return Response.error("Error fallback!; reason was: " + cause.getMessage());
            }

            @Override
            public Response campaignList(String start, String end, Integer pageIndex, Integer pageSize){
                return Response.error("Error fallback!; reason was: " + cause.getMessage());
            }

            @Override
            public Response digest(String start, String end) {
                return Response.error("Error fallback!; reason was: " + cause.getMessage());
            }

            @Override
            public Response list(String start, String end, Integer pageIndex, Integer pageSize) {
                return Response.error("Error fallback!; reason was: " + cause.getMessage());
            }

            @Override
            public List<RecordCountAndCreateTime> countCreator(LocalDate start, LocalDate end, Boolean isGroupBy) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordCountAndCreateTime> countInviter(LocalDate start, LocalDate end, Boolean isGroupBy) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordCountAndCreateTime> countJoiner(LocalDate start, LocalDate end, Boolean isGroupBy) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordCountAndCreateTime> countPublishCampaign(LocalDate start, LocalDate end, Boolean isGroupBy) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordCountAndCreateTime> countAAWorkShop(LocalDate start, LocalDate end, Boolean isGroupBy) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordCountAndCreateTime> countAAWorkShopActive(LocalDate start, LocalDate end) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordCountAndCreateTime> countTotalCampaign() {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordCountAndCreateTime> countTotalUser() {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordCountAndCreateTime> countUser(LocalDate start, LocalDate end, Boolean isGroupBy) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordCountAndCreateTime> countApply(LocalDate start, LocalDate end, Boolean isGroupBy) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordCountAndCreateTime> countApplyCampaign(LocalDate start, LocalDate end, Boolean isGroupBy) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordCountAndCreateTime> countRegister(LocalDate start, LocalDate end, Boolean isGroupBy) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordCountAndCreateTime> countRegisterTimes(LocalDate start, LocalDate end, Boolean isGroupBy) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordCountAndCreateTime> countActiveCampaign(LocalDate start, LocalDate end) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordCountAndCreateTime> countInviteShareTimes(LocalDate start, LocalDate end, Boolean isGroupBy) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordCountAndCreateTime> countVisitorShareTimes(LocalDate start, LocalDate end, Boolean isGroupBy) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordCountAndCreateTime> countABOShareTimes(LocalDate start, LocalDate end, Boolean isGroupBy) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordCountAndCreateTime> countChannelApply(LocalDate start, LocalDate end, Boolean isGroupBy, Boolean isABOChannel) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordOpenIdAndAda> countCreateOpenIdAndAda(LocalDate start, LocalDate end) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordOpenIdAndAda> countApplyOpenIdAndAda(LocalDate start, LocalDate end) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }

            @Override
            public List<RecordOpenIdAndAda> countShareOpenIdAndAda(LocalDate start, LocalDate end) {
                log.error("Error AATool Service fallback!; reason was: " + cause.getMessage());
                return new ArrayList<>();
            }
        };
    }
}
