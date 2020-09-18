package com.bojun.common.binding.command;


/**
 * ViewAdapter
 */
public class BindingCommand<T> {
    private BindingAction execute;

    public BindingCommand(BindingAction execute) {
        this.execute = execute;
    }

    public void execute() {
        if (execute != null) {
            execute.call();
        }
    }
}
