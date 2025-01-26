package com.util;

import com.entity.BlogEntity;
import com.entity.PermissionEntity;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.exception.custom.RoleException;
import com.exception.custom.UserException;
import com.repository.BlogRepository;
import com.repository.PermissionRepository;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.util.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BlogRepository blogRepository;

    @Autowired
    public DatabaseInitializer(RoleRepository roleRepository, PermissionRepository permissionRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, BlogRepository blogRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.blogRepository = blogRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Database initialization started!");

//        createPermission();
        createRole();
        createUser();
        createBlog();

        System.out.println("Database initialization completed!");
    }

    public void createPermission() {
        List<PermissionEntity> permissions = Arrays.asList(
                // auth controller
                new PermissionEntity("/auth/login/credentials", ApiMethodEnum.POST),
                new PermissionEntity("/auth/login/socials", ApiMethodEnum.POST),
                new PermissionEntity("/auth/refresh", ApiMethodEnum.GET),
                new PermissionEntity("/auth/logout", ApiMethodEnum.GET),

                // blog controller
                new PermissionEntity("/blogs", ApiMethodEnum.GET)
        );
        permissionRepository.saveAll(permissions);
    }

    public void createRole() {
        if (!roleRepository.existsBy()) {
            List<RoleEntity> roles = List.of(
                    new RoleEntity(RoleNameEnum.ADMIN, permissionRepository.findAll())
            );
            roleRepository.saveAll(roles);
        }
    }

    public void createUser() {
        if (!userRepository.existsBy()) {
            RoleEntity admin = roleRepository.findByRoleName(RoleNameEnum.ADMIN).orElseThrow(() -> new RoleException("Role name not existed!"));
            List<UserEntity> users = List.of(
                    UserEntity.builder()
                            .username("truc")
                            .password(passwordEncoder.encode("123"))
                            .avatar("admin.jpg")
                            .fullname("Nguyen Vuong Truc")
                            .accountType(AccountTypeEnum.CREDENTIALS)
                            .email("trucnvhe180248@fpt.edu.vn")
                            .gender(GenderEnum.MALE)
                            .job(JobEnum.STUDENT)
                            .role(admin)
                            .build()
            );
            userRepository.saveAll(users);
        }
    }

    public void createBlog() {
        if (!blogRepository.existsBy()) {
            UserEntity user = userRepository.findByUsername("truc").orElseThrow(() -> new UserException("User not existed!"));
            List<BlogEntity> blogs = List.of(
                    BlogEntity.builder()
                            .title("Lập Trình Web Từ A Đến Z: Hướng Dẫn Chi Tiết")
                            .content("Bạn muốn học lập trình web nhưng không biết bắt đầu từ đâu? Bài viết này sẽ giúp bạn hiểu rõ các khái niệm quan trọng từ frontend đến backend, đồng thời hướng dẫn bạn sử dụng những công cụ phổ biến như HTML, CSS, JavaScript, React, và Spring Boot để xây dựng website chuyên nghiệp.")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("1.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Những Sai Lầm Cần Tránh Khi Học Lập Trình")
                            .content("Khi mới bắt đầu lập trình, nhiều người mắc phải những sai lầm phổ biến làm chậm tiến trình học tập. Bài viết này sẽ chỉ ra những lỗi cơ bản mà lập trình viên mới hay gặp và cách khắc phục để bạn có thể học hiệu quả hơn.")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("2.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Top 10 Công Cụ Hữu Ích Cho Lập Trình Viên")
                            .content("Công cụ phù hợp giúp lập trình viên làm việc nhanh chóng và hiệu quả hơn. Trong bài viết này, chúng ta sẽ cùng khám phá 10 công cụ cần thiết mà bất kỳ lập trình viên nào cũng nên biết và sử dụng hàng ngày.")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("3.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Làm Chủ Git Và GitHub: Hướng Dẫn Dành Cho Người Mới")
                            .content("Git và GitHub là hai công cụ quan trọng giúp lập trình viên quản lý mã nguồn và làm việc nhóm hiệu quả. Bài viết này sẽ giúp bạn hiểu cách sử dụng Git từ cơ bản đến nâng cao để cải thiện kỹ năng quản lý dự án.")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("4.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Làm Sao Để Trở Thành Lập Trình Viên Full-Stack?")
                            .content("Full-stack developer là một trong những vị trí được săn đón nhất trong ngành công nghệ. Bài viết này sẽ giúp bạn hiểu rõ những kỹ năng cần có và lộ trình học tập để trở thành một lập trình viên full-stack chuyên nghiệp.")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("5.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Cách Viết Code Sạch Và Dễ Bảo Trì")
                            .content("Viết code không chỉ để máy tính hiểu, mà còn để con người có thể đọc và bảo trì dễ dàng. Bài viết này sẽ chia sẻ những nguyên tắc quan trọng giúp bạn viết mã nguồn sạch, dễ hiểu và tối ưu hơn.")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("6.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Học JavaScript Trong 30 Ngày: Lộ Trình Chi Tiết")
                            .content("JavaScript là ngôn ngữ lập trình phổ biến nhất hiện nay. Bài viết này sẽ cung cấp cho bạn một lộ trình học JavaScript từ cơ bản đến nâng cao trong 30 ngày, giúp bạn nhanh chóng làm chủ ngôn ngữ này.")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("7.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Lập Trình Viên Có Cần Học Data Structures & Algorithms?")
                            .content("Cấu trúc dữ liệu và thuật toán là một phần quan trọng trong lập trình, nhưng liệu bạn có thực sự cần học chúng? Bài viết này sẽ phân tích vai trò của DSA trong sự nghiệp lập trình viên và cách áp dụng chúng vào thực tế.")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("8.jpg")
                            .build()
            );
            blogRepository.saveAll(blogs);
        }
    }


}
