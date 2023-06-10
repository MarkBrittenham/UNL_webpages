/* This applet's main purpose is to provide a Pascal's Triangle in HTML
   function. */
      
import java.lang.*;

public class pascaltri extends java.applet.Applet {
  public static final String VERSION = "v0.14";
  
  // --- applet methods ---
  public void init() {
    System.out.println(VERSION);
  }
  
  public void start() {
    System.out.println(pascaltri(50));
  }

  // --- main code ---
  // used by function below
  public String right(String i, int n) {
    return i.substring(i.length() - n);
  }

  // outputs a HTML page of Pascal's triangle, up to and including row rows,
  // bolding even numbers
  
  public String pascaltri(String rows) { 
    return pascaltri((new Integer(rows)).intValue()); 
  }
  
  public String pascaltri(int rows) { 
    int buffer_length_guess = rows * (rows - 1) * 3             // 3 chars for each item 
                            + rows * 5     					    // row overhead
                            + 70								// fixed overhead + slack
                            + rows * (1 + rows / 10) * 9;        // number of <b>..</b> runs
    StringBuffer t = new StringBuffer(buffer_length_guess);                                    
    
    int x[] = new int[rows+1];           // the current array 
    int i, j, z;                         // i, j, z are loop vars and locals.
    boolean have_b = false, need_b = false;  // falgs for the bolding tags
    String h;
    
    t.append("<title> Pascal's triangle from Java </title>\n<center><pre>\n");
    for (i=0; i<=rows; i++) {  // the first row, with one element, is row 0. 
	  // calculate for the level
      x[0] = 1; x[i] = 1;
      for (j=i-1; j>0; j--) x[j] += x[j-1];
      // make the HTML
      for (j=0; j<=i; j++) {  // j over [0,i]
        z = x[j];                  
        h = right("0"+z+" ", 3);   // h is what we are adding to the string
        need_b = ((z & 1) == 0);   // need_b iff z is even
        if (need_b != have_b) {
          t.append(need_b ? "<b>" : "</b>");
          have_b = need_b;
        }
        t.append(h);        
      }
	  t.append("\n");
    }
    t.append("</pre></center>\n");
    return t.toString();
  }
  
}