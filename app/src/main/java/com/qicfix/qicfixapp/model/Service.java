package com.qicfix.qicfixapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by stevefoo on 4/22/16.
 */
public class Service {

    @SerializedName("creationDate")
    private Date creationDate;

    @SerializedName("startDate")
    private Date startDate;

    @SerializedName("endDate")
    private Date endDate;

    @SerializedName("cancelDate")
    private Date cancelDate;

    @SerializedName("cost")
    private double cost;

    @SerializedName("clientDescription")
    private String clientDescription;

    @SerializedName("towerDescription")
    private String towerDescription;

    @SerializedName("cityDestination")
    private String cityDestination;

    @SerializedName("cityPickup")
    private String cityPickup;

    @SerializedName("clientId")
    private Integer clientId;

    @SerializedName("id")
    private Integer id;

    @SerializedName("latitudePickup")
    private double latitudePickup;

    @SerializedName("longitudePickup")
    private double longitudePickup;

    @SerializedName("latitudeDestination")
    private double latitudeDestination;

    @SerializedName("longitudeDestination")
    private double longitudeDestination;

    @SerializedName("statePickup")
    private String statePickup;

    @SerializedName("stateDestination")
    private String stateDestination;

    @SerializedName("streetAddressPickup")
    private String streetAddressPickup;

    @SerializedName("streetAddressDestination")
    private String streetAddressDestination;

    @SerializedName("zipcodePickup")
    private String zipcodePickup;

    @SerializedName("zipcodeDestination")
    private String zipcodeDestination;

    public String getCityDestination() {
        return cityDestination;
    }

    public void setCityDestination(String cityDestination) {
        this.cityDestination = cityDestination;
    }

    public String getCityPickup() {
        return cityPickup;
    }

    public void setCityPickup(String cityPickup) {
        this.cityPickup = cityPickup;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getLatitudePickup() {
        return latitudePickup;
    }

    public void setLatitudePickup(double latitudePickup) {
        this.latitudePickup = latitudePickup;
    }

    public double getLongitudePickup() {
        return longitudePickup;
    }

    public void setLongitudePickup(double longitudePickup) {
        this.longitudePickup = longitudePickup;
    }

    public String getStateDestination() {
        return stateDestination;
    }

    public void setStateDestination(String stateDestination) {
        this.stateDestination = stateDestination;
    }

    public String getStatePickup() {
        return statePickup;
    }

    public void setStatePickup(String statePickup) {
        this.statePickup = statePickup;
    }

    public String getStreetAddressDestination() {
        return streetAddressDestination;
    }

    public void setStreetAddressDestination(String streetAddressDestination) {
        this.streetAddressDestination = streetAddressDestination;
    }

    public String getStreetAddressPickup() {
        return streetAddressPickup;
    }

    public void setStreetAddressPickup(String streetAddressPickup) {
        this.streetAddressPickup = streetAddressPickup;
    }

    public String getZipcodeDestination() {
        return zipcodeDestination;
    }

    public void setZipcodeDestination(String zipcodeDestination) {
        this.zipcodeDestination = zipcodeDestination;
    }

    public String getZipcodePickup() {
        return zipcodePickup;
    }

    public void setZipcodePickup(String zipcodePickup) {
        this.zipcodePickup = zipcodePickup;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getClientDescription() {
        return clientDescription;
    }

    public void setClientDescription(String clientDescription) {
        this.clientDescription = clientDescription;
    }

    public String getTowerDescription() {
        return towerDescription;
    }

    public void setTowerDescription(String towerDescription) {
        this.towerDescription = towerDescription;
    }

    public double getLatitudeDestination() {
        return latitudeDestination;
    }

    public void setLatitudeDestination(double latitudeDestination) {
        this.latitudeDestination = latitudeDestination;
    }

    public double getLongitudeDestination() {
        return longitudeDestination;
    }

    public void setLongitudeDestination(double longitudeDestination) {
        this.longitudeDestination = longitudeDestination;
    }
}