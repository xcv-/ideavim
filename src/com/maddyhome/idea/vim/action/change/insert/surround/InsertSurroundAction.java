package com.maddyhome.idea.vim.action.change.insert.surround;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.maddyhome.idea.vim.command.Argument;
import com.maddyhome.idea.vim.common.TextRange;
import com.maddyhome.idea.vim.group.CommandGroups;
import com.maddyhome.idea.vim.group.MotionGroup;
import com.maddyhome.idea.vim.handler.ChangeEditorActionHandler;
import org.jetbrains.annotations.NotNull;

public abstract class InsertSurroundAction extends EditorAction {
  public InsertSurroundAction(final char lhs, final char rhs) {
    super(new ChangeEditorActionHandler() {
      public boolean execute(@NotNull Editor editor, DataContext context, int count, int rawCount, @NotNull Argument argument) {
        TextRange range = MotionGroup.getMotionRange(editor, context, count, rawCount, argument, true, false);

        CommandGroups.getInstance().getChange().insertText(editor, range.getStartOffset(), Character.toString(lhs));
        CommandGroups.getInstance().getChange().insertText(editor, range.getEndOffset(), Character.toString(rhs));

        return true;
      }
    });
  }
}
