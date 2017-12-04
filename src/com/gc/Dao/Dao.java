package com.gc.Dao;

import java.util.List;

import com.gc.dto.ItemDto;

public interface Dao {
	
	//add/insert/create
		public void insert(ItemDto dto);
		
		//read/List
		public List<ItemDto> listAll(); 
		
		//update
		public void update(ItemDto dto);
		
		//delete
		public void delete(ItemDto dto);

		//get item by id
		public ItemDto listById(int valueOf);

}
