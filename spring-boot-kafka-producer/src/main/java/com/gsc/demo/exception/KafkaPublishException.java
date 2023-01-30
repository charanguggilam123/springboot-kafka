package com.gsc.demo.exception;

public class KafkaPublishException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 9102296045935587324L;

  public KafkaPublishException(String message) {
    super(message);
  }

  public KafkaPublishException(Throwable ex) {
    super(ex);
  }

  public KafkaPublishException(String message, Throwable ex) {
    super(message, ex);
  }

}
