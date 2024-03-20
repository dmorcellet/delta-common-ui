package delta.common.ui.swing.labels;

import java.util.Arrays;

import junit.framework.TestCase;

/**
 * Tests for class {@link SplitIntoLines}.
 * @author DAM
 */
public class SplitIntoLinesTest extends TestCase
{
  private void testASample(String text, int linesCount)
  {
    System.out.println(text+" ("+linesCount+")");
    String[] ret=SplitIntoLines.split(text,linesCount);
    System.out.println(" => "+Arrays.toString(ret));
  }

  /**
   * Test method for {@link delta.common.ui.swing.labels.SplitIntoLines#split(java.lang.String, int)}.
   */
  public void testSplit()
  {
    testASample("Reforged Burglar's Sword of the Second Age",3);
    testASample("",1);
    testASample("",2);
    testASample("Toto",2);
    testASample("Toto titi",1);
    testASample("Toto titi tata",2);
    testASample("Toto titi tata",1);
  }
}
