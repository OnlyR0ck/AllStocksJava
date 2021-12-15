package com.server.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class StocksHistoricalModels implements Serializable {
    public String symbol;
    @SerializedName("historical")
    public List<StocksHistoricalModel> models;
}

