package dao;

import java.util.HashMap;

public class Filters {
    String keyword, sort, location, startPrice, endPrice, categoryId;
    HashMap<String, String> filtersMap = new HashMap<>();
    int limit = 2, page = 1;

    public Filters(String keyword, String categoryId, String startPrice, String endPrice, String sort,
            String location, int page) {
        this.keyword = keyword;
        this.categoryId = categoryId;
        this.startPrice = startPrice;
        this.endPrice = endPrice;
        this.sort = sort;
        this.location = location;
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public int getPage() {
        return page;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getSort() {
        return sort;
    }

    public String getLocation() {
        return location;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public String getEndPrice() {
        return endPrice;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public HashMap<String, String> getFiltersMap() {
        return filtersMap;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public void setEndPrice(String endPrice) {
        this.endPrice = endPrice;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setFiltersMap(HashMap<String, String> filters) {
        this.filtersMap = filters;
    }

    @Override
    public String toString() {
        return "Filters [keyword=" + keyword + ", sort=" + sort + ", location=" + location + ", startPrice="
                + startPrice + ", endPrice=" + endPrice + ", categoryId=" + categoryId + ", filtersMap=" + filtersMap
                + ", limit=" + limit + ", page=" + page + "]";
    }

}
