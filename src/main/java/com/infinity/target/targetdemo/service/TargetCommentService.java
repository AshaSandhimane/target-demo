package com.infinity.target.targetdemo.service;

import com.infinity.target.targetdemo.bean.Comment;

public interface TargetCommentService {

	boolean saveComments(Comment comment,Long productId);

}
