package weather.api;

import java.io.IOException;
import java.time.LocalDate;

public interface WeatherApi {
    Iterable<DailyWeatherInfo> pastWeather(LocalDate fromDate, LocalDate toDate, String location) throws IOException;
}
