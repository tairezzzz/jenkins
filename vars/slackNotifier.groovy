def call(channel) {
    def commits = [];
    def buildResult = currentBuild.currentResult;
    def publisher = LastChanges.getLastChangesPublisher "LAST_SUCCESSFUL_BUILD", "SIDE", "LINE", true, true, "", "", "", "", ""
    publisher.publishLastChanges()
    def changes = publisher.getLastChanges()

    if(changes){
        for (commit in changes.getCommits()) {
            def commitInfo = commit.getCommitInfo()
            commits << "\n  - ${commitInfo.getCommitMessage().trim()} [${commitInfo.getCommitterName().trim()}]"
        }
    }

    def msg = "${buildResult}: ${env.JOB_NAME} #${env.BUILD_NUMBER}  (<${env.BUILD_URL}|Open>) ${commits.join('')}"

    if ( buildResult == "SUCCESS" ) {
        slackSend channel: channel, color: "good", message: msg
    }
    else if( buildResult == "FAILURE" ) {
        slackSend channel: channel, color: "danger", message: msg
    }
    else if( buildResult == "UNSTABLE" ) {
        slackSend channel: channel, color: "warning", message: msg
    }
    else {
        slackSend channel: channel, color: "danger", message: msg
    }
}
