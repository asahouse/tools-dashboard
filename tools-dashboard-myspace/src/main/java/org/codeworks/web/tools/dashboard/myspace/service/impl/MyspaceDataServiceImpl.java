package org.codeworks.web.tools.dashboard.myspace.service.impl;

import org.codeworks.web.tools.dashboard.myspace.exception.CustomException;
import org.codeworks.web.tools.dashboard.myspace.jooq.Tables;
import org.codeworks.web.tools.dashboard.myspace.model.dto.AboDto;
import org.codeworks.web.tools.dashboard.myspace.model.dto.GalleryDataDto;
import org.codeworks.web.tools.dashboard.myspace.model.dto.MusicDataDto;
import org.codeworks.web.tools.dashboard.myspace.model.dto.TemplateDataDto;
import org.codeworks.web.tools.dashboard.myspace.service.MyspaceDataService;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.tools.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * A+画删数据接口
 */
@Service
public class MyspaceDataServiceImpl implements MyspaceDataService {

    private final DSLContext dsl;

    public MyspaceDataServiceImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    private Condition parseDateRange(String start,String end,Condition condition){
        if(condition==null){
            return null;
        }
        if(!StringUtils.isEmpty(start)){
            condition = condition.and(Tables.GALLERY.CREATETIME.ge(Timestamp.valueOf(LocalDateTime.of(LocalDate.parse(start,DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MIN))));
        }
        if(!StringUtils.isEmpty(end)){
            condition = condition.and(Tables.GALLERY.CREATETIME.le(Timestamp.valueOf(LocalDateTime.of(LocalDate.parse(end,DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MAX))));
        }
        return condition;
    }

    @Override
    public Integer galleryCount(String start, String end) throws RuntimeException {
        Condition condition = DSL.trueCondition();
        condition = parseDateRange(start,end,condition);
        condition = condition.and(Tables.GALLERY.STATUS.eq(1));
        Integer rst = dsl.selectCount().from(Tables.GALLERY).where(condition).fetchOne().value1();
        return rst;
    }

    @Override
    public Integer galleryCreatorCount(String start,String end) throws RuntimeException {
        Condition condition = DSL.trueCondition();
        condition = parseDateRange(start,end,condition);
        condition = condition.and(Tables.GALLERY.STATUS.eq(1));
        Integer rst = dsl.selectCount().from(dsl.select(Tables.GALLERY.ADA).from(Tables.GALLERY).where(condition).groupBy(Tables.GALLERY.ADA)).fetchAny().value1();
        return rst;
    }

    @Override
    public List<String> galleryCreatorAda(String start,String end) throws RuntimeException{
        Condition condition = DSL.trueCondition();
        condition = parseDateRange(start,end,condition);
        condition = condition.and(Tables.GALLERY.STATUS.eq(1));
        List<String> adas = dsl.select(Tables.GALLERY.ADA).from(Tables.GALLERY).where(condition).groupBy(Tables.GALLERY.ADA).fetch(Tables.GALLERY.ADA);
        return adas;
    }

    @Override
    public Integer galleryCreatorCountWithQuantity(String start,String end, Integer quantity) throws RuntimeException {
        Condition condition = DSL.trueCondition();
        condition = parseDateRange(start,end,condition);
        condition = condition.and(Tables.GALLERY.STATUS.eq(1));
        Integer rst = dsl.selectCount().from(dsl.select(Tables.GALLERY.ADA).from(Tables.GALLERY).where(condition).groupBy(Tables.GALLERY.ADA).having(Tables.GALLERY.ADA.count().ge(quantity))).fetchAny().value1();
        return rst;
    }

    @Override
    public AboDto galleryTopCreator(String start,String end) throws RuntimeException {
        Condition condition = DSL.trueCondition();
        condition = parseDateRange(start,end,condition);
        condition = condition.and(Tables.GALLERY.STATUS.eq(1));
        AboDto dto = dsl.select(Tables.GALLERY.ADA.as("ada"),Tables.GALLERY.OPENID.as("openId"),Tables.USER.NICKNAME.as("nickname"),Tables.GALLERY.ID.count().as("count"))
                .from(Tables.GALLERY)
                .leftJoin(Tables.USER).on(Tables.USER.OPENID.eq(Tables.GALLERY.OPENID)).where(condition).groupBy(Tables.GALLERY.ADA,Tables.GALLERY.OPENID,Tables.USER.NICKNAME).orderBy(Tables.GALLERY.ID.count().desc()).limit(1).fetchAnyInto(AboDto.class);
        return dto;
    }

    @Override
    public BigDecimal galleryPageCount(String start, String end)throws RuntimeException{
        Condition condition = DSL.trueCondition();
        condition = parseDateRange(start,end,condition);
        condition = condition.and(Tables.GALLERY.STATUS.eq(1));
        Integer gc = dsl.selectCount().from(Tables.GALLERY).where(condition).fetchOne().value1();
        Integer gdc = dsl.selectCount().from(Tables.GALLERYDETAIL).join(Tables.GALLERY).on(Tables.GALLERYDETAIL.GALLERYID.eq(Tables.GALLERY.ID)).where(condition).fetchOne().value1();
        return BigDecimal.valueOf(gdc).divide(BigDecimal.valueOf(gc),2,BigDecimal.ROUND_HALF_UP);
    }

    private Select temStep(Timestamp st,Timestamp et)throws RuntimeException{
        return dsl.select(Tables.GALLERYDETAIL.CHOOSETEMPLATE.as("templateCode"),Tables.GALLERYDETAIL.ID.count().as("galleryCount"))
                .from(Tables.GALLERYDETAIL).join(Tables.GALLERY).on(Tables.GALLERYDETAIL.GALLERYID.eq(Tables.GALLERY.ID))
                .and(Tables.GALLERY.STATUS.eq(1))
                .and(Tables.GALLERYDETAIL.STATUS.eq(1))
                .and(Tables.GALLERY.CREATETIME.between(st,et))
                .and(Tables.GALLERYDETAIL.CHOOSETEMPLATE.likeRegex("^[0-9\\.]+$"))
                .groupBy(Tables.GALLERYDETAIL.CHOOSETEMPLATE);
    }

    private Select creatorStep(Timestamp st,Timestamp et)throws RuntimeException{
        return dsl.select(DSL.field("t.chooseTemplate", DSL.getDataType(String.class)).as("templateCode"),DSL.field("t.openid", DSL.getDataType(String.class)).count().as("creatorCount"))
                .from(
                        dsl.select(Tables.GALLERYDETAIL.CHOOSETEMPLATE,Tables.GALLERY.OPENID)
                                .from(Tables.GALLERYDETAIL).join(Tables.GALLERY).on(Tables.GALLERYDETAIL.GALLERYID.eq(Tables.GALLERY.ID))
                                .and(Tables.GALLERY.STATUS.eq(1))
                                .and(Tables.GALLERYDETAIL.STATUS.eq(1))
                                .and(Tables.GALLERY.CREATETIME.between(st,et))
                                .and(Tables.GALLERYDETAIL.CHOOSETEMPLATE.likeRegex("^[0-9\\.]+$"))
                                .groupBy(Tables.GALLERYDETAIL.CHOOSETEMPLATE,Tables.GALLERY.OPENID).asTable("t")
                ).groupBy(DSL.field("t.chooseTemplate", DSL.getDataType(String.class)));
    }

    private Select detailLikeStep(Timestamp st, Timestamp et)throws RuntimeException{
        return dsl.select(Tables.GALLERYDETAIL.CHOOSETEMPLATE.as("templateCode"),Tables.CLICKLIKE.ID.count().as("likeCount"))
                .from(Tables.GALLERYDETAIL).join(Tables.CLICKLIKE)
                .on(Tables.GALLERYDETAIL.ID.eq(Tables.CLICKLIKE.DETAILID))
                .and(Tables.GALLERYDETAIL.STATUS.eq(1))
                .and(Tables.CLICKLIKE.CREATETIME.between(st,et))
                .and(Tables.GALLERYDETAIL.CHOOSETEMPLATE.likeRegex("^[0-9\\.]+$"))
                .join(Tables.GALLERY)
                .on(Tables.GALLERYDETAIL.GALLERYID.eq(Tables.GALLERY.ID))
                .and(Tables.GALLERY.STATUS.eq(1))
                .groupBy(Tables.GALLERYDETAIL.CHOOSETEMPLATE);
    }

    private Select likeStep(Timestamp st,Timestamp et)throws RuntimeException{
        return dsl.select(dateFormat(Tables.CLICKLIKE.CREATETIME,"%Y%m%d").as("t"),Tables.CLICKLIKE.ID.count().as("c"))
                .from(Tables.CLICKLIKE)
                .where(Tables.CLICKLIKE.CREATETIME.between(st,et))
                .groupBy(dateFormat(Tables.CLICKLIKE.CREATETIME,"%Y%m%d"));
    }

    private Select galleryCreatorStep(Timestamp st,Timestamp et)throws RuntimeException{
        return dsl.select(dateFormat(Tables.GALLERY.CREATETIME,"%Y%m%d").as("t"),Tables.GALLERY.ID.count().as("c"))
                .from(Tables.GALLERY)
                .where(Tables.GALLERY.CREATETIME.between(st,et)).and(Tables.GALLERY.STATUS.eq(1))
                .groupBy(dateFormat(Tables.GALLERY.CREATETIME,"%Y%m%d"));
    }

    private Select createGalleryStep(Timestamp st,Timestamp et)throws RuntimeException{
        return dsl.select(DSL.field("t.t",DSL.getDataType(String.class)).as("t"),DSL.field("t.openId",DSL.getDataType(String.class)).count().as("c")).from(
                dsl.select(dateFormat(Tables.GALLERY.CREATETIME,"%Y%m%d").as("t"),Tables.GALLERY.OPENID.as("openId"))
                        .from(Tables.GALLERY)
                        .where(Tables.GALLERY.CREATETIME.between(st,et)).and(Tables.GALLERY.STATUS.eq(1))
                        .groupBy(dateFormat(Tables.GALLERY.CREATETIME,"%Y%m%d"),Tables.GALLERY.OPENID).asTable("t")
        ).groupBy(DSL.field("t.t",DSL.getDataType(String.class)));
    }

    public Field<String> dateFormat(Field<Timestamp> field, String format) {
        return DSL.field("date_format({0}, {1})", SQLDataType.VARCHAR,
                field, DSL.inline(format));
    }

    @Override
    public Page<TemplateDataDto> templateData(String start,String end, Integer page,Integer size)throws RuntimeException{
        if(StringUtils.isEmpty(start) || StringUtils.isEmpty(end)){
            throw new CustomException(301);
        }
        page = page==null?0:page>0?page-1:page;
        Timestamp st = Timestamp.valueOf(LocalDateTime.of(LocalDate.parse(start,DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MIN));
        Timestamp et = Timestamp.valueOf(LocalDateTime.of(LocalDate.parse(end,DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MAX));
        SelectSeekStep1<Record4<Integer, Integer, Integer, Integer>, Integer> step;
        step = dsl.select(
                    DSL.field("tem.templateCode", DSL.getDataType(String.class)).cast(Integer.class).as("templateCode"),
                    DSL.field("tem.galleryCount", DSL.getDataType(Integer.class)).as("galleryCount"),
                    DSL.field("creator.creatorCount", DSL.getDataType(Integer.class)).as("creatorCount"),
                    DSL.field("lik.likeCount", DSL.getDataType(Integer.class)).as("likeCount")
                )
                .from(temStep(st,et).asTable("tem")
        ).join(
                creatorStep(st,et).asTable("creator")
        ).on(DSL.field("tem.templateCode", DSL.getDataType(String.class)).eq(DSL.field("creator.templateCode",DSL.getDataType(String.class))))
        .join(
                detailLikeStep(st,et).asTable("lik")
        ).on(DSL.field("tem.templateCode", DSL.getDataType(String.class)).eq(DSL.field("lik.templateCode", DSL.getDataType(String.class))))
        .orderBy(DSL.cast(DSL.field("tem.templateCode",DSL.getDataType(String.class)),DSL.getDataType(Integer.class)).asc());
        Pageable pageable = null;
        List<TemplateDataDto> l;
        if(page!=null && size!=null){
            pageable = new PageRequest(page,size);
            l = dsl.select().from(step).limit(pageable.getPageSize())
                    .offset(pageable.getOffset()).fetchInto(TemplateDataDto.class);
        }else{
            l = step.fetchInto(TemplateDataDto.class);
        }
        Integer count = dsl.selectCount().from(step).fetchOne().value1();
        return new PageImpl<>(l,pageable,count);
    }

    @Override
    public Page<GalleryDataDto> organiseList(String start, String end, Integer page, Integer size) throws RuntimeException {
        if(StringUtils.isEmpty(start) || StringUtils.isEmpty(end)){
            throw new CustomException(301);
        }
        page = page==null?0:page>0?page-1:page;
        Timestamp st = Timestamp.valueOf(LocalDateTime.of(LocalDate.parse(start,DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MIN));
        Timestamp et = Timestamp.valueOf(LocalDateTime.of(LocalDate.parse(end,DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MAX));
        SelectSeekStep1<Record2<Integer, Integer>, Integer> step;
        step = dsl.select(
                DSL.field("cr.t", DSL.getDataType(String.class)).cast(Integer.class).as("date"),
                DSL.field("cr.c", DSL.getDataType(Integer.class)).as("creatorCount")
        )
                .from(galleryCreatorStep(st,et).asTable("cr"))
                .orderBy(DSL.cast(DSL.field("cr.t",DSL.getDataType(String.class)),DSL.getDataType(Integer.class)).asc());
        Pageable pageable = null;
        List<GalleryDataDto> l;
        if(page!=null && size!=null){
            pageable = new PageRequest(page,size);
            l = dsl.select().from(step).limit(pageable.getPageSize())
                    .offset(pageable.getOffset()).fetchInto(GalleryDataDto.class);
        }else{
            l = step.fetchInto(GalleryDataDto.class);
        }
        Integer count = dsl.selectCount().from(step).fetchOne().value1();
        return new PageImpl<>(l,pageable,count);
    }

    @Override
    public Page<GalleryDataDto> galleryDataList(String start, String end, Integer page, Integer size) throws RuntimeException {
        if(StringUtils.isEmpty(start) || StringUtils.isEmpty(end)){
            throw new CustomException(301);
        }
        page = page==null?0:page>0?page-1:page;
        Timestamp st = Timestamp.valueOf(LocalDateTime.of(LocalDate.parse(start,DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MIN));
        Timestamp et = Timestamp.valueOf(LocalDateTime.of(LocalDate.parse(end,DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MAX));
        SelectSeekStep1<Record3<Integer, Integer,Integer>, Integer> step;
        step = dsl.select(
                DSL.field("c.t", DSL.getDataType(String.class)).cast(Integer.class).as("date"),
                DSL.field("c.c", DSL.getDataType(Integer.class)).as("likeCount"),
                DSL.field("g.c", DSL.getDataType(Integer.class)).as("galleryCount")
        )
                .from(likeStep(st,et).asTable("c"))
                .join(createGalleryStep(st,et).asTable("g"))
                .on(DSL.field("c.t", DSL.getDataType(String.class)).eq(DSL.field("g.t",DSL.getDataType(String.class))))
                .orderBy(DSL.cast(DSL.field("c.t",DSL.getDataType(String.class)),DSL.getDataType(Integer.class)).asc());
        Pageable pageable = null;
        List<GalleryDataDto> l;
        if(page!=null && size!=null){
            pageable = new PageRequest(page,size);
            l = dsl.select().from(step).limit(pageable.getPageSize())
                    .offset(pageable.getOffset()).fetchInto(GalleryDataDto.class);
        }else{
            l = step.fetchInto(GalleryDataDto.class);
        }
        Integer count = dsl.selectCount().from(step).fetchOne().value1();
        return new PageImpl<>(l,pageable,count);
    }

    @Override
    public List<MusicDataDto> musicData(String start,String end)throws RuntimeException{
        if(StringUtils.isEmpty(start) || StringUtils.isEmpty(end)){
            throw new CustomException(301);
        }
        Timestamp st = Timestamp.valueOf(LocalDateTime.of(LocalDate.parse(start,DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MIN));
        Timestamp et = Timestamp.valueOf(LocalDateTime.of(LocalDate.parse(end,DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MAX));
        List<MusicDataDto> l = dsl.select(
                Tables.GALLERY.CHOOSEMUSIC.cast(Integer.class).as("musicCode"),
                Tables.GALLERY.ID.count().as("useCount")
        )
                .from(Tables.GALLERY)
                .where(Tables.GALLERY.STATUS.eq(1))
                .and(Tables.GALLERY.CREATETIME.between(st,et))
                .and(Tables.GALLERY.CHOOSEMUSIC.likeRegex("^[0-9\\.]+$"))
                .groupBy(Tables.GALLERY.CHOOSEMUSIC)
                .orderBy(Tables.GALLERY.CHOOSEMUSIC.cast(Integer.class).asc())
                .fetchInto(MusicDataDto.class);
        return l;
    }

    @Override
    public List<TemplateDataDto> templateData(String start,String end)throws RuntimeException{
        if(StringUtils.isEmpty(start) || StringUtils.isEmpty(end)){
            throw new CustomException(301);
        }
        Timestamp st = Timestamp.valueOf(LocalDateTime.of(LocalDate.parse(start,DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MIN));
        Timestamp et = Timestamp.valueOf(LocalDateTime.of(LocalDate.parse(end,DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MAX));
        List<TemplateDataDto> l = dsl.select(
                DSL.field("t.templateCode", DSL.getDataType(String.class)).cast(Integer.class).as("templateCode"),
                DSL.field("t.galleryCount", DSL.getDataType(Integer.class)).as("useCount")
        )
                .from(temStep(st,et).asTable("t"))
                .orderBy(DSL.cast(DSL.field("t.templateCode",DSL.getDataType(String.class)),DSL.getDataType(Integer.class)).asc())
                .fetchInto(TemplateDataDto.class);
        return l;
    }

}
