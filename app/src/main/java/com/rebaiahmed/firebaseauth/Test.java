package com.rebaiahmed.firebaseauth;

/**
 * Created by Rebai Ahmed on 18/04/2018.
 */

public class Test {
    private String userId;
    private int valueQ1, valueQ2, valueQ3, valueQ4, valueQ5;

    public Test(String userId, int valueQ1, int valueQ2, int valueQ3, int valueQ4, int valueQ5) {
        this.userId = userId;
        this.valueQ1 = valueQ1;
        this.valueQ2 = valueQ2;
        this.valueQ3 = valueQ3;
        this.valueQ4 = valueQ4;
        this.valueQ5 = valueQ5;
    }

    public Test() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getValueQ1() {
        return valueQ1;
    }

    public void setValueQ1(int valueQ1) {
        this.valueQ1 = valueQ1;
    }

    public int getValueQ2() {
        return valueQ2;
    }

    public void setValueQ2(int valueQ2) {
        this.valueQ2 = valueQ2;
    }

    public int getValueQ3() {
        return valueQ3;
    }

    public void setValueQ3(int valueQ3) {
        this.valueQ3 = valueQ3;
    }

    public int getValueQ4() {
        return valueQ4;
    }

    public void setValueQ4(int valueQ4) {
        this.valueQ4 = valueQ4;
    }

    public int getValueQ5() {
        return valueQ5;
    }

    public void setValueQ5(int valueQ5) {
        this.valueQ5 = valueQ5;
    }
}
