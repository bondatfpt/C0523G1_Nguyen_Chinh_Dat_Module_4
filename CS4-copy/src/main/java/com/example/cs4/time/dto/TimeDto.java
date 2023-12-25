package com.example.cs4.time.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class TimeDto {
    private int id;
    @Min(value = 4, message = "Hiện tại chỉ mở cửa từ 4 giờ - 23h!")
    @Max(value = 22, message = "Hiện tại chỉ mở cửa từ 4 giờ - 23h!")
    private int time;
    private boolean isDelete;

    public TimeDto() {
    }

    public TimeDto(int id, int time, boolean isDelete) {
        this.id = id;
        this.time = time;
        this.isDelete = isDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
