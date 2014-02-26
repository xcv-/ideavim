/*
 * IdeaVim - Vim emulator for IDEs based on the IntelliJ platform
 * Copyright (C) 2003-2013 The IdeaVim authors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.maddyhome.idea.vim.action.change.delete;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.maddyhome.idea.vim.command.Command;
import com.maddyhome.idea.vim.command.SelectionType;
import com.maddyhome.idea.vim.common.TextRange;
import com.maddyhome.idea.vim.group.CommandGroups;
import com.maddyhome.idea.vim.handler.VisualOperatorActionHandler;
import com.maddyhome.idea.vim.helper.EditorHelper;
import org.jetbrains.annotations.NotNull;

/**
 */
public class DeleteVisualLinesEndAction extends EditorAction {
  public DeleteVisualLinesEndAction() {
    super(new Handler());
  }

  private static class Handler extends VisualOperatorActionHandler {
    protected boolean execute(@NotNull Editor editor, @NotNull DataContext context, @NotNull Command cmd,
                              @NotNull TextRange range) {
      if (range.isMultiple()) {
        int[] starts = range.getStartOffsets();
        int[] ends = range.getEndOffsets();
        for (int i = 0; i < starts.length; i++) {
          if (ends[i] > starts[i]) {
            ends[i] = EditorHelper.getLineEndForOffset(editor, starts[i]);
          }
        }

        range = new TextRange(starts, ends);
        return CommandGroups.getInstance().getChange().deleteRange(editor, range,
                                                                   SelectionType.BLOCK_WISE, false, true);
      }
      else {
        range = new TextRange(EditorHelper.getLineStartForOffset(editor, range.getStartOffset()),
                              EditorHelper.getLineEndForOffset(editor, range.getEndOffset()) + 1);

        return CommandGroups.getInstance().getChange().deleteRange(editor, range,
                                                                   SelectionType.LINE_WISE, false, true);
      }
    }
  }
}
