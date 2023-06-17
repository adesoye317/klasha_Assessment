package com.klasha.assessment.model.response.get_details;

import com.klasha.assessment.model.response.get_population.PopulationCountsItem;

import java.util.List;

public class GetDetailsResponse {


    private String capital;
    private Location location;
    private String currency;
    private String ISO2;
    private String ISO3;
    private List<PopulationCountsItem> population;

    public List<PopulationCountsItem> getPopulation() {
        return population;
    }

    public void setPopulation(List<PopulationCountsItem> population) {
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getISO2() {
        return ISO2;
    }

    public void setISO2(String ISO2) {
        this.ISO2 = ISO2;
    }

    public String getISO3() {
        return ISO3;
    }

    public void setISO3(String ISO3) {
        this.ISO3 = ISO3;
    }

    @Override
    public String toString() {
        return "GetDetailsResponse{" +
                "population=" + population +
                ", capital='" + capital + '\'' +
                ", location=" + location +
                ", currency='" + currency + '\'' +
                ", ISO2='" + ISO2 + '\'' +
                ", ISO3='" + ISO3 + '\'' +
                '}';
    }
}
