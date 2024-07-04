package com.minod.shop.exception;

public class NotEnoughStockException extends RuntimeException {
    // extends RuntimeException 으로 커스텀 예외 만들때, 아래처럼 override 메서드를 추가 해줘야 한다.<외울 필요는 없다.
    public NotEnoughStockException() {
        super();
    }

    public NotEnoughStockException(String message) {
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }

    // 얘는 없어도 된다.
//    protected NotEnoughStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//        super(message, cause, enableSuppression, writableStackTrace);
//    }
}
