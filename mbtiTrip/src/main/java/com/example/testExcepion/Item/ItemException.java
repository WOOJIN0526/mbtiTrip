package com.example.testExcepion.Item;

import lombok.Getter;

@Getter
public class ItemException extends RuntimeException{

		private final ItemExceptionEnum itemExceptionEnum;
		
		public ItemException(ItemExceptionEnum itemExceptionEnum) {
			super(itemExceptionEnum.getMessage());
			this.itemExceptionEnum = itemExceptionEnum;
		}
}
