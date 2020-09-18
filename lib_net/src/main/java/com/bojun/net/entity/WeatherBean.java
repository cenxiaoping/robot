package com.bojun.net.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/3 17:51
 */
public class WeatherBean {
    private long code;
    private ResultBean result;

    public class ResultBean {
        private List<HeWeather5Bean> HeWeather5;

        public List<HeWeather5Bean> getHeWeather5() {
            if (HeWeather5 == null) {
                return new ArrayList<>();
            }
            return HeWeather5;
        }

        public void setHeWeather5(List<HeWeather5Bean> heWeather5) {
            HeWeather5 = heWeather5;
        }
    }

    public class HeWeather5Bean {
        private List<Daily_forecastBean> daily_forecast;

        public List<Daily_forecastBean> getDaily_forecast() {
            if (daily_forecast == null) {
                return new ArrayList<>();
            }
            return daily_forecast;
        }

        public void setDaily_forecast(List<Daily_forecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }
    }

    public class Daily_forecastBean {
        private TmpBean tmp;

        public TmpBean getTmp() {
            return tmp;
        }

        public void setTmp(TmpBean tmp) {
            this.tmp = tmp;
        }
    }

    public class TmpBean {
        private String max;
        private String min;

        public String getMax() {
            return max == null ? "" : max;
        }

        public void setMax(String max) {
            this.max = max == null ? "" : max;
        }

        public String getMin() {
            return min == null ? "" : min;
        }

        public void setMin(String min) {
            this.min = min == null ? "" : min;
        }
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }
}