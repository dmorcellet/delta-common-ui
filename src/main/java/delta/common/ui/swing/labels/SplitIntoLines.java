package delta.common.ui.swing.labels;

/**
 * Split text into a given number of lines.
 * @author DAM
 */
public class SplitIntoLines
{
  private double _bestValue=10000;
  private String[] _bestLines=null;
  private String[] _words;
  private int[] _nbWordsPerLine;

  /**
   * Split the given text into lines.
   * @param text Text to split.
   * @param lineCount Maximum number of lines.
   * @return the result lines.
   */
  public static String[] split(String text, int lineCount)
  {
    if (text.length()==0)
    {
      return new String[]{""};
    }
    String[] words=text.split(" ");
    final int nbWords=words.length;
    if (nbWords==1)
    {
      return new String[]{text};
    }
    SplitIntoLines split=new SplitIntoLines(words,lineCount);
    return split.doIt();
  }

  private SplitIntoLines(String[] words, int lineCount)
  {
    _words=words;
    _nbWordsPerLine=new int[lineCount];
  }

  private String[] doIt()
  {
    eval(0,_words.length);
    return _bestLines;
  }

  private void useDecomposition(String[] lines)
  {
    double value=evaluateDecomposition(lines);
    if (_bestLines==null)
    {
      _bestValue=value;
      _bestLines=lines;
    }
    else
    {
      if (value<_bestValue)
      {
        _bestValue=value;
        _bestLines=lines;
      }
    }
  }

  private void eval(int startIndex, int wordsLeft)
  {
    if (startIndex==_nbWordsPerLine.length-1)
    {
      _nbWordsPerLine[_nbWordsPerLine.length-1]=wordsLeft;
      String[] lines=splitTextInLines(_words,_nbWordsPerLine);
      useDecomposition(lines);
    }
    else
    {
      for(int i=1;i<=wordsLeft-1;i++)
      {
        _nbWordsPerLine[startIndex]=i;
        eval(startIndex+1,wordsLeft-i);
      }
    }
  }

  private static String[] splitTextInLines(String[] words, int[] nbWordsPerLine)
  {
    int nbLines=nbWordsPerLine.length;
    String[] ret=new String[nbLines];
    int wordIndex=0;
    for(int i=0;i<nbLines;i++)
    {
      StringBuilder sb=new StringBuilder();
      for(int j=0;j<nbWordsPerLine[i];j++)
      {
        if (sb.length()>0) sb.append(' ');
        sb.append(words[wordIndex]);
        wordIndex++;
      }
      ret[i]=sb.toString();
    }
    return ret;
  }

  private double evaluateDecomposition(String[] lines)
  {
    double ret=0;
    int nbLines=lines.length;
    double[] widths=new double[nbLines];
    double totalWidth=0;
    int index=0;
    for(String line : lines)
    {
      widths[index]=line.length();
      totalWidth+=widths[index];
      index++;
    }
    double mean=totalWidth/nbLines;
    for(int i=0;i<nbLines;i++)
    {
      ret+=Math.abs(widths[i]-mean);
    }
    return ret;
  }
}
