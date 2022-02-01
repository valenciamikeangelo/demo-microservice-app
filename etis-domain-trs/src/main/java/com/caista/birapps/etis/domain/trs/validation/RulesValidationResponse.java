/*
 * Modified by: fuentem
 * Last updated: Oct 17, 2018 2:53:20 PM
 */
package com.caista.birapps.etis.domain.trs.validation;

import java.util.ArrayList;
import java.util.List;

public class RulesValidationResponse {

  private List<String> messages;


  public RulesValidationResponse() {
    this.messages = new ArrayList<String>();
  }


  /**
   * @return the messages
   */
  public List<String> getMessages() {
    return messages;
  }

}
