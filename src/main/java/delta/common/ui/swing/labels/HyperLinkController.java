package delta.common.ui.swing.labels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import delta.common.ui.swing.GuiFactory;

/**
 * Controller for an hyperlink label.
 * @author DAM
 */
public class HyperLinkController
{
  private JLabel _label;
  private Color _color;
  private String _text;
  private HyperLinkAction _action;

  /**
   * Constructor.
   * @param action Hyperlink action.
   */
  public HyperLinkController(HyperLinkAction action)
  {
    this(action,GuiFactory.buildLabel(""));
  }

  /**
   * Constructor.
   * @param action Hyperlink action.
   * @param label Label to use.
   */
  public HyperLinkController(HyperLinkAction action, JLabel label)
  {
    _action=action;
    _label=label;
    _label.setCursor(new Cursor(Cursor.HAND_CURSOR));
    _label.addMouseListener(new MouseAdapter()
    {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        doIt();
      }
    });
    String linkText=action.getLinkText();
    setText(linkText);
  }

  /**
   * Get the managed label.
   * @return a label.
   */
  public JLabel getLabel()
  {
    return _label;
  }

  /**
   * Set the text color.
   * @param color Color to use (<code>null</code> to use default).
   */
  public void setColor(Color color)
  {
    _color=color;
    updateLabel();
  }

  /**
   * Set link text.
   * @param linkText Text to set.
   */
  public void setText(String linkText)
  {
    _text=linkText;
    updateLabel();
  }

  private void updateLabel()
  {
    if (_label instanceof LabelWithHalo)
    {
      _label.setText(_text);
    }
    else
    {
      _label.setText(buildHTML());
    }
  }

  private String buildHTML()
  {
    StringBuilder sb=new StringBuilder();
    sb.append("<html><a href=\"\"");
    if (_color!=null)
    {
      sb.append(" style=\"color:").append(colorToHex(_color)).append('"');
    }
    sb.append('>');
    sb.append(_text);
    sb.append("</a></html>");
    return sb.toString();
  }

  private String colorToHex(Color color)
  {
    int red=color.getRed();
    int green=color.getGreen();
    int blue=color.getBlue();
    return String.format("#%02x%02x%02x", Integer.valueOf(red), Integer.valueOf(green), Integer.valueOf(blue));  
  }

  /**
   * Set the action.
   * @param action Action to set.
   */
  public void setAction(HyperLinkAction action)
  {
    _action=action;
  }

  private void doIt()
  {
    _action.doClick(this);
  }

  /**
   * Release all managed resources.
   */
  public void dispose()
  {
    _label=null;
  }
}
