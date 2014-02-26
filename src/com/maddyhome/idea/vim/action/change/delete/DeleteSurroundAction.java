package com.maddyhome.idea.vim.action.change.delete;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.maddyhome.idea.vim.command.Argument;
import com.maddyhome.idea.vim.group.CommandGroups;
import com.maddyhome.idea.vim.handler.ChangeEditorActionHandler;
import com.maddyhome.idea.vim.helper.SurroundHelper;
import org.jetbrains.annotations.NotNull;

public class DeleteSurroundAction extends EditorAction {
  public DeleteSurroundAction() {
    super(new ChangeEditorActionHandler() {
      public boolean execute(@NotNull Editor editor, DataContext context, int count, int rawCount, @NotNull Argument argument) {
        char arg = argument.getCharacter();
        return CommandGroups.getInstance().getChange().deleteSurround(editor, SurroundHelper.lhs(arg), SurroundHelper.rhs(arg));
      }
    });
  }
}
