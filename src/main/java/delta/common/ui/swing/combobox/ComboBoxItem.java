package delta.common.ui.swing.combobox;

/**
 * A combox box item that comes with a label.
 * @param <T> Type of managed data.
 * @author DAM
 */
public class ComboBoxItem<T>
{
  private T _item;
  private String _label;

  /**
   * Constructor.
   * @param item Managed item.
   * @param label Associated label.
   */
  public ComboBoxItem(T item, String label)
  {
    _item=item;
    _label=label;
  }

  /**
   * Get the managed item.
   * @return the managed item.
   */
  public T getItem()
  {
    return _item;
  }

  /**
   * Get the label.
   * @return the label.
   */
  public String getLabel()
  {
    return _label;
  }

  @Override
  public String toString()
  {
    return _label;
  }
}
