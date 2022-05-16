def call(Object args) {
  if (!args.remote) {
    throw new Exception("Remote is empty")
  }

  if (!args.command) {
    throw new Exception("Command is empty")
  }


  pathCommand = ''
  if (args.path) {
    pathCommand = "cd $args.path && \n"
  }

  environments = '';
  args.env.each { env ->
    environments += "$env ";
  }

  command = """
	  $pathCommand$environments${args.command.trim()}
  """

  sshCommand remote: args.remote, command: "\n${command.trim()}"
}
