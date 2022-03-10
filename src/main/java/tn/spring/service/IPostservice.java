package tn.spring.service;


import org.json.JSONException;
import tn.spring.entity.BestAndWorstPost;
import tn.spring.entity.Posts;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface IPostservice {

    public Posts createPost(Posts p, String mail) throws JSONException, URISyntaxException, IOException;



    public BestAndWorstPost BestPost();
    public BestAndWorstPost WorstPost();

    Posts createPostAndAffectToUser(Posts p, String email);

    public Posts updatePost(Posts p);
    public List<Posts> getAllPosts();
    public Posts getPostById(Long id);
    public void deletePostById(Long id);
    public void likeAPost(Long idPost , Long id);
    public void dislikeAPost(Long idPost, Long id );
    public Posts createPostAndAffectToUserAndDepartement(Posts p,Long userId,Long depId) throws IOException;






}

