package org.codeworks.web.toolsdashboardaatool.service;

import lombok.extern.slf4j.Slf4j;
import org.codeworks.web.toolsdashboardaatool.dto.interactive.InteractiveApplyListDTO;
import org.codeworks.web.toolsdashboardaatool.dto.interactive.InteractiveRegisterListDTO;
import org.codeworks.web.toolsdashboardaatool.dto.interactive.InteractiveShareListDTO;
import org.codeworks.web.toolsdashboardaatool.service.dsl.DSLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InteractiveService extends ABDashboardService {


    /**
     活动分享次数 - 来自活动创始人 = 室主的分享次数
     活动分享次数 - 来自非活动创建人 = 邀请人的分享次数 + 游客的分享次数
     活动分享总量 = 室主 + 游客 + 邀请人
     活动报名人数 - 来自活动创始人 = 通过室主产生报名的人数
     活动报名人数 - 来自非活动创始人 = 通过非室主产生报名的人数
     活动报名总人数 = 通过非室主产生报名的人数 + 通过室主产生报名的人数
     活动报名总次数 = 总报名次数
     */


    @Autowired
    DSLService dslService;

    /**
     * 分享数据列表
     * @param start
     * @param end
     * @return
     */
    public List<InteractiveShareListDTO> getShareList(LocalDate start, LocalDate end){

        Set<Integer> durationDates = this.parseLocalDateToStringSet(start, end);

        Map<Integer, Long> resultCountShareFromABO = this.parseMultiResultCountToMap(
                dslService.countABOShareTimes(start, end, true));

        Map<Integer, Long> resultCountShareFromInvite = this.parseMultiResultCountToMap(
                dslService.countInviteShareTimes(start, end, true));

        Map<Integer, Long> resultCountShareFromVisitor = this.parseMultiResultCountToMap(
                dslService.countVisitorShareTimes(start, end, true));

        return durationDates.parallelStream().map(date -> {
            Long countFromABO = Optional.ofNullable(resultCountShareFromABO.get(date)).orElse(0L);
            Long countFromInvite = Optional.ofNullable(resultCountShareFromInvite.get(date)).orElse(0L);
            Long countFromVisitor = Optional.ofNullable(resultCountShareFromVisitor.get(date)).orElse(0L);

            Long notFromABO = BigDecimal.valueOf(countFromInvite)
                    .add(BigDecimal.valueOf(countFromVisitor)).longValue();

            InteractiveShareListDTO dto = InteractiveShareListDTO.builder()
                    .fromCreaterTimes(countFromABO)
                    .fromNotCreaterTimes(notFromABO)
                    .date(date).build();
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * 报名数据列表
     * @param start
     * @param end
     * @return
     */
    public List<InteractiveApplyListDTO> getApplyList(LocalDate start, LocalDate end){

        Set<Integer> durationDates = this.parseLocalDateToStringSet(start, end);

        Map<Integer, Long> resultCountFromABO = this.parseMultiResultCountToMap(
                dslService.countChannelApply(start, end, true, true));

        Map<Integer, Long> resultCountNotFromABO = this.parseMultiResultCountToMap(
                dslService.countChannelApply(start, end, true, false));

        Map<Integer, Long> resultCountTotalTimes = this.parseMultiResultCountToMap(
                dslService.countApply(start, end, true));


        return durationDates.parallelStream().map(date -> {
            Long countFromABO = Optional.ofNullable(resultCountFromABO.get(date)).orElse(0L);
            Long countNotFromABO = Optional.ofNullable(resultCountNotFromABO.get(date)).orElse(0L);
            Long totalTimes = Optional.ofNullable(resultCountTotalTimes.get(date)).orElse(0L);

            Long totalUser = BigDecimal.valueOf(countFromABO)
                    .add(BigDecimal.valueOf(countNotFromABO)).longValue();

            InteractiveApplyListDTO dto = InteractiveApplyListDTO.builder()
                    .fromCreaterTimes(countFromABO)
                    .fromNotCreaterTimes(countNotFromABO)
                    .totalApplyTimes(totalTimes)
                    .totalApplyUser(totalUser)
                    .date(date).build();
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * 签到数据列表
     * @param start
     * @param end
     * @return
     */
    public List<InteractiveRegisterListDTO> getRegisterList(LocalDate start, LocalDate end){

        Set<Integer> durationDates = this.parseLocalDateToStringSet(start, end);

        Map<Integer, Long> resultRegisterCountUser = this.parseMultiResultCountToMap(
                dslService.countRegister(start, end, true));

        Map<Integer, Long> resultRegisterCountTimes = this.parseMultiResultCountToMap(
                dslService.countRegisterTimes(start, end, true));


        return durationDates.parallelStream().map(date -> {
            Long totalRegisterTimes = Optional.ofNullable(resultRegisterCountTimes.get(date)).orElse(0L);
            Long totalRegisterUser = Optional.ofNullable(resultRegisterCountUser.get(date)).orElse(0L);

            InteractiveRegisterListDTO dto = InteractiveRegisterListDTO.builder()
                    .totalRegisterTimes(totalRegisterTimes)
                    .totalRegisterUser(totalRegisterUser)
                    .date(date).build();
            return dto;
        }).collect(Collectors.toList());
    }
}
