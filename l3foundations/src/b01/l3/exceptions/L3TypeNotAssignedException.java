/*
 * Created on Jun 1, 2006
 */
package b01.l3.exceptions;

/**
 * @author 01Barmaja
 */
public class L3TypeNotAssignedException extends L3Exception {

  /**
   * Comment for <code>serialVersionUID</code>
   */
  private static final long serialVersionUID = 3977858462653101112L;
  
  /**
   * @param message
   * @param cause
   */
  public L3TypeNotAssignedException(String message, Throwable cause) {
    super(message, cause);
    // TODO Auto-generated constructor stub
  }
  /**
   * @param cause
   */
  public L3TypeNotAssignedException(Throwable cause) {
    super(cause);
    // TODO Auto-generated constructor stub
  }
  /**
   * 
   */
  public L3TypeNotAssignedException() {
    super();
    // TODO Auto-generated constructor stub
  }
  /**
   * @param message
   */
  public L3TypeNotAssignedException(String message) {
    super(message);
    // TODO Auto-generated constructor stub
  }
}
