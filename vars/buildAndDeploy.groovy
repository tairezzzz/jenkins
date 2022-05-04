def call(Object[] args) {

    args.each { params ->
        def dockerRegistry = params.get('dockerRegistry');
        def registryCredentials = params.get('registryCredentials');
        def tags = params.get('tags');
        def buildArgs = params.get('buildArgs');

        def DOCKER_IMAGE = docker.build("${dockerRegistry}:${tags[0]}", buildArgs)

        docker.withRegistry('', registryCredentials) {
            tags.each { tag ->
                DOCKER_IMAGE.push(tag);
            }
        }
    }

    args.each { params ->
        def dockerRegistry = params.get('dockerRegistry');
        def tags = params.get('tags');

        tags.each { tag ->
            sh "docker rmi ${dockerRegistry}:${tag}"
        }
    }
}
