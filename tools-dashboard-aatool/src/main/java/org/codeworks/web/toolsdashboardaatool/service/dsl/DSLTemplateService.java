package org.codeworks.web.toolsdashboardaatool.service.dsl;

import com.codahale.metrics.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.codeworks.web.toolsdashboardaatool.dto.RecordCountAndCreateTime;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 把原有的报表SQL,输出为JOOQ结果集
 * 按模板维度输出
 */

@Slf4j
@Service
public class DSLTemplateService {

    @Autowired
    DSLContext dsl;

    /**
     * 活动创建人
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    @Timed
    @Cacheable("CacheDSLCountCreator")
    public List<RecordCountAndCreateTime> countCreator(LocalDate start, LocalDate end, Boolean isGroupBy, Integer templateId){

        String groupBySQL = isGroupBy ? " group by TO_DAYS(c.createTime);" : ";";
        return dsl.resultQuery(
                "select count(DISTINCT createUserId) as count, c.createTime from aatool.campaign c " +
                        "where c.`status` != 1 and c.templateId = ? " +
                        "and c.createTime BETWEEN ? and ? " + groupBySQL,
                templateId,
                start.atTime(0,0), end.atTime(23,59,59))
                .fetchInto(RecordCountAndCreateTime.class);
    }

    /**
     * 邀请人
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    @Timed
    @Cacheable("CacheDSLCountInviter")
    public List<RecordCountAndCreateTime> countInviter(LocalDate start, LocalDate end, Boolean isGroupBy, Integer templateId){

        String groupBySQL = isGroupBy ? " group by TO_DAYS(s.createTime);" : ";";
        return  dsl.resultQuery(
                "select count(DISTINCT s.userId) as count, s.createTime from aatool.share s " +
                        "join aatool.campaign c on FIND_IN_SET(c.id , s.`campaignIds`) and c.`templateId` = ? " +
                        "where s.userType = 2 and s.inviteCode != '0' and s.inviteCode != '-1' " + //LENGTH(s.inviteCode) > 1
                        "and s.createTime BETWEEN ? and ? " + groupBySQL,
                templateId,
                start.atTime(0,0), end.atTime(23,59,59))
                .fetchInto(RecordCountAndCreateTime.class);
    }

    /**
     * 活动参与者 (分享给其他人，但没有报名)
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    @Timed
    @Cacheable("CacheDSLCountJoiner")
    public List<RecordCountAndCreateTime> countJoiner(LocalDate start, LocalDate end, Boolean isGroupBy, Integer templateId){

        String groupBySQL = isGroupBy ? " group by TO_DAYS(s.createTime);" : ";";
        return  dsl.resultQuery(
                "select count(DISTINCT s.userId) as count , s.createTime from aatool.share s " +
                        "join aatool.campaign c on FIND_IN_SET(c.id , s.`campaignIds`) and c.`templateId` = ? " +
                        "where s.userType = 3 and s.inviteCode = '0' " + //LENGTH(s.inviteCode) = 1
                        "and s.createTime BETWEEN ? and ? " + groupBySQL,
                templateId,
                start.atTime(0,0), end.atTime(23,59,59))
                .fetchInto(RecordCountAndCreateTime.class);
    }

    /**
     * 活动当月发布总数
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    @Timed
    @Cacheable("CacheDSLCountPublishCampaign")
    public List<RecordCountAndCreateTime> countPublishCampaign(LocalDate start, LocalDate end, Boolean isGroupBy, Integer templateId){

        String groupBySQL = isGroupBy ? " group by TO_DAYS(c.createTime);" : ";";
        return  dsl.resultQuery(
                "select count(c.id) as count , c.createTime from aatool.campaign c " +
                        "where c.`status` != 1 and c.templateId = ? " +
                        "and c.createTime BETWEEN ? and ? " + groupBySQL,
                templateId,
                start.atTime(0,0), end.atTime(23,59,59))
                .fetchInto(RecordCountAndCreateTime.class);
    }

    /**
     * 关联移动工作室的当月活动数(期间创建的活动)
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    @Timed
    @Cacheable("CacheDSLCountAAWorkShop")
    public List<RecordCountAndCreateTime> countAAWorkShop(LocalDate start, LocalDate end, Boolean isGroupBy, Integer templateId){

        String groupBySQL = isGroupBy ? " group by TO_DAYS(c.createTime);" : ";";
        return  dsl.resultQuery(
                "select count(c.id) as count , c.createTime from aatool.campaign c " +
                        "where c.`status` = 0 and c.ada is not null and c.templateId = ? " +
                        "and c.createTime BETWEEN ? and ? " + groupBySQL,
                templateId,
                start.atTime(0,0), end.atTime(23,59,59))
                .fetchInto(RecordCountAndCreateTime.class);
    }

    /**
     * 关联移动工作室的活动总数(期间举行的活动)
     * @param start
     * @param end
     * @return
     */
    @Timed
    @Cacheable("CacheDSLCountAAWorkShopActive")
    public List<RecordCountAndCreateTime> countAAWorkShopActive(LocalDate start, LocalDate end, Integer templateId){
        return  dsl.resultQuery(
                "select count(c.id) as count from aatool.campaign c " +
                        "where c.`status` = 0 and c.ada is not null and c.`templateId` = ? " +
                        "and (c.startTime >= ? and c.endTime <= ?) ",
                templateId,
                start.atTime(0,0), end.atTime(23,59,59))
                .fetchInto(RecordCountAndCreateTime.class);
    }

    /**
     * 总报名次数
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    @Timed
    @Cacheable("CacheDSLCountApply")
    public List<RecordCountAndCreateTime> countApply(LocalDate start, LocalDate end, Boolean isGroupBy, Integer templateId){

        String groupBySQL = isGroupBy ? " group by TO_DAYS(a.createTime);" : ";" ;
        return  dsl.resultQuery(
                "select count(a.id) as count , a.createTime from aatool.apply a " +
                        "join aatool.campaign c on a.`campaignId` = c.id and c.`templateId` = ? " +
                        "where a.`status` = 0 " +
                        "and a.createTime BETWEEN ? and ? " + groupBySQL,
                templateId,
                start.atTime(0,0), end.atTime(23,59,59))
                .fetchInto(RecordCountAndCreateTime.class);
    }

    /**
     * 有产生报名的活动数
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    @Timed
    @Cacheable("CacheDSLCountApplyCampaign")
    public List<RecordCountAndCreateTime> countApplyCampaign(LocalDate start, LocalDate end, Boolean isGroupBy, Integer templateId){

        String groupBySQL = isGroupBy ? " group by TO_DAYS(a.createTime);" : ";";
        return  dsl.resultQuery(
                "select count(distinct c.id) as count , a.createTime from aatool.campaign c " +
                        "join aatool.apply a " +
                        "on c.id = a.campaignId and c.`templateId` = ? and c.`status` = 0 " +
                        "where a.createTime BETWEEN ? and ? " + groupBySQL,
                templateId,
                start.atTime(0,0), end.atTime(23,59,59)).fetchInto(RecordCountAndCreateTime.class);
    }

    /**
     * 活动助手签到人数 （记录产生时间为准)
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    @Timed
    @Cacheable("CacheDSLCountRegister")
    public List<RecordCountAndCreateTime> countRegister(LocalDate start, LocalDate end, Boolean isGroupBy, Integer templateId){

        String groupBySQL = isGroupBy ? " group by TO_DAYS(r.createTime);" : ";";
        return  dsl.resultQuery(
                "select count(DISTINCT r.userId) as count , r.createTime from aatool.campaign c " +
                        "join aatool.register r on c.id = r.campaignId " +
                        "where c.`status` = 0 and c.`templateId` = ? " +
                        "and r.createTime BETWEEN ? and ? " + groupBySQL,
                templateId,
                start.atTime(0,0), end.atTime(23,59,59)).fetchInto(RecordCountAndCreateTime.class);
    }

    /**
     * 活动助手签到次数 （记录产生时间为准）
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    @Timed
    @Cacheable("CacheDSLCountRegisterTimes")
    public List<RecordCountAndCreateTime> countRegisterTimes(LocalDate start, LocalDate end, Boolean isGroupBy, Integer templateId){

        String groupBySQL = isGroupBy ? " group by TO_DAYS(r.createTime);" : ";";
        return  dsl.resultQuery(
                "select count(c.id) as count , r.createTime from aatool.campaign c " +
                        "join aatool.register r on c.id = r.campaignId " +
                        "where c.`status` = 0 and c.`templateId` = ? " +
                        "and r.createTime BETWEEN ? and ? " + groupBySQL,
                templateId,
                start.atTime(0,0), end.atTime(23,59,59)).fetchInto(RecordCountAndCreateTime.class);
    }

    /**
     * 当前有效活动
     * @param start
     * @param end
     * @return
     */
    @Timed
    @Cacheable("CacheDSLCountActiveCampaign")
    public List<RecordCountAndCreateTime> countActiveCampaign(LocalDate start, LocalDate end, Integer templateId){

        return  dsl.resultQuery(
                "select count(c.id) as count from aatool.campaign c " +
                        "where c.`status` = 0 and c.`templateId` = ? " +
                        "and (c.startTime >= ? and c.endTime <= ?) ",
                templateId,
                start.atTime(0,0), end.atTime(23,59,59)).fetchInto(RecordCountAndCreateTime.class);
    }

    /**
     * 邀请人（有填邀请码）的分享次数
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    @Timed
    @Cacheable("CacheDSLCountInviteShareTimes")
    public List<RecordCountAndCreateTime> countInviteShareTimes(LocalDate start, LocalDate end, Boolean isGroupBy, Integer templateId){

        String groupBySQL = isGroupBy ? " group by TO_DAYS(s.createTime);" : ";" ;
        return  dsl.resultQuery(
                "select count(s.id) as count ,s.createTime from aatool.`share` s " +
                        "join aatool.campaign c on FIND_IN_SET(c.id , s.`campaignIds`) and c.`templateId` = ? " +
                        "where s.userType=2 and s.inviteCode != '0' and s.inviteCode != '-1' " +
                        "and s.createTime BETWEEN ? and ? " + groupBySQL,
                templateId,
                start.atTime(0,0), end.atTime(23,59,59)).fetchInto(RecordCountAndCreateTime.class);
    }

    /**
     * 游客（无填邀请码）的分享次数
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    @Timed
    @Cacheable("CacheDSLCountVisitorShareTimes")
    public List<RecordCountAndCreateTime> countVisitorShareTimes(LocalDate start, LocalDate end, Boolean isGroupBy, Integer templateId){

        String groupBySQL = isGroupBy ? " group by TO_DAYS(s.createTime);" : ";";
        return  dsl.resultQuery(
                "select count(s.id) as count , s.createTime from aatool.share s " +
                        "join aatool.campaign c on FIND_IN_SET(c.id , s.`campaignIds`) and c.`templateId` = ? " +
                        "where s.userType = 3 and s.inviteCode = '0' " + //LENGTH(s.inviteCode) = 1
                        "and s.createTime BETWEEN ? and ? " + groupBySQL,
                templateId,
                start.atTime(0,0), end.atTime(23,59,59)).fetchInto(RecordCountAndCreateTime.class);
    }

    /**
     * 室主的分享次数
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    @Timed
    @Cacheable("CacheDSLCountABOShareTimes")
    public List<RecordCountAndCreateTime> countABOShareTimes(LocalDate start, LocalDate end, Boolean isGroupBy, Integer templateId){

        String groupBySQL = isGroupBy ? " group by TO_DAYS(s.createTime);" : ";";
        return  dsl.resultQuery(
                "select count(s.id) as count , s.createTime from aatool.`share` s " +
                        "join aatool.campaign c on FIND_IN_SET(c.id , s.`campaignIds`) and c.`templateId` = ? " +
                        "where s.userType=1 and s.inviteCode = '-1' " +
                        "and s.createTime BETWEEN ? and ? " + groupBySQL,
                templateId,
                start.atTime(0,0), end.atTime(23,59,59)).fetchInto(RecordCountAndCreateTime.class);
    }

    /**
     * 是否通过室主产生报名的人数
     * @param start
     * @param end
     * @param isGroupBy
     * @param isABOChannel
     * @return
     */
    @Timed
    @Cacheable("CacheDSLCountChannelApply")
    public List<RecordCountAndCreateTime> countChannelApply(LocalDate start, LocalDate end, Boolean isGroupBy, Boolean isABOChannel, Integer templateId){

        String handlerShareIdSQL = isABOChannel ? "s.handlerShareId = -1 " : "s.handlerShareId != -1 ";

        String groupBySQL = isGroupBy ? " group by TO_DAYS(a.createTime);" : ";";
        return  dsl.resultQuery(
                "select COUNT(DISTINCT u.id) as count , a.createTime from aatool.`user` u " +
                        "join aatool.apply a ON u.id = a.userId " +
                        "join aatool.`share` s ON s.id = a.shareId " +
                        "join aatool.campaign c on a.`campaignId` = c.id and c.`templateId` = ? " +
                        "where " +
                        handlerShareIdSQL +
                        "and u.ada is not null and u.ada != '' " +
                        "and a.createTime BETWEEN ? and ? " + groupBySQL,
                templateId,
                start.atTime(0,0), end.atTime(23,59,59)).fetchInto(RecordCountAndCreateTime.class);
    }
}
