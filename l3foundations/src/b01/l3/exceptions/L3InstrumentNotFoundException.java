/*
 * Created on Jun 1, 2006
 */
package b01.l3.exceptions;

/**
 * @author 01Barmaja
 */
public class L3InstrumentNotFoundException extends L3Exception {

  /**
   * Comment for <code>serialVersionUID</code>
   */
  private static final long serialVersionUID = 3977858462653101112L;
  
  /**
   * @param message
   * @param cause
   */
  public L3InstrumentNotFoundException(String message, Throwable cause) {
    super(message, cause);
    // TODO Auto-generated constructor stub
  }
  /**
   * @param cause
   */
  public L3InstrumentNotFoundException(Throwable cause) {
    super(cause);
    // TODO Auto-generated constructor stub
  }
  /**
   * 
   */
  public L3InstrumentNotFoundException() {
    super();
    // TODO Auto-generated constructor stub
  }
  /**
   * @param message
   */
  public L3InstrumentNotFoundException(String message) {
    super(message);
    // TODO Auto-generated constructor stub
  }
}
