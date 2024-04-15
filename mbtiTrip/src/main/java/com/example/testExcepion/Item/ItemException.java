package com.example.testExcepion.Item;

import java.util.regex.Pattern;

import com.example.test.item.DTO.ItemDTO;

import lombok.Getter;

@Getter
public class ItemException extends RuntimeException{

		private final ItemExceptionEnum itemExceptionEnum;
		
		public ItemException(ItemExceptionEnum itemExceptionEnum) {
			super(itemExceptionEnum.getMessage());
			this.itemExceptionEnum = itemExceptionEnum;
		}
		
		
		public static void validationItem(ItemDTO itemDTO) {
			//TITLE ck
			if(itemDTO.getItemName() == null) {
				throw new ItemException(ItemExceptionEnum.ITEM_TITLE_MISMATCH);
			}
			if(itemDTO.getItemName().length()>20) {
				throw new ItemException(ItemExceptionEnum.ITEM_TITLE_SIZEMISS);
			}
			boolean title = titleCk(itemDTO);
			if(title) {
				throw new ItemException(ItemExceptionEnum.ITEM_TITLE_VALIDTION);
			}
			if(itemDTO.getType() == null) {
				throw new ItemException(ItemExceptionEnum.ITEM_INFORMATION_MISSING);	
			}
			if(itemDTO.getUsername() == null) {
				throw new ItemException(ItemExceptionEnum.ITEM_USER_NOT_FOUND);
			}
			if(itemDTO.getPrice() == null) {
				throw new ItemException(ItemExceptionEnum.ITEM_INFORMATION_MISSING);	
			}
			if(itemDTO.getLocation() == null) {
				throw new ItemException(ItemExceptionEnum.ITEM_INFORMATION_MISSING);	
			}
			if(itemDTO.getTel() == null) {
				throw new ItemException(ItemExceptionEnum.ITEM_INFORMATION_MISSING);	
			}
			if(itemDTO.getContents() == null) {
				throw new ItemException(ItemExceptionEnum.ITEM_INFORMATION_MISSING);	
			}
			if(itemDTO.getContents().length() > 1000) {
				throw new ItemException(ItemExceptionEnum.ITEM_CONTENTS_SIZEMISS);
			}
		}
		
		
		private static boolean titleCk(ItemDTO itemDTO) {
			String itemName = itemDTO.getItemName();
			boolean valid = Pattern.matches("^[a-zA-Z0-9가-힣]*$", itemName);
			
			return !valid;
			
		}
}
