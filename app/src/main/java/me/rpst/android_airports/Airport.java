package me.rpst.android_airports;

/**
 * Created by robin on 23/10/2017.
 */

public class Airport {

    private String icao;
    private String name;
    private Double longitude;
    private Double latitude;
    private Double elevation;
    private String isoCountry;
    private String municipality;

    public Airport() {
    }

    public Airport withIcao(String icao) {
        this.icao = icao;
        return this;
    }

    public Airport withName(String name) {
        this.name = name;
        return this;
    }

    public Airport withLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Airport withLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Airport withElevation(Double elevation) {
        this.elevation = elevation;
        return this;
    }

    public Airport withIsoCountry(String isoCountry) {
        this.isoCountry = isoCountry;
        return this;
    }

    public Airport withMunicipality(String municipality) {
        this.municipality = municipality;
        return this;
    }

    public String getIcao() {
        return icao;
    }

    public String getName() {
        return name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getElevation() {
        return elevation;
    }

    public String getIsoCountry() {
        return isoCountry;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getFirstLetterOfIcao() {
        return String.valueOf(icao.charAt(0)).toUpperCase();
    }
}
