package study.huhao.demo.domain.models.blog;

import study.huhao.demo.domain.core.DomainService;
import study.huhao.demo.domain.core.Page;
import study.huhao.demo.domain.core.excpetions.EntityNotFoundException;
import study.huhao.demo.domain.models.user.UserId;

public class BlogDomainService implements DomainService {

    private final BlogRepository blogRepository;

    public BlogDomainService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog createBlog(String title, String body, UserId authorId) {
        var blog = new Blog(title, body, authorId);
        blogRepository.save(blog);
        return blog;
    }

    public Blog getBlog(BlogId id) {
        return blogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Blog.class, id));
    }

    public void saveBlog(BlogId id, String title, String body) {
        var blog = blogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Blog.class, id));
        blog.save(title, body);
        blogRepository.save(blog);
    }

    public void deleteBlog(BlogId id) {
        var existed = blogRepository.existById(id);
        if (!existed) throw new EntityNotFoundException(Blog.class, id);
        blogRepository.deleteById(id);
    }

    public void publishBlog(BlogId id) {
        var blog = blogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Blog.class, id));
        blog.publish();
        blogRepository.save(blog);
    }

    public Page<Blog> getAllBlog(BlogCriteria criteria) {
        return blogRepository.findAllWithPagination(criteria);
    }
}
