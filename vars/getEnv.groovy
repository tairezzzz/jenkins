String call(Object conditions, String paramsEnv = '') {
    String environment;

    if (paramsEnv) {
        return paramsEnv;
    }

    conditions.each { key, value ->
        if (GIT_BRANCH in value || GIT_BRANCH == value) {
            environment = key
        }
    }

    println "Current environment is: $environment"

    return environment
}
