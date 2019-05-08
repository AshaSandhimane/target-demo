package com.infinity.target.targetdemo.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infinity.target.targetdemo.bean.Comment;
import com.infinity.target.targetdemo.service.impl.TargetCommentServiceImpl;

@RestController
@RequestMapping(value = "/target")
public class TargetCommentsController {

	@Autowired
	TargetCommentServiceImpl targetCommentServiceImpl;

	@RequestMapping(method =RequestMethod.POST,value = "/{productId}/postComment")
	public ResponseEntity<String> postComment(@Valid @RequestBody Comment comment, @PathVariable Long productId )
	{
		ResponseEntity<String> response=null;
		boolean CommentResponse;

		CommentResponse = targetCommentServiceImpl.saveComments(comment,productId);
		if(CommentResponse)
		{ 
			response=new ResponseEntity<String>("Dear Customer,Comment posted successfully", HttpStatus.CREATED);
		} 
		else
		{
			response=new ResponseEntity<String>("Dear Customer,please porvide the valid comments", HttpStatus.BAD_REQUEST);
		}
		return response;
	}

}
