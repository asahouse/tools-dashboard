package org.codeworks.web.tools.dashboard.myspace.service;

import org.codeworks.web.tools.dashboard.myspace.model.dto.AboDto;
import org.codeworks.web.tools.dashboard.myspace.model.dto.GalleryDataDto;
import org.codeworks.web.tools.dashboard.myspace.model.dto.MusicDataDto;
import org.codeworks.web.tools.dashboard.myspace.model.dto.TemplateDataDto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

/**
 * A+画删数据接口
 */
public interface MyspaceDataService {

    /**
     * 创建画册数
     * @param start
     * @param end
     * @return
     * @throws RuntimeException
     */
    Integer galleryCount(String start, String end)throws RuntimeException;

    /**
     * 获取创建画册总人数
     * @param start
     * @param end
     * @return
     * @throws RuntimeException
     */
    Integer galleryCreatorCount(String start,String end)throws RuntimeException;

    /**
     * 创建了画册的ADA号
     * @param start
     * @param end
     * @return
     * @throws RuntimeException
     */
    List<String> galleryCreatorAda(String start,String end) throws RuntimeException;

    /**
     * 按创建数量维度获取创建画册人数
     * @param start
     * @param end
     * @param quantity
     * @return
     * @throws RuntimeException
     */
    Integer galleryCreatorCountWithQuantity(String start,String end,Integer quantity)throws RuntimeException;

    /**
     * 创建画册最多的ADA
     * @param start
     * @param end
     * @return
     * @throws RuntimeException
     */
    AboDto galleryTopCreator(String start,String end)throws RuntimeException;

    /**
     * 获取画册平均页数
     * @param start
     * @param end
     * @return
     * @throws RuntimeException
     */
    BigDecimal galleryPageCount(String start, String end)throws RuntimeException;

    /**
     * 获取模板数据
     * @param start
     * @param end
     * @param page
     * @param size
     * @return
     * @throws RuntimeException
     */
    Page<TemplateDataDto> templateData(String start,String end, Integer page,Integer size)throws RuntimeException;

    /**
     * 获取汇总数据列表数据
     * @param start
     * @param end
     * @param page
     * @param size
     * @return
     * @throws RuntimeException
     */
    Page<GalleryDataDto> organiseList(String start,String end, Integer page,Integer size)throws RuntimeException;

    /**
     * 获取画册分析列表数据
     * @param start
     * @param end
     * @param page
     * @param size
     * @return
     * @throws RuntimeException
     */
    Page<GalleryDataDto> galleryDataList(String start,String end, Integer page,Integer size)throws RuntimeException;

    /**
     * 获取画册音乐数据
     * @param start
     * @param end
     * @return
     * @throws RuntimeException
     */
    List<MusicDataDto> musicData(String start, String end)throws RuntimeException;

    /**
     * 获取画册模板数据
     * @param start
     * @param end
     * @return
     * @throws RuntimeException
     */
    List<TemplateDataDto> templateData(String start, String end)throws RuntimeException;

}
