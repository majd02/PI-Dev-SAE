package tn.spring.service;

import tn.spring.entity.CommentPost;

import java.util.List;

public interface ICommentPostService {

    public CommentPost createAndAffectCommentToPost(CommentPost cp, Long id);
    public List<CommentPost> getCommentByPostId(Long id);
    public CommentPost updateComment(CommentPost cp);
    public void deleteComment(Long id);
}
