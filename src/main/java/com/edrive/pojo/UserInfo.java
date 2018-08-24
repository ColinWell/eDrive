package com.edrive.pojo;

/**
 * Created by Cxy on 2018/4/11.
 */
public class UserInfo {
    private int userId;
    private String nickName;
    private String roomId;
    private String roomName;
    private Double roomSize;
    private String puDateBegin;
    private String birthday;
    private String signal;
    private double propertyFee;
    private double utilitiesFee;

    public UserInfo() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Double getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(Double roomSize) {
        this.roomSize = roomSize;
    }

    public String getPuDateBegin() {
        return puDateBegin;
    }

    public void setPuDateBegin(String puDateBegin) {
        this.puDateBegin = puDateBegin;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public double getPropertyFee() {
        return propertyFee;
    }

    public void setPropertyFee(double propertyFee) {
        this.propertyFee = propertyFee;
    }

    public double getUtilitiesFee() {
        return utilitiesFee;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setUtilitiesFee(double utilitiesFee) {
        this.utilitiesFee = utilitiesFee;
    }
}
