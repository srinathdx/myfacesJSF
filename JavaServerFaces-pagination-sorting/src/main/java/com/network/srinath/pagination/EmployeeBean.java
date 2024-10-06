package com.network.srinath.pagination;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class EmployeeBean<Employee> {
	private List<Employee> employees;
	private int currentPage = 1;
	private int pageSize = 4;
	private int totalEmployees;
	private String sortField = "id";
    private String sortOrder = "ASC";

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalEmployees(int totalEmployees) {
		this.totalEmployees = totalEmployees;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees() throws SQLException {
		StopWatch stopWatch = new StopWatch();
		int offset = (currentPage - 1) * pageSize;
		List<com.network.srinath.pagination.Employee> results = new EmployeeDAO().getEmployees(offset, pageSize, sortField, sortOrder);
		System.out.println("Execution took in seconds: " + (stopWatch.getElapsedTime()));
		return (List<Employee>) results;
	}

	public String nextPage() {
		if (currentPage < getTotalPages()) {
			currentPage++;
		}
		 return String.valueOf(currentPage);
	}

	public String previousPage() {
		if (currentPage > 1) {
			currentPage--;
		}
		return String.valueOf(currentPage);
	}

	public String goToPage(int page) {
		System.out.println("Inside get Employee for page " + page);
		 currentPage = page;
		 return String.valueOf(currentPage);
	}
	
	 public void sort(String field) {
	        if (field.equals(sortField)) {
	            sortOrder = sortOrder.equals("ASC") ? "DESC" : "ASC";
	        } else {
	            sortField = field;
	            sortOrder = "ASC";
	        }
	    }
	    

	public int getTotalEmployees() throws SQLException {
		if (totalEmployees == 0) {
			totalEmployees = new EmployeeDAO().getTotalEmployees();
		}
		return totalEmployees;
	}

	public int getTotalPages() {
		try {
			return (int) Math.ceil((double) getTotalEmployees() / pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentPage;
	}

	public List<Integer> getTotalPagesList() {
		List<Integer> pages = new ArrayList<>();
		for (int i = 1; i <= getTotalPages(); i++) {
			pages.add(i);
		}
		return pages;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	// Getters and Setters
}