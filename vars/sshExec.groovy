def call(Object args) {
  if (!args.remote) {
    throw new Exception("Remote is empty")
  }

  if (!args.command) {
    throw new Exception("Command is empty")
  }


  pathCommand = ''
  if (args.path) {
    pathCommand = "cd $args.path"
  }

  environments = '';
  args.env.each { env ->
    environments += "$env ";
  }

  shFile = """
    #!/bin/bash
    set -e

    $pathCommand
    ${args.command}
  """

  sshCommand remote: args.remote, command: "$environments sh <<< '$shFile'"
}
