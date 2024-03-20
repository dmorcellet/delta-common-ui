package delta.common.ui.swing.labels;

import java.util.Arrays;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Tests for class {@link SplitIntoLines}.
 * @author DAM
 */
public class SplitIntoLinesTest extends TestCase
{
  private void testASample(String text, int linesCount, String... expected)
  {
    System.out.println(text+" ("+linesCount+")");
    String[] ret=SplitIntoLines.split(text,linesCount);
    System.out.println(" => "+Arrays.toString(ret));
    Assert.assertEquals(expected.length,ret.length);
    for(int i=0;i<expected.length;i++)
    {
      Assert.assertEquals(expected[i],ret[i]);
    }
  }

  /**
   * Test method for {@link delta.common.ui.swing.labels.SplitIntoLines#split(java.lang.String, int)}.
   */
  public void testSplit()
  {
    testASample("Reforged Burglar's Sword of the Second Age",3,"Reforged Burglar's", "Sword of the", "Second Age");
    testASample("",1,"");
    testASample("",2,"");
    testASample("Toto",2,"Toto");
    testASample("Toto titi",1,"Toto titi");
    testASample("Toto titi tata",2,"Toto", "titi tata");
    testASample("Toto titi tata",1,"Toto titi tata");
  }
}
