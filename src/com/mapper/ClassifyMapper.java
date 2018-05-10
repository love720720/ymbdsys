package com.mapper;

import java.util.List;

import com.pojo.Classify;

public interface ClassifyMapper {

	public List<Classify> classifyList();

	public int insertClassify(Classify classify);

	public void updateClassify(Classify classify);

	public void deleteClassify(int id);

	public Classify getClassify(int id);
}
