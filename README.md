IdeaVim
=======

This is a personally customized fork of the official IntelliJ IDEA plugin for
Vim emulation, [IdeaVim](https://github.com/JetBrains/ideavim)

Changes/Additions:
- :vsplit, :split, :unsplit, c-w to alternate between splits
- ñ mapped to : by default
- za, space mapped to za
- New entire document text-object, af (a file)
- Minimal, buggy implementation of the surround plugin
- Enter in normal mode inserts empty line below, stays in insert mode
- c-j is equivalent to 5j and c-k is equivalent to 5k
- Change commands and visual mode pasting don't overwrite the register


Original Authors
-------

See [AUTHORS.md](https://github.com/JetBrains/ideavim/blob/master/AUTHORS.md)
from the original repository for a list of authors and contributors.


License
-------

IdeaVim is licensed under the terms of the GNU Public license version 2.

