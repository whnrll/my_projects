package com.itheima.model;

public class AlarmMessage {
    private int scopeId;
    private String name;
    private String id0;
    private String id1;
    private String alarmMessage;
    private long startTime;
    String ruleName;

    public int getScopeId() {
        return scopeId;
    }

    public void setScopeId(int scopeId) {
        this.scopeId = scopeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId0() {
        return id0;
    }

    public void setId0(String id0) {
        this.id0 = id0;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getAlarmMessage() {
        return alarmMessage;
    }

    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage = alarmMessage;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }


    @Override
    public String toString() {
        return "AlarmMessage{" +
                "scopeId=" + scopeId +
                ", name='" + name + '\'' +
                ", id0='" + id0 + '\'' +
                ", id1='" + id1 + '\'' +
                ", alarmMessage='" + alarmMessage + '\'' +
                ", startTime=" + startTime +
                ", ruleName='" + ruleName + '\'' +
                '}';
    }
}