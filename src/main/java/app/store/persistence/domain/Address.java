package app.store.persistence.domain;


import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {
    private static final long serialVersionUID = 1L;


    public Address() {
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCity() {
        return city;
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
        return province;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(name, address.name) &&
                Objects.equals(fullAddress, address.fullAddress) &&
                Objects.equals(zone, address.zone) &&
                Objects.equals(city, address.city) &&
                Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(province, address.province) &&
                Objects.equals(longitude, address.longitude) &&
                Objects.equals(latitude, address.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fullAddress, zone, city, zipCode, province, longitude, latitude);
    }

    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                ", fullAddress='" + fullAddress + '\'' +
                ", zone='" + zone + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", province='" + province + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
