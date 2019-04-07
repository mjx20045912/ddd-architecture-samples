package study.huhao.demo.adapters.persistence.blog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import study.huhao.demo.adapters.persistence.JpaConfiguration;
import study.huhao.demo.domain.models.blog.Blog;
import study.huhao.demo.domain.models.blog.BlogRepository;
import study.huhao.demo.domain.models.user.UserId;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
@DataJpaTest
class BlogRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BlogRepository blogRepository;

    @Test
    void save() {
        var blog = new Blog("Test Blog", "Something...", UserId.of(UUID.randomUUID().toString()));

        blogRepository.save(blog);

        var createdBlogDto = entityManager.find(BlogDto.class, blog.getId().toString());

        assertThat(createdBlogDto.getId()).isEqualTo(blog.getId().toString());
    }
}