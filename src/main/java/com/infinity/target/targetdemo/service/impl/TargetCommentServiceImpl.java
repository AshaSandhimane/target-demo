package com.infinity.target.targetdemo.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infinity.target.targetdemo.bean.Comment;
import com.infinity.target.targetdemo.dao.CommentDao;
import com.infinity.target.targetdemo.service.TargetCommentService;

@Service
public class TargetCommentServiceImpl implements TargetCommentService{

	Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	CommentDao commentDao;

	@Override
	public boolean saveComments(Comment comment,Long productId) {
		Properties prop = new Properties();
		boolean valid=false;

		InputStream input = null;
		try {
			input = ClassLoader.getSystemClassLoader().getResourceAsStream("CommentsList.properties");
			prop.load(input);
			Set<String> keys = prop.stringPropertyNames();
			boolean isValid=isObjectionalComment(keys,comment.getCommentData().toLowerCase());
			if(!isValid)
			{
				comment.setProductId(productId);
				commentDao.save(comment);
				valid=true;
			}else
			{
				valid=false;
			}

		} catch (FileNotFoundException exception) {
			log.error("error while reading the property file", exception.getMessage(),exception);
		} catch (IOException exception) {
			log.error("error while fetching the property file", exception.getMessage(),exception);
		}
		return valid;
	}
	private boolean isObjectionalComment(Set<String> keys, String comment)
	{
		for(String key :keys)
		{
			 String value = "\\b"+key+"\\b";
	         Pattern pattern=Pattern.compile(value);
	         Matcher matcher=pattern.matcher(comment);
	         if(matcher.find())
	         {
	         return true;
	         }
		}
		return false;
	}
}
