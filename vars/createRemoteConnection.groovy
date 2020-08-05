def call(env, params) {
    def REMOTE = [:];

    params.each { value ->
        def condition = value.get('condition');
        if (env in condition || env == condition) {
            withCredentials([usernamePassword(
                    credentialsId: value.get('credentialsId'),
                    usernameVariable: 'USERNAME',
                    passwordVariable: 'PASSWORD'
            )]) {
                REMOTE.name = value.get('host');
                REMOTE.host = value.get('host');
                REMOTE.user = USERNAME;
                REMOTE.password = PASSWORD;
                REMOTE.allowAnyHosts = true;
            }
        }
    }

    return REMOTE;
}
