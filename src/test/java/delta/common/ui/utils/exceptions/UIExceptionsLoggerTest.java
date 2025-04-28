package delta.common.ui.utils.exceptions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.junit.jupiter.api.Test;

/**
 * Test for the UI exceptions logger.
 * @author DAM
 */
class UIExceptionsLoggerTest
{
  @Test
  void testExceptionInEvent()
  {
    UIExceptionsLogger.init();
    // cause an exception on the EDT
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        ((Object)null).toString();
      }
    });
  }

  @Test
  void testExceptionInModalCall()
  {
    UIExceptionsLogger.init();
    final JDialog dialog=new JDialog();
    ActionListener buttonAL=new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        ((Object)null).toString();
      }
    };
    JButton b=new JButton();
    b.addActionListener(buttonAL);
    dialog.add(b);
    // Timer to click
    ActionListener al=new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        b.doClick();
      }
    };
    Timer t=new Timer(1000,al);
    t.start();
    // Timer to close the dialog
    ActionListener al2=new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        dialog.setVisible(false);
      }
    };
    Timer t2=new Timer(2000,al2);
    t2.start();

    dialog.setModal(true);
    dialog.setVisible(true);
  }
}
