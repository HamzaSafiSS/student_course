package com.hamza.studentcourse.dto;

import java.util.List;
// THis class does two things
//1. List of actual data of the current page.
// 2. Pagination contect. page number, size, totals, is-last

public class PagedResponse<T> {

    private List<T> content;
    //T indicates this class can work with any type of object.
    // We can Use what we need instead of T but T is very common.
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public PagedResponse(List<T> content, int pageNumber, int pageSize,
                         long totalElements, int totalPages, boolean last) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public List<T> getContent() { return content; }
    public int getPageNumber() { return pageNumber; }
    public int getPageSize() { return pageSize; }
    public long getTotalElements() { return totalElements; }
    public int getTotalPages() { return totalPages; }
    public boolean isLast() { return last; }
}