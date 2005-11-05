package com.maddyhome.idea.vim.action;

/*
 * IdeaVim - A Vim emulator plugin for IntelliJ Idea
 * Copyright (C) 2003-2005 Rick Maddy
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

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.editor.Editor;
import com.maddyhome.idea.vim.KeyHandler;
import com.maddyhome.idea.vim.VimPlugin;

import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

public class PassThruDelegateAction extends AbstractDelegateAction
{
    public PassThruDelegateAction(KeyStroke stroke)
    {
        this.stroke = stroke;
    }

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
        else
        {
            KeyHandler.getInstance().handleKey(editor, stroke, event.getDataContext());
        }
    }

    private KeyStroke stroke;
}