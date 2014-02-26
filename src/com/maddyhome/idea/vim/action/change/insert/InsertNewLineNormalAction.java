package com.maddyhome.idea.vim.action.change.insert;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.maddyhome.idea.vim.command.Argument;
import com.maddyhome.idea.vim.group.CommandGroups;
import com.maddyhome.idea.vim.handler.ChangeEditorActionHandler;
import org.jetbrains.annotations.NotNull;

public class InsertNewLineNormalAction extends EditorAction {
  public InsertNewLineNormalAction() {
    super(new Handler());
  }

  private static class Handler extends ChangeEditorActionHandler {
    public boolean execute(@NotNull Editor editor, @NotNull DataContext context, int count, int rawCount, Argument argument) {
      if (editor.isOneLineMode()) {
        return false;
      }

      CommandGroups.getInstance().getChange().insertNewLineNormal(editor, context);

      return true;
    }
  }
}
