package org.codeworks.web.toolsdashboardaatool.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * 分页工具类
 */
@Data
@NoArgsConstructor
public class ArrayPage {

    /**总的结果集*/
    private Object[] result = new Object[]{};

    /**起始查询页数*/
    private int pageIndex = 1;
    /**每页显示多少*/
    private int pageSize = 10;

    public ArrayPage(Object[] result) {
        this.result = result;
    }

    public ArrayPage(Object[] result, int pageIndex, int pageSize) {
        this(result);
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }


    /** 内部使用 */
    private int getPage() {
        return this.pageIndex<=0 ? 0 : this.pageIndex-1;
    }

    /**
     * 获取当前页号
     * @return
     */
    public int getPageNo() {
        return this.pageIndex<=0 ? 0 : this.pageIndex;
    }

    /**
     * 获取总页数
     * @return
     */
    public int getPageTotalNo() {
        return this.getTotalCount() % this.pageSize == 0 ? this.getTotalCount() / this.pageSize : this.getTotalCount() / this.pageSize + 1;
    }

    /**
     * 获取总条数
     * @return
     */
    public int getTotalCount() {
        return this.result.length;
    }

    /**
     * 判断是否是最后一页
     * @return
     */
    public boolean getIsLastPage() {
        int expectedSize = this.getPageNo() * this.pageSize;
        return expectedSize >= this.getTotalCount() && expectedSize - this.pageSize <= this.getTotalCount() ;
    }

    /**
     * 判断是否是第一页
     * @return
     */
    public boolean getIsFirstPage() {
        return this.getPageNo() == 1;
    }

    /**
     * 获取上一页起始索引
     * @return
     */
    public int getPreviousPageStart() {
        return this.getPage() <= this.pageSize ? 0 : this.getPage() - this.pageSize;
    }

    /**
     * 获取下一页起始索引
     */
    public int getNextPageStart() {
        return this.getPage() + this.pageSize;
    }

    /**
     * 获取最后一页起始索引
     */
    public int getLastPageStart() {
        return (this.getPageTotalNo() - 1) * this.pageSize;
    }

    /**
     * 获取实际需要显示的结果集
     * @return
     */
    public Object[] getDisplayResult() {

        int offsetStart = this.getPage() * this.pageSize;
        int offsetEnd = offsetStart + this.pageSize;

        return ArrayUtils.subarray(this.result, offsetStart, offsetEnd);
    }

    public static void main(String[] args) {
        Object[] strs = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
        ArrayPage page = new ArrayPage(strs, 1, 3);
        System.out.println(Arrays.asList(page.getDisplayResult()));
        System.out.println("总条数:" + page.getTotalCount());
        System.out.println("当前第:" + page.getPageNo() + "页");
        System.out.println("总页数:" + page.getPageTotalNo());
        System.out.println("是否为最后一页:" + page.getIsLastPage());
        System.out.println("是否为第一页:" + page.getIsFirstPage());

        System.out.println("下一页起始索引:" + page.getNextPageStart());
        System.out.println("上一页起始索引:" + page.getPreviousPageStart());
        System.out.println("最后一页起始索引:" + page.getLastPageStart());
    }

}
