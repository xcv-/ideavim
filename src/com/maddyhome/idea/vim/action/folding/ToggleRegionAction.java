package com.maddyhome.idea.vim.action.folding;

import com.intellij.codeInsight.folding.CodeFoldingManager;
import com.intellij.codeInsight.folding.impl.FoldingUtil;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.FoldRegion;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;

public class ToggleRegionAction extends EditorAction {
  public ToggleRegionAction() {
    super(new EditorActionHandler() {
      @Override
      public void execute(final Editor editor, DataContext dataContext) {
        CodeFoldingManager foldingManager = CodeFoldingManager.getInstance(editor.getProject());
        foldingManager.updateFoldRegions(editor);

        final int line = editor.getCaretModel().getLogicalPosition().line;

        Runnable processor = new Runnable() {
          @Override
          public void run() {
            FoldRegion region = FoldingUtil.findFoldRegionStartingAtLine(editor, line);
            if (region != null) {
              region.setExpanded(!region.isExpanded());
            }
            else {
              int offset = editor.getCaretModel().getOffset();
              FoldRegion[] regions = FoldingUtil.getFoldRegionsAtOffset(editor, offset);

              for (FoldRegion region1 : regions) {
                region1.setExpanded(!region1.isExpanded());
                break;
              }
            }
          }
        };
        editor.getFoldingModel().runBatchFoldingOperation(processor);
      }

      @Override
      public boolean isEnabled(Editor editor, DataContext dataContext) {
        return super.isEnabled(editor, dataContext) && editor.getProject() != null;
      }

    });
  }
}


