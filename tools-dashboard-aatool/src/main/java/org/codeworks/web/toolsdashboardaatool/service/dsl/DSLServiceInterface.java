package org.codeworks.web.toolsdashboardaatool.service.dsl;

import org.codeworks.web.toolsdashboardaatool.dto.RecordCountAndCreateTime;

import java.time.LocalDate;
import java.util.List;

/**
 * 把原有的报表SQL,输出为JOOQ结果集
 */

public interface DSLServiceInterface {

    /**
     * 活动创建人
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    List<RecordCountAndCreateTime> countCreator(LocalDate start, LocalDate end, Boolean isGroupBy);

    /**
     * 邀请人
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    List<RecordCountAndCreateTime> countInviter(LocalDate start, LocalDate end, Boolean isGroupBy);

    /**
     * 活动参与者 (分享给其他人，但没有报名)
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    List<RecordCountAndCreateTime> countJoiner(LocalDate start, LocalDate end, Boolean isGroupBy);

    /**
     * 活动当月发布总数
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    List<RecordCountAndCreateTime> countPublishCampaign(LocalDate start, LocalDate end, Boolean isGroupBy);

    /**
     * 关联移动工作室的当月活动数(期间创建的活动)
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    List<RecordCountAndCreateTime> countAAWorkShop(LocalDate start, LocalDate end, Boolean isGroupBy);

    /**
     * 关联移动工作室的活动总数(期间举行的活动)
     * @param start
     * @param end
     * @return
     */
    List<RecordCountAndCreateTime> countAAWorkShopActive(LocalDate start, LocalDate end);

    /**
     * 活动发布总数
     * @return
     */
    List<RecordCountAndCreateTime> countTotalCampaign();

    /**
     * 用户总数
     * @return
     */
    List<RecordCountAndCreateTime> countTotalUser();

    /**
     * 用户总数 (日期列表)
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    List<RecordCountAndCreateTime> countUser(LocalDate start, LocalDate end, Boolean isGroupBy);

    /**
     * 总报名次数
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    List<RecordCountAndCreateTime> countApply(LocalDate start, LocalDate end, Boolean isGroupBy);

    /**
     * 有产生报名的活动数
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    List<RecordCountAndCreateTime> countApplyCampaign(LocalDate start, LocalDate end, Boolean isGroupBy);

    /**
     * 活动助手签到人数 （记录产生时间为准)
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    List<RecordCountAndCreateTime> countRegister(LocalDate start, LocalDate end, Boolean isGroupBy);

    /**
     * 活动助手签到次数 （记录产生时间为准）
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    List<RecordCountAndCreateTime> countRegisterTimes(LocalDate start, LocalDate end, Boolean isGroupBy);

    /**
     * 当前有效活动
     * @param start
     * @param end
     * @return
     */
    List<RecordCountAndCreateTime> countActiveCampaign(LocalDate start, LocalDate end);

    /**
     * 邀请人（有填邀请码）的分享次数
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    List<RecordCountAndCreateTime> countInviteShareTimes(LocalDate start, LocalDate end, Boolean isGroupBy);

    /**
     * 游客（无填邀请码）的分享次数
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    List<RecordCountAndCreateTime> countVisitorShareTimes(LocalDate start, LocalDate end, Boolean isGroupBy);

    /**
     * 室主的分享次数
     * @param start
     * @param end
     * @param isGroupBy
     * @return
     */
    List<RecordCountAndCreateTime> countABOShareTimes(LocalDate start, LocalDate end, Boolean isGroupBy);

    /**
     * 是否通过室主产生报名的人数
     * @param start
     * @param end
     * @param isGroupBy
     * @param isABOChannel
     * @return
     */
    List<RecordCountAndCreateTime> countChannelApply(LocalDate start, LocalDate end, Boolean isGroupBy, Boolean isABOChannel);
    
}
