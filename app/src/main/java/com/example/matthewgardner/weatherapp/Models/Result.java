package com.example.matthewgardner.weatherapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by matthewgardner on 1/5/18.
 */

public class Result {
    @SerializedName("channel")
    @Expose
    private Channel channel;

    public Result(Channel channel) {
        super();
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
