package org.example.buildingcompany.classes;

public class Address {
    private Integer id;
    private String address;
    private String postalCode;
    private City city;

    public Address() {}

    public Address(Integer id, String address, String postalCode, City city) {
        this.id = id;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city= " + city.getName() +
                '}';
    }
}
