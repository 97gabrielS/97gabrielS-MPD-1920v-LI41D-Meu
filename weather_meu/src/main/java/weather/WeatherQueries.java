package weather;

import weather.api.DailyWeatherInfo;
import weather.api.WeatherApi;

import java.io.IOException;
import java.time.LocalDate;
import java.util.function.Predicate;

import static weather.queries.QueriesEager.filter;
import static weather.queries.QueriesEager.map;


/**
 * Class with methods for several weather queries
 */
public class WeatherQueries {

    private WeatherApi weatherApi;


    public WeatherQueries(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public Iterable<Integer> getMaxTemperatures(LocalDate fromDate, LocalDate toDate, String location) throws IOException {
        Iterable<DailyWeatherInfo> weatherInfos = weatherApi.pastWeather(fromDate, toDate, location);

        return map(weatherInfos, (dailyWeatherInfo -> dailyWeatherInfo.getTempMaxC()));
    }


    public Iterable<DailyWeatherInfo> getDailyWeatherInfoWithMaxTemperaturesAbove(LocalDate fromDate, LocalDate toDate, String location, int thresholdTemp) throws IOException {
        return queryDailyWeatherInfo(fromDate, toDate, location,
                (dailyWeatherInfo -> dailyWeatherInfo.getTempMaxC() >= thresholdTemp));

    }

    public Iterable<DailyWeatherInfo> getDailyWeatherInfoWithMaxTemperaturesBetween(LocalDate fromDate, LocalDate toDate, String location, int min, int max) throws IOException {
        return queryDailyWeatherInfo(fromDate, toDate, location,
                (dailyWeatherInfo -> dailyWeatherInfo.getTempMaxC() >= min && dailyWeatherInfo.getTempMaxC() <= max));
    }

    public Iterable<DailyWeatherInfo> getDailyWeatherInfoWithMinTemperaturesAbove(LocalDate fromDate, LocalDate toDate, String location, int thresholdTemp) throws IOException {
        return queryDailyWeatherInfo(fromDate, toDate, location,
                (dailyWeatherInfo -> dailyWeatherInfo.getTempMinC() >= thresholdTemp));
    }

    public Iterable<DailyWeatherInfo> getDailyWeatherInfoWithMinTemperaturesBetween(LocalDate fromDate, LocalDate toDate, String location, int min, int max) throws IOException {
        return queryDailyWeatherInfo(fromDate, toDate, location,
                dailyWeatherInfo -> dailyWeatherInfo.getTempMinC() >= min && dailyWeatherInfo.getTempMinC() <= max);
    }



    private Iterable<DailyWeatherInfo> queryDailyWeatherInfo(LocalDate fromDate, LocalDate toDate, String location, Predicate<DailyWeatherInfo> pred) throws IOException {
        Iterable<DailyWeatherInfo> weatherInfos = weatherApi.pastWeather(fromDate, toDate, location);

        return filter(weatherInfos, pred);
    }
}
