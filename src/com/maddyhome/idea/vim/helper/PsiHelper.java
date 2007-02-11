package com.maddyhome.idea.vim.helper;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiMethod;

import java.util.ArrayList;
import java.util.List;

/*
 * IdeaVim - A Vim emulator plugin for IntelliJ Idea
 * Copyright (C) 2003-2006 Rick Maddy
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

public class PsiHelper
{
    public static boolean isJavaFile(Editor editor)
    {
        PsiFile file = getFile(editor);

        if (file == null)
        {
            return false;
        }

        return file instanceof PsiJavaFile; // API change - don't merge
    }

    public static int findMethodStart(Editor editor, int offset, int count)
    {
        return findMethodOrClass(editor, offset, count, true);
    }

    public static int findMethodEnd(Editor editor, int offset, int count)
    {
        return findMethodOrClass(editor, offset, count, false);
    }

    private static int findMethodOrClass(Editor editor, int offset, int count, boolean isStart)
    {
        PsiJavaFile file = (PsiJavaFile)getFile(editor);
        int dir = count > 0 ? 1 : -1;
        count = Math.abs(count);

        int res = offset;
        for (int i = 0; i < count; i++)
        {
            res = scanClasses(file.getClasses(), res, dir, isStart);
            if (res == -1)
            {
                break;
            }
        }

        return res;
    }

    private static int scanClasses(PsiClass[] classes, int offset, int dir, boolean isStart)
    {
        PsiClass fclass = null;
        boolean inside = false;
        int closest = dir > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        for (PsiClass clazz : classes)
        {
            PsiElement lbrace = clazz.getLBrace();
            PsiElement rbrace = clazz.getRBrace();
            if (lbrace == null || rbrace == null)
            {
                continue;
            }

            if (dir > 0)
            {
                if (offset >= lbrace.getTextOffset() && offset < rbrace.getTextOffset())
                {
                    fclass = clazz;
                    inside = true;
                    break;
                }
                // We are before this class
                else if (offset < lbrace.getTextOffset() && lbrace.getTextOffset() < closest)
                {
                    fclass = clazz;
                    closest = lbrace.getTextOffset();
                }
            }
            else
            {
                if (offset > lbrace.getTextOffset() && offset <= rbrace.getTextOffset())
                {
                    fclass = clazz;
                    inside = true;
                    break;
                }
                // We are after this class
                if (offset > rbrace.getTextOffset() && rbrace.getTextOffset() > closest)
                {
                    fclass = clazz;
                    closest = rbrace.getTextOffset();
                }
            }

        }

        if (fclass == null)
        {
            // No more classes to be found in the direction we are searching
            return -1;
        }
        else if (!inside)
        {
            PsiElement lbrace = fclass.getLBrace();
            PsiElement rbrace = fclass.getRBrace();
            if (lbrace == null || rbrace == null)
            {
                return -1;
            }

            return dir > 0 ? lbrace.getTextOffset() : rbrace.getTextOffset();
        }
        else
        {
            // We are in the class so see if we have a match on a method or inner class.
            PsiElement lbrace = fclass.getLBrace();
            PsiElement rbrace = fclass.getRBrace();
            if (lbrace == null || rbrace == null)
            {
                return -1;
            }

            int res = scanClass(fclass, offset, dir, isStart);

            if (res == -1)
            {
                if (dir > 0)
                {
                    res = rbrace.getTextOffset();
                }
                else
                {
                    res = lbrace.getTextOffset();
                }
            }

            return res;
        }
    }

    private static int scanClass(PsiClass clazz, int offset, int dir, boolean isStart)
    {
        int closest = dir > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        PsiMethod fmethod = null;
        PsiClass fclass = null;
        boolean inside = false;

        PsiClass[] classes = clazz.getInnerClasses();
        for (PsiClass aClass : classes)
        {
            PsiElement lbrace = aClass.getLBrace();
            PsiElement rbrace = aClass.getRBrace();
            if (lbrace == null || rbrace == null)
            {
                continue;
            }

            if (dir > 0)
            {
                if (offset >= lbrace.getTextOffset() && offset < rbrace.getTextOffset())
                {
                    fclass = aClass;
                    inside = true;
                    break;
                }
                // We are before this class
                else if (offset < lbrace.getTextOffset() && lbrace.getTextOffset() < closest)
                {
                    fclass = aClass;
                    closest = lbrace.getTextOffset();
                }
            }
            else
            {
                if (offset > lbrace.getTextOffset() && offset <= rbrace.getTextOffset())
                {
                    fclass = aClass;
                    inside = true;
                    break;
                }
                // We are after this class
                if (offset > rbrace.getTextOffset() && rbrace.getTextOffset() > closest)
                {
                    fclass = aClass;
                    closest = rbrace.getTextOffset();
                }
            }

        }

        PsiMethod[] methods = clazz.getMethods();
        for (int i = 0; i < methods.length && !inside; i++)
        {
            PsiCodeBlock body = methods[i].getBody();
            if (body == null)
            {
                continue;
            }

            PsiElement lbrace = body.getLBrace();
            PsiElement rbrace = body.getRBrace();
            if (lbrace == null || rbrace == null)
            {
                continue;
            }

            PsiElement brace = isStart ? lbrace : rbrace;

            if (dir > 0)
            {
                if (offset >= lbrace.getTextOffset() && offset <= rbrace.getTextOffset())
                {
                    int res = scanMethod(methods[i], offset, dir, isStart);
                    if (res != -1)
                    {
                        return res;
                    }
                }

                // We are before this method
                if (offset < brace.getTextOffset() && brace.getTextOffset() < closest)
                {
                    fmethod = methods[i];
                    closest = brace.getTextOffset();
                    if (!isStart)
                    {
                        int res = scanMethod(methods[i], offset, dir, isStart);
                        if (res != -1)
                        {
                            closest = res;
                            inside = true;
                        }
                    }
                }
            }
            else
            {
                if (offset >= lbrace.getTextOffset() && offset <= rbrace.getTextOffset())
                {
                    int res = scanMethod(methods[i], offset, dir, isStart);
                    if (res != -1)
                    {
                        return res;
                    }
                }

                // We are after this method
                if (offset > brace.getTextOffset() && brace.getTextOffset() > closest)
                {
                    fmethod = methods[i];
                    closest = brace.getTextOffset();
                    if (isStart)
                    {
                        int res = scanMethod(methods[i], offset, dir, isStart);
                        if (res != -1)
                        {
                            closest = res;
                            inside = true;
                        }
                    }
                }
            }
        }

        if (fmethod == null && fclass == null)
        {
            // No more classes or methods to be found in the direction we are searching
            return -1;
        }
        else if (fmethod != null)
        {
            if (!inside)
            {
                PsiCodeBlock body = fmethod.getBody();
                if (body == null)
                {
                    return -1;
                }

                PsiElement lbrace = body.getLBrace();
                PsiElement rbrace = body.getRBrace();
                if (lbrace == null || rbrace == null)
                {
                    return -1;
                }

                return isStart ? lbrace.getTextOffset() : rbrace.getTextOffset();
            }
            else
            {
                return closest;
            }
        }
        else if (fclass != null)
        {
            if (!inside)
            {
                PsiElement lbrace = fclass.getLBrace();
                PsiElement rbrace = fclass.getRBrace();
                if (lbrace == null || rbrace == null)
                {
                    return -1;
                }

                return dir > 0 ? lbrace.getTextOffset() : rbrace.getTextOffset();
            }
            else
            {
                // We are in the class so see if we have a match on a method or inner class.
                PsiElement lbrace = fclass.getLBrace();
                PsiElement rbrace = fclass.getRBrace();
                if (lbrace == null || rbrace == null)
                {
                    return -1;
                }

                int res = scanClass(fclass, offset, dir, isStart);

                if (res == -1)
                {
                    if (dir > 0)
                    {
                        res = rbrace.getTextOffset();
                    }
                    else
                    {
                        res = lbrace.getTextOffset();
                    }
                }

                return res;
            }
        }

        return -1;
    }

    private static int scanMethod(PsiMethod fmethod, int offset, int dir, boolean start)
    {
        List<PsiClass> classes = new ArrayList<PsiClass>();
        findClasses(fmethod.getBody(), classes);

        if (classes.size() > 0)
        {
            return scanClasses(classes.toArray(new PsiClass[] {}), offset, dir, start);
        }

        return -1;
    }

    private static void findClasses(PsiElement element, List<PsiClass> classes)
    {
        PsiElement[] children = element.getChildren();
        for (PsiElement child : children)
        {
            if (child instanceof PsiClass)
            {
                classes.add((PsiClass)child);
                return;
            }

            findClasses(child, classes);
        }
    }

    private static PsiFile getFile(Editor editor)
    {
        VirtualFile vf = EditorData.getVirtualFile(editor);
        Project proj = EditorData.getProject(editor);
        PsiManager mgr = PsiManager.getInstance(proj);

        return mgr.findFile(vf);
    }
}
