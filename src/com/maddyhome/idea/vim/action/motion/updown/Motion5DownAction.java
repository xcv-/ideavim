package com.maddyhome.idea.vim.action.motion.updown;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.maddyhome.idea.vim.action.motion.MotionEditorAction;
import com.maddyhome.idea.vim.command.Argument;
import com.maddyhome.idea.vim.command.Command;
import com.maddyhome.idea.vim.group.CommandGroups;
import com.maddyhome.idea.vim.handler.motion.MotionEditorActionHandler;
import com.maddyhome.idea.vim.helper.EditorData;
import org.jetbrains.annotations.NotNull;

public class Motion5DownAction extends MotionEditorAction {
  public Motion5DownAction() {
    super(new Handler());
  }

  private static class Handler extends MotionEditorActionHandler {
    public int getOffset(@NotNull Editor editor, DataContext context, int count, int rawCount, Argument argument) {
      return CommandGroups.getInstance().getMotion().moveCaretVertical(editor, 5*count);
    }

    protected void preMove(@NotNull Editor editor, DataContext context, Command cmd) {
      col = EditorData.getLastColumn(editor);
    }

    protected void postMove(@NotNull Editor editor, DataContext context, Command cmd) {
      EditorData.setLastColumn(editor, col);
    }

    private int col;
  }
}
