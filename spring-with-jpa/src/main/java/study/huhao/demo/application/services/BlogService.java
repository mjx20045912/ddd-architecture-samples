package study.huhao.demo.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.huhao.demo.domain.core.Page;
import study.huhao.demo.domain.models.blog.*;
import study.huhao.demo.domain.models.user.UserId;

@Service
@Transactional
public class BlogService {

    private final BlogDomainService blogDomainService;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        blogDomainService = new BlogDomainService(blogRepository);
    }

    public Blog createBlog(String title, String body, UserId authorId) {
        return blogDomainService.createBlog(title, body, authorId);
    }

    public Blog getBlog(BlogId id) {
        return blogDomainService.getBlog(id);
    }

    public void saveBlog(BlogId id, String title, String body) {
        blogDomainService.saveBlog(id, title, body);
    }

    public void deleteBlog(BlogId id) {
        blogDomainService.deleteBlog(id);
    }

    public void publishBlog(BlogId id) {
        blogDomainService.publishBlog(id);
    }

    public Page<Blog> getAllBlog(BlogCriteria criteria) {
        return blogDomainService.getAllBlog(criteria);
    }
}
