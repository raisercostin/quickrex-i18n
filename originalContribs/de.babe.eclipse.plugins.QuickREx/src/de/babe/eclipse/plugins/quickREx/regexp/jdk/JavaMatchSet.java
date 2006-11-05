/*******************************************************************************
 * Copyright (c) 2005 Bastian Bergerhoff and others
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution.
 * 
 * Contributors:
 *     Bastian Bergerhoff - initial API and implementation
 *     Andreas Studer - Contributions to handling global flags
 *******************************************************************************/
package de.babe.eclipse.plugins.quickREx.regexp.jdk;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.babe.eclipse.plugins.quickREx.regexp.Flag;
import de.babe.eclipse.plugins.quickREx.regexp.MatchSet;

/**
 * MatchSet using JDK-regular expressions.
 * 
 * @author bastian.bergerhoff, andreas.studer
 */
public class JavaMatchSet implements MatchSet {

  private final Pattern pattern;
  private final Matcher matcher;

  private final static Collection flags = new Vector();
  
  static {
    flags.add(JavaFlag.JDK_CANON_EQ);
    flags.add(JavaFlag.JDK_CASE_INSENSITIVE);
    flags.add(JavaFlag.JDK_COMMENTS);
    flags.add(JavaFlag.JDK_DOTALL);
    flags.add(JavaFlag.JDK_MULTILINE);
    flags.add(JavaFlag.JDK_UNICODE_CASE);
    flags.add(JavaFlag.JDK_UNIX_LINES);
  }
  
  /**
   * Returns a Collection of all Compiler-Flags the JDK-implementation
   * knows about.
   * 
   * @return a Collection of all Compiler-Flags the JDK-implementation
   * knows about
   */
  public static Collection getAllFlags() {
    return flags;
  }
  
  /**
   * The constructor - uses JDK-regular expressions
   * to evaluate the passed regular expression against
   * the passed text.
   * 
   * @param regExp the regular expression
   * @param text the text to evaluate regExp against
   * @param flags a Collection of Flags to pass to the Compiler
   */
  public JavaMatchSet(String regExp, String text, Collection flags) {
    int iFlags = 0;
    for (Iterator iter = flags.iterator(); iter.hasNext();) {
      Flag element = (Flag)iter.next();
      iFlags = iFlags | element.getFlag();
    }
    pattern = Pattern.compile(regExp, iFlags);
    matcher = pattern.matcher(text);
  }

  /* (non-Javadoc)
   * @see de.babe.eclipse.plugins.quickREx.regexp.MatchSet#nextMatch()
   */
  public boolean nextMatch() {
    return matcher.find();
  }

  /* (non-Javadoc)
   * @see de.babe.eclipse.plugins.quickREx.regexp.MatchSet#start()
   */
  public int start() {
    return matcher.start();
  }

  /* (non-Javadoc)
   * @see de.babe.eclipse.plugins.quickREx.regexp.MatchSet#end()
   */
  public int end() {
    return matcher.end();
  }

  /* (non-Javadoc)
   * @see de.babe.eclipse.plugins.quickREx.regexp.MatchSet#groupCount()
   */
  public int groupCount() {
    return matcher.groupCount();
  }

  /* (non-Javadoc)
   * @see de.babe.eclipse.plugins.quickREx.regexp.MatchSet#groupContents(int)
   */
  public String groupContents(int groupIndex) {
    return matcher.group(groupIndex);
  }

  /* (non-Javadoc)
   * @see de.babe.eclipse.plugins.quickREx.regexp.MatchSet#groupStart(int)
   */
  public int groupStart(int groupIndex) {
    return matcher.start(groupIndex);
  }

  /* (non-Javadoc)
   * @see de.babe.eclipse.plugins.quickREx.regexp.MatchSet#groupEnd(int)
   */
  public int groupEnd(int groupIndex) {
    return matcher.end(groupIndex);
  }
}
