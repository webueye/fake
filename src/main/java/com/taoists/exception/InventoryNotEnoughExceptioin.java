package com.taoists.exception;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-2
 */
@SuppressWarnings("serial")
public class InventoryNotEnoughExceptioin extends RuntimeException {

	public InventoryNotEnoughExceptioin() {
		super();
	}

	public InventoryNotEnoughExceptioin(String message) {
		super(message);
	}

}
