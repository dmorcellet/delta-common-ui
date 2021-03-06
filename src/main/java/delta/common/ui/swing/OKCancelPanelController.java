package delta.common.ui.swing;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import delta.common.ui.swing.buttons.ButtonUtils;

/**
 * Controller for a panel with OK and cancel button.
 * @author DAM
 */
public class OKCancelPanelController
{
  /**
   * OK command.
   */
  public static final String OK_COMMAND="OK";
  /**
   * Cancel command.
   */
  public static final String CANCEL_COMMAND="CANCEL";
  private JPanel _panel;
  private JButton _okButton;
  private JButton _cancelButton;

  /**
   * Constructor.
   */
  public OKCancelPanelController()
  {
    build(true,true);
  }

  /**
   * Constructor.
   * @param useOK Use the OK button.
   * @param useCancel Use the cancel button.
   */
  public OKCancelPanelController(boolean useOK, boolean useCancel)
  {
    build(useOK,useCancel);
  }

  private void build(boolean useOK, boolean useCancel)
  {
    _panel=GuiFactory.buildPanel(new FlowLayout(FlowLayout.TRAILING));
    if (useOK)
    {
      _okButton=GuiFactory.buildButton("OK");
      _okButton.setActionCommand(OK_COMMAND);
      _panel.add(_okButton);
    }
    if (useCancel)
    {
      _cancelButton=GuiFactory.buildButton("Cancel");
      _cancelButton.setActionCommand(CANCEL_COMMAND);
      _panel.add(_cancelButton);
    }
  }

  /**
   * Get the managed panel.
   * @return the managed panel.
   */
  public JPanel getPanel()
  {
    return _panel;
  }

  /**
   * Get the OK button.
   * @return the OK button.
   */
  public JButton getOKButton()
  {
    return _okButton;
  }

  /**
   * Get the cancel button.
   * @return the cancel button.
   */
  public JButton getCancelButton()
  {
    return _cancelButton;
  }

  /**
   * Release all managed resources.
   */
  public void dispose()
  {
    if (_panel!=null)
    {
      _panel.removeAll();
      _panel=null;
    }
    ButtonUtils.dispose(_okButton);
    _okButton=null;
    ButtonUtils.dispose(_cancelButton);
    _cancelButton=null;
  }
}
