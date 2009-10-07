package com.maddyhome.idea.vim.helper;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

public class DataPackage
{
    public DataPackage(DataContext context)
    {
        project = PlatformDataKeys.PROJECT.getData(context);
        virtualFile = PlatformDataKeys.VIRTUAL_FILE.getData(context);
        editor = PlatformDataKeys.EDITOR.getData(context);
    }

    public DataPackage(AnActionEvent event)
    {
        project = event.getData(PlatformDataKeys.PROJECT);
        virtualFile = event.getData(PlatformDataKeys.VIRTUAL_FILE);
        editor = event.getData(PlatformDataKeys.EDITOR);
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
