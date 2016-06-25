package com.zhangzhihao.SpringMVCSeedProject.Service;

import com.zhangzhihao.SpringMVCSeedProject.Model.Teacher;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class TeacherService extends BaseService<Teacher> {
	/**
	 * 保存对象并返回自己(带id)
	 *
	 * @param model 需要添加的对象
	 * @return 实体
	 */
	public Teacher saveAndGetTeacher(@NotNull final Teacher model) {
		model.setId(this.saveAndGetIntegerID(model));
		return model;
	}
}
