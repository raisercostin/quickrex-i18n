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
public class CompletionTriggerWord extends CompletionTrigger {

  public final static String INSTANCE_QNAME = "wordtrigger"; 
  public final static String WORD_ATTRIBUTE_QNAME = "word";
  
  private final String word;
  private final String extension;
  private final String plainProposal;

  /**
   * @param p_word
   */
  public CompletionTriggerWord(String p_word, String p_extendWith, String p_plainProposal) {
    this.word = p_word;
    this.extension = p_extendWith;
    this.plainProposal = p_plainProposal;
  }

  
  /*package*/ String getWord() {
    return this.word;
  }
  
  /*package*/ String getPlainProposal() {
    return this.plainProposal;
  }
  
  /*package*/ String getExtension() {
    return this.extension;
  }
    
  /* (non-Javadoc)
   * @see de.babe.eclipse.plugins.quickREx.regexp.CompletionTrigger#isMatchFor(java.lang.String)
   */
  public boolean isMatchFor(String text) {
    for (int i=0; i<this.word.length(); i++) {
      if (text.endsWith(this.word.substring(0,this.word.length()-i))) {
        return true;
      }
    }
    return false;
  }

  /* (non-Javadoc)
   * @see de.babe.eclipse.plugins.quickREx.regexp.CompletionTrigger#getInsertString(java.lang.String)
   */
  public String getInsertString(String text) {
    String wordRemainder = null;
    for (int i=0; i<this.word.length(); i++) {
      if (text.endsWith(this.word.substring(0,this.word.length()-i))) {
        wordRemainder = this.word.substring(this.word.length()-i);
        break;
      }
    }
    return wordRemainder+this.extension;
  }

  /* (non-Javadoc)
   * @see de.babe.eclipse.plugins.quickREx.regexp.CompletionTrigger#getInsertString()
   */
  public String getInsertString() {
    return this.getInsertString(this.text);
  }

  /* (non-Javadoc)
   * @see de.babe.eclipse.plugins.quickREx.regexp.CompletionTrigger#compareTo(de.babe.eclipse.plugins.quickREx.regexp.CompletionTrigger)
   */
  public int compareTo(CompletionTrigger p_other) {
    // Always prefer WordCompletions unless the standard proposal starts with a \ and only wants to add one character
    if (p_other instanceof CompletionTriggerExpression) {
      if ((p_other.getPlainProposal().startsWith(("\\")) || p_other.getPlainProposal().startsWith(("(")) || p_other.getPlainProposal().startsWith(("[")))&& p_other.getInsertString().length() == 1) {
        return +1;
      } else {
        return -1;
      }
    }
    // The shorter the String to insert, the better...
    int thisLength = this.getInsertString().length();
    int otherLength = p_other.getInsertString().length();
    if (thisLength < otherLength) {
      return -1;
    } else {
      return +1;
    }
  }
}
