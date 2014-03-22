/**
 * 
 */
package org.eclipselabs.xtend.xtras.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.eclipselabs.xtend.xtras.javagen.holder.ClassHolder;
import org.eclipselabs.xtend.xtras.javagen.holder.FileHolder;
import org.eclipselabs.xtend.xtras.javagen.holder.ImportsHolder;
import org.eclipselabs.xtend.xtras.javagen.util.SourceCodeUtility;
import org.junit.Test;

import com.google.common.io.Files;

/**
 * JUnit Tests for {@link SourceCodeUtility}
 * 
 * @author jbr
 */
public class SourceCodeUtilityTest {

  @Test
  public void test() {
    ClassHolder declaringClass = new ClassHolder("aa.bbbb.cc", "MyClass");
    File srcFolder = Files.createTempDir();
    String content = "public class MyClass {}";
    ImportsHolder imports = new ImportsHolder(declaringClass);
    imports.useClass(new ClassHolder("xx.yyyy.zz", "XClass"));
    FileHolder actual = SourceCodeUtility.createSourceFile(declaringClass, imports, content, srcFolder);

    assertEquals(new File(new File(new File(new File(srcFolder, "aa"), "bbbb"), "cc"), "MyClass.java"), actual.getFile());
    assertTrue(actual.getContent().startsWith("package aa.bbbb.cc;"));
    assertTrue(actual.getContent().contains("import xx.yyyy.zz.XClass;"));
    assertTrue(actual.getContent().endsWith("public class MyClass {}"));
  }

}
