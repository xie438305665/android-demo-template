package com.android.library.net.entity.template;

import com.android.library.net.entity.BaseEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author y
 * @create 2019/4/8
 */
public class NewListEntity<T> extends BaseEntity<NewListEntity<T>> {

    private int pageNum;
    private int pageSize;
    private int size;
    private int startRow;
    private int endRow;
    private String total;
    private int pages;
    private int prePage;
    private int nextPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private int navigateFirstPage;
    private int navigateLastPage;
    private int firstPage;
    private int lastPage;
    private List<T> list;
    @SerializedName("navigatepageNums")
    private List<Integer> navigatePageNumList;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<Integer> getNavigatePageNumList() {
        return navigatePageNumList;
    }

    public void setNavigatePageNumList(List<Integer> navigatePageNumList) {
        this.navigatePageNumList = navigatePageNumList;
    }

    public <M> NewListEntity copy(List<M> t) {
        NewListEntity<M> newListEntity = new NewListEntity<>();
        newListEntity.setList(t);
        newListEntity.setPageNum(getPageNum());
        newListEntity.setPageSize(getPageSize());
        newListEntity.setSize(getSize());
        newListEntity.setStartRow(getStartRow());
        newListEntity.setEndRow(getEndRow());
        newListEntity.setTotal(getTotal());
        newListEntity.setPages(getPages());
        newListEntity.setPrePage(getPrePage());
        newListEntity.setNextPage(getNextPage());
        newListEntity.setFirstPage(isFirstPage());
        newListEntity.setLastPage(isLastPage());
        newListEntity.setHasPreviousPage(isHasPreviousPage());
        newListEntity.setHasNextPage(isHasNextPage());
        newListEntity.setNavigatePages(getNavigatePages());
        newListEntity.setNavigateFirstPage(getNavigateFirstPage());
        newListEntity.setNavigateLastPage(getNavigateLastPage());
        newListEntity.setFirstPage(getFirstPage());
        newListEntity.setLastPage(getLastPage());
        newListEntity.setNavigatePageNumList(getNavigatePageNumList());
        return newListEntity;
    }
}
