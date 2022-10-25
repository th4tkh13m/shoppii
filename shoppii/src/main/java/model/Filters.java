package model;

import java.util.Arrays;
import java.util.HashMap;

public class Filters {
    String keyword, sort, startPrice, endPrice;
    String[] locations, categoriesId;
    HashMap<String, String> filtersMap = new HashMap<>();
    int limit = 9, page = 1;

    public Filters(String keyword, String sort, String startPrice, String endPrice, String[] locations,
            String[] categoriesId, int page) {
        this.keyword = keyword;
        this.sort = sort;
        this.startPrice = startPrice;
        this.endPrice = endPrice;
        this.locations = locations;
        this.categoriesId = categoriesId;
        this.page = page;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }

    public void setCategoriesId(String[] categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String[] getLocations() {
        return locations;
    }

    public String[] getCategoriesId() {
        return categoriesId;
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

    public String getStartPrice() {
        return startPrice;
    }

    public String getEndPrice() {
        return endPrice;
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

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public void setEndPrice(String endPrice) {
        this.endPrice = endPrice;
    }

    public void setFiltersMap(HashMap<String, String> filters) {
        this.filtersMap = filters;
    }

    @Override
    public String toString() {
        return "Filters [keyword=" + keyword + ", sort=" + sort + ", startPrice=" + startPrice + ", endPrice="
                + endPrice + ", locations=" + Arrays.toString(locations) + ", categoriesId="
                + Arrays.toString(categoriesId) + ", filtersMap=" + filtersMap + ", limit=" + limit + ", page=" + page
                + "]";
    }

}
