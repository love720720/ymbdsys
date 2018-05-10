package com.mapper;

import java.util.List;

import com.pojo.Taste;

public interface TasteMapper {

	public List<Taste> tasteList();
	
	public int insertTaste(Taste taste);

	public void updateTaste(Taste taste);

	public void deleteTaste(int id);

	public Taste getTaste(int id);
}
