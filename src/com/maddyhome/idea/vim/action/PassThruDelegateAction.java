package com.maddyhome.idea.vim.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.editor.Editor;
import com.maddyhome.idea.vim.VimPlugin;
import com.maddyhome.idea.vim.KeyHandler;

import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

public class PassThruDelegateAction extends AbstractDelegateAction
{
    public void actionPerformed(AnActionEvent event)
    {
        final Editor editor = (Editor)event.getDataContext().getData(DataConstants.EDITOR);
        if (editor == null || !VimPlugin.isEnabled())
        {
            getOrigAction().actionPerformed(event);
        }
        else if (event.getInputEvent() instanceof KeyEvent)
        {
            KeyStroke key = KeyStroke.getKeyStrokeForEvent((KeyEvent)event.getInputEvent());
            KeyHandler.getInstance().handleKey(editor, key, event.getDataContext());
        }
    }
}