package com.maddyhome.idea.vim.action.change.insert.surround;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.maddyhome.idea.vim.command.Command;
import com.maddyhome.idea.vim.common.TextRange;
import com.maddyhome.idea.vim.group.CommandGroups;
import com.maddyhome.idea.vim.handler.VisualOperatorActionHandler;
import com.maddyhome.idea.vim.helper.SurroundHelper;
import org.jetbrains.annotations.NotNull;

public class InsertSurroundVisualAction extends EditorAction {
  protected InsertSurroundVisualAction() {
    super(new VisualOperatorActionHandler() {
      @Override
      protected boolean execute(@NotNull Editor editor, @NotNull DataContext context, @NotNull Command cmd, @NotNull TextRange range) {
        char delim = cmd.getArgument().getCharacter();
        char lhs = SurroundHelper.lhs(delim);
        char rhs = SurroundHelper.rhs(delim);

        int offset = 0;

        for (int i = 0; i < range.size(); i++) {
          CommandGroups.getInstance().getChange().insertText(editor, range.getStartOffsets()[i]+offset, Character.toString(lhs));
          offset += 1;
          CommandGroups.getInstance().getChange().insertText(editor, range.getEndOffsets()[i]+offset, Character.toString(rhs));
          offset += 1;
        }

        return true;
      }
    });
  }
}
