package exception;

/**
 * Function:
 * 业务异常 exception
 * @author wenzeng
 * @date 2023/6/13
 */
public class BizException extends RuntimeException{

    private String message;

    private String code;


    public BizException(String msg) {
        this.message = msg;
    }


}
