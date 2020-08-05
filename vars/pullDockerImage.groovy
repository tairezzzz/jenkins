def call(params) {
    def remote = params.get('remote');
    def credentialsId = params.get('credentialsId');
    def image = params.get('image');

    withCredentials([usernamePassword(
            credentialsId: credentialsId,
            usernameVariable: 'USERNAME',
            passwordVariable: 'PASSWORD'
    )]) {
        sshCommand remote: remote, command: "docker login -u $USERNAME -p $PASSWORD"
        sshCommand remote: remote, command: "docker pull $image"
        sshCommand remote: remote, command: "docker logout"
    }
}
