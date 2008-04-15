@NAME@ - Version @VERSION@ for IDEA @IDEA-VERSION@

This plugin attempts to emulate the functionality of VIM within IDEA. It
actually emulates 'gvim' more than 'vim' since IDEA is a graphical IDE (of
course).


Installation

Use IDEA's plugin manager to install the latest version of the plugin.

First Time Installation or Upgrade from 0.10.1 to 0.10.3

UNIX/Linux

Copy Vim.xml from the plugin to $HOME/.IntelliJIdea50/config/keymaps. You will
have to create the keymaps directory if it doesn't exist.

Mac/OSX

Copy Vim.xml from the plugin to ~/Library/Preferences/IntelliJIDEA80/keymaps.
You will have to create the keymaps directory if it doesn't exist.

Windows

Copy Vim.xml from the plugin to
C:\Documents and Settings\<user>\.IntelliJIdea50\config\keymaps.
You will have to create the keymaps directory if it doesn't exist.

Starting

Once the files have been installed into their proper locations, start IDEA
normally and then perform the following steps. These only need to be done the
first time:

1) Select the "Tools" menu. There should be a new menu option labeled
   "VIM Emulator". This should have a checkmark next to it. If not, please
   select this menu to check it. If this menu option is not available, the
   plugin is not installed.
2) Select the "Options|Keymaps" menu. There should be a new keymap labeled "Vim"
   listed in the top listbox. If there is no such keymap listed you did not
   install the Vim.xml file in the proper location. Please highlight "Vim" and
   click on the "Set Active" button. Click "OK" to save these changes.

At this point you must use VIM keystrokes in all editors.


Disabling the @NAME@ Plugin

If you wish to disable the plugin, select the "Tools|VIM Emulator" menu so
it is unchecked. You must also select "Options|Keymaps" and make a keymap other
than "Vim" the active keymap. At this point IDEA will work with it's regular
keyboard shortcuts.


Changes to IDEA

Undo/Redo

The @NAME@ plugin uses it's own undo/redo functionality so it is important
that you use the standard VIM keys 'u' and 'Ctrl-R' for undo/redo instead of
the built in undo/redo. An exception might be if you wish to undo the creation
of a new class. For this you must select the Edit|Undo menu since @NAME@
doesn't support this feature. Using the built in undo/redo while editing a
file will result in strange behavior and you will most likely lose changes.

Escape

In IDEA, the Escape key is used during editing to cancel code completion
windows and parameter tooltips. While in VIM Insert mode, Escape is used to
return back to Normal mode. If you are typing in Insert mode and a code
completion window is popped up, pressing Escape will both cancel the window
and exit Insert mode. If a parameter tooltip appears, pressing Escape will not
make the tooltip go away whether in Insert or Normal mode. The only way to make
the tooltip disappear is to move the caret outside of the parameter area of
the method call. (I would love to receive solutions for both of these issues.)

Dialog Boxes

Many dialog boxes in IDEA contain single line entry fields. Many of these fields
are actually one line editors and you must use VIM keystrokes in these fields.
If you change a value by entering insert/replace mode you must press escape to
complete the changes. You must try to avoid making changes that result in the
value getting split into multiple lines.

To abort a dialog box you can press Escape. If you are in insert/replace mode
you will need to press Escape twice - once to exit insert mode, the other to
abort the dialog. You can tab between fields normally unless you are in
insert/replace mode. Enter will perform its default behavior unless you are in
insert/replace mode.

Menu Changes

In order to emulate the keystrokes used by VIM, several of the default hotkeys
used by IDEA had to be changed. Below is a list of IDEA menus, their default
keyboard shortcuts, and their new VIM keystrokes.

File
     Save All                 Ctrl-S              :w

Edit
     Undo                     Ctrl-Z              u
     Redo                     Ctrl-Shift-Z        Ctrl-R
     Cut                      Ctrl-X              "+x or Shift-Delete
     Copy                     Ctrl-C              "+y or Ctrl-Insert
     Paste                    Ctrl-V              "+P or Shift-Insert
     Select All               Ctrl-A              ggVG

Search
     Find                     Ctrl-F              /
     Replace                  Ctrl-R              :s
     Find Next                F3                  n
     Find Previous            Shift-F3            N

View
     Quick JavaDoc            Ctrl-Q              K
     Parameter Info           Ctrl-P              <None>
     Swap Panels              Ctrl-U              <None>
     Recent Files...          Ctrl-E              <None>

Goto
     Class...                 Ctrl-N              :class
     Line...                  Ctrl-G              G
     Declaration              Ctrl-B              gd
     Super Method             Ctrl-U              <None>

Code
     Override Methods...      Ctrl-O              <None>
     Implement Methods...     Ctrl-I              <None>
     Complete Code                                (Only in Insert mode)
          Basic               Ctrl-Space          Ctrl-Space
          Smart Type          Ctrl-Shift-Space    Ctrl-Shift-Space
          Class Name          Ctrl-Alt-Space      Ctrl-Alt-Space
     Insert Live Template     Ctrl-J              <None>

Tools
     Version Control
          Check In Project    Ctrl-K              <None>


Summary of Supported/Unsupported VIM Features

Supported

Motion keys
Deletion/Changing
Insert mode commands
Marks
Registers
VIM undo/redo
Visual mode commands
Some Ex commands
Some :set options
Full VIM regular expressions for search and search/replace
Macros
Diagraphs
Command line history
Search history
VIM help
Jumplists

Not Supported (yet)
Maps
Various, lesser used (by me anyway), commands
Window commands

Please see the help system for a complete list of supported commands.
