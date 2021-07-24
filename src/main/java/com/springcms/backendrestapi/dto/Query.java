package com.springcms.backendrestapi.dto;

public class Query {
	
	private String query;
	private String columnName;
	public Query() {
	}
	public Query(String query, String columnName) {
		this.query = query;
		this.columnName = columnName;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	@Override
	public String toString() {
		return "Query [query=" + query + ", columnName=" + columnName + "]";
	}
	
	
	
}
