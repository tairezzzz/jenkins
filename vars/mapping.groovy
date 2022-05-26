String call(Object conditions, String params = '') {
    String data;

    conditions.each { key, value ->
        if (params in value) {
            data = key
        }
    }

    return data
}
