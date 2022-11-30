package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

public class TestGeoServiceImpl {

    @Test
     public void byIp() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Assertions.assertEquals(Country.RUSSIA, geoService.byIp("172.102.18.38").getCountry());
        Assertions.assertEquals(Country.USA, geoService.byIp("96.102.18.38").getCountry());
    }

    @Test
    void byCoordinates() {
    }
}

