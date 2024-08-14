def call(params) {
    def remote = params.get('remote');
    def credentialsId = params.get('credentialsId');
    def dockerRegistry = params.get('dockerRegistry');
    def user = params.get('registryCredentialUser');
    def pass = params.get('registryCredentialPass');
    def image = params.get('image');

    if (credentialsId) {
        withCredentials([usernamePassword(
                credentialsId: credentialsId,
                usernameVariable: 'USERNAME',
                passwordVariable: 'PASSWORD'
        )]) {
            sshCommand remote: remote, command: "docker login -u $USERNAME -p $PASSWORD"
            sshCommand remote: remote, command: "docker pull $image"
            sshCommand remote: remote, command: "docker logout"
        }
    } else {
        sshCommand remote: remote, command: "docker login -u $user -p $pass"
        sshCommand remote: remote, command: "echo ${pass} | docker login -u ${user} --password-stdin ${dockerRegistry}"
        sshCommand remote: remote, command: "docker pull $image"
        sshCommand remote: remote, command: "docker logout"
    }
}
