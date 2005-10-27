@NAME@ - Version @VERSION@ for IDEA @IDEA-VERSION@

This plugin attempts to emulate the functionality of VIM within IDEA. It
actually emulates 'gvim' more than 'vim' since IDEA is a graphical IDE (of
course).


Installation

Use IDEA's plugin manager to install the latest version of the plugin.

First Time Upgrade from any version of @NAME@ prior to 0.10.1

The custom keymap isn't needed anymore and should be removed. The following
steps should be completed to perform the upgrade:

1) Download the latest plugin via IDEA's plugin manager.
2) Optionally restart IDEA.
3) Select File|Settings.
4) Select Keymap.
5) Select the Default keymap (or other desired default keymap other than Vim).
6) Press the Set Active button.
7) Select the Vim keymap.
8) Press the Delete button.
9) Press the Yes button.
10) Press the OK button.
11) Restart IDEA.

Starting

Once the files have been installed into their proper locations, start IDEA
normally and then perform the following step. This only needs to be done the
first time:

1) Select the "Tools" menu. There should be a new menu option labeled
   "VIM Emulator". This should have a checkmark next to it. If not, please
   select this menu to check it. If this menu option is not available, the
   plugin was not installed properly.

At this point you must use VIM keystrokes in all editors.


Disabling the @NAME@ Plugin

If you wish to disable the plugin, select the "Tools|VIM Emulator" menu so
it is unchecked. All keyboard shortcuts will return to their normal, non-VIM
settings.


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

Menu Changes

In order to emulate the keystrokes used by VIM, several of the default hotkeys
used by IDEA had to be changed. Below is a list of IDEA menus, their default
keyboard shortcuts, and their new VIM keystrokes.

File
     Save All                 Ctrl-S              :w

Edit
     Undo                     Ctrl-Z              u
     Redo                     Ctrl-Shift-Z        Ctrl-R
     Cut                      Ctrl-X              "+x
     Copy                     Ctrl-C              "+y
     Paste                    Ctrl-V              "+P
     Select All               Ctrl-A              ggVG

Search
     Find                     Ctrl-F              /
     Replace                  Ctrl-R              :s
     Find Next                F3                  n
     Find Previous            Shift-F3            N

View
     Quick JavaDoc            Ctrl-Q              K
     Parameter Info           Ctrl-P              Ctrl-Shift-P
     Swap Panels              Ctrl-U              <None>
     Recent Files...          Ctrl-E              <None>
     Type Hierarchy           Ctrl-H              Ctrl-Alt-Shift-H

Goto
     Class...                 Ctrl-N              Alt-Shift-N
     Line...                  Ctrl-G              G
     Declaration              Ctrl-B              gd
     Super Method             Ctrl-U              Ctrl-Shift-U

Code
     Override Methods...      Ctrl-O              Ctrl-Shift-O
     Implement Methods...     Ctrl-I              Ctrl-Shift-I
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
macros
Diagraphs

Not Supported (yet)
Keymaps
Various, lesser used (by me anyway), commands
Jumplists
Window commands
Command line history
Search history

Please see the file 'index.txt' for a complete list of supported, soon-to-be
supported, and never-to-be supported commands.
