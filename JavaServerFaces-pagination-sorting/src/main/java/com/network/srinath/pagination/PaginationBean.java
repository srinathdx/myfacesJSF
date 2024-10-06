package com.network.srinath.pagination;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import java.util.List;

@ManagedBean
@ViewScoped
public class PaginationBean {
    private DataModel<String> model;
    private int pageSize = 5;
    private int rowCount;
    private int currentPage = 1;
    private List<String> data;

    public DataModel<String> getModel() {
        int fromIndex = (currentPage - 1) * pageSize;
        int toIndex = Math.min(currentPage * pageSize, rowCount);
        model = new PageDataModel<>(data.subList(fromIndex, toIndex), rowCount, pageSize);
        return model;
    }

    public void setPage(int page) {
        this.currentPage = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getRowCount() {
        return rowCount;
    }

    // Initialization method to set data and row count
    public void init() {
        // Initialize data and rowCount here
    }
}
