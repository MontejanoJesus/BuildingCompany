package org.example.buildingcompany;

import org.example.buildingcompany.classes.City;
import org.example.buildingcompany.service.impl.CityServiceImpl;
import org.example.buildingcompany.service.interfaces.ICityService;

import java.sql.SQLException;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws SQLException, InterruptedException {
        ICityService cityService = new CityServiceImpl();
        List<City> cities = cityService.getAll();
        City city = cityService.getById(1l);


    }
}
