package com.maddyhome.idea.vim.helper;

import com.intellij.openapi.command.CommandAdapter;
import com.intellij.openapi.command.CommandEvent;
import com.intellij.openapi.diagnostic.Logger;

public class DelegateCommandListener extends CommandAdapter
{
    public static DelegateCommandListener getInstance()
    {
        return instance;
    }

    public void setRunnable(StartFinishRunnable runnable)
    {
        this.runnable = runnable;
        inCommand = false;
    }

    public StartFinishRunnable getRunnable()
    {
        return runnable;
    }

    public void commandStarted(CommandEvent event)
    {
        inCommand = true;
        logger.debug("Command started: " + event);
        logger.debug("Name: " + event.getCommandName());
        logger.debug("Group: " + event.getCommandGroupId());

        if (runnable != null)
        {
            runnable.start();
        }
    }

    public void commandFinished(CommandEvent event)
    {
        logger.debug("Command finished: " + event);
        logger.debug("Name: " + event.getCommandName());
        logger.debug("Group: " + event.getCommandGroupId());

        if (runnable != null && inCommand)
        {
            runnable.finish();
            runnable = null;
        }

        inCommand = false;
    }

    private DelegateCommandListener()
    {
    }

    public static interface StartFinishRunnable
    {
        void start();
        void finish();
    }

    private boolean inCommand = false;
    private StartFinishRunnable runnable;

    private static Logger logger = Logger.getInstance(DelegateCommandListener.class.getName());
    private static DelegateCommandListener instance = new DelegateCommandListener();
}