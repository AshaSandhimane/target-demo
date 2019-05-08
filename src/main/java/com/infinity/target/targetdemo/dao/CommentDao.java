package com.infinity.target.targetdemo.dao;

import com.infinity.target.targetdemo.bean.Comment;

public interface CommentDao {

	public void save(Comment comment);
	public Comment get(int commentId);
	public void delete(int commentId);

}
