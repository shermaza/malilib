package fi.dy.masa.malilib.event.dispatch;

import java.util.ArrayList;
import java.util.List;
import fi.dy.masa.malilib.config.util.ConfigUtils;
import fi.dy.masa.malilib.event.InitializationHandler;

public class InitializationDispatcherImpl implements InitializationDispatcher
{
    protected final List<InitializationHandler> handlers = new ArrayList<>();

    public InitializationDispatcherImpl()
    {
    }

    @Override
    public void registerInitializationHandler(InitializationHandler handler)
    {
        if (this.handlers.contains(handler) == false)
        {
            this.handlers.add(handler);
        }
    }

    /**
     * NOT PUBLIC API - DO NOT CALL
     */
    public void onGameInitDone()
    {
        if (this.handlers.isEmpty() == false)
        {
            for (InitializationHandler handler : this.handlers)
            {
                handler.registerModHandlers();
            }
        }

        ConfigUtils.loadAllConfigsFromFile();
    }
}
