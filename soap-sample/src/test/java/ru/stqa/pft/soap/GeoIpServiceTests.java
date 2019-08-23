package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("2a00:1fa0:46a6:6bab:2425:6d00:5d9:37f7");
    assertEquals(geoIP, "<GeoIP><Country>MY</Country><State>12</State></GeoIP>");
  }

  @Test
  public void testInvalidIp() {
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("2a00:1fa0:46a6:6bab:2425:6d00:5d9:xxxx");
    assertEquals(geoIP, "<GeoIP><Country>MY</Country><State>12</State></GeoIP>");
  }

}
