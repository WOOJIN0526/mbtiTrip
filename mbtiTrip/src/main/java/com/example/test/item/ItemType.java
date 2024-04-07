package com.example.test.item;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ItemType {
	replace, adventure
}

/*
// ex replace 장소 등록시 
// RequestMapping(value = "replace/Create", methodPost)
// ~~~~Create(~~~ ItemDTO){
 * 	itemDto.setType(ItemType.repalce)
 */