/*******************************************************************************
 * Copyright (c) 2005 Bastian Bergerhoff and others
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution.
 * 
 * Contributors:
 *     Bastian Bergerhoff - initial API and implementation
 *******************************************************************************/
package de.babe.eclipse.plugins.quickREx.regexp;

/**
 * @author bastian.bergerhoff
 */
public abstract class CompletionTrigger {

  public final static String COMPLETION_VALUE_ATTRIBUTE_QNAME = "completion";
  
  protected String text;
  
  public abstract boolean isMatchFor(String p_text);

  public abstract String getInsertString(String p_text);

  public abstract String getInsertString();

  public abstract int compareTo(CompletionTrigger p_other);

  /*package*/ abstract String getPlainProposal();
  
  /**
   * @param string
   */
  public void setText(String p_text) {
    this.text = p_text;
  }
}
