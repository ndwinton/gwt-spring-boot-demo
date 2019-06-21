package io.pivotal.demo;

import io.pivotal.demo.client.GwtDemoTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class GwtDemoSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for GwtDemo");
    suite.addTestSuite(GwtDemoTest.class);
    return suite;
  }
}
