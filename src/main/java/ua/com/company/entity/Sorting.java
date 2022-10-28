package ua.com.company.entity;

import ua.com.company.DBConstants;

public class Sorting {
    private String sortingField = "p.title";
    private String sortingType = "ASC";
    private int starRecord = 0;
    private int pageSize = 6;

    public Sorting() {
    }

    public String getSortingField() {
        return sortingField;
    }

    public void setSortingField(String sortingField) {
        this.sortingField = sortingField;
    }

    public String getSortingType() {
        return sortingType;
    }

    public void setSortingType(String sortingType) {
        this.sortingType = sortingType;
    }

    public int getStarRecord() {
        return starRecord;
    }

    public void setStarRecord(int starRecord) {
        this.starRecord = starRecord;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}