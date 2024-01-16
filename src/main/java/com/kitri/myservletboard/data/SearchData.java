package com.kitri.myservletboard.data;

public class SearchData {
    String period;
    int date; // period 를 int 로 변환

    public String getPeriod() {
        return period;
    }

    public int getDate(String period) {
        if (period.equals("1")) {
            return date =1;
        } else if (period.equals("7")) {
            return date =7;
        } else if (period.equals("30")) {
            return date =30;
        } else if (period.equals("180")) {
            return date =180;
        } else if (period.equals("365")) {
            return date =365;
        }
        return 0;
    }

    private String orderBy;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setDate(int date) {
        this.date = date;
    }
}

