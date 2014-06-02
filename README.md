# lein-file-replace

A Leiningen plugin to replace text in files with other text, possibly
from a project map.

## Usage

Put `[lein-file-replace "0.1.0"]` into your `:plugins` vector.

Here's a dumb thing you could do:

    $ lein file-replace README.md License Tomato

More useful is to add the following to your `:release-tasks` to keep
your `README.md` in sync with your project's version when you release
your project:

    ["file-replace" "README.md" "your-project \"" "\"]" "version"]

See this project's [project.clj] for a working example.

## License

Copyright © 2014 Jim Crossley

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
