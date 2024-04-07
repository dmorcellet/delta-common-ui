package delta.common.ui.swing.icons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;

import delta.common.ui.swing.draw.HaloPainter;

/**
 * Icon with text display on bottom right.
 * @author DAM
 */
public class IconWithText implements Icon
{
  /**
   * Default font.
   */
  public static final Font DEFAULT_FONT=new Font(Font.DIALOG,Font.BOLD,12);

  /**
   * Position of text inside the icon.
   * @author DAM
   */
  public enum Position
  {
    /**
     * Bottom right.
     */
    BOTTOM_RIGHT,
    /**
     * Top left.
     */
    TOP_LEFT
  }

  private Icon _icon;
  private String _text;
  private Font _font;
  private Color _color;
  private Position _position;
  private boolean _useHalo;
  private int _xMargin;
  private int _yMargin;

  /**
   * Constructor.
   * @param icon Embedded icon.
   * @param text Text to display.
   * @param color Color to use for text.
   */
  public IconWithText(Icon icon, String text, Color color)
  {
    _icon=icon;
    _text=text;
    _font=DEFAULT_FONT;
    _color=color;
    _position=Position.BOTTOM_RIGHT;
    _useHalo=true;
    _xMargin=2;
  }

  /**
   * Set position to use.
   * @param position Position to use.
   */
  public void setPosition(Position position)
  {
    _position=position;
  }

  /**
   * Set the "use halo" flag.
   * @param useHalo Value to set.
   */
  public void setUseHalo(boolean useHalo)
  {
    _useHalo=useHalo;
  }

  @Override
  public void paintIcon(Component c, Graphics g, int x, int y)
  {
    if (_icon!=null)
    {
      _icon.paintIcon(c,g,x,y);
    }

    if (_text.length()>0)
    {
      g.setFont(_font);
      FontMetrics metrics=g.getFontMetrics(_font);
      Rectangle2D r=metrics.getStringBounds(_text,g);

      int dx;
      int dy;
      if (_position==Position.BOTTOM_RIGHT)
      {
        dx = (int)(getIconWidth() - r.getWidth()) - _xMargin;
        dy = getIconHeight() - metrics.getDescent() - _yMargin;
      }
      else
      {
        dx = 5;
        dy = metrics.getAscent() + 1;
      }
      dx+=x;
      dy+=y;

      if (_useHalo)
      {
        HaloPainter.drawStringWithHalo(g,dx,dy,_text,_color,Color.BLACK);
      }
      else
      {
        g.setColor(_color);
        g.drawString(_text,dx,dy);
      }
    }
  }

  /**
   * Set text to display.
   * @param text Text to display.
   */
  public void setText(String text)
  {
    _text=text;
  }

  /**
   * Set the font size.
   * @param size Size to set.
   */
  public void setFontSize(int size)
  {
    _font=_font.deriveFont(size);
  }

  /**
   * Set font in "bold".
   */
  public void setBold()
  {
    _font=_font.deriveFont(Font.BOLD);
  }

  /**
   * Set font.
   * @param font Font to set.
   */
  public void setFont(Font font)
  {
    _font=font;
  }

  /**
   * Set the X margin.
   * @param xMargin Value to set (pixels).
   */
  public void setXMargin(int xMargin)
  {
    _xMargin=xMargin;
  }

  /**
   * Set the Y margin.
   * @param yMargin Value to set (pixels).
   */
  public void setYMargin(int yMargin)
  {
    _yMargin=yMargin;
  }

  @Override
  public int getIconWidth()
  {
    return (_icon!=null)?_icon.getIconWidth():0;
  }

  @Override
  public int getIconHeight()
  {
    return (_icon!=null)?_icon.getIconHeight():0;
  }
}
