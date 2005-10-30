package com.maddyhome.idea.vim.action;

import com.intellij.openapi.actionSystem.AnActionEvent;

public class OriginalDelegateAction extends AbstractDelegateAction
{
    public void actionPerformed(AnActionEvent event)
    {
        getOrigAction().actionPerformed(event);
    }
}