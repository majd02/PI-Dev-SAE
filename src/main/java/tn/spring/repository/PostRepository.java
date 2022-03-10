package tn.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.spring.entity.BestAndWorstPost;
import tn.spring.entity.Posts;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {
    @Query("from Posts p order by p.DatePost desc")
    List<Posts> findAllSortedByDatePostReverse();

    Posts findFirstByOrderByPostIdDesc();

    //@Query("from Posts p order by p.DatePost desc")
    //Page<Posts> findAll(final Pageable pageable);



    @Query( value="SELECT posts.title_post AS name , COUNT( posts_user_likes.posts_post_id) occ FROM posts_user_likes"
            + "  LEFT JOIN posts ON posts.post_id=posts_user_likes.posts_post_id"
            + " GROUP BY(posts_post_id) "
            + "ORDER BY  occ "
            + "DESC LIMIT 1", nativeQuery=true)
    public BestAndWorstPost bestPost();

    @Query(value="SELECT posts.title_post AS name , COUNT( posts_user_dislikes.posts_post_id) occ FROM posts_user_dislikes "
            + "LEFT JOIN posts ON posts.post_id=posts_user_dislikes.posts_post_id "
            + "GROUP BY(posts_post_id)"
            + " ORDER BY  occ "
            + "DESC LIMIT 1",nativeQuery=true)
    public BestAndWorstPost worstPost();

    @Query(value="SELECT posts.title_post AS name ,COUNT( posts_user_likes.posts_post_id) occ"
            + " FROM posts_user_likes LEFT JOIN posts ON posts.post_id = posts_user_likes.posts_post_id "
            + "where Month(posts.date_post) = Month(CURRENT_DATE) "
            + "GROUP BY(post_id) ORDER BY  occ  "
            + "DESC LIMIT 3", nativeQuery=true )
    public List<BestAndWorstPost> bestpostbymonth();

}

