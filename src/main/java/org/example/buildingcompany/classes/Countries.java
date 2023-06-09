package org.example.buildingcompany.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "countries")
public class Countries {
    List<Country> countriesList = new ArrayList<>();
    @XmlElement(name = "country")
    public List<Country> getCountries() {
        return countriesList;
    }

    public void setCountries(List<Country> countries) {
        this.countriesList = countries;
    }
}
