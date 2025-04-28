package delta.common.ui.utils.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logger for uncaught UI exceptions.
 * @author DAM
 */
public class UIExceptionsLogger implements Thread.UncaughtExceptionHandler
{
  private static final Logger LOGGER=LoggerFactory.getLogger(UIExceptionsLogger.class);

  @Override
  public void uncaughtException(Thread t, Throwable e)
  {
    String msg="Uncaught exception in UI thread "+t.getName();
    LOGGER.error(msg,e);
  }

  /**
   * Initialize the logging handler.
   */
  public static void init()
  {
    Thread.setDefaultUncaughtExceptionHandler(new UIExceptionsLogger());
    System.setProperty("sun.awt.exception.handler", UIExceptionsLogger.class.getName());
  }
}
