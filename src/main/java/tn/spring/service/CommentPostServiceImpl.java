package tn.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.entity.CommentPost;
import tn.spring.entity.Posts;
import tn.spring.repository.CommentPostRepository;
import tn.spring.repository.PostRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CommentPostServiceImpl implements ICommentPostService {

    @Autowired
    CommentPostRepository commentRepo;
    @Autowired
    PostRepository postRepo;
   // @Autowired
   // UserRepository userRepo;

    @Override
    public CommentPost createAndAffectCommentToPost(CommentPost cp, Long idPost) {

    	System.out.print("in method create And Effect Comment to Post");
        Posts p = postRepo.findById(idPost).orElse(null);
        p.getComments().add(cp);
        cp.setPosts(p);
        p.setNbComment(+1);
        System.out.print("post :"+idPost);
        System.out.print("cp :"+cp.toString());

        Timestamp ts=new Timestamp(System.currentTimeMillis());
        Date date=new Date(ts.getTime());

        cp.setCommentDate(date);


        return commentRepo.save(cp);

    }

    @Override
    public List<CommentPost> getCommentByPostId(Long id) {
        Posts p = postRepo.findById(id).orElse(null);
        List<CommentPost> comments = p.getComments();
        return comments;
    }

    @Override
    public void deleteComment(Long id) {

    	System.out.print("In Method Delete comment");
    	System.out.print("Im gonna delete the comment");
        commentRepo.deleteById(id);
        System.out.print("Out Of Method delete Comment succefully");
    }

    @Override
    public CommentPost updateComment(CommentPost cp) {

        return commentRepo.save(cp);
    }

    public CommentPost getCommentById(Long id) {
        return commentRepo.findById(id).orElse(null);
    }

    public int Like(Long id){

        CommentPost p= commentRepo.findById(id).orElse(null);
        int likes=p.getLikes();
        return likes+1;

    }

    public int dislike(Long id){
        CommentPost p= commentRepo.findById(id).orElse(null);
        int dislikes=p.getDislikes();
        return dislikes+1;

    }

}

