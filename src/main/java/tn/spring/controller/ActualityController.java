package tn.spring.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.spring.entity.AppUser;
import tn.spring.entity.BestAndWorstPost;
import tn.spring.entity.CommentPost;
import tn.spring.entity.Posts;
import tn.spring.repository.DemandeRepository;
import tn.spring.repository.PostRepository;
import tn.spring.repository.UserRepository;
import tn.spring.service.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/Actuality")
public class ActualityController {
    @Autowired
    PostServiceImpl postService;
    @Autowired
    UserRepository userRepo;
    @Autowired
    CommentPostServiceImpl commentService;
    @Autowired
    DemandeRepository DR;
    //http://localhost:8089/SpringMVCMajd/Actuality/create-post
    @PostMapping("/create-post")
    public Posts createPost(@RequestBody Posts post,Principal principal ) throws JSONException, URISyntaxException, IOException {


        String  emaill = principal.getName();


        return postService.createPost(post,emaill);
    }

    @PostMapping("/create-full-post")
    public Posts createPostAndAffectToUser(@RequestBody Posts p)throws JSONException, URISyntaxException, IOException{
        Principal principal = null;
        String path = p.getImagePost();

        String resp =  ImageAnalysisQuickstart.GetAnalyze(path);

        p.setAnalysis(resp);
        String respp =  ImageAnalysisQuickstart.GetAnalyze(path);

        String  emaill = principal.getName();
        Long i= DR.jibid(emaill);
        AppUser us = userRepo.findById(i).orElse(null);
        p.setUser(us);
        return postService.createPostAndAffectToUser(p, emaill);
    }

    // http://localhost:8089/SpringMVCMajd/Actuality/create-full-post/1
    @PostMapping("/create-full-post/{dep-id}")
    public Posts createPostAndAffectToDeparmentAndUser( @RequestBody Posts p, @PathVariable(name="dep-id")Long depId,Principal principal) throws IOException {
        //log.info("departement :"+userId.toString());

        //log.info("departement :"+userId.toString());
        String path = p.getImagePost();

        String resp =  ImageAnalysisQuickstart.GetAnalyze(path);

        String  email = principal.getName();




        p.setAnalysis(resp);
        return postService.createPostAndAffectToUserAndDepartement(p, email, depId);

    }


    //http://localhost:8089/SpringMVCMajd/Actuality/share-post/2
    @PostMapping("/share-post/{postId}")
    public String Share(@PathVariable("postId")Long postId){
    Posts p = this.postService.getPostById(postId);
    String image=p.getImagePost();
    String cont=p.getContent();
    String UploadLink= AzureStorage.Storage(image);
    String res= SocialMedia.Getsharelink(UploadLink,cont);
    
    return res ;
}









    //http://localhost:8089/SpringMVCMajd/Actuality/update-post/2
    @PutMapping("/update-post/{postId}")
    public Posts updatePost(@PathVariable("postId") Long postId){
        Posts p = this.postService.getPostById(postId);
        postService.updatePost(p);
        return p;
    }
    //http://localhost:8089/SpringMVCMajd/Actuality/get-all-posts
    @GetMapping("/get-all-posts")
    public List<Posts> getAll(){
        List<Posts> listPosts = postService.getAllPosts();
        return listPosts;
    }

    //http://localhost:8089/SpringMVCMajd/Actuality/get-post-by-id/2
    @GetMapping("/get-post-by-id/{post-id}")
    public Posts getPostById(@PathVariable("post-id") Long id){
        Posts p=postService.getPostById(id);
        return p;
    }
    //http://localhost:8089/SpringMVCMajd/Actuality/delete-post/2
    @DeleteMapping("/delete-post/{post-id}")
    public void deletePost(@PathVariable("post-id")Long id) {
        postService.deletePostById(id);
    }


        //http://localhost:8089/SpringMVCMajd/Actuality/likepost/{idPost}
    @PutMapping("/likepost/{idPost}")
    public void LikePost( @PathVariable("idPost") Long idPost , Principal principal)
    {
        String  emaill = principal.getName();
        Long i= DR.jibid(emaill);


        postService.likeAPost(idPost,i) ;

    }
    //http://localhost:8089/SpringMVCMajd/Actuality/disLikepost/{idPost}
    @PutMapping("/disLikepost/{idPost}")
    public void DisLikePost( @PathVariable("idPost") Long idPost , Principal principal)
    {
        String  emaill = principal.getName();
        Long i= DR.jibid(emaill);
        postService.dislikeAPost(idPost, i) ;

    }

    // http://localhost:8089/SpringMVCMajd/Actuality/get-best-post-by-month
    @GetMapping("/get-best-post-by-month")
    public List<BestAndWorstPost> getbestPostByMonth() {
        List<BestAndWorstPost> listPosts = postService.bestPostByMonth();
        return listPosts;
    }



    // http://localhost:8089/SpringMVCMajd/Actuality/add-comment/1
    @PostMapping("/add-comment/{idPost}")
    public CommentPost addComment(@RequestBody CommentPost  cp,@PathVariable Long idPost) {

        return commentService.createAndAffectCommentToPost(cp, idPost);

    }

    // http://localhost:8089/SpringMVCMajd/Actuality/retrieve-comments-by-postid/2
    @GetMapping("/retrieve-comments-by-postid/{post-id}")
    public List<CommentPost> retrieveComment(@PathVariable("post-id") Long postId) {
        List<CommentPost> listComment=commentService.getCommentByPostId(postId);
        return listComment;
    }

    // http://localhost:8089/SpringMVCMajd/update-comment/2
    @PutMapping("/update-comment/{comment-id}")
    public CommentPost updateComment(@PathVariable("comment-id")Long commentId){
        CommentPost cp = commentService.getCommentById(commentId);
        commentService.updateComment(cp);
        return cp;
    }

}

