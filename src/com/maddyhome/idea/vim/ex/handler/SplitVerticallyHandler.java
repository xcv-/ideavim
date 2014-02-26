package com.maddyhome.idea.vim.ex.handler;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.maddyhome.idea.vim.KeyHandler;
import com.maddyhome.idea.vim.ex.CommandHandler;
import com.maddyhome.idea.vim.ex.ExCommand;
import com.maddyhome.idea.vim.ex.ExException;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SplitVerticallyHandler extends CommandHandler {
  public SplitVerticallyHandler() {
    super("vs", "lit", RANGE_FORBIDDEN | ARGUMENT_FORBIDDEN | DONT_REOPEN);
  }

  public boolean execute(@NotNull Editor editor, @NotNull final DataContext context, @NotNull ExCommand cmd) throws ExException {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        KeyHandler.executeAction("SplitVertically", context);
      }
    });

    return true;
  }
}
