/**
 * 
 */
package com.infinity.target.targetdemo.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.infinity.target.targetdemo.bean.Comment;
import com.infinity.target.targetdemo.dao.CommentDao;

@Repository
@Transactional
public class CommentDaoImpl implements CommentDao{



	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void save(Comment comment) {
		if(comment.getId()==null)
		{
			this.entityManager.persist(comment);
		}else
		{
			this.entityManager.merge(comment);
		}
	}

	@Override
	public Comment get(int commentId) {
		Comment comment = this.entityManager.find(Comment.class, commentId);
		return comment;
	}
	
	@Override
	public void delete(int commentId) {
		Comment comment=get(commentId);
		this.entityManager.remove(comment);
	}

}
