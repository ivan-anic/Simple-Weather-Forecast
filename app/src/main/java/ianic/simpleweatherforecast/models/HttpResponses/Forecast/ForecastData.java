package ianic.simpleweatherforecast.models.HttpResponses.Forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ForecastData {

    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<List> list = new ArrayList<>();

    /**
     * No args constructor for use in serialization
     */
    public ForecastData() {
    }

    /**
     * @param message
     * @param cnt
     * @param cod
     * @param list
     * @param city
     */
    public ForecastData(City city, String cod, Double message, Integer cnt, java.util.List<List> list) {
        this.city = city;
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
    }

    /**
     * @return The city
     */
    public City getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * @return The cod
     */
    public String getCod() {
        return cod;
    }

    /**
     * @param cod The cod
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     * @return The message
     */
    public Double getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(Double message) {
        this.message = message;
    }

    /**
     * @return The cnt
     */
    public Integer getCnt() {
        return cnt;
    }

    /**
     * @param cnt The cnt
     */
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    /**
     * @return The list
     */
    public java.util.List<List> getList() {
        return list;
    }

    /**
     * @param list The list
     */
    public void setList(java.util.List<List> list) {
        this.list = list;
    }


    public class City {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("coord")
        @Expose
        private Coord coord;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("population")
        @Expose
        private Integer population;

        /**
         * No args constructor for use in serialization
         */
        public City() {
        }

        /**
         * @param coord
         * @param id
         * @param name
         * @param population
         * @param country
         */
        public City(Integer id, String name, Coord coord, String country, Integer population) {
            this.id = id;
            this.name = name;
            this.coord = coord;
            this.country = country;
            this.population = population;
        }

        /**
         * @return The id
         */
        public Integer getId() {
            return id;
        }

        /**
         * @param id The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         * @return The name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name The name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return The coord
         */
        public Coord getCoord() {
            return coord;
        }

        /**
         * @param coord The coord
         */
        public void setCoord(Coord coord) {
            this.coord = coord;
        }

        /**
         * @return The country
         */
        public String getCountry() {
            return country;
        }

        /**
         * @param country The country
         */
        public void setCountry(String country) {
            this.country = country;
        }

        /**
         * @return The population
         */
        public Integer getPopulation() {
            return population;
        }

        /**
         * @param population The population
         */
        public void setPopulation(Integer population) {
            this.population = population;
        }

    }

    public class Coord {

        @SerializedName("lon")
        @Expose
        private Double lon;
        @SerializedName("lat")
        @Expose
        private Double lat;

        /**
         * No args constructor for use in serialization
         */
        public Coord() {
        }

        /**
         * @param lon
         * @param lat
         */
        public Coord(Double lon, Double lat) {
            this.lon = lon;
            this.lat = lat;
        }

        /**
         * @return The lon
         */
        public Double getLon() {
            return lon;
        }

        /**
         * @param lon The lon
         */
        public void setLon(Double lon) {
            this.lon = lon;
        }

        /**
         * @return The lat
         */
        public Double getLat() {
            return lat;
        }

        /**
         * @param lat The lat
         */
        public void setLat(Double lat) {
            this.lat = lat;
        }

    }

    public class List {

        @SerializedName("dt")
        @Expose
        private Integer dt;
        @SerializedName("temp")
        @Expose
        private Temp temp;
        @SerializedName("pressure")
        @Expose
        private Double pressure;
        @SerializedName("humidity")
        @Expose
        private Integer humidity;
        @SerializedName("weather")
        @Expose
        private java.util.List<Weather> weather = new ArrayList<Weather>();
        @SerializedName("speed")
        @Expose
        private Double speed;
        @SerializedName("deg")
        @Expose
        private Integer deg;
        @SerializedName("clouds")
        @Expose
        private Integer clouds;

        /**
         * No args constructor for use in serialization
         */
        public List() {
        }

        /**
         * @param clouds
         * @param dt
         * @param humidity
         * @param pressure
         * @param speed
         * @param deg
         * @param weather
         * @param temp
         */
        public List(Integer dt, Temp temp, Double pressure, Integer humidity, java.util.List<Weather> weather, Double speed, Integer deg, Integer clouds) {
            this.dt = dt;
            this.temp = temp;
            this.pressure = pressure;
            this.humidity = humidity;
            this.weather = weather;
            this.speed = speed;
            this.deg = deg;
            this.clouds = clouds;
        }

        /**
         * @return The dt
         */
        public Integer getDt() {
            return dt;
        }

        /**
         * @param dt The dt
         */
        public void setDt(Integer dt) {
            this.dt = dt;
        }

        /**
         * @return The temp
         */
        public Temp getTemp() {
            return temp;
        }

        /**
         * @param temp The temp
         */
        public void setTemp(Temp temp) {
            this.temp = temp;
        }

        /**
         * @return The pressure
         */
        public Double getPressure() {
            return pressure;
        }

        /**
         * @param pressure The pressure
         */
        public void setPressure(Double pressure) {
            this.pressure = pressure;
        }

        /**
         * @return The humidity
         */
        public Integer getHumidity() {
            return humidity;
        }

        /**
         * @param humidity The humidity
         */
        public void setHumidity(Integer humidity) {
            this.humidity = humidity;
        }

        /**
         * @return The weather
         */
        public java.util.List<Weather> getWeather() {
            return weather;
        }

        /**
         * @param weather The weather
         */
        public void setWeather(java.util.List<Weather> weather) {
            this.weather = weather;
        }

        /**
         * @return The speed
         */
        public Double getSpeed() {
            return speed;
        }

        /**
         * @param speed The speed
         */
        public void setSpeed(Double speed) {
            this.speed = speed;
        }

        /**
         * @return The deg
         */
        public Integer getDeg() {
            return deg;
        }

        /**
         * @param deg The deg
         */
        public void setDeg(Integer deg) {
            this.deg = deg;
        }

        /**
         * @return The clouds
         */
        public Integer getClouds() {
            return clouds;
        }

        /**
         * @param clouds The clouds
         */
        public void setClouds(Integer clouds) {
            this.clouds = clouds;
        }

    }

    public class Temp {

        @SerializedName("day")
        @Expose
        private Double day;
        @SerializedName("min")
        @Expose
        private Double min;
        @SerializedName("max")
        @Expose
        private Double max;
        @SerializedName("night")
        @Expose
        private Double night;
        @SerializedName("eve")
        @Expose
        private Double eve;
        @SerializedName("morn")
        @Expose
        private Double morn;

        /**
         * No args constructor for use in serialization
         */
        public Temp() {
        }

        /**
         * @param min
         * @param eve
         * @param max
         * @param morn
         * @param night
         * @param day
         */
        public Temp(Double day, Double min, Double max, Double night, Double eve, Double morn) {
            this.day = day;
            this.min = min;
            this.max = max;
            this.night = night;
            this.eve = eve;
            this.morn = morn;
        }

        /**
         * @return The day
         */
        public Double getDay() {
            return day;
        }

        /**
         * @param day The day
         */
        public void setDay(Double day) {
            this.day = day;
        }

        /**
         * @return The min
         */
        public Double getMin() {
            return min;
        }

        /**
         * @param min The min
         */
        public void setMin(Double min) {
            this.min = min;
        }

        /**
         * @return The max
         */
        public Double getMax() {
            return max;
        }

        /**
         * @param max The max
         */
        public void setMax(Double max) {
            this.max = max;
        }

        /**
         * @return The night
         */
        public Double getNight() {
            return night;
        }

        /**
         * @param night The night
         */
        public void setNight(Double night) {
            this.night = night;
        }

        /**
         * @return The eve
         */
        public Double getEve() {
            return eve;
        }

        /**
         * @param eve The eve
         */
        public void setEve(Double eve) {
            this.eve = eve;
        }

        /**
         * @return The morn
         */
        public Double getMorn() {
            return morn;
        }

        /**
         * @param morn The morn
         */
        public void setMorn(Double morn) {
            this.morn = morn;
        }

    }

    public class Weather {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("main")
        @Expose
        private String main;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("icon")
        @Expose
        private String icon;

        /**
         * No args constructor for use in serialization
         */
        public Weather() {
        }

        /**
         * @param id
         * @param icon
         * @param description
         * @param main
         */
        public Weather(Integer id, String main, String description, String icon) {
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }

        /**
         * @return The id
         */
        public Integer getId() {
            return id;
        }

        /**
         * @param id The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         * @return The main
         */
        public String getMain() {
            return main;
        }

        /**
         * @param main The main
         */
        public void setMain(String main) {
            this.main = main;
        }

        /**
         * @return The description
         */
        public String getDescription() {
            return description;
        }

        /**
         * @param description The description
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * @return The icon
         */
        public String getIcon() {
            return icon;
        }

        /**
         * @param icon The icon
         */
        public void setIcon(String icon) {
            this.icon = icon;
        }

    }
}