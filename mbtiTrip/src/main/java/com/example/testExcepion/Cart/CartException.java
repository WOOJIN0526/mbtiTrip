package com.example.testExcepion.Cart;

public class CartException extends RuntimeException {
	
	private final CartExceptionEnum cartExceptionEnum;
	
	public CartException(CartExceptionEnum cartExceptionEnum) {
		super(cartExceptionEnum.getMessage());
		this.cartExceptionEnum = cartExceptionEnum;
	}
}
