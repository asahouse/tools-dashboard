--group by TO_DAYS(c.createTime);

-- 活动创建人 --
select count(DISTINCT createUserId) from aatool.campaign c
where c.createTime BETWEEN '2017-10-01' and '2017-10-31';

-- 邀请人
select count(DISTINCT s.userId) from aatool.share s 
where s.userType=2 and LENGTH(s.inviteCode) > 1 and s.createTime BETWEEN '2017' and '2017-10-31';

-- 活动参与者 (分享给其他人，但没有报名)
select count(DISTINCT s.userId) from aatool.share  s
where s.userType = 3 and LENGTH(s.inviteCode) = 1 and s.inviteCode = 0 and s.createTime BETWEEN '2017-10-01' and '2017-10-31';

-- 活动当月发布总数
select count(*) from aatool.campaign c 
where c.`status` != 1 and 
c.createTime BETWEEN '2017-10-01' and '2017-10-31';

-- 关联移动工作室的当月活动数
select count(*) from aatool.campaign c
where c.`status` = 0 and c.ada is not null and c.createTime BETWEEN '2017-10-01'and '2017-10-31';

-- 活动发布总数(仅有截止日期)
select count(*) from aatool.campaign c 
where c.`status` != 1 and 
c.createTime <= '2017-10-31';

-- 用户总数 --
select count(*) from aatool.user u
where u.createTime BETWEEN '2017-10-01' and '2017-10-31';

-- 总报名次数
select count(*) from aatool.apply a
where a.`status` = 0 and a.createTime BETWEEN '2017-10-01' and '2017-10-31';

-- 有产生报名的活动数
select count(distinct c.id) from aatool.campaign c
join aatool.apply a
on c.id = a.campaignId
where c.`status` = 0 
and a.createTime BETWEEN '2017-10-01' and '2017-10-31';

-- 活动助手签到人数 （记录产生时间为准）
select count(DISTINCT r.userId) from aatool.campaign c
join aatool.register r on c.id = r.campaignId
where c.`status` = 0 
and r.createTime BETWEEN '2017-10-01' and '2017-10-31';

-- 活动助手签到次数 （记录产生时间为准）
select count(*) from aatool.campaign c
join aatool.register r on c.id = r.campaignId
where c.`status` = 0 
and r.createTime BETWEEN '2017-10-01' and '2017-10-31';

-- 当期有效活动
select count(*) from aatool.campaign c 
where c.`status` = 0 and (c.startTime >= '2017-10-01' and c.endTime <= '2017-10-31');

-- 关联移动工作室的活动总数
select count(*) from aatool.campaign c
where c.`status` = 0 and c.ada is not null and (c.startTime >= '2017-10-01' and c.endTime <= '2017-10-31');

-- 邀请人（有填邀请码）的分享次数
select count(*) from aatool.`share` s 
where s.userType=2 and LENGTH(s.inviteCode) > 1 and s.createTime BETWEEN '2017-10-01' and '2017-10-31';

-- 游客（无填邀请码）的分享次数
select count(s.id) from aatool.share s
where s.userType = 3 and LENGTH(s.inviteCode) = 1 and s.inviteCode = 0 
and s.createTime BETWEEN '2017-10-01' and '2017-10-31';

-- 室主的分享次数
select count(id) from aatool.`share` s 
where s.userType=1 and s.inviteCode = -1 and s.createTime BETWEEN '2017-10-01' and '2017-10-31';

-- 通过非室主产生报名的人数
select COUNT(DISTINCT u.id) from aatool.`user` u
join aatool.apply a ON u.id = a.userId
join aatool.`share` s ON s.id = a.shareId 
where s.handlerShareId != -1 and u.ada is not null and u.ada != '' and a.createTime BETWEEN '2017-10-01' and '2017-10-31';

-- 通过室主产生报名的人数
select COUNT(DISTINCT u.id) from aatool.`user` u
join aatool.apply a ON u.id = a.userId
join aatool.`share` s ON s.id = a.shareId 
where s.handlerShareId = -1 and u.ada is not null and u.ada != '' and a.createTime BETWEEN '2017-10-01' and '2017-10-31';




