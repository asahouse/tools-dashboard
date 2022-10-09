package org.codeworks.web.toolsdashboardaatool.service;

import org.codeworks.web.toolsdashboardaatool.dto.overview.OverviewDigestDTO;
import org.codeworks.web.toolsdashboardaatool.dto.overview.OverviewListDTO;
import org.codeworks.web.toolsdashboardaatool.service.dsl.DSLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 为活动助手分栏的首页概要输出业务数据
 */
@Service
public class OverviewService extends ABDashboardService {

    @Autowired
    DSLService dslService;

    public OverviewDigestDTO getDigest(LocalDate start, LocalDate end){

        Long countCreate = this.parseSingleResultCountToLong(
                dslService.countCreator(start, end, false));

        Long countInvite = this.parseSingleResultCountToLong(
                dslService.countInviter(start, end, false));

        Long countJoin = this.parseSingleResultCountToLong(
                dslService.countJoiner(start, end, false));

        Long countUser = this.parseSingleResultCountToLong(
                dslService.countUser(start, end, false));

        return OverviewDigestDTO.builder()
                .createCount(countCreate)
                .inviteCount(countInvite)
                .joinCount(countJoin)
                .userCount(countUser)
                .build();
    }

    public List<OverviewListDTO> getList(LocalDate start, LocalDate end){

        Set<Integer> durationDates = this.parseLocalDateToStringSet(start, end);

        Map<Integer, Long> resultCountCreate = this.parseMultiResultCountToMap(dslService.countCreator(start, end, true));
        Map<Integer, Long> resultCountInvite = this.parseMultiResultCountToMap(dslService.countInviter(start, end, true));
        Map<Integer, Long> resultCountJoin = this.parseMultiResultCountToMap(dslService.countJoiner(start, end, true));
        Map<Integer, Long> resultCountUser = this.parseMultiResultCountToMap(dslService.countUser(start, end, true));

        return durationDates.stream().map(date -> {
            Long countCreate = Optional.ofNullable(resultCountCreate.get(date)).orElse(0L);
            Long countInvite = Optional.ofNullable(resultCountInvite.get(date)).orElse(0L);
            Long countJoin = Optional.ofNullable(resultCountJoin.get(date)).orElse(0L);
            Long countUser = Optional.ofNullable(resultCountUser.get(date)).orElse(0L);
            OverviewListDTO dto = OverviewListDTO.builder()
                    .createCount(countCreate).inviteCount(countInvite)
                    .joinCount(countJoin).userCount(countUser).date(date).build();
            return dto;
        }).collect(Collectors.toList());
    }



}
