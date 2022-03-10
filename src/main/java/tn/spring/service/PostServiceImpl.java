package tn.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.spring.entity.AppUser;
import tn.spring.entity.BestAndWorstPost;
import tn.spring.entity.Departement;
import tn.spring.entity.Posts;
import tn.spring.repository.DemandeRepository;
import tn.spring.repository.DepartementRepository;
import tn.spring.repository.PostRepository;
import tn.spring.repository.UserRepository;
import tn.spring.service.SentimentAnalysis.SentimentAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Set;


@Service
@Slf4j
public class PostServiceImpl implements IPostservice {

    @Autowired
    PostRepository postRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    DepartementRepository departmentRepo;
    @Autowired
    DemandeRepository DR;


    @Override
    public Posts createPost(Posts p,String mail) throws JSONException, URISyntaxException, IOException {
        try{

            log.info("in Method CreatePost");

            postRepo.save(p);
            log.info("the post is saved");
            log.info("out of the method CreatePost");}
        catch (Exception e) {
            log.error("out of method CreatePost");
        }
        Timestamp ts=new Timestamp(System.currentTimeMillis());
        Date date=new Date(ts.getTime());
        p.setDatePost(date);

        String path = p.getImagePost();
        String resp =  ImageAnalysisQuickstart.GetAnalyze(path);
        p.setAnalysis(resp);

        String text=p.getContent();
        String analysis = SentimentAPI.GetSentiment(text);
        p.setSentiment(analysis);




        log.info("hahahahah");

        Long i= DR.jibid(mail);
        log.info(mail);
        AppUser us = userRepo.findById(i).get();
        String test= us.getEmail();
        log.info(mail);
        p.setUser(us);


        return postRepo.save(p);

    }

    @Override
    public BestAndWorstPost BestPost() {
        return postRepo.bestPost();
    }

    @Override
    public BestAndWorstPost WorstPost() {
        return postRepo.worstPost();
    }


    @Override
    public List<Posts> getAllPosts() {
        log.info("In Method retrieve All Post");

        return postRepo.findAll();
    }

    @Override
    public Posts getPostById(Long id) {
        log.info("In Method retrieve Post By Id");
        return postRepo.findById(id).get();
    }

    @Override
    public void deletePostById(Long id) {
        log.info("In Method delete Post");
        log.info("i'm gonna delete the Post");
        postRepo.deleteById(id);
        log.info("Out Of Method delete Post without errors");

    }

    @Override
    public void likeAPost(Long idPost, Long id) {
        Posts p = postRepo.findById(idPost).orElseGet(null);
        AppUser u = userRepo.findById(id).orElseGet(null);
        Set<AppUser> l = p.getUserLikes();

        if(p.getUserDislikes().contains(u))
        {
            p.getUserDislikes().remove(u);
            l.add(u);
            p.setUserLikes(l);
        }
        else
        {	if(p.getUserLikes().contains(u)) {
            p.getUserLikes().remove(u);
        }
        else {l.add(u);}
        }

        postRepo.save(p);

    }

    @Override
    public void dislikeAPost(Long idPost, Long id) {
        Posts p = postRepo.findById(idPost).orElseGet(null);
        AppUser u = userRepo.findById(id).orElseGet(null);
        Set<AppUser> l = p.getUserDislikes();
        if(p.getUserLikes().contains(u))
        {
            p.getUserLikes().remove(u);
            l.add(u);
            p.setUserDislikes(l);
        }
        else
        {	l.add(u);}
        int likes=p.getNbreacts()-1;
        p.setNbreacts(likes);
        postRepo.save(p);


    }

    @Override
    public Posts createPostAndAffectToUserAndDepartement(Posts p, Long userId, Long depId) throws IOException {
        return null;
    }


    @Override
    public Posts createPostAndAffectToUser(Posts p,String email) {
        Long i= DR.jibid(email);
        System.out.println(i);


        AppUser us = userRepo.findById(i).orElse(null);
        System.out.println(us.getEmail());
        //  log.info("userid :"+userId);
        p.setUser(us);
        System.out.println(p.getContent());
        postRepo.save(p);

        return p;
        }


    public Posts createPostAndAffectToUserAndDepartement(Posts p, String email, Long depId) throws IOException {

        Long i= DR.jibid(email);
        AppUser uuu = userRepo.findById(i).get();
        int warning=uuu.getWarningNum();

        String text= p.getContent();

        int compteur=0;

            if(SentimentAPI.GetSentiment(text)=="negative"){
                compteur++;
                warning++;

            }
            uuu.setWarningNum(warning);
            userRepo.save(uuu);
        if(compteur>0){
            log.info("this message is blocked because he contain a bad word would you please delete it than try again !! and if you "
                    + "post 2 times with bad words your account will be blocked");
            return null;
        }
        else{
            int comm=0;
            p.setDepartements(new ArrayList<Departement>());
            Departement d = departmentRepo.findById(depId).get();
            p.getDepartements().add(d);
            Date dd = new Date();
            AppUser u = userRepo.findById(i).get();
            p.setUser(u);
            p.setDatePost(dd);
            p.setNbComment(comm);

            postRepo.save(p);
            List<AppUser> users = new ArrayList<>();


            return p;
        }
    }





    public List<BestAndWorstPost> bestPostByMonth(){
        return postRepo.bestpostbymonth();
    }


    @Override
    public Posts updatePost(Posts p) {

        return this.postRepo.save(p);
    }

    public List<Posts> getAllPaginated(final Integer pageNumber) {
        Integer index = pageNumber - 1;
        Page<Posts> posts = this.postRepo.findAll(PageRequest.of(index, 20));
        return posts.stream().collect(Collectors.toList());
    }


    public void uploadImage(final MultipartFile file) throws IOException {
        UUID imgGeneratedId = UUID.nameUUIDFromBytes(file.getBytes());
        File convertFile = new File("src/main/frontend/src/assets/images/" + imgGeneratedId + file.getOriginalFilename());
        Posts foundPost = postRepo.findFirstByOrderByPostIdDesc();
        foundPost.setImagePost("./assets/images/" + imgGeneratedId + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        postRepo.save(foundPost);
    }
    //+ React


    }








