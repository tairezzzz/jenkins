def call(remote, into, filesPath) {
    filesPath.each { filePath ->
        def findedFiles = findFiles(glob: filePath)
        findedFiles.each { file ->
            sshPut remote: remote, from: file.name, into: into
        }
    }
}
