package com.maddyhome.idea.vim.helper;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

public class DataPackage
{
    public DataPackage(DataContext context)
    {
        project = DataKeys.PROJECT.getData(context);
        virtualFile = DataKeys.VIRTUAL_FILE.getData(context);
        editor = DataKeys.EDITOR.getData(context);
    }

    public DataPackage(AnActionEvent event)
    {
        project = event.getData(DataKeys.PROJECT);
        virtualFile = event.getData(DataKeys.VIRTUAL_FILE);
        editor = event.getData(DataKeys.EDITOR);
    }

    public DataContext getDataContext()
    {
        return new EditorDataContext(editor);
    }

    public Project getProject()
    {
        return project;
    }

    public VirtualFile getVirtualFile()
    {
        return virtualFile;
    }

    public Editor getEditor()
    {
        return editor;
    }

    private Project project;
    private VirtualFile virtualFile;
    private Editor editor;
}
