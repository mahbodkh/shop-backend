package app.store.service.dto;

import app.store.service.util.TextHelper;

public class AddressDto {

    public AddressDto() {
    }

    private String name;
    private String fullAddress;
    private String zone;
    private String city;
    private String zipCode;
    private String province;
    private String longitude;
    private String latitude;

    public String getName() {
        return TextHelper.toStandardPersian(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullAddress() {
        return TextHelper.toStandardPersian(fullAddress);
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getZone() {
        return TextHelper.toStandardPersian(zone);
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCity() {
        return TextHelper.toStandardPersian(city);
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getProvince() {
        return TextHelper.toStandardPersian(province);
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
