package org.codeworks.web.toolsdashboardwebfacade.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.codeworks.web.toolsdashboardwebfacade.dto.Response;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.PageStatisticsVisitRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.statistics.GetDataCollectionRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.statistics.GetDataRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.statistics.GetDataRequestRequisite;
import org.codeworks.web.toolsdashboardwebfacade.service.ABDashboardService;
import org.codeworks.web.toolsdashboardwebfacade.service.BaiduService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public abstract class ABWebController extends ABDashboardService {

    /**
     * 生成Excel文件
     * @param response
     * @param fileName
     * @param reportName
     * @param headWord
     * @param dMap
     * @throws IOException
     */
    public void generate(HttpServletResponse response,
                          String fileName,
                          String reportName,
                          String[] headWord,
                          Map<String, Map<String, List<Object[]>>> dMap) throws IOException {

        if (!Optional.ofNullable(dMap).isPresent() && dMap.isEmpty()) {
            log.error("Not Data!!");
            return;
        }

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=Report_"+fileName+".xls");

        HSSFWorkbook workbook = new HSSFWorkbook();

        //报告字体
        Font reportFont = workbook.createFont();
        reportFont.setBold(true);
        reportFont.setFontName("微软雅黑");
        reportFont.setFontHeightInPoints((short) 20);

        // 创建标题字体
        Font headFont = workbook.createFont();
        headFont.setBold(true);
        headFont.setFontName("微软雅黑");
        headFont.setFontHeightInPoints((short) 13);

        // 创建合并单元格字体
        Font mergeFont = workbook.createFont();
        mergeFont.setBold(true);
        mergeFont.setFontName("微软雅黑");
        mergeFont.setFontHeightInPoints((short) 13);

        // 创建报告样式
        CellStyle reportStyle = workbook.createCellStyle();
        reportStyle.setFont(reportFont);
        reportStyle.setAlignment(HorizontalAlignment.LEFT); // 居左
        reportStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中

        // 创建标题样式
        CellStyle headStyle = workbook.createCellStyle();
        headStyle.setFont(headFont);
        headStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
        headStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        headStyle.setBorderTop(BorderStyle.THIN);
        headStyle.setBorderLeft(BorderStyle.THIN);
        headStyle.setBorderRight(BorderStyle.THIN);
        headStyle.setBorderBottom(BorderStyle.THIN);
        headStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headStyle.setAlignment(HorizontalAlignment.CENTER); // 居中

        // 创建合并单元格样式
        CellStyle mergeStyle = workbook.createCellStyle();
        mergeStyle.setFont(mergeFont);
        mergeStyle.setFillBackgroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        mergeStyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中
        mergeStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中

        // 创建每行字体
        Font eachFont = workbook.createFont();
        eachFont.setFontName("微软雅黑");
        eachFont.setFontHeightInPoints((short) 10);

        // 创建每行样式
        CellStyle eachStyle = workbook.createCellStyle();
        eachStyle.setFont(eachFont);
        eachStyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中

        // 创建百分比样式
        HSSFCellStyle rateCellStyle = workbook.createCellStyle();
        rateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
        rateCellStyle.setFont(eachFont);

        HSSFCellStyle numberCellStyle = workbook.createCellStyle();
        numberCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
        numberCellStyle.setFont(eachFont);

        //循环sheet
        dMap.entrySet().stream().forEach((Map.Entry<String, Map<String, List<Object[]>>> single) -> {

            HSSFSheet sheet = workbook.createSheet(single.getKey());
            sheet.setColumnWidth(0, 5500);
            sheet.setColumnWidth(1, 1500);
            sheet.setColumnWidth(2, 10000);
            sheet.setColumnWidth(3, 3000);

            //报告标题大字
            HSSFRow bigWordRow = sheet.createRow(0);
            Cell bigWordCell = bigWordRow.createCell(0);
            bigWordCell.setCellStyle(reportStyle);
            bigWordCell.setCellValue(reportName);

            CellRangeAddress reportCellRange = new CellRangeAddress(
                    0, 0, 0, 5);//包含到"报告文字单元格"的下标可合并
            sheet.addMergedRegion(reportCellRange);//合并

            //创建标题行
            HSSFRow headRow = sheet.createRow(2);
            IntStream.range(0, headWord.length).forEachOrdered(i -> {
                sheet.autoSizeColumn(i);
                Cell cell = headRow.createCell(i);
                cell.setCellStyle(headStyle);
                cell.setCellValue(headWord[i]);
            });

            int skipBeforeRowIndex = 3; //跳过上面的标题等行数

            //数据项
            Map<String, List<Object[]>> d = single.getValue();
            List<String> categories = Arrays.asList(d.keySet().toArray(new String[]{}));//统计类型


            //每个统计分类
            IntStream.range(0, categories.size()).parallel().forEachOrdered(i -> {

                String singleCategory = categories.get(i);
                List<Object[]> collectRowData = d.get(singleCategory);

                Integer totalEndRow = i == 0 ? collectRowData.size() :
                        IntStream.range(0, i+1).parallel().map(s -> d.get(categories.get(s)).size()).sum();
                Integer beforeEndRow = totalEndRow - collectRowData.size();

                //行信息,按数据项多少计算
                int startRow = skipBeforeRowIndex + beforeEndRow;
                int endRow = skipBeforeRowIndex + beforeEndRow + collectRowData.size() - 1;

                HSSFRow mergeRow = sheet.createRow(startRow);//创建合并单元格首行
                Cell categoryCell = mergeRow.createCell(0);//创建统计分类单元格,默认一行中第一列
                categoryCell.setCellValue(singleCategory);
                categoryCell.setCellStyle(mergeStyle);

                CellRangeAddress categoryCellRange = new CellRangeAddress(
                        startRow, endRow, 0, 0);//包含到"统计分类单元格"的下标可合并

                sheet.addMergedRegion(categoryCellRange);//合并

                IntStream.range(0, collectRowData.size()).parallel().forEachOrdered(k -> {
                    Object[] eachRowData = collectRowData.get(k);//一行数据

                    HSSFRow eachRow = k==0 ? mergeRow : sheet.createRow(startRow + k);//创建每行,首行使用合并单元格

                    //一行中每列
                    IntStream.range(0, eachRowData.length).parallel().forEachOrdered(j -> {
                        Cell cell = eachRow.createCell(j+1);//加1起避让合并单元格
                        cell.setCellStyle(eachStyle);

                        Object val = eachRowData[j];//单元格值

                        //若是"序号"列,则直接把下标设值
                        if (val instanceof String) {
                            if (val.toString().equals("##"))
                                cell.setCellValue(k+1);//替换为序号
                            else
                                cell.setCellValue(val.toString());
                        }else if (val instanceof Double) {
                            cell.setCellValue(Double.valueOf(val.toString()));
                            cell.setCellStyle(rateCellStyle);
                        }else if (val instanceof Long) {
                            cell.setCellValue(Long.valueOf(val.toString()));
                            cell.setCellStyle(numberCellStyle);
                        }else if (val instanceof Integer) {
                            cell.setCellValue(Integer.valueOf(val.toString()));
                            cell.setCellStyle(numberCellStyle);
                        }
                    });
                });


            });
        });



        try (OutputStream fOut = response.getOutputStream()) {
            workbook.write(fOut);
        }
    }

    public Integer parseDateToInteger(String date){
        return Integer.valueOf(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(DateTimeFormatter.ofPattern("yyyMMdd")).toString());
    }

    public Map<String, Object> getVisitPageSumMap(Integer siteId, String start, String end, String searchWord, String name,
                                              BaiduService service){
        PageStatisticsVisitRequest req = new PageStatisticsVisitRequest().builder()
                .start_date(parseDateToInteger(start))
                .end_date(parseDateToInteger(end))
                .site_id(siteId)
                .searchWord(searchWord)
                .build();

        //sum开头是供应服务规定的
        Response resp = service.visit(req, 1, 100);
        Map<String, Object> result = new HashMap<>();
        result.put("uv", Optional.ofNullable(resp.get("sumUv")).orElse(0));
        result.put("pv", Optional.ofNullable(resp.get("sumPv")).orElse(0));
        result.put("name", Optional.ofNullable(name).orElse(searchWord));
        return result;
    }

    public List<Object[]> filterBaiduPvOrUv(List<Object[]> source, boolean isGetPV){
        String filterValue = isGetPV ? "-PV" : "-UV";
        return source.parallelStream().filter(v -> {
            Optional choose = Arrays.asList(v).parallelStream().filter(
                    single -> single.toString().indexOf(filterValue)!=-1).findAny();
            return choose.isPresent();
        }).collect(Collectors.toList());
    }

    public List<Object[]> getBaiduVisitPvAndUvBetween(Integer siteId, String searchWord, String showWord,
                                               String start, String end, BaiduService service){
        //日期期间每日数据
        LocalDate startLocal = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endLocal = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Set<Integer> durationDates = this.parseLocalDateToStringSet(startLocal, endLocal);

        return durationDates.parallelStream().flatMap(
                date -> this.getBaiduPvAndUv(siteId, "visit/toppage/a", searchWord, showWord,
                        date, date, service).parallelStream()
                        .map(objects -> new Object[]{date, objects[1], objects[2]}).parallel()
        ).collect(Collectors.toList());
    }

    public List<Object[]> getBaiduVisitPvAndUv(Integer siteId, String searchWord, String showWord,
                                               String start, String end, BaiduService service){
        //期间总值
        return this.getBaiduPvAndUv(siteId, "visit/toppage/a", searchWord, showWord,
                parseDateToInteger(start), parseDateToInteger(end), service);
    }

    public List<Object[]> getBaiduSitePvAndUvBetween(Integer siteId, String showWord,
                                                      String start, String end, BaiduService service){
        //日期期间每日数据
        LocalDate startLocal = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endLocal = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Set<Integer> durationDates = this.parseLocalDateToStringSet(startLocal, endLocal);

        return durationDates.parallelStream().flatMap(
                date -> this.getBaiduPvAndUv(siteId, "overview/getTimeTrendRpt", null, showWord,
                        date, date, service).parallelStream()
                        .map(objects -> new Object[]{date, objects[1], objects[2]}).parallel()
        ).collect(Collectors.toList());
    }

    public List<Object[]> getBaiduSitePvAndUv(Integer siteId, String showWord,
                                              String start, String end, BaiduService service){
        return this.getBaiduPvAndUv(siteId, "overview/getTimeTrendRpt", null, showWord,
                parseDateToInteger(start), parseDateToInteger(end), service);
    }

    public List<Object[]> getBaiduPvAndUv(Integer siteId, String method, String searchWord, String showWord,
                                          Integer start, Integer end, BaiduService service){
        GetDataRequestRequisite baiduRequisite = new GetDataRequestRequisite();
        baiduRequisite.setSite_id(siteId);
        baiduRequisite.setStart_date(start);
        baiduRequisite.setEnd_date(end);

        GetDataRequest baiduSingelReqPV = new GetDataRequest();
        baiduSingelReqPV.setMethod(method);
        baiduSingelReqPV.setMetrics("pv_count");
        Optional.ofNullable(searchWord).ifPresent(t -> baiduSingelReqPV.setSearchWord(t));

        GetDataRequest biaduSingleReqUV = new GetDataRequest();
        biaduSingleReqUV.setMethod(method);
        biaduSingleReqUV.setMetrics("visitor_count");
        Optional.ofNullable(searchWord).ifPresent(t -> biaduSingleReqUV.setSearchWord(t));

        GetDataCollectionRequest fiveBaiduReqCollect = new GetDataCollectionRequest(
                baiduRequisite,baiduSingelReqPV,biaduSingleReqUV);

        JSONArray fiveBaiduData = JSONArray.parseArray(JSONObject.toJSONString(
                service.achieveBaiduDatas(fiveBaiduReqCollect)));

        return IntStream.range(0, fiveBaiduData.size()).mapToObj(i -> {
            String field = fiveBaiduData.getJSONObject(i).getJSONArray("fields").getString(1);
            Long sum = IntStream.range(0, fiveBaiduData.getJSONObject(i).getJSONArray("items").getJSONArray(1).size())
                    .parallel().mapToLong(j -> {
                        String val = fiveBaiduData.getJSONObject(i).getJSONArray("items").getJSONArray(1).getJSONArray(j).getString(0);
                        return NumberUtils.isDigits(val) ? Long.valueOf(val) : 0;
                    }).sum();
            String name = field.equals("pv_count") ? "-PV" : "-UV";
            return new Object[]{"##",showWord + name, sum};
        }).collect(Collectors.toList());
    }
}
