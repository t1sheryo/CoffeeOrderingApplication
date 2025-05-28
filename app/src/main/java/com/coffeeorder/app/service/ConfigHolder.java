package com.coffeeorder.app.service;

// Класс для хранения распаршенных данных из конфига

// тут обработать когда параметров в строке нет

public class ConfigHolder {
    /*@Service
    public class ConfigHolder {

        private AppConfig appConfig;

        @Autowired
        private ConfigurationRepository repository;

        @PostConstruct
        public void init() {
            reload();
        }

        public void reload() {
            ConfigurationEntity entity = repository.findById("configKey").orElse(null);
            if (entity != null) {
                appConfig = parseConfig(entity.getValue());
            } else {
                appConfig = new AppConfig(5, 10, 2);
            }
        }

        private AppConfig parseConfig(String value) {
            if (value == null) return new AppConfig(5, 10, 2);
            String[] parts = value.split(",");
            int n = parts.length > 0 ? parseIntSafe(parts[0], 5) : 5;
            int x = parts.length > 1 ? parseIntSafe(parts[1], 10) : 10;
            int m = parts.length > 2 ? parseIntSafe(parts[2], 2) : 2;
            return new AppConfig(n, x, m);
        }

        private int parseIntSafe(String s, int defaultValue) {
            try {
                return Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }

        public AppConfig getAppConfig() {
            return appConfig;
        }
    }*/

}
