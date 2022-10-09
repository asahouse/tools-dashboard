package org.codeworks.web.tools.dashboard.myspace.web;

import io.swagger.annotations.*;
import org.codeworks.web.tools.dashboard.myspace.model.dto.ExportDataDto;
import org.codeworks.web.tools.dashboard.myspace.model.dto.GalleryDataDto;
import org.codeworks.web.tools.dashboard.myspace.model.dto.MusicDataDto;
import org.codeworks.web.tools.dashboard.myspace.model.dto.TemplateDataDto;
import org.codeworks.web.tools.dashboard.myspace.service.MyspaceDataService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * A+画册数据接口
 */
@RestController
@RequestMapping("data")
public class DataController {

    private final MyspaceDataService myspaceDataService;

    public DataController(MyspaceDataService myspaceDataService) {
        this.myspaceDataService = myspaceDataService;
    }

    /**
     * 获取A+画册首页汇总数据
     * @param start
     * @param end
     * @return creatorCountWithQuantity / creatorCount / topCreator / galleryCount / avgPage
     */
    @ApiOperation("获取A+画册首页汇总数据")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "start", value = "起始时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "end", value = "结束时间 yyyy-MM-dd", dataType = "String", paramType = "query")
    })
    @GetMapping("organise")
    public Response organise(@RequestParam String start,@RequestParam String end){
        GalleryDataDto dto = new GalleryDataDto();
        dto.setCreatorCountWithQuantity(myspaceDataService.galleryCreatorCountWithQuantity(start,end,5));
        dto.setCreatorCount(myspaceDataService.galleryCreatorCount(start,end));
        dto.setTopCreator(myspaceDataService.galleryTopCreator(start,end));
        dto.setGalleryCount(myspaceDataService.galleryCount(start,end));
        dto.setAvgPage(myspaceDataService.galleryPageCount(start,end));
        return Response.ok(dto);
    }

    /**
     * 获取创建了画册的ADA
     * @param start
     * @param end
     * @return
     */
    @ApiOperation("获取创建了画册的ADA")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "start", value = "起始时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "end", value = "结束时间 yyyy-MM-dd", dataType = "String", paramType = "query")
    })
    @GetMapping("creator/ada")
    public List<String> creatorAda(@RequestParam String start,@RequestParam String end){
        return myspaceDataService.galleryCreatorAda(start,end);
    }

    /**
     * 获取A+画册导出汇总数据
     * @param start
     * @param end
     * @return creatorCountWithQuantity / creatorCount / topCreator / galleryCount / avgPage
     *          musicData -> musicCode / useCount
     *          templateData -> templateCode /useCount
     */
    @ApiOperation("获取A+画册导出汇总数据")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "start", value = "起始时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "end", value = "结束时间 yyyy-MM-dd", dataType = "String", paramType = "query")
    })
    @GetMapping("export/data")
    public Response exportData(@RequestParam String start,@RequestParam String end){
        ExportDataDto dto = new ExportDataDto();
        dto.setCreatorCountWithQuantity(myspaceDataService.galleryCreatorCountWithQuantity(start,end,5));
        dto.setCreatorCount(myspaceDataService.galleryCreatorCount(start,end));
        dto.setTopCreator(myspaceDataService.galleryTopCreator(start,end));
        dto.setGalleryCount(myspaceDataService.galleryCount(start,end));
        dto.setAvgPage(myspaceDataService.galleryPageCount(start,end));
        dto.setMusicData(myspaceDataService.musicData(start,end));
        dto.setTemplateData(myspaceDataService.templateData(start,end));
        return Response.ok(dto);
    }

    /**
     * 获取模板数据列表
     * @param start
     * @param end
     * @param page
     * @param size
     * @return templateCode / galleryCount / creatorCount / likeCount
     */
    @ApiOperation("获取模板数据列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "start", value = "起始时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "end", value = "结束时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "当前页码", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页行数", dataType = "int", paramType = "query")
    })
    @GetMapping("template")
    public Response templateListData(@RequestParam String start, @RequestParam String end,
                                     @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size){
        Page<TemplateDataDto> l = myspaceDataService.templateData(start,end,page,size);
        return Response.ok(l.getContent()).add("total",l.getTotalElements());
    }

    /**
     * 获取音乐数据列表
     * @param start
     * @param end
     * @return musicCode / useCount
     */
    @ApiOperation("获取音乐数据列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "start", value = "起始时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "end", value = "结束时间 yyyy-MM-dd", dataType = "String", paramType = "query")
    })
    @GetMapping("music")
    public Response musicListData(@RequestParam String start,@RequestParam String end){
        List<MusicDataDto> l = myspaceDataService.musicData(start,end);
        return Response.ok(l);
    }

    /**
     * 获取画册基础数据列表
     * @param start
     * @param end
     * @param page
     * @param size
     * @return date / creatorCount
     */
    @ApiOperation("获取画册基础数据列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "start", value = "起始时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "end", value = "结束时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "当前页码", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页行数", dataType = "int", paramType = "query")
    })
    @GetMapping("organise/list")
    public Response organiseDataList(@RequestParam String start, @RequestParam String end,
                                     @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size){
        Page<GalleryDataDto> l = myspaceDataService.organiseList(start,end,page,size);
        return Response.ok(l.getContent()).add("total",l.getTotalElements());
    }

    /**
     * 获取画册分析数据列表
     * @param start
     * @param end
     * @param page
     * @param size
     * @return date / likeCount / galleryCount
     */
    @ApiOperation("获取画册分析数据列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "start", value = "起始时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "end", value = "结束时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "当前页码", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页行数", dataType = "int", paramType = "query")
    })
    @GetMapping("gallery/list")
    public Response galleryDataList(@RequestParam String start, @RequestParam String end,
                                    @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size){
        Page<GalleryDataDto> l = myspaceDataService.galleryDataList(start,end,page,size);
        return Response.ok(l.getContent()).add("total",l.getTotalElements());
    }

}
