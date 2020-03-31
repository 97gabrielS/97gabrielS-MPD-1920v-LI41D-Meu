package weather.api;

import weather.utils.HttpRequest;

public class WeatherWebApiTestHttp extends WeatherWebApiTest {
    public WeatherWebApiTestHttp() {
        super(new HttpRequest());
    }
}
