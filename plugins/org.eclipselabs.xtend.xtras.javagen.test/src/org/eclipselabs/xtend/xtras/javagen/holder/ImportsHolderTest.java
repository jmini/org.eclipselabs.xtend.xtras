package org.eclipselabs.xtend.xtras.javagen.holder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JUnit tests for {@link ImportsHolder}
 * 
 * @author jbr
 */
public class ImportsHolderTest {

  @Test
  public void testEmpty() {
    ClassHolder declaringClass = new ClassHolder("aa.bbbbb.ccc", "DeclaringClass");

    ImportsHolder importsHolder = new ImportsHolder(declaringClass);

    String imports = importsHolder.toText().toString();
    assertEquals("", imports.trim());
  }

  @Test
  public void testImport() {
    ClassHolder declaringClass = new ClassHolder("aa.bbbbb.ccc", "DeclaringClass");

    ImportsHolder importsHolder = new ImportsHolder(declaringClass);
    assertEquals("DeclaringClass", importsHolder.useClass(new ClassHolder("aa.bbbbb.ccc", "DeclaringClass")));

    //Import MyClass 2 times:
    assertEquals("MyClass", importsHolder.useClass(new ClassHolder("xx.yyyyy.zzz", "MyClass")));
    assertEquals("MyClass", importsHolder.useClass(new ClassHolder("xx.yyyyy.zzz", "MyClass")));

    //Test the conflict:
    assertEquals("SomeClass", importsHolder.useClass(new ClassHolder("aa.bbbbb.ccc", "SomeClass")));
    assertEquals("xx.yyyyy.zzz.SomeClass", importsHolder.useClass(new ClassHolder("xx.yyyyy.zzz", "SomeClass")));

    //Test java.lang:
    assertEquals("String", importsHolder.useClass(new ClassHolder("java.lang", "String")));
    assertEquals("Integer", importsHolder.useClass(new ClassHolder("java.lang", "Integer")));

    //Test no packageName:
    assertEquals("NoPackageNameClass", importsHolder.useClass(new ClassHolder(null, "NoPackageNameClass")));

    String imports = importsHolder.toText().toString();

    //Import DeclaringClass:
    assertFalse("no import for DeclaringClass", imports.contains("import aa.bbbbb.ccc.DeclaringClass;"));

    //Import MyClass:
    assertTrue("import for MyClass", imports.contains("import xx.yyyyy.zzz.MyClass;"));

    //Import SomeClass conflict:
    assertTrue("import for SomeClass (aa.bbbbb.ccc)", imports.contains("import aa.bbbbb.ccc.SomeClass;"));
    assertFalse("no import for SomeClass (xx.yyyyy.zzz)", imports.contains("import xx.yyyyy.zzz.SomeClass;"));

    //Import java.lang:
    assertFalse("no import for String", imports.contains("import java.lang.String;"));
    assertFalse("no import for Integer", imports.contains("import java.lang.Integer;"));

    //Import no packageName:
    assertFalse("no import for NoPackageNameClass", imports.contains("NoPackageNameClass"));
  }

  @Test
  public void testImportFullClassName() {
    ClassHolder declaringClass = new ClassHolder("aa.bbbbb.ccc", "DeclaringClass");
    ImportsHolder importsHolder = new ImportsHolder(declaringClass);

    assertEquals("DeclaringClass", importsHolder.useClass("aa.bbbbb.ccc.DeclaringClass"));
    assertEquals("MyClass", importsHolder.useClass("xx.yyyyy.zzz.MyClass"));
    assertEquals("String", importsHolder.useClass("java.lang.String"));
    assertEquals("WrongClass", importsHolder.useClass("WrongClass"));

    String imports = importsHolder.toText().toString();
    assertFalse("no import for DeclaringClass", imports.contains("import aa.bbbbb.ccc.DeclaringClass;"));
    assertTrue("import for MyClass", imports.contains("import xx.yyyyy.zzz.MyClass;"));
    assertFalse("no import for String", imports.contains("import java.lang.String;"));
    assertFalse("no import for WrongClass", imports.contains("WrongClass"));
  }

  @Test
  public void testImportGenerics0Param() {
    ClassHolder declaringClass = new ClassHolder("aa.bbbbb.ccc", "DeclaringClass");
    ImportsHolder importsHolder = new ImportsHolder(declaringClass);

    assertEquals("MyClass", importsHolder.useClassWithGenerics(new ClassHolder("xx.yyyyy.zzz", "MyClass")));

    String imports = importsHolder.toText().toString();

    assertTrue("import for MyClass", imports.contains("import xx.yyyyy.zzz.MyClass;"));
  }

  @Test
  public void testImportGenerics1Param() {
    ClassHolder declaringClass = new ClassHolder("aa.bbbbb.ccc", "DeclaringClass");
    ImportsHolder importsHolder = new ImportsHolder(declaringClass);

    assertEquals("MyClass<AClass>", importsHolder.useClassWithGenerics(new ClassHolder("xx.yyyyy.zzz", "MyClass"), new ClassHolder("xx.yyyyy.zzz", "AClass")));

    String imports = importsHolder.toText().toString();

    assertTrue("import for MyClass", imports.contains("import xx.yyyyy.zzz.MyClass;"));
    assertTrue("import for MyClass", imports.contains("import xx.yyyyy.zzz.AClass;"));
  }

  @Test
  public void testImportGenerics2Param() {
    ClassHolder declaringClass = new ClassHolder("aa.bbbbb.ccc", "DeclaringClass");
    ImportsHolder importsHolder = new ImportsHolder(declaringClass);

    assertEquals("MyClass<AClass, BClass>", importsHolder.useClassWithGenerics(new ClassHolder("xx.yyyyy.zzz", "MyClass"), new ClassHolder("xx.yyyyy.zzz", "AClass"), new ClassHolder("xx.yyyyy.zzz", "BClass")));

    String imports = importsHolder.toText().toString();

    assertTrue("import for MyClass", imports.contains("import xx.yyyyy.zzz.MyClass;"));
    assertTrue("import for MyClass", imports.contains("import xx.yyyyy.zzz.AClass;"));
    assertTrue("import for MyClass", imports.contains("import xx.yyyyy.zzz.BClass;"));
  }

  @Test
  public void testImportGenerics2ParamWithConflict() {
    ClassHolder declaringClass = new ClassHolder("aa.bbbbb.ccc", "DeclaringClass");
    ImportsHolder importsHolder = new ImportsHolder(declaringClass);

    assertEquals("MyClass<AClass, oo.ppppp.qqq.AClass>", importsHolder.useClassWithGenerics(new ClassHolder("xx.yyyyy.zzz", "MyClass"), new ClassHolder("xx.yyyyy.zzz", "AClass"), new ClassHolder("oo.ppppp.qqq", "AClass")));

    String imports = importsHolder.toText().toString();

    assertTrue("import for MyClass", imports.contains("import xx.yyyyy.zzz.MyClass;"));
    assertTrue("import for MyClass", imports.contains("import xx.yyyyy.zzz.AClass;"));
    assertFalse("import for MyClass", imports.contains("import oo.ppppp.qqq.AClass;"));
  }
}
