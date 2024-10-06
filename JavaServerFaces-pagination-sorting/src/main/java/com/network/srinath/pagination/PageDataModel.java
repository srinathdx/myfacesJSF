package com.network.srinath.pagination;

import java.util.List;

import javax.faces.model.ListDataModel;

public class PageDataModel<T> extends ListDataModel<T> {
    private int pageSize;
    private int rowCount;
    private List<T> data;

    public PageDataModel(List<T> data, int rowCount, int pageSize) {
        super(data);
        this.data = data;
        this.rowCount = rowCount;
        this.pageSize = pageSize;
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<T> getData() {
        return data;
    }
}
