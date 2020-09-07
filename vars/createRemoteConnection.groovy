def call(env, params) {
  def REMOTE = [:];

  params.each { value ->
    def condition = value.get('condition');
    if (env in condition || env == condition) {

      REMOTE.name = value.get('host');
      REMOTE.host = value.get('host');
      REMOTE.allowAnyHosts = true;

      try {
        withCredentials([usernamePassword(
          credentialsId: value.get('credentialsId'),
          usernameVariable: 'USERNAME',
          passwordVariable: 'PASSWORD'
        )]) {
          REMOTE.user = USERNAME;
          REMOTE.password = PASSWORD;
        }
      } catch (e) {
      }

      try {
        withCredentials([sshUserPrivateKey(
          credentialsId: value.get('credentialsId'),
          keyFileVariable: 'KEY_FILE',
          usernameVariable: 'USERNAME',
        )]) {
          REMOTE.user = USERNAME;
          REMOTE.identity = sh(
            script: "cat $KEY_FILE",
            returnStdout: true
          )
        }
      } catch (e) {
      }
    }
  }

  return REMOTE;
}

