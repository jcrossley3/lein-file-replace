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

As you might've noticed, the task takes either 3 or 4 arguments. The
first arg is always the relative path to the file you want to change,
and the last arg is always the replacement text, which may be the name
of a key in your project map.

The 2nd arg of the 3-arity form is a regular expression identifying
the text you want to replace. Any captured groups may be referenced in
your replacement text with a dollar sign, e.g. $1, $2, etc.

When using the 4-arity form, the 2nd arg is the *lookbehind* text to
match and the 3rd arg is the *lookahead* text. So whatever text is *in
between them* will be replaced. Uniquely identifying the text before
and after what you want to replace can be easier than coming up with
the right regex.

See this project's [project.clj](project.clj) for a working example.

## License

Copyright © 2014 Jim Crossley

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
