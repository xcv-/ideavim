package com.maddyhome.idea.vim.action.change.change.surround;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.maddyhome.idea.vim.command.Argument;
import com.maddyhome.idea.vim.group.CommandGroups;
import com.maddyhome.idea.vim.handler.ChangeEditorActionHandler;
import com.maddyhome.idea.vim.helper.SurroundHelper;
import org.jetbrains.annotations.NotNull;

public abstract class ChangeSurroundAction extends EditorAction {
  public ChangeSurroundAction(final char lhs, final char rhs) {
    super(new ChangeEditorActionHandler() {
      @Override
      public boolean execute(@NotNull Editor editor, DataContext context, int count, int rawCount, @NotNull Argument argument) {
        char newSurround = argument.getCharacter();
        System.out.printf("changeSurround: '%c%c' -> '%c'\n", lhs, rhs, newSurround);

        return CommandGroups.getInstance().getChange().changeSurround(
          editor, lhs, rhs, SurroundHelper.lhs(newSurround), SurroundHelper.rhs(newSurround));
      }
    });
  }
}

