package com.example.testExcepion.Item;

import java.util.regex.Pattern;

import com.example.test.item.DTO.ItemDTO;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
public class ItemException extends RuntimeException{

		private final ItemExceptionEnum itemExceptionEnum;
		
		public ItemException(ItemExceptionEnum itemExceptionEnum) {
			super(itemExceptionEnum.getMessage());
			this.itemExceptionEnum = itemExceptionEnum;
		}
		
		
		/*
		 * Exception 반응 테스트 완료  4.15 작업자 신성진
		 * */
		public static void validationItem(ItemDTO itemDTO) {
			//TITLE ck
			if(itemDTO.getItemName() == null) {
				throw new ItemException(ItemExceptionEnum.ITEM_TITLE_MISMATCH);
			}
			if(itemDTO.getItemName().length()>15) {
				throw new ItemException(ItemExceptionEnum.ITEM_TITLE_SIZEMISS);
			}
			boolean title = titleCk(itemDTO);
			if(title) {
				throw new ItemException(ItemExceptionEnum.ITEM_TITLE_VALIDTION);
			}
			if(itemDTO.getType() == null) {
				throw new ItemException(ItemExceptionEnum.ITEM_TYPE_MISS);	
			}
			if(itemDTO.getUsername() == null) {
				throw new ItemException(ItemExceptionEnum.ITEM_USER_NOT_FOUND);
			}
			if(itemDTO.getPrice() == null) {
				throw new ItemException(ItemExceptionEnum.ITEM_PRICE_MISS);	
			}
			log.info("getPrice");
			if(itemDTO.getLocation() == null) {
				throw new ItemException(ItemExceptionEnum.ITEM_UNKNOWN_LOCATION);	
			}
			log.info("getLocation");
			if(itemDTO.getTel() == null) {
				throw new ItemException(ItemExceptionEnum.ITEM_PRICE_MISS);	
			}
			log.info("getTel");
			if(itemDTO.getContents() == null) {
				throw new ItemException(ItemExceptionEnum.ITEM_UNKNOWN_ADMINTEL);	
			}
			log.info("getContents.null");
			if(itemDTO.getContents().length() > 1000) {
				throw new ItemException(ItemExceptionEnum.ITEM_CONTENTS_SIZEMISS);
			}
			log.info("getContents");
		}
		
		
		private static boolean titleCk(ItemDTO itemDTO) {
			String itemName = itemDTO.getItemName();
			boolean valid = Pattern.matches("^[a-zA-Z0-9가-힣]*$", itemName);
			return !valid;
			
		}
}
