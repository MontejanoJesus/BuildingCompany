package org.example.buildingcompany.classes;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "country")
public class Country {

    private Long id;

    private String name;

    public Country(){}
    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
