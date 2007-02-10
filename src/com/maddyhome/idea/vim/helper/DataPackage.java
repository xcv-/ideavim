package com.maddyhome.idea.vim.helper;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

public class DataPackage
{
    public DataPackage(DataContext context)
    {
        project = (Project)context.getData(DataConstants.PROJECT);
        virtualFile = (VirtualFile)context.getData(DataConstants.VIRTUAL_FILE);
        editor = (Editor)context.getData(DataConstants.EDITOR);
    }

    public DataPackage(AnActionEvent event)
    {
        this(event.getDataContext());
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
