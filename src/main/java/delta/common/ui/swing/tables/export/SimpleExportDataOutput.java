package delta.common.ui.swing.tables.export;

import java.io.PrintStream;

/**
 * Simple implementation of the export data output.
 * @author DAM
 */
public class SimpleExportDataOutput implements ExportDataOutput
{
  private PrintStream _out;

  /**
   * Constructor.
   * @param out Output stream.
   */
  public SimpleExportDataOutput(PrintStream out)
  {
    _out=out;
  }

  @Override
  public void writeData(String[] data)
  {
    int index=0;
    for(String cell : data)
    {
      if (index>0)
      {
        _out.print('\t');
      }
      if (cell!=null)
      {
        _out.print(cell);
      }
      index++;
    }
    _out.println();
  }
}
