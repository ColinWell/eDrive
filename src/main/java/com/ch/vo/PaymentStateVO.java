package com.ch.vo;

/**
 * Created by Cxy on 2018/4/17.
 * 0:未缴费
 * 1:已缴费
 * 2:无需缴费
 */
public class PaymentStateVO {

    private int propertyState;
    private int parkingState;
    private int utilitiesState;

    public PaymentStateVO() {
    }

    public int getPropertyState() {
        return propertyState;
    }

    public void setPropertyState(int propertyState) {
        this.propertyState = propertyState;
    }

    public int getParkingState() {
        return parkingState;
    }

    public void setParkingState(int parkingState) {
        this.parkingState = parkingState;
    }

    public int getUtilitiesState() {
        return utilitiesState;
    }

    public void setUtilitiesState(int utilitiesState) {
        this.utilitiesState = utilitiesState;
    }
}
