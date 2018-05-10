package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.ClassifyMapper;
import com.pojo.Classify;

@Service
public class ClassifyService {

	@Autowired
	private ClassifyMapper classifyMapper;
	
	public List<Classify> classifyList() {
		return classifyMapper.classifyList();
	}

	public int insertClassify(Classify classify) {
		return classifyMapper.insertClassify(classify);
	}

	public void updateClassify(Classify classify) {
		classifyMapper.updateClassify(classify);
	}
	
	public void deleteClassify(int id) {
		classifyMapper.deleteClassify(id);
	}

	public Classify getClassify(int id) {
		return classifyMapper.getClassify(id);
	}
}
