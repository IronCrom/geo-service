package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

public class TestMessageSender {
    GeoService geoService;
    LocalizationService localizationService;
    MessageSender messageSender;

    @BeforeEach
    public void init() {
        geoService = Mockito.mock(GeoServiceImpl.class);
        localizationService = Mockito.mock(LocalizationServiceImpl.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    public void testSendRu() {
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER,"172.123.12.19");
        Mockito.when(geoService.byIp("172.123.12.19")).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        //Mockito.when(geoService.byIp(String.valueOf(headers.get(IP_ADDRESS_HEADER)))).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        Assertions.assertEquals("Добро пожаловать",messageSender.send(headers));
        System.out.println();
    }

    @Test
    public void testSendEn(){
        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER,"96.123.12.19");
        //Mockito.when(geoService.byIp(String.valueOf(headers.get(IP_ADDRESS_HEADER)))).thenReturn(new Location("New York", Country.USA, null, 0));
        Mockito.when(geoService.byIp("96.123.12.19")).thenReturn(new Location("New York", Country.USA, null, 0));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        Assertions.assertEquals("Welcome",messageSender.send(headers));
        System.out.println();
    }

}