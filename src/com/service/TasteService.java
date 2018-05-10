package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.TasteMapper;
import com.pojo.Taste;

@Service
public class TasteService {

	@Autowired
	private TasteMapper tasteMapper;
	
	public List<Taste> tasteList() {
		return tasteMapper.tasteList();
	}

	public int insertTaste(Taste taste) {
		return tasteMapper.insertTaste(taste);
	}

	public void updateTaste(Taste taste) {
		tasteMapper.updateTaste(taste);
	}
	
	public void deleteTaste(int id) {
		tasteMapper.deleteTaste(id);
	}

	public Taste getTaste(int id) {
		return tasteMapper.getTaste(id);
	}
}
