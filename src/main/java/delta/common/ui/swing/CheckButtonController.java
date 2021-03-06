package delta.common.ui.swing;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import delta.common.ui.swing.icons.IconsManager;

/**
 * Controller for a button that displays state icons.
 * @author DAM
 */
public class CheckButtonController
{
  private ImageIcon[] _icons;
  private JButton _button;
  private ActionListener _listener;

  /**
   * Constructor.
   * @param iconPaths Icons to use.
   */
  public CheckButtonController(String[] iconPaths)
  {
    _icons=new ImageIcon[iconPaths.length];
    int index=0;
    for(String iconPath : iconPaths)
    {
      ImageIcon icon=IconsManager.getIcon(iconPath);
      _icons[index]=icon;
      index++;
    }
    _button=buildButton();
  }

  /**
   * Get the managed button.
   * @return the managed button.
   */
  public JButton getButton()
  {
    return _button;
  }

  private Dimension getIconSize()
  {
    int width=0;
    int height=0;
    for(ImageIcon icon : _icons)
    {
      int iconHeight=icon.getIconHeight();
      if (iconHeight>height) height=iconHeight;
      int iconWidth=icon.getIconWidth();
      if (iconWidth>width) width=iconWidth;
    }
    return new Dimension(width,height);
  }

  private JButton buildButton()
  {
    JButton button=GuiFactory.buildIconButton();
    button.setIcon(_icons[0]);
    Dimension size=getIconSize();
    button.setSize(size);
    return button;
  }

  /**
   * Set the state of this button.
   * @param index State index (starting at 0).
   */
  public void setState(int index)
  {
    _button.setIcon(_icons[index]);
  }

  /**
   * Release all managed resources.
   */
  public void dispose()
  {
    _icons=null;
    if (_button!=null)
    {
      if (_listener!=null)
      {
        _button.removeActionListener(_listener);
      }
      _button=null;
    }
    _listener=null;
  }
}
