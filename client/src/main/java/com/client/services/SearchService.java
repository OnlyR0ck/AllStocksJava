package com.client.services;

public class SearchService {
    private static String searchTerm;
    private static SearchService instance;

    public static SearchService getInstance() {
        if(instance == null)
        {
            instance = new SearchService();
        }
        return instance;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
