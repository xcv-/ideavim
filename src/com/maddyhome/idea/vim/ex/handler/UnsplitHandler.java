package com.maddyhome.idea.vim.ex.handler;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.maddyhome.idea.vim.KeyHandler;
import com.maddyhome.idea.vim.ex.CommandHandler;
import com.maddyhome.idea.vim.ex.ExCommand;
import com.maddyhome.idea.vim.ex.ExException;

import javax.swing.*;

public class UnsplitHandler extends CommandHandler {
  public UnsplitHandler() {
    super("uns", "lit", ARGUMENT_FORBIDDEN | RANGE_FORBIDDEN | DONT_REOPEN);
  }

  @Override
  public boolean execute(Editor editor, final DataContext context, ExCommand cmd) throws ExException {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        KeyHandler.executeAction("Unsplit", context);
      }
    });

    return true;
  }
}
