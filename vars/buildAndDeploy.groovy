def call(params) {
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

    tags.each { tag ->
        sh "docker rmi ${dockerRegistry}:${tag}"
    }

    return DOCKER_IMAGE;
}
